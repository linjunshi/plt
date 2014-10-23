package com.santrong.plt.webpage.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/teacher")
public class TeacherAction extends BaseAction {
	
	/**
	 * 老师首页
	 * @return
	 */
	@RequestMapping(value="")
	public String teacherIndex() {
		return catagory("all", "all");
	}
	
	
	/**
	 * 按年级搜索的老师
	 * @param grade
	 * @return
	 */
	@RequestMapping("/{grade}")
	public String catagory(@PathVariable String grade) {
		return catagory(grade, "all");
	}
	
	/**
	 * 按年级和科目搜索的老师
	 * @param grade
	 * @param subject
	 * @return
	 */
	@RequestMapping("/{grade}/{subject}")
	public String catagory(@PathVariable String grade, @PathVariable String subject) {
		UserDao userDao = new UserDao();
		List<UserItem> teacherList = userDao.selectAll();
		
		HttpServletRequest request = getRequest();
		request.setAttribute("teacherList", teacherList);
		
		return "teacher/index";
	}	
	
	/**
	 * 老师详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}.html")
	public String detail(@PathVariable String id) {
		
		return "teacher/detail";
	}
	
}
