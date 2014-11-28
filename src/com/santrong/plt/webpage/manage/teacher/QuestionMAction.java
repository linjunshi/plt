package com.santrong.plt.webpage.manage.teacher;

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

	/**
	 * 打开新增、修改试题的页面
	 * @return String
	 */
	@RequestMapping(value="/addOrModifyQuestion", method=RequestMethod.GET)
	public String addOrModifyQuestion(){
		try {
			HttpServletRequest request = this.getRequest();
			String questionId = request.getParameter("questionId");
			if (MyUtils.isNull(questionId)) {
				//打开新增页面
				request.setAttribute("addOrModify", "add");
			} else {
				//打开修改页面
				TrainQuestionDao tqDao = new TrainQuestionDao();
				TrainQuestionItem tqItem = tqDao.selectById(questionId);
				request.setAttribute("tqItem", tqItem);
				request.setAttribute("addOrModify", "modify");
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/myTrainMAdd";
	}
	
	/**
	 * 新增、修改一条试题记录
	 * @author huangweihua
	 * @param tqItem
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="/addOrModifyQuestion", method=RequestMethod.POST)
	public String addOrModifyQuestion(TrainQuestionItem tqForm){
		try {
			UserItem user = this.currentUser();
			if(user == null) {
				addError("请您先登录用户！");
				return this.redirect("account/login");
			}
			
			if (tqForm != null) {
				
				if (tqForm.getQuestionType() <= 0) {
					addError("请您选择题目的类型！");
				}
				if (MyUtils.isNull(tqForm.getTopic())) {
					addError("请您填写试题的题目！");
				}
				if (tqForm.isSingleSelection()) {//单选题
					if (tqForm.getAnswer() <= 0) {
						addError("请您勾选正确的答案！");
					}
				} else if (tqForm.isMulChoice()) {//多选题
					if (!(tqForm.getPageAnswer() != null)) {
						addError("请您勾选正确的答案！");
					}
				}
				
				
				if (!(errorSize() > 0)) {
					if (MyUtils.isNull(tqForm.getId())) {
						// id不为空，执行新增操作
						TrainQuestionDao tqDao = new TrainQuestionDao();
						TrainQuestionItem tqItem = new TrainQuestionItem(); 
						tqItem.setId(MyUtils.getGUID());
						tqItem.setTopic(tqForm.getTopic());
						tqItem.setQuestionType(tqForm.getQuestionType());
						tqItem.setOpt1(tqForm.getOpt1());
						tqItem.setOpt2(tqForm.getOpt2());
						tqItem.setOpt3(tqForm.getOpt3());
						tqItem.setOpt4(tqForm.getOpt4());
						if (tqForm.isSingleSelection()) {//单选题
							tqItem.setAnswer(tqForm.getAnswer());
						} else if (tqForm.isMulChoice()) {//多选题
							int sumAnswer = 0;
							for (int i = 0; i < tqForm.getPageAnswer().length; i++) {
								sumAnswer = sumAnswer + tqForm.getPageAnswer()[i];
							}
							tqItem.setAnswer(sumAnswer);
						}
						tqItem.setRemark(tqForm.getRemark());
						tqItem.setOwnerId(user.getId());
						tqItem.setDel(0);
						tqItem.setCts(new Date());
						tqItem.setUts(new Date());
						
						if (tqDao.insert(tqItem)) {
							addError("新增试题成功！");
							return "/manage/teacher/myTrainMAdd";
						}
					} else {
						// id不为空，执行修改操作
						
						
						TrainQuestionDao tqDao = new TrainQuestionDao();
						TrainQuestionItem tqItem = tqDao.selectById(tqForm.getId());
						tqItem.setTopic(tqForm.getTopic());
						tqItem.setQuestionType(tqForm.getQuestionType());
						tqItem.setOpt1(tqForm.getOpt1());
						tqItem.setOpt2(tqForm.getOpt2());
						tqItem.setOpt3(tqForm.getOpt3());
						tqItem.setOpt4(tqForm.getOpt4());
						tqItem.setOwnerId(user.getId());
						if (tqForm.isSingleSelection()) {//单选题
							tqItem.setAnswer(tqForm.getAnswer());
						} else if (tqForm.isMulChoice()) {//多选题
							int sumAnswer = 0;
							for (int i = 0; i < tqForm.getPageAnswer().length; i++) {
								sumAnswer = sumAnswer + tqForm.getPageAnswer()[i];
							}
							tqItem.setAnswer(sumAnswer);
						}
						tqItem.setRemark(tqForm.getRemark());
						tqItem.setUts(new Date());
						if(tqDao.update(tqItem)){
							return this.redirect("/manage/question/list");
						}
					}
				}
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/myTrainMAdd";
	}
}
