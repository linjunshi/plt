package com.santrong.plt.webpage.home;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.log.Log;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.home.dao.GradeDao;
import com.santrong.plt.webpage.home.dao.SchoolDao;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.SchoolQuery;
import com.santrong.plt.webpage.home.entry.SchoolView;
import com.santrong.plt.webpage.home.entry.SubjectItem;
import com.santrong.plt.webpage.home.entry.SubjectView;
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @Author weinianjie
 * @Date 2014-7-10
 * @Time 下午10:05:54
 */
@Controller
public class HomeAction extends BaseAction{
	
	@RequestMapping("/index")
	public String index(){
		
			Object u = getRequest().getSession().getAttribute(Global.SessionKey_LoginUser);
			if(u != null) {
				UserItem user = (UserItem)u;
				getRequest().setAttribute("user", user);
			}

			
			GradeDao gradeDao = new GradeDao();
			List<SubjectView> subjectView = gradeDao.selectGrade();
			
			// 获取科目
			SubjectDao subjectDao = new SubjectDao();
			for(int i=0;i<subjectView.size();i++) {
				List<SubjectItem> subjectList = subjectDao.selectByGradeGroup(subjectView.get(i).getGradeGroup());
				subjectView.get(i).setSubjectList(subjectList);
			}
			getRequest().setAttribute("subjectView", subjectView);
			
			// 获取学校
			SchoolDao schoolDao = new SchoolDao();
			SchoolQuery schoolQuery = new SchoolQuery();
			schoolQuery.setAreaCode((String)(getRequest().getSession().getAttribute(Global.SessionKey_AreaCode)));
			List<SchoolView> schoolView = new ArrayList<SchoolView>();
			for(int i=0;i<subjectView.size();i++) {
				SchoolView view = new SchoolView();
				view.setGradeGroup(subjectView.get(i).getGradeGroup());
				view.setGradeName(subjectView.get(i).getGradeName());
				
				schoolQuery.setSchoolGrade(view.getGradeGroup());
				view.setSchoolList(schoolDao.selectByQuery(schoolQuery));
				schoolView.add(view);
			}
			getRequest().setAttribute("schoolView", schoolView);

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
	public String logout() {
		HttpServletRequest request = getRequest();

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
		return "404";
	}
}
