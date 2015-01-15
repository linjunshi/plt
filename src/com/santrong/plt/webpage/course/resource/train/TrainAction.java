package com.santrong.plt.webpage.course.resource.train;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.resource.train.dao.TrainDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainHistoryDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryForm;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionIndex;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/train")
public class TrainAction extends StudentBaseAction {

	/**
	 * 做作业详细页
	 * @param resId 测验的id
	 * @param index 习题的index
	 * @return
	 */
	@RequestMapping("")
	public String home(String resId) {
		
		//TODO 权限判断
		
		String module = "answer";// 答题模式answer，统计模式result
		
		// 获取测验
		TrainDao trainDao = new TrainDao();
		TrainItem train = trainDao.selectById(resId);
		
		// 获取章节
		ChapterDao chapterDao = new ChapterDao();
		ChapterItem chapter = chapterDao.selectByResourceId(resId);
		
		// 未知的train或者train没有添加到课程章节里的，无法开始该测试
		if(train ==  null || chapter == null) {
			this.redirect("/");
		}
		
		// 获取课程
		CourseDao courseDao = new CourseDao();
		CourseItem course = courseDao.selectByChapterId(chapter.getCourseId());		
		
		// 获取习题总数
		TrainQuestionDao dao = new TrainQuestionDao();
		int total = dao.selectCountByTrainId(resId);		
		
		// 获取答题记录
		int doneCount = 0;
		int wrongCount = 0;
		TrainHistoryDao historyDao = new TrainHistoryDao();
		List<TrainHistoryItem> historyList = historyDao.selectUserHistory(this.currentUser().getId(), resId, chapter.getId());// 有历史记录会进入答题结果状态，情况历史记录才能进入答题状态
		for(TrainHistoryItem item:historyList) {
			module = "result";
			if(item.getAnswer() != null) {
				doneCount++;
				if(item.getResult() == 0) {
					wrongCount++;
				}
			}
		}
		
		// 数据组装
		List<TrainQuestionIndex> indexList = new ArrayList<TrainQuestionIndex>();
		for(int i=0;i<total;i++) {
			TrainQuestionIndex qIndex = new TrainQuestionIndex();
			if(historyList.size() > 0) {
				qIndex.setAnswer(historyList.get(i).getAnswer());
				qIndex.setResult(historyList.get(i).getResult());
			}
			indexList.add(qIndex);
		}

		// 基本数据
		HttpServletRequest request = this.getRequest();
		request.setAttribute("module", module);
		request.setAttribute("total", total);
		request.setAttribute("train", train);
		request.setAttribute("chapter", chapter);
		request.setAttribute("course", course);
		request.setAttribute("indexList", indexList);
		
		// 页面处于答题结果时候需要的数据
		request.setAttribute("doneCount", doneCount);
		request.setAttribute("wrongCount", wrongCount);
		
		return "course/resource/train/detail";
	}
	
	/**
	 * 做作业时候获取单道习题
	 * @param trainId
	 * @param index
	 * @return
	 */
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public String questionGet(String trainId, String chapterId, Integer index) {
		// 获取题目
		TrainQuestionDao questionDao = new TrainQuestionDao();
		TrainQuestionItem question = questionDao.selectByTrainIdAndIndex(trainId, --index);
		if(question != null) {
			// 获取做题记录，有可能没有
			TrainHistoryDao historyDao = new TrainHistoryDao();
			TrainHistoryItem history = historyDao.selectByUnique(question.getId(), trainId, chapterId, this.currentUser().getId());
			
			this.getRequest().setAttribute("question", question);
			this.getRequest().setAttribute("history", history);
			return "course/resource/train/question";
		}
		return "blank";
	}
	
	/**
	 * 提交试题--批量提交
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	@ResponseBody
	public String questionPost(TrainHistoryForm form) {
		
		// TODO 权限判断
		
		// 该获取测验的所有习题
		TrainQuestionDao questionDao = new TrainQuestionDao();
		List<TrainQuestionItem> questionList = questionDao.selectByTrainId(form.getTrainId());
		
		// 用户提交的数据跟习题对比结果后存入历史
		if(questionList != null && questionList.size() == form.getAnswer().length) {
			try{
				ThreadUtils.beginTranx();
				
				// 不管有没有，先删除历史再说
				TrainHistoryDao historydao = new TrainHistoryDao();
				historydao.deleteUserHistory(this.currentUser().getId(), form.getTrainId(), form.getChapterId());			
				
				for(int i=0;i<questionList.size();i++) {
					int result = questionList.get(i).getAnswer() == form.getAnswer()[i]? 1 : 0;
					TrainHistoryItem history = new TrainHistoryItem();
					history.setId(MyUtils.getGUID());
					history.setTrainId(form.getTrainId());
					history.setChapterId(form.getChapterId());
					history.setQuestionId(questionList.get(i).getId());
					history.setUserId(this.currentUser().getId());
					history.setAnswer(form.getAnswer()[i]);
					history.setResult(result);
					history.setDel(0);
					history.setCts(new Date());
					history.setCts(new Date());
					historydao.insert(history);
				}
				
				ThreadUtils.commitTranx();
				return SUCCESS;
			}catch(Exception e) {
				ThreadUtils.rollbackTranx();
				Log.printStackTrace(e);
				return FAIL;
			}
		}
		
		return FAIL;
	}
	
	/**
	 * 重置测验
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	@ResponseBody
	public String reset(String chapterId, String trainId) {
		TrainHistoryDao dao = new TrainHistoryDao();
		if(dao.deleteUserHistory(this.currentUser().getId(), trainId, chapterId) > 0) {
			return SUCCESS;
		}
		return FAIL;
	}
}
