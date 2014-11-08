package com.santrong.plt.webpage.manage.teacher.train;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.manage.TeacherBaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

@Controller
@RequestMapping("/manage/question")
public class QuestionMAction extends TeacherBaseAction{

	@RequestMapping("/list")
	public String questionList(){
		UserItem user = this.currentUser();
		TrainQuestionDao tqDao = new TrainQuestionDao();
		List<TrainQuestionItem> questionList = tqDao.selectByUserId(user.getId());
//		questionList = null;
		HttpServletRequest request = this.getRequest();
		request.setAttribute("questionList", questionList);
		
		return "/manage/teacher/myTrainMList";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String questionDelete(String questionId){
		TrainQuestionDao tqDao = new TrainQuestionDao();
		if (tqDao.deleteById(questionId)) {
			return this.redirect("/manage/question/list");
		}
		return this.redirect("/manage/question/list");
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addQuestion(){
		return "/manage/teacher/myTrainMAdd";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addQuestionPost(int questionType, String topic, String opt1, String opt2, String opt3, String opt4, String[] answer){
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
		return FAIL;
	}
	
	
}
