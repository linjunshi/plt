package com.santrong.plt.webpage.course.resource.train;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.resource.train.dao.TrainDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainHistoryDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryForm;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryView;
import com.santrong.plt.webpage.course.resource.train.entry.TrainItem;
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
	public String home(String resId, Integer index) {
		
		//TODO 权限判断
		
		index = index == null? 0 : --index;
		
		TrainDao trainDao = new TrainDao();
		TrainItem train = trainDao.selectById(resId);
		
		ChapterDao chapterDao = new ChapterDao();
		ChapterItem chapter = chapterDao.selectByResourceId(resId);
		
		if(train ==  null) {
			this.redirect("/");
		}
		
		TrainQuestionDao dao = new TrainQuestionDao();
		
		int total = dao.selectCountByTrainId(resId);
		if(total > 0) {
			if(index < 0 || index >= total) {
				this.redirect("/");
			}
			
			TrainQuestionItem question = dao.selectByTrainIdAndIndex(resId, index);
			
			this.getRequest().setAttribute("question", question);
		}
		
		this.getRequest().setAttribute("resId", resId);
		this.getRequest().setAttribute("index", ++index);
		this.getRequest().setAttribute("train", train);
		this.getRequest().setAttribute("chapter", chapter);
		this.getRequest().setAttribute("total", total);
		
		return "course/resource/train/detail";
	}
	
	@RequestMapping(value = "/question/commit", method = RequestMethod.POST)
	public String questionCommit(TrainHistoryForm form) {
		
		// TODO 权限判断
		
		TrainQuestionDao dao = new TrainQuestionDao();
		TrainQuestionItem question = dao.selectById(form.getQuestionId());
		TrainHistoryItem history = dao.selectInHistory(form.getQuestionId(), form.getTrainId(), form.getChapterId(), this.currentUser().getId());
		int result = question.getAnswer() == form.answerTotal()? 1 : 0;
		
		if(history == null) {
			history  = new TrainHistoryItem();
		}
		history.setUserId(this.currentUser().getId());
		history.setChapterId(form.getChapterId());
		history.setTrainId(form.getTrainId());
		history.setQuestionId(form.getQuestionId());
		history.setAnswer(form.answerTotal());
		history.setResult(result);
		history.setUts(new Date());
		
		if(MyUtils.isNotNull(history.getId())) {// 已经有的就更新
			dao.updateInHistory(history);
		}else {// 没有的就新增
			history.setId(MyUtils.getGUID());
			history.setCts(new Date());
			dao.insertInHistory(history);
		}
		
		// 不是最后一题，就跳转下一题
		if(form.getIndex() < form.getTotal()) {
			return this.redirect("/train?resId=" + form.getResourceId() + "&index=" + (form.getIndex() + 1));
		}
		
		// 否则跳转结账页面
		return this.redirect("/train/result?resId=" + form.getResourceId() + "&chapterId=" + form.getChapterId());
	}
	
	@RequestMapping(value = "/result")
	public String questionCommit(String resId, String chapterId) {
		TrainQuestionDao qDao = new TrainQuestionDao();
		TrainHistoryDao hDao = new TrainHistoryDao();
		int total = qDao.selectCountByTrainId(resId);// 总题数
		List<TrainHistoryView> historyList = hDao.selectUserDone(this.currentUser().getId(), resId, chapterId);
		int replayCount = historyList.size();
		
		HttpServletRequest request = this.getRequest();
		request.setAttribute("total", total);
		request.setAttribute("replayCount", replayCount);
		request.setAttribute("historyList", historyList);
		
		return "course/resource/train/result";
	}
}
