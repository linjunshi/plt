package com.santrong.plt.webpage.home;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeDefineEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseView;
import com.santrong.plt.webpage.home.dao.AreaDao;
import com.santrong.plt.webpage.home.entry.AreaItem;
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
			view.setSchoolList(schoolDao.selectTotalByGradeGroup(view.getGradeGroup(), area.getCityCode(), 5));
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
	
	
	/**
	 * 切换城市
	 * @return
	 */
	@RequestMapping("/changecity")
	public String changeCityGet() {
		AreaDao dao = new AreaDao();
		List<AreaItem> cityList = dao.selectCity();
		
		TreeMap<String, List<AreaItem>> cityMap = new TreeMap<String, List<AreaItem>>();
		for(AreaItem city:cityList) {
			String firstWord = city.getAreaEnName().substring(0, 1);
			List<AreaItem> list = cityMap.get(firstWord);
			
			if(list == null) {
				list = new ArrayList<AreaItem>();
			}
			
			list.add(city);
			cityMap.put(firstWord, list);
		}
		this.getRequest().setAttribute("cityMap", cityMap);
		return "changeCity";
	}
	
	
	/**
	 * 切换城市
	 * @return
	 */
	@RequestMapping("/changecity/{city}")
	public String changeCityPost(@PathVariable String city) {
		AreaDao dao = new AreaDao();
		AreaItem area = dao.selectByAreaEnName(city);
		
		if(area == null) {
			this.redirect("/404");
		}
		
		AreaEntry sessionArea = new AreaEntry();
		sessionArea.setCityCode(area.getAreaCode());
		sessionArea.setCityName(area.getAreaName());
		this.getRequest().getSession().setAttribute(Global.SessionKey_Area, sessionArea);
		
		return this.redirect("/");
	}	
	
	
	/**
	 * 404页面 
	 * @return
	 */
	@RequestMapping("/404")
	public String page404() {
		return "404";
	}
	
	
	/**
	 * 500页面 
	 * @return
	 */
	@RequestMapping("/500")
	public String page500() {
		return "500";
	}
}
