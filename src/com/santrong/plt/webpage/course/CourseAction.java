package com.santrong.plt.webpage.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseDetailView;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/course")
public class CourseAction extends BaseAction {
	
	/**
	 * 点播课首页
	 * @return
	 */
	@RequestMapping(value="")
	public String index() {
		return catagory("all", "all");
	}
	
	/**
	 * 按年级搜索的点播课
	 * @param grade
	 * @return
	 */
	@RequestMapping("/{grade}")
	public String catagory(@PathVariable String grade) {
		return catagory(grade, "all");
	}
	
	/**
	 * 按年级和科目搜索的点播课
	 * @param grade
	 * @param subject
	 * @return
	 */
	@RequestMapping("/{grade}/{subject}")
	public String catagory(@PathVariable String grade, @PathVariable String subject) {
		
		CourseDao courseDao = new CourseDao();
		List<CourseItem> courseList = courseDao.selectByQuery();
		
		HttpServletRequest request = getRequest();
		request.setAttribute("courseList", courseList);
		
		return "course/index";
	}	
	
	/**
	 * 点播课详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}.html")
	public String detail(@PathVariable String id) {
		
		// 课程详细
		CourseDao courseDao = new CourseDao();
		CourseDetailView course = courseDao.selectDetailById(id);
		if(course == null) {
			return "404";
		}
		
		// 课程老师
		UserDao userDao = new UserDao();
		UserItem teacher = userDao.selectById(course.getOwnerId());
		
		// 相关课程
		
		// 老师名下其他课程
		
		HttpServletRequest request = getRequest();
		request.setAttribute("course", course);
		request.setAttribute("teacher", teacher);
		
		return "course/detail";
	}
	
}
