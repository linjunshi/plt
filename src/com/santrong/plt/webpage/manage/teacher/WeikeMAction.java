package com.santrong.plt.webpage.manage.teacher;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.ValidateTools;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.dao.WeikeDao;
import com.santrong.plt.webpage.course.entry.CourseForm;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.WeikeQuery;
import com.santrong.plt.webpage.manage.TeacherBaseAction;

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
			CourseDao courseDao = new CourseDao();
			CourseItem course = courseDao.selectById(courseId);
			// 判断当前用户是否是该课程的所有者
			if(course == null || !course.getOwnerId().equals(this.currentUser().getId())) {
				return this.redirect("/");
			}
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("course", course);
			request.setAttribute("fn", "modify");
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
				if (MyUtils.isNull(courseForm.getCourseName())) {
					addError("课程名称不能为空！");
				}
				if (courseForm.getPrice() == null) {
					addError("课程价格不能为空！");
				}
				if (courseForm.getLimitCount() == null) {
					addError("课程限购数量不能为空！");
				}
				if (courseForm.getLive() == null) {
					addError("是否是直播课程不能为空！");
				}
				if (MyUtils.isNotNull(courseForm.getEndTime()) && !ValidateTools.isDate(courseForm.getEndTime())) {
					addError("日期格式不正确！");
				}

				if(this.errorSize() == 0) {
					
					courseForm.setCourseName(courseForm.getCourseName().trim());
					BeanUtils.copyProperties(courseForm, courseItem);
					
					courseItem.setEndTime(MyUtils.stringToDate(courseForm.getEndTime(), "yyyy-MM-dd"));
					courseItem.setUts(new Date());
					if (courseDao.update(courseItem)) {
						addError("修改课程成功！");
					} else {
						addError("修改课程失败，请刷新页面后重新操作！");
					}
				}
				this.getRequest().setAttribute("course", courseForm);
				this.getRequest().setAttribute("fn", "modify");		
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
