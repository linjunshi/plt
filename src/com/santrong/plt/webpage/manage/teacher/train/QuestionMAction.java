package com.santrong.plt.webpage.manage.teacher.train;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionQuery;
import com.santrong.plt.webpage.manage.TeacherBaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

@Controller
@RequestMapping("/manage/question")
public class QuestionMAction extends TeacherBaseAction{

	@RequestMapping("/list")
	public String questionList() {
		try {
			UserItem user = this.currentUser();
			
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			TrainQuestionDao tqDao = new TrainQuestionDao();
			TrainQuestionQuery query = new TrainQuestionQuery();
			query.setPageNum(pageNum);
			query.setUserId(user.getId());
			query.setDel(0);
			query.setCount(tqDao.selectCountByQuery(query));
			List<TrainQuestionItem> questionList = tqDao.selectByQuery(query);
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("questionList", questionList);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/myTrainMList";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String questionDelete(String questionId){
		try {
			TrainQuestionDao tqDao = new TrainQuestionDao();
			if (tqDao.deleteById(questionId)) {
				return this.redirect("/manage/question/list");
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/manage/question/list");
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addQuestion(){
		return "/manage/teacher/myTrainMAdd";
	}
	
	/**
	 * 新增一条试题记录到题库中
	 * @author huangweihua
	 * @param questionType
	 * @param topic
	 * @param opt1
	 * @param opt2
	 * @param opt3
	 * @param opt4
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addQuestionPost(int questionType, String topic, String opt1, String opt2, String opt3, String opt4, String[] answer){
		try {
			UserItem user = this.currentUser();
			if(user == null) {
				addError("请您先登录用户！");
				return "/login";
			}
			
			if (questionType <= 0) {
				addError("请您选择题目的类型！");
				return "/manage/teacher/myTrainMAdd";
			}
			
			if (!MyUtils.isNotNull(topic)) {
				addError("请您填写试题的题目！");
				return "/manage/teacher/myTrainMAdd";
			}
			
			if (!(answer != null)) {
				addError("请您勾选正确的答案！");
				return "/manage/teacher/myTrainMAdd";
			}
			
			int sum = 0;
			for (int i = 0; i < answer.length; i++) {
				sum = sum + MyUtils.stringToInt(answer[i]);
			}
			TrainQuestionDao tqDao = new TrainQuestionDao();
			TrainQuestionItem tqItem = new TrainQuestionItem(); 
			tqItem.setId(MyUtils.getGUID());
			tqItem.setTopic(topic);
			tqItem.setQuestionType(questionType);
			tqItem.setOpt1(opt1);
			tqItem.setOpt2(opt2);
			tqItem.setOpt3(opt3);
			tqItem.setOpt4(opt4);
			tqItem.setAnswer(sum);
			tqItem.setOwnerId(user.getId());
			tqItem.setDel(0);
			tqItem.setCts(new Date());
			tqItem.setUts(new Date());
			
			if (tqDao.insert(tqItem)) {
				addError("新增试题成功！");
				return "/manage/teacher/myTrainMAdd";
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
	
	@RequestMapping("/update")
	public String updateQuestion(String questionId){
		try {
			TrainQuestionDao tqDao = new TrainQuestionDao();
			TrainQuestionItem tqItem = tqDao.selectById(questionId);

			HttpServletRequest request = this.getRequest();
			request.setAttribute("tqItem", tqItem);
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/myTrainMUpdate";
	}
	
	@RequestMapping(value = "/updatePost",method=RequestMethod.POST)
	public String updateQuestionPost(TrainQuestionItem tqItem){
		try {
			if (tqItem != null) {
				int sumAnswer = 0;
				for (int i = 0; i < tqItem.getPageAnswer().length; i++) {
					sumAnswer = sumAnswer + tqItem.getPageAnswer()[i];
				}
				
				TrainQuestionDao tqDao = new TrainQuestionDao();
				tqItem.setAnswer(sumAnswer);
				tqItem.setUts(new Date());
				tqDao.update(tqItem);
				
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/manage/question/list");
	}
}
