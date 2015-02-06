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
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionForm;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionQuery;
import com.santrong.plt.webpage.manage.TeacherBaseAction;
import com.santrong.plt.webpage.manage.superman.entry.KnowledgeItem;
import com.santrong.plt.webpage.manage.superman.entry.KnowledgeQuestionView;
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
			query.setOrderRule("desc");
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
				request.setAttribute("operation", "add");
			} else {
				//打开修改页面或者试题审核界面
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
				request.setAttribute("operation", "modify");
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/myTrainMAdd";
	}
	
	/**
	 * 打开试题审核界面
	 */
	@RequestMapping(value="/auditing", method=RequestMethod.GET)
	public String auditingQuestion(){
		try {
			HttpServletRequest request = this.getRequest();
			String questionId = request.getParameter("questionId");
			
			if (MyUtils.isNotNull(questionId)) {
				//打开修改页面或者试题审核界面
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
				request.setAttribute("operation", "auditing");
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
	public String addOrModifyQuestion(TrainQuestionForm tqForm){
		try {
			HttpServletRequest request = this.getRequest();
			String knowledgeIds = request.getParameter("knowledgeIds");//原来绑定的知识点IDs
			String operation = request.getParameter("operation");//用来判断是否是审核
			int status = this.getIntParameter("status");
			
			if ("add".equalsIgnoreCase(operation)) {//打开新增页面
				request.setAttribute("operation", "add");
			} else if ("modify".equalsIgnoreCase(operation)) {//打开修改页面
				request.setAttribute("operation", "modify");
			} else if ("auditing".equalsIgnoreCase(operation)) {//打开审核页面
				request.setAttribute("operation", "auditing");
			}
			
			if (tqForm != null) {
				
				if (MyUtils.isNull(tqForm.getGradeId())) {
					addError("请您选择试题所属年级 ！");
				}
				if (MyUtils.isNull(tqForm.getSubjectId())) {
					addError("请您选择试题的所属学科 ！");
				}
				if (MyUtils.isNull(tqForm.getUnitId())) {
					addError("请您选择试题的所属单元 ！");
				}
				if (MyUtils.isNull(knowledgeIds)) {
					addError("亲，您还没有绑定知识点呢 ！");
				}
				if (tqForm.getLevel() < 0) {
					addError("请您选择难易程度 ！");
				}
				if (tqForm.getQuestionType() <= 0) {
					addError("请您选择题目的类型 ！");
				}
				if (MyUtils.isNull(tqForm.getTopic())) {
					addError("请您填写试题的题目 ！");
				}
//				if (tqForm.isSingleSelection()) {//单选题
//					if (tqForm.getAnswer() == null) {
//						addError("请您勾选正确的答案 ！");
//					}
//				} else if (tqForm.isMulChoice()) {//多选题
//					if (!(tqForm.getPageAnswer() != null)) {
//						addError("请您勾选正确的答案 ！");
//					}
//				} else if (tqForm.isTrueOrFlase()) {//判断题
//					if (tqForm.getAnswer2() == null) {
//						addError("请您勾选正确的答案 ！");
//					}
//				} else if (tqForm.isBlankFilling()) {//填空题
//					if (MyUtils.isNull(tqForm.getAnswer3().trim())) {
//						addError("请您填写正确的答案 ！");
//					}
//				}
				
				if (!(errorSize() > 0)) {
					if (MyUtils.isNull(tqForm.getId())) {
						
						// id为空，执行新增操作
						String[] stringArr = null;
						boolean result = false;
						
						ThreadUtils.beginTranx();
						TrainQuestionDao tqDao = new TrainQuestionDao();
						TrainQuestionItem tqItem = new TrainQuestionItem();
						tqItem.setId(MyUtils.getGUID());
						tqItem.setGradeId(tqForm.getGradeId());//学段年级
						tqItem.setSubjectId(tqForm.getSubjectId());//学科
						tqItem.setUnitId(tqForm.getUnitId());//单元
						tqItem.setTopic(tqForm.getTopic().trim());
						tqItem.setQuestionType(tqForm.getQuestionType());
						
						if (tqForm.isSingleSelection()) {//单选题
							tqItem.setAnswer(tqForm.getAnswer());
						} else if (tqForm.isMulChoice()) {//多选题
							tqItem.setAnswer(MyUtils.consistNums(tqForm.getPageAnswer()));//拼接字符串
						} else if (tqForm.isTrueOrFlase()){//判断题
							tqItem.setAnswer(tqForm.getAnswer2());
						} else if (tqForm.isBlankFilling()){//填空题
							tqItem.setAnswer(tqForm.getAnswer3().trim());
						}
						
						tqItem.setRemark(tqForm.getRemark());
						tqItem.setTimeLimit(0);//限制时间
						tqItem.setOwnerId(this.currentUser().getId());
						tqItem.setLevel(tqForm.getLevel());//易：0 ，中 ：10 ， 难：100
						tqItem.setStatus(TrainQuestionItem.Status_New);//0:新建 ,1:已审批 ,100:已删除
						tqItem.setCts(new Date());
						tqItem.setUts(new Date());
						result = tqDao.insert(tqItem);
						// 删除原来绑定的知识点，再重新绑定
						if (MyUtils.isNotNull(knowledgeIds)) {
							stringArr = knowledgeIds.split(",");
							for (String knowledgeId : stringArr) {
								tqDao.addKnowledge2Question(tqItem.getId(), knowledgeId);
							}
						}
						ThreadUtils.commitTranx();
						
						if (result) {
							addError("新增试题成功 ！");
							return "/manage/teacher/myTrainMAdd";
						} else {
							addError("新增试题失败！");
							request.setAttribute("tqItem", tqForm);
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
								if (!kItem.getUnitId().equals(tqForm.getUnitId())) {
									addError("亲，您修改了知识点分类，请您重新绑定知识点 ！");
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
						tqItem.setUnitId(tqForm.getUnitId());//单元
						tqItem.setTopic(tqForm.getTopic());
						tqItem.setQuestionType(tqForm.getQuestionType());
						if (tqForm.isSingleSelection()) {//单选题
							tqItem.setAnswer(tqForm.getAnswer());
						} else if (tqForm.isMulChoice()) {//多选题
							tqItem.setAnswer(MyUtils.consistNums(tqForm.getPageAnswer()));//拼接字符串
						} else if (tqForm.isTrueOrFlase()){//判断题
							tqItem.setAnswer(tqForm.getAnswer2());
						} else if (tqForm.isBlankFilling()){//填空题
							tqItem.setAnswer(tqForm.getAnswer3().trim());
						}
						tqItem.setRemark(tqForm.getRemark());
						tqItem.setLevel(tqForm.getLevel());//易：0 ，中 ：10 ， 难：100
						tqItem.setStatus(status);//0:新建 ,1:已审批 ,2:审核不通过,100:已删除
						tqItem.setUts(new Date());
						result = tqDao.update(tqItem);
						
						ThreadUtils.commitTranx();//提交事务
						if(result){
							return this.redirect("/manage/question/list");
						} else {
							addError("亲，您操作失败了，请您刷新页面后重新操作！");
							request.setAttribute("tqItem", tqForm);
						}
					}
				} else {
					request.setAttribute("tqItem", tqForm);
				}
			}
			if (MyUtils.isNotNull(tqForm.getId())) {//提交错误的时候，初始化修改表单页面的数据
				// 获取已经绑定的知识点
				TrainQuestionDao tqDao = new TrainQuestionDao();
				List<KnowledgeQuestionView> k2qList = tqDao.selectKnowledge2QuestionByQId(tqForm.getId());
				for (KnowledgeQuestionView kqItem : k2qList) {
					knowledgeIds += "," + kqItem.getKnowledgeId();
				}
				if (knowledgeIds != "") {
					knowledgeIds = knowledgeIds.substring(1);//移除前面的多余的逗号
				}
				request.setAttribute("knowledgeIds", knowledgeIds);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/myTrainMAdd";
	}

}
