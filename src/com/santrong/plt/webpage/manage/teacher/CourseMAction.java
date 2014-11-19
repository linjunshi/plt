package com.santrong.plt.webpage.manage.teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseQuery;
import com.santrong.plt.webpage.manage.TeacherBaseAction;

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
		
		return "manage/teacher/courseList";
	}
	
	@RequestMapping("/add")
	public String addCourse(){
		return "/manage/teacher/courseAdd";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCoursePost(){
		
		return this.redirect("/manage/course");
	}
	
	@RequestMapping("/changeCover")
	public String changeCover(){
		return "/manage/teacher/changeCover";
	}
}
