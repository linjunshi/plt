package com.santrong.plt.webpage.manage.teacher;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseQuery;
import com.santrong.plt.webpage.manage.TeacherBaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年11月10日
 * @time 下午2:16:14
 */
@Controller
@RequestMapping("/manage/course")
public class CourseMAction extends TeacherBaseAction {

	/**
	 * 课程管理
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
			
			CourseDao dao = new CourseDao();
			CourseQuery query = new CourseQuery();
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
		return "manage/teacher/courseList";
	}
	
	@RequestMapping("/add")
	public String addCourse(){
		return "/manage/teacher/courseAdd";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCoursePost(CourseItem courseItem, String endTimeStr){
		try {
			UserItem user = this.currentUser();
			if (user == null) {
				return this.redirect("/login");
			}
			
			if (courseItem != null) {
				
				if (MyUtils.isNull(courseItem.getCourseName())) {
					addError("课程名称不能为空！");
					return "/manage/teacher/courseAdd";
				}
				if (Integer.valueOf(courseItem.getPrice()) == null) {
					addError("课程价格不能为空！");
					return "/manage/teacher/courseAdd";
				}
				if (Integer.valueOf(courseItem.getChapterCount()) == null) {
					addError("课时数量不能为空！");
					return "/manage/teacher/courseAdd";
				}
				if (Integer.valueOf(courseItem.getLive()) == null) {
					addError("是否是直播课程不能为空！");
					return "/manage/teacher/courseAdd";
				}
//				if (MyUtils.isNull(courseItem.getGradeId())) {
//					addError("请您选择课程级别！");
//					return "/manage/teacher/courseAdd";
//				}
				
//				TODO SubjectId 科目关联待解决 gradeId
				CourseDao courseDao = new CourseDao();
				courseItem.setId(MyUtils.getGUID());
				courseItem.setOwnerId(user.getId());
				courseItem.setGradeId("10000");
				courseItem.setSubjectId("10000");
				courseItem.setSaleCount(0);
				courseItem.setCollectCount(0);
				courseItem.setCommentCount(0);
				courseItem.setChapterCount(courseItem.getChapterCount() > 0 ? courseItem.getChapterCount() : 0);
				if (MyUtils.isNotNull(endTimeStr)) {
					courseItem.setEndTime(MyUtils.stringToDate(endTimeStr, "yyyy-MM-dd HH:mm:ss"));
				} else {
					courseItem.setEndTime(null);
				}
				courseItem.setCts(new Date());
				courseItem.setUts(new Date());
				if (courseDao.insert(courseItem)) {
					addError("新增课程成功！");
				} else {
					addError("新增课程失败！");
					return "/manage/teacher/courseAdd";
				}
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/manage/course");
	}
	
	@RequestMapping("/modify")
	public String modifyCourse(String courseId){
		try {
			
			CourseDao courseDao = new CourseDao();
			CourseItem course = courseDao.selectById(courseId);
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("course", course);
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/courseAdd";
	}
	
	/**
	 * 修改课程信息页面
	 * @param courseItem
	 * @return
	 */
	@RequestMapping(value = "/modifyPost", method = RequestMethod.POST)
	public String modifyCoursePost(CourseItem courseItem ,String endTimeStr){
		try {
			UserItem user = this.currentUser();
			if (user == null) {
				return this.redirect("/login");
			}
			
			if (courseItem != null) {
				
				if (MyUtils.isNull(courseItem.getCourseName())) {
					addError("课程名称不能为空！");
					return "/manage/teacher/courseAdd";
				}
				if (Integer.valueOf(courseItem.getPrice()) == null) {
					addError("课程价格不能为空！");
					return "/manage/teacher/courseAdd";
				}
				if (Integer.valueOf(courseItem.getChapterCount()) == null) {
					addError("课时数量不能为空！");
					return "/manage/teacher/courseAdd";
				}
				if (Integer.valueOf(courseItem.getLive()) == null) {
					addError("是否是直播课程不能为空！");
					return "/manage/teacher/courseAdd";
				}
//				if (MyUtils.isNull(courseItem.getGradeId())) {
//					addError("请您选择课程级别！");
//					return "/manage/teacher/courseAdd";
//				}
				
				CourseDao courseDao = new CourseDao();
				CourseItem course = courseDao.selectById(courseItem.getId());
				course.setCourseName(courseItem.getCourseName());
				course.setTeacher(courseItem.getTeacher());
				course.setChapterCount(courseItem.getChapterCount() > 0 ? courseItem.getChapterCount() : 0);
				if (MyUtils.isNotNull(endTimeStr)) {
					courseItem.setEndTime(MyUtils.stringToDate(endTimeStr, "yyyy-MM-dd HH:mm:ss"));
				} else {
					courseItem.setEndTime(null);
				}
				course.setLive(courseItem.getLive());
				course.setPrice(courseItem.getPrice());
				course.setRemark(courseItem.getRemark());
//				TODO GradeId年级功能，SubjectId，待完善
//				course.setSubjectId("10000");
				course.setGradeId(MyUtils.isNull(courseItem.getGradeId()) ? "10000" : courseItem.getGradeId());
				
				course.setUts(new Date());
				if (courseDao.update(course)) {
					addError("修改课程成功！");
				} else {
					addError("修改课程失败！");
				}
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/manage/course");
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String courseDelete(String courseId){
		try {
			CourseDao courseDao = new CourseDao();
			courseDao.deleteById(courseId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/manage/course");
	}
	
	@RequestMapping(value="/chapterEditor", method=RequestMethod.POST)
	public String editChapter(String courseId) {
		try {
			ChapterDao cDao = new ChapterDao();
			List<ChapterItem> chapterList = cDao.selectAllByCourseId(courseId);
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("chapterList", chapterList);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "";
	}
}
