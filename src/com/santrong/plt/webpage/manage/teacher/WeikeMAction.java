package com.santrong.plt.webpage.manage.teacher;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.ValidateTools;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.dao.WeikeDao;
import com.santrong.plt.webpage.course.entry.CourseForm;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.WeikeKnowledgeView;
import com.santrong.plt.webpage.course.entry.WeikeQuery;
import com.santrong.plt.webpage.course.resource.train.dao.KnowledgeDao;
import com.santrong.plt.webpage.manage.TeacherBaseAction;
import com.santrong.plt.webpage.manage.superman.entry.KnowledgeItem;

/**
 * @author weinianjie
 * @date 2014年11月10日
 * @time 下午2:16:14
 */
@Controller
@RequestMapping("/manage/weike")
public class WeikeMAction extends TeacherBaseAction {

	/**
	 * 微课管理
	 * @return
	 */
	@RequestMapping("")
	public String myCourse() {
		try {
			HttpServletRequest request = getRequest();
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			WeikeDao dao = new WeikeDao();
			WeikeQuery query = new WeikeQuery();
			query.setUserId(this.currentUser().getId());
			query.setPageSize(12);
			query.setPageNum(pageNum);
			query.setCount(dao.selectCountByQuery(query));
			List<CourseItem> courseList = dao.selectByQuery(query);
			
			request.setAttribute("courseList", courseList);
			request.setAttribute("query", query);
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/teacher/weikeList";
	}
	

	/**
	 * 获取微课修改页面
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyCourse(String courseId){
		try {
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			CourseDao courseDao = new CourseDao();
			CourseItem course = courseDao.selectById(courseId);
			// 判断当前用户是否是该课程的所有者
			if(course == null || !course.getOwnerId().equals(this.currentUser().getId())) {
				return this.redirect("/");
			}
			
			// 获取微课已经绑定的知识点
			List<WeikeKnowledgeView> w2kList = courseDao.selectCourse2KnowledgeByCouseId(courseId);
			String knowledgeIds = "";
			for (WeikeKnowledgeView wkItem : w2kList) {
				knowledgeIds += "," + wkItem.getKnowledgeId();
			}
			if (knowledgeIds != "") {
				knowledgeIds = knowledgeIds.substring(1);//移除前面的多余的逗号
			}
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("knowledgeIds", knowledgeIds);
			request.setAttribute("course", course);
			request.setAttribute("fn", "modify");
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/weikeAdd";
	}
	
	
	/**
	 * 修改微课信息页面
	 * @param courseItem
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyCoursePost(CourseForm courseForm){
		try {
			HttpServletRequest request = this.getRequest();
			String knowledgeIds = request.getParameter("knowledgeIds");//原来绑定的知识点IDs
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			request.setAttribute("pageNum", pageNum);
			
			CourseDao courseDao = new CourseDao();
			CourseItem courseItem = courseDao.selectById(courseForm.getId());
			
			// 判断当前用户是否是该课程的所有者
			if(courseItem == null || !courseItem.getOwnerId().equals(this.currentUser().getId())) {
				return this.redirect("/");
			}
			
			if (courseForm != null) {
				
				if (MyUtils.isNull(courseForm.getGradeId())) {
					addError("请您选择课程级别！");
				}
				if (MyUtils.isNull(courseForm.getSubjectId())) {
					addError("请您选择课程科目！");
				}
				if (MyUtils.isNull(courseForm.getUnitId())) {
					addError("请您选择学期单元！");
				}
				if (MyUtils.isNull(courseForm.getCourseName())) {
					addError("课程名称不能为空！");
				}
				if (courseForm.getPrice() == null) {
					addError("课程价格不能为空！");
				}
				if (courseForm.getLimitCount() == null) {
					addError("课程限购数量不能为空！");
				}
//				if (courseForm.getLive() == null) {
//					addError("是否是直播课程不能为空！");
//				}
				if (MyUtils.isNotNull(courseForm.getEndTime()) && !ValidateTools.isDate(courseForm.getEndTime())) {
					addError("日期格式不正确！");
				}

				if(this.errorSize() == 0) {
					
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
							if (!kItem.getUnitId().equals(courseForm.getUnitId())) {
								addError("亲，您修改了知识点分类，请您重新绑定知识点 ！");
								request.setAttribute("tqItem", courseForm);
								return "/manage/teacher/myTrainMAdd";
							}
						}
						courseDao.removeAllKnowledge4Course(courseForm.getId());
						for (String knowledgeId : stringArr) {
							courseDao.addKnowledge2Course(courseForm.getId(), knowledgeId);
						}
					}
					
					courseForm.setCourseName(courseForm.getCourseName().trim());
					BeanUtils.copyProperties(courseForm, courseItem);
					courseItem.setEndTime(MyUtils.stringToDate(courseForm.getEndTime(), "yyyy-MM-dd"));
					courseItem.setUts(new Date());
					result = courseDao.update(courseItem);
					
					ThreadUtils.commitTranx();//提交事务
					
					if (result) {
						addError("修改课程成功！");
					} else {
						addError("修改课程失败，请刷新页面后重新操作！");
					}
				}
				request.setAttribute("course", courseForm);
				request.setAttribute("fn", "modify");
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		
		return this.redirect("/manage/weike/modify?courseId=" + courseForm.getId());
	}
	
	@RequestMapping("/add")
	public String insert() {
		//TODO 添加课程
		//TODO 添加章节
		//TODO 添加章节关联视频
		return "";
	}
}
