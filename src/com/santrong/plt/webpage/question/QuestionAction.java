package com.santrong.plt.webpage.question;

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
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionIndex;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionQuery;

/**
 * @author weinianjie
 * @date 2015年1月6日
 * @time 上午10:54:59
 */
@Controller
@RequestMapping("/question")
public class QuestionAction extends BaseAction {

	@RequestMapping("")
	public String index() {
		try {
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			TrainQuestionDao tqDao = new TrainQuestionDao();
			TrainQuestionQuery query = new TrainQuestionQuery();
			query.setPageNum(pageNum);
			query.setCount(tqDao.selectCountByQuery(query));
			query.setOrderBy("cts");
			List<TrainQuestionItem> questionList = tqDao.selectByQuery(query);
			
			// 从session中获知已选择题目
			HttpServletRequest request = this.getRequest();
			@SuppressWarnings("unchecked")
			HashSet<String> map = (HashSet<String>)request.getSession().getAttribute(Global.SessionKey_Assemble_Question);
			if(map == null) {
				map = new HashSet<String>();
			}
			Iterator<String> iter = map.iterator();
			while(iter.hasNext()) {
				String qid = iter.next();
				for(int i=0;i<questionList.size();i++) {
					if(questionList.get(i).getId().equals(qid)) {
						questionList.get(i).setAssemble(true);
						break;
					}
				}
			}
			
			request.setAttribute("questionList", questionList);
			request.setAttribute("query", query);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		
		return "question/index";
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
	public String begin(String cid, String[] qid, Integer[] answer) {
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
				int result = 0;
//				result = questionList.get(i).getAnswer() == answer[i]? 1 : 0;
				if(answer[i] != 0) {
					doneCount++;
					if(result == 0) {
						wrongCount++;
					}
				}				
				TrainQuestionIndex qIndex = new TrainQuestionIndex();
//				qIndex.setAnswer(answer[i]);
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
					competition.setFlag(CompetitionItem.Flag_Person);
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
//						history.setAnswer(answer[i]);
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
				if(item.getAnswer() != null) {
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
	
}


