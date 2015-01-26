package com.santrong.plt.webpage.war;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.competition.dao.CompetitionDao;
import com.santrong.plt.webpage.competition.entry.CompetitionAttendItem;
import com.santrong.plt.webpage.competition.entry.CompetitionHistoryItem;
import com.santrong.plt.webpage.competition.entry.CompetitionItem;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.AnswersOptionsEntry;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionIndex;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionQuery;
import com.santrong.plt.webpage.home.dao.LessonUnitDao;
import com.santrong.plt.webpage.home.entry.LessonUnitEntry;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2015年1月6日
 * @time 上午10:54:59
 */
@Controller
@RequestMapping("/war")
public class WarAction extends BaseAction {

	@RequestMapping("")
	public String index() {
		return "war/index";
	}
	
	@RequestMapping("person")
	public String person() {
		return "war/person_w";
	}
	
	@RequestMapping("group")
	public String group() {
		return "war/group_w";
	}
	
	@RequestMapping("game")
	public String game() {
		return "war/game_w";
	}	
	
	// 选题或者取消选题
	@SuppressWarnings("unchecked")
	@RequestMapping("/assembleQuestion")
	@ResponseBody
	public String assembleQuestion(String qid) {
		String rt = "";
		try{
			HashSet<String> map = (HashSet<String>)this.getRequest().getSession().getAttribute(Global.SessionKey_Assemble_Question);
			if(map == null) {
				map = new HashSet<String>();
			}
			if(map.contains(qid)) {
				map.remove(qid);
				rt = "remove";
			}else{
				map.add(qid);
				rt = "add";
			}
			this.getRequest().getSession().setAttribute(Global.SessionKey_Assemble_Question, map);
		}catch(Exception e) {
			Log.printStackTrace(e);
			return FAIL;
		}
		return rt;
	}
	
	
	/**
	 * 获取题目列表，如果传递cid则根据cid获取，不传递则从session中获取
	 * @param cid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TrainQuestionItem> getQuestionList(String cid) {
		
		List<TrainQuestionItem> questionList = new ArrayList<TrainQuestionItem>();
		
		if(MyUtils.isNotNull(cid)) {
			CompetitionDao competitionDao = new CompetitionDao();
			questionList = competitionDao.selectQuestionsByCompetitionId(cid);
			
		}else {
			HashSet<String> map = (HashSet<String>)this.getRequest().getSession().getAttribute(Global.SessionKey_Assemble_Question);
			if(map != null) {
				List<String> idList = new ArrayList<String>();
				Iterator<String> iter = map.iterator();
				while(iter.hasNext()) {
					idList.add(iter.next());
				}
				if(idList.size() > 0) {
					// 获取题目
					TrainQuestionDao tqDao = new TrainQuestionDao();
					questionList = tqDao.selectByIds(idList.toArray(new String[idList.size()]));
				}
			}
			
		}
		return questionList;
	}
	
	
	/**
	 * 开始做题，重新做题
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/begin", method = RequestMethod.GET)
	public String begin(String cid) {
		
		// 获取题目
		List<TrainQuestionItem> questionList = getQuestionList(cid);
		this.getRequest().setAttribute("questionList", questionList);
		
		return "question/begin";
	}
	

	/**
	 * 提交试题--批量提交
	 * 未登录用户仅仅计算结果，登录用户给予持久化
	 * @return
	 */
	@RequestMapping(value = "/begin", method = RequestMethod.POST)
	public String begin(String cid, String[] qid, String[] answer) {
		int doneCount = 0;
		int wrongCount = 0;
		
		// 获取题目
		List<TrainQuestionItem> questionList = getQuestionList(cid);
		this.getRequest().setAttribute("questionList", questionList);
		
		// 用户提交的数据跟习题答案对比运算出结果
		List<TrainQuestionIndex> indexList = new ArrayList<TrainQuestionIndex>();
		if(questionList != null && questionList.size() == answer.length && questionList.size() > 0) {
			int total = questionList.size();
			for(int i=0;i<total;i++) {
				int result = questionList.get(i).getAnswer() == answer[i]? 1 : 0;
				if(MyUtils.isNotNull(answer[i])) {
					doneCount++;
					if(result == 0) {
						wrongCount++;
					}
				}				
				TrainQuestionIndex qIndex = new TrainQuestionIndex();
				qIndex.setAnswer(answer[i]);
				qIndex.setResult(result);
				indexList.add(qIndex);
			}
			
			// 如果登录了，持久化
			if(this.isLogin()) {
				try{
					ThreadUtils.beginTranx();
					
					// 插入虚拟竞赛（个人练习）
					CompetitionDao competitionDao = new CompetitionDao();
					CompetitionItem competition = new CompetitionItem();
					competition.setId(MyUtils.getGUID());
					competition.setBeginTime(new Date());
					competition.setOwnerId(this.currentUser().getId());
					competition.setTitle("个人练习");
					competition.setRemark("个人练习");
					competition.setFlag(0);
					competition.setDel(0);
					competition.setCts(new Date());
					competition.setUts(new Date());
					competitionDao.insert(competition);
					
					// 插入虚拟产于竞赛
					CompetitionAttendItem attend = new CompetitionAttendItem();
					attend.setId(MyUtils.getGUID());
					attend.setCompetitionId(competition.getId());
					attend.setUserId(this.currentUser().getId());
					attend.setCts(new Date());
					competitionDao.insertAttend(attend);
					
					// 插入做题历史
					for(int i=0;i<indexList.size();i++) {
						CompetitionHistoryItem history = new CompetitionHistoryItem();				
						history.setId(MyUtils.getGUID());
						history.setAttendId(attend.getId());
						history.setQuestionId(qid[i]);
						history.setAnswer(answer[i]);
						history.setResult(indexList.get(i).getResult());
						history.setCts(new Date());
						history.setCts(new Date());
						competitionDao.insertHistory(history);
					}
					
					ThreadUtils.commitTranx();
				}catch(Exception e) {
					ThreadUtils.rollbackTranx();
					Log.printStackTrace(e);
				}
			}
		}
		
		HttpServletRequest request = this.getRequest();
		request.setAttribute("questionList", questionList);
		request.setAttribute("indexList", indexList);
		request.setAttribute("doneCount", doneCount);
		request.setAttribute("wrongCount", wrongCount);		
		
		return "question/end";
	}	
	
	
	/**
	 * 记录在库的练习或者竞赛结果查看做题详细
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/result")
	public String result(String cid) {
		
		// TODO 移植到后台，以继承必须登录和做归属判断
		
		if(MyUtils.isNull(cid)) {
			return "404";
		}

		int doneCount = 0;
		int wrongCount = 0;
		
		// 获取题目
		List<TrainQuestionItem> questionList = getQuestionList(cid);
		this.getRequest().setAttribute("questionList", questionList);
		
		// 获取答题历史
		List<CompetitionHistoryItem> historyList = new ArrayList<CompetitionHistoryItem>();
		if(MyUtils.isNotNull(cid) && this.isLogin()) {
			CompetitionDao historyDao = new CompetitionDao();
			historyList = historyDao.selectUserHistory(this.currentUser().getId(), cid);// 有历史记录会进入答题结果状态，情况历史记录才能进入答题状态
			for(CompetitionHistoryItem item:historyList) {
				if(MyUtils.isNotNull(item.getAnswer())) {
					doneCount++;
					if(item.getResult() == 0) {
						wrongCount++;
					}
				}
			}					
		}
		
		// 设置对错属性
		int total = questionList.size();
		List<TrainQuestionIndex> indexList = new ArrayList<TrainQuestionIndex>();
		for(int i=0;i<total;i++) {
			TrainQuestionIndex qIndex = new TrainQuestionIndex();
			if(historyList.size() > 0) {
				qIndex.setAnswer(historyList.get(i).getAnswer());
				qIndex.setResult(historyList.get(i).getResult());
			}
			indexList.add(qIndex);
		}
		
		HttpServletRequest request = this.getRequest();
		request.setAttribute("questionList", questionList);
		request.setAttribute("indexList", indexList);
		request.setAttribute("doneCount", doneCount);
		request.setAttribute("wrongCount", wrongCount);
		
		return "question/end";
	}
	
	/**
	 * 打开个人练习的试题页面
	 * @return
	 */
	@RequestMapping(value = "/exams", method = RequestMethod.GET)
	public String person_exams() {
		try {
			HttpServletRequest request = this.getRequest();
			String gradeId = request.getParameter("gradeId");
			String subjectId = request.getParameter("subjectId");
			String unitId = request.getParameter("unitId");
			String weikeId = request.getParameter("weikeId");
			String type = request.getParameter("type");// personExams 个人练习,unitExams 单元练习,classExams 课后练习
			
			// 获取当前用户对象信息
			UserItem user = this.currentUser();
			if (user == null) {
				// 没登陆，注意：异步的时候才这样子写，jquery对返回的结果作了判断
				return this.redirectLogin();
			}
			
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			if (MyUtils.isNull(type)) {
				type = "personExams";//个人练习
			}
			
			TrainQuestionDao tqDao = new TrainQuestionDao();
			List<TrainQuestionItem> questionList = null;
			if (type.equalsIgnoreCase("personExams")) {//个人练习
				
				TrainQuestionQuery query = new TrainQuestionQuery();
				query.setPageNum(pageNum);
				query.setPageSize(1);//只显示一条
				query.setGradeId(gradeId);//通过年级查询
				query.setSubjectId(subjectId);//通过科目查询
				query.setUnitId(unitId);//通过单元查询
//				query.setQuestionType(1);//只查询单选题
				query.setOrderBy("cts");
//				query.setOrderBy("gradeId");
//				query.setOrderBy("level");
				query.setCount(tqDao.selectCountByQuery(query));
				questionList = tqDao.selectByQuery(query);
				
				request.setAttribute("query", query);
				
			} else if (type.equalsIgnoreCase("classExams")) {//classExams 课后练习
				if (MyUtils.isNotNull(weikeId)) {
					int pageCount = 0;
					pageCount = tqDao.selectCountByWeikeId(weikeId);
					questionList = tqDao.selectQuestionBySameKnowledges(weikeId, (pageNum - 1), 1);
					
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("weikeId", weikeId);
				}
			}
			
			// 反查获取科目、年级、学期、单元
			if (questionList != null && questionList.size() == 1) {
				LessonUnitDao luDao = new LessonUnitDao();
				LessonUnitEntry luEntry = new LessonUnitEntry();
				int questionType = 1;
				for (TrainQuestionItem tqItem : questionList) {
					luEntry = luDao.selectGSUById(tqItem.getUnitId());
					questionType = tqItem.getQuestionType();
				}
				request.setAttribute("luEntry", luEntry);
				
				// 获取选项 获取选项数值
				List<AnswersOptionsEntry> optionsList = new ArrayList<AnswersOptionsEntry>();
				if (questionType == TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE) {//判断题
					for (int i = 0; i < TrainQuestionItem.Answers_En_TrueOrFalse.length; i++) {
						AnswersOptionsEntry entry = new AnswersOptionsEntry();
						entry.setOption(TrainQuestionItem.Answers_En_TrueOrFalse[i].toLowerCase());
						entry.setAnswer(String.valueOf(TrainQuestionItem.Answers[i]));
						optionsList.add(entry);
					}
				} else if (questionType == TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION
					|| questionType == TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE) {//选择题
					for (int i = 0; i < TrainQuestionItem.Answers_Options.length; i++) {
						AnswersOptionsEntry entry = new AnswersOptionsEntry();
						entry.setOption(TrainQuestionItem.Answers_Options[i].toLowerCase());
						entry.setAnswer(String.valueOf(TrainQuestionItem.Answers[i]));
						optionsList.add(entry);
					}
				}
				request.setAttribute("optionsList", optionsList);
			}
			
			request.setAttribute("questionList", questionList);
			request.setAttribute("gradeId", gradeId);
			request.setAttribute("subjectId", subjectId);
			request.setAttribute("unitId", unitId);
			request.setAttribute("type", type);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		
		return "war/person_exams";
	}
	
	// 添加试题测试历史记录
	@RequestMapping("/addExamToHistory")
	@ResponseBody
	public String addExamToHistory() {
		HttpServletRequest request = this.getRequest();
		String answer = request.getParameter("answer");
		String questionId = request.getParameter("questionId");
		
		if (MyUtils.isNotNull(answer) && MyUtils.isNotNull(questionId)) {
			ThreadUtils.beginTranx();
			CompetitionDao competitionDao = new CompetitionDao();
			CompetitionAttendItem attend = new CompetitionAttendItem();
			if (!competitionDao.existDoneExamByUserId(this.currentUser().getId())) {
				// 插入虚拟报名测试表
				attend.setId(MyUtils.getGUID());
				attend.setCompetitionId(null);
				attend.setUserId(this.currentUser().getId());
				attend.setCts(new Date());
				competitionDao.insertAttend(attend);
			} else {
				attend = competitionDao.selectAttendByUserId(this.currentUser().getId());
			}
			// 疑问？ 是否要记录每一道做答的历史记录，包括以前可能做过该道题的历史记录？
//			if (!competitionDao.existHistory(attend.getId(), questionId)) {
				TrainQuestionDao tqDao = new TrainQuestionDao();
				TrainQuestionItem tqItem = tqDao.selectById(questionId);
				if (tqItem != null) {
					int result = tqItem.getAnswer().equalsIgnoreCase(answer) ? TrainHistoryItem.ANSWER_IS_RIGHT : TrainHistoryItem.ANSWER_IS_WRONG;
					// 插入做题历史
					CompetitionHistoryItem history = new CompetitionHistoryItem();				
					history.setId(MyUtils.getGUID());
					history.setAttendId(attend.getId());
					history.setQuestionId(questionId);
					history.setAnswer(answer);
					history.setResult(result);
					history.setCts(new Date());
					history.setUts(new Date());
					competitionDao.insertHistory(history);
				}
//			} else {
//				TrainQuestionDao tqDao = new TrainQuestionDao();
//				TrainQuestionItem tqItem = tqDao.selectById(questionId);
//				if (tqItem != null) {
//					int result = tqItem.getAnswer().equalsIgnoreCase(answer) ? TrainHistoryItem.ANSWER_IS_RIGHT : TrainHistoryItem.ANSWER_IS_WRONG;
//					// 修改做题历史
//					CompetitionHistoryItem history = competitionDao.selectHistoryByAttendId(attend.getId(), questionId);
//					history.setAnswer(answer);
//					history.setResult(result);
//					history.setUts(new Date());
//					competitionDao.updateHistory(history);
//				}
//			}
			ThreadUtils.commitTranx();
			return SUCCESS;
		}
		return FAIL;
	}
}


