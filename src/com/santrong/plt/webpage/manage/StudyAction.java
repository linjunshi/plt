package com.santrong.plt.webpage.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CourseDao;
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

	@RequestMapping("/course")
	public String mycourse() {
		
		UserItem user = this.currentUser();
		if(user == null) {
			// 没登陆
			return this.redirect("/login");
		}
		
		CourseDao courseDao = new CourseDao();
		
		List<CourseItem> course = courseDao.selectByUserId(user.getId());
		
		HttpServletRequest request = getRequest();
		request.setAttribute("course", course);
		
		return "manage/mycourse";
	}
	
}
