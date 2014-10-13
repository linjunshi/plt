package com.santrong.plt.webpage.home;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeDefineEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseView;
import com.santrong.plt.webpage.home.dao.SchoolDao;
import com.santrong.plt.webpage.home.entry.SchoolQuery;
import com.santrong.plt.webpage.home.entry.SchoolView;
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @Author weinianjie
 * @Date 2014-7-10
 * @Time 下午10:05:54
 */
@Controller
public class HomeAction extends BaseAction{
	
	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response){
			String areaCode = (String)(request.getSession().getAttribute(Global.SessionKey_AreaCode));
			
			// 推荐学校
			SchoolDao schoolDao = new SchoolDao();
			SchoolQuery schoolQuery = new SchoolQuery();
			schoolQuery.setAreaCode(areaCode);
			List<SchoolView> schoolView = new ArrayList<SchoolView>();
			for(GradeDefineEntry grade : GradeDefine.gradeList) {
				SchoolView view = new SchoolView();
				view.setGradeGroup(grade.getGradeGroup());
				view.setGradeName(grade.getGradeName());
				view.setGradeEnName(grade.getGradeEnName());
				
				schoolQuery.setSchoolGrade(view.getGradeGroup());
				view.setSchoolList(schoolDao.selectByQuery(schoolQuery));
				schoolView.add(view);
			}
			request.setAttribute("schoolView", schoolView);
			
			// 推荐老师
			UserDao userDao = new UserDao();
			List<UserItem> teacherList = userDao.selectAll();
			request.setAttribute("teacherList", teacherList);
			
			// 直播课程
			
			// 点播课程
			CourseDao vodDao = new CourseDao();
			for(GradeDefineEntry entry : GradeDefine.gradeList) {
				int gradeGroup = entry.getGradeGroup();
				String prefix = entry.getGradeEnName();
				List<CourseView> vodList = vodDao.selectForIndexList(gradeGroup, areaCode);
				
				request.setAttribute(prefix  + "_vodList", vodList);
				request.setAttribute(prefix + "_subjectList", entry.getGradeSubjectList());
			}

			return "index";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet() {
		
		return "login";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String loginPOST(String username, String password) {
		if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)) {
			return "error_login_nullInput";
		}
		
		UserDao userDao = new UserDao();
		UserItem user = userDao.selectByUserName(username);
		
		if(user == null) {
			return "error_login_user_not_exists";
		}
		
		if(!user.getPassword().equals(MyUtils.getMD5(password))) {
			return "error_login_password_wrong";
		}
		
		getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
		
		return SUCCESS;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request) {

		UserItem user = (UserItem)request.getSession().getAttribute(Global.SessionKey_LoginUser);
		if(user == null) {
			return SUCCESS;
		}
		
		try{
			request.getSession().removeAttribute(Global.SessionKey_LoginUser);
		}catch(Exception e) {
			Log.printStackTrace(e);
			return FAIL;
		}
		
		request.getSession().invalidate();
		
		return SUCCESS;
	}

	
	@RequestMapping("/404")
	public String page404() {
		return "404";
	}
	
	
	@RequestMapping("/500")
	public String page500() {
		return "500";
	}
}
