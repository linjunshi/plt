package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseBuyQuery;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;

/**
 * @author weinianjie
 * @date 2014年10月14日
 * @time 上午10:34:09
 */
@Controller
@RequestMapping("/study")
public class StudyAction extends StudentBaseAction {

	/**
	 * 我的课程
	 * @return
	 */
	@RequestMapping("/course")
	public String myCourse() {
		
		HttpServletRequest request = getRequest();
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		CourseDao courseDao = new CourseDao();
		CourseBuyQuery query = new CourseBuyQuery();
		query.setUserId(this.currentUser().getId());
		query.setPageSize(12);
		query.setPageNum(pageNum);
		query.setCount(courseDao.selectCountByQuery(query));
		List<CourseItem> courseList = courseDao.selectByQuery(query);
		
		request.setAttribute("courseList", courseList);
		request.setAttribute("query", query);
		
		return "manage/student/myCourse";
	}
	
	/**
	 * 我的直播
	 * @return
	 */
	@RequestMapping("/live")
	public String myLive() {
		return "manage/student/myLive";
	}
	
}
