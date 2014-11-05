package com.santrong.plt.webpage.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseBuyQuery;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年10月14日
 * @time 上午10:34:09
 */
@Controller
@RequestMapping("/study")
public class StudyAction extends BaseAction {

	/**
	 * 我的课程
	 * @return
	 */
	@RequestMapping("/course")
	public String mycourse() {
		
		UserItem user = this.currentUser();
		if(user == null) {
			// 没登陆
			return this.redirect("/login");
		}
		
		HttpServletRequest request = getRequest();
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		CourseDao courseDao = new CourseDao();
		CourseBuyQuery query = new CourseBuyQuery();
		query.setUserId(user.getId());
		query.setPageSize(12);
		query.setPageNum(pageNum);
		query.setCount(courseDao.selectCountByBuyQuery(query));
		List<CourseItem> courseList = courseDao.selectByBuyQuery(query);
		
		request.setAttribute("courseList", courseList);
		request.setAttribute("query", query);
		
		return "manage/mycourse";
	}
	
	/**
	 * 我的直播
	 * @return
	 */
	@RequestMapping("/live")
	public String mylive() {
		return "manage/mylive";
	}
	
}
