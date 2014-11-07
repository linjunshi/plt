package com.santrong.plt.webpage.home;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeDefineEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseView;
import com.santrong.plt.webpage.school.dao.SchoolDao;
import com.santrong.plt.webpage.school.entry.GradeSchoolView;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;
import com.santrong.plt.webpage.teacher.entry.UserQuery;

/**
 * @Author weinianjie
 * @Date 2014-7-10
 * @Time 下午10:05:54
 */
@Controller
public class HomeAction extends BaseAction{
	
	@RequestMapping("")
	public String index(HttpServletRequest request, HttpServletResponse response){
		AreaEntry area = (AreaEntry)(request.getSession().getAttribute(Global.SessionKey_Area));
		
		
		// 推荐学校
		SchoolDao schoolDao = new SchoolDao();
		List<GradeSchoolView> schoolView = new ArrayList<GradeSchoolView>();
		for(GradeDefineEntry grade : GradeDefine.gradeList) {
			GradeSchoolView view = new GradeSchoolView();
			view.setGradeGroup(grade.getGradeGroup());
			view.setGradeName(grade.getGradeName());
			view.setGradeEnName(grade.getGradeEnName());
			view.setSchoolList(schoolDao.selectTotalByGradeGroup(view.getGradeGroup(), area.getCityCode(), 6));
			schoolView.add(view);
		}
		request.setAttribute("schoolView", schoolView);
		
		// 推荐老师
		UserDao userDao = new UserDao();
		UserQuery userQuery = new UserQuery();
		userQuery.setAreaCode(area.getCityCode());
		userQuery.setRole(UserItem.Role_Teacher);
		userQuery.setPageSize(6);
		List<UserItem> teacherList = userDao.selectByQuery(userQuery);
		request.setAttribute("teacherList", teacherList);
		
		// 直播课程
		
		// 点播课程
		CourseDao courseDao = new CourseDao();
		for(GradeDefineEntry entry : GradeDefine.gradeList) {
			int gradeGroup = entry.getGradeGroup();
			String prefix = entry.getGradeEnName();
			List<CourseView> courseList = courseDao.selectForIndexList(gradeGroup, area.getCityCode());
			
			request.setAttribute(prefix  + "_courseList", courseList);
			request.setAttribute(prefix + "_subjectList", entry.getGradeSubjectList());
		}

		return "index";
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
