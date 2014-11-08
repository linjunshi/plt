package com.santrong.plt.webpage.manage.teacher.train;

import java.util.Date;

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

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addQuestion(){
		return "/manage/teacher/myTrainManager";
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
			return "/manage/teacher/myTrainManager";
		}
		
		if (!MyUtils.isNotNull(topic)) {
			addError("请您填写试题的题目！");
			return "/manage/teacher/myTrainManager";
		}
		
		if (!(answer != null)) {
			addError("请您勾选正确的答案！");
			return "/manage/teacher/myTrainManager";
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
			return redirect("/manage/question/add");
		}
		return FAIL;
	}
	
	
}
