package com.santrong.plt.webpage.manage.teacher;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.resource.train.dao.KnowledgeDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeItem;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeQuestionView;
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
			query.setCount(tqDao.selectCountByQuery(query));
			query.setOrderBy("cts");
			List<TrainQuestionItem> questionList = tqDao.selectByQuery(query);
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("questionList", questionList);
			request.setAttribute("query", query);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/myTrainMList";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String questionDelete(String questionId){
		try {
			TrainQuestionDao tqDao = new TrainQuestionDao();
			TrainQuestionItem tqItem = tqDao.selectById(questionId);
			// 判断当前用户是否是该课程的所有者
			if(tqItem == null || !tqItem.getOwnerId().equals(this.currentUser().getId())) {
				return this.redirect("/");
			}
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
				// 判断当前用户是否是该课程的所有者
				if(tqItem == null || !tqItem.getOwnerId().equals(this.currentUser().getId())) {
					return this.redirect("/");
				}
				
				// 获取已经绑定的知识点
				List<KnowledgeQuestionView> k2qList = tqDao.selectKnowledge2QuestionByQId(questionId);
				String knowledgeIds = "";
				for (KnowledgeQuestionView kqItem : k2qList) {
					knowledgeIds += "," + kqItem.getKnowledgeId();
				}
				if (knowledgeIds != "") {
					knowledgeIds = knowledgeIds.substring(1);//移除前面的多余的逗号
				}
				
				request.setAttribute("knowledgeIds", knowledgeIds);
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
			HttpServletRequest request = this.getRequest();
			String knowledgeIds = request.getParameter("knowledgeIds");//原来绑定的知识点IDs
			
			//打开新增页面
			request.setAttribute("addOrModify", "add");
			
			if (tqForm != null) {
				
				if (MyUtils.isNull(tqForm.getGradeId())) {
					addError("请您选择试题所属年级！");
				}
				if (MyUtils.isNull(tqForm.getSubjectId())) {
					addError("请您选择试题的所属学科！");
				}
				if (tqForm.getQuestionType() <= 0) {
					addError("请您选择题目的类型！");
				}
				if (MyUtils.isNull(tqForm.getOpt1().trim())) {
					addError("请您填写内容！");
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
				if (MyUtils.isNull(knowledgeIds)) {
					addError("亲，您还没有绑定知识点呢！");
				}
				
				if (!(errorSize() > 0)) {
					if (MyUtils.isNull(tqForm.getId())) {
						
						// id为空，执行新增操作
						TrainQuestionDao tqDao = new TrainQuestionDao();
						TrainQuestionItem tqItem = new TrainQuestionItem();
						tqItem.setId(MyUtils.getGUID());
						tqItem.setGradeId(tqForm.getGradeId());//学段年级
						tqItem.setSubjectId(tqForm.getSubjectId());//学科
						tqItem.setTopic(tqForm.getTopic().trim());
						tqItem.setQuestionType(tqForm.getQuestionType());
						tqItem.setOpt1(tqForm.getOpt1().trim());
						tqItem.setOpt2(tqForm.getOpt2().trim());
						tqItem.setOpt3(tqForm.getOpt3().trim());
						tqItem.setOpt4(tqForm.getOpt4().trim());
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
						tqItem.setTimeLimit(0);//限制时间
						tqItem.setOwnerId(this.currentUser().getId());
						tqItem.setStatus(TrainQuestionItem.Status_New);
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
						// 判断当前用户是否是该课程的所有者
						if(tqItem == null || !tqItem.getOwnerId().equals(this.currentUser().getId())) {
							return this.redirect("/");
						}
						
						// 点击绑定知识点的
						String[] stringArr = null;
						boolean result = false;
						
						ThreadUtils.beginTranx();//开始事务
						
						// 删除原来绑定的知识点，再重新绑定
						if (MyUtils.isNotNull(knowledgeIds)) {
							stringArr = knowledgeIds.split(",");
							KnowledgeDao kDao = new KnowledgeDao();
							List<KnowledgeItem> kList = kDao.selectByIds(stringArr);
							for (KnowledgeItem kItem : kList) {
								if (!kItem.getGradeId().equals(tqForm.getGradeId()) || !kItem.getSubjectId().equals(tqForm.getSubjectId())) {
									addError("亲，您修改了知识点分类，请您重新绑定知识点！");
									request.setAttribute("tqItem", tqForm);
									return "/manage/teacher/myTrainMAdd";
								}
							}
							tqDao.removeAllKnowledge4Question(tqForm.getId());
							for (String knowledgeId : stringArr) {
								tqDao.addKnowledge2Question(tqForm.getId(), knowledgeId);
							}
						}
						
						// 修改保存试题信息
						tqItem.setGradeId(tqForm.getGradeId());//学段年级
						tqItem.setSubjectId(tqForm.getSubjectId());//学科
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
						tqItem.setUts(new Date());
						result = tqDao.update(tqItem);
						
						ThreadUtils.commitTranx();//提交事务
						if(result){
							return this.redirect("/manage/question/list");
						} else {
							addError("亲，您修改失败了，请您刷新页面后重新操作！");
							request.setAttribute("tqItem", tqForm);
						}
					}
				} else {
					request.setAttribute("tqItem", tqForm);
				}
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/myTrainMAdd";
	}
	
}
