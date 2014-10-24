package com.santrong.plt.webpage.user;

import java.util.Date;

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
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年9月23日
 * @time 上午11:28:52
 */
@Controller
public class UserAction extends BaseAction {

	/**
	 * 获取注册页面
	 * @return
	 */
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public String regist() {
		
		return "regist";
	}
	
	/**
	 * 注册页面提交
	 * @param username
	 * @param password
	 * @param pwdagain
	 * @return
	 */
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String registPost(String username, String password, String pwdagain) {
		if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password) || StringUtils.isNullOrEmpty(pwdagain)) {
			return "regist";
		}
		if(!password.equals(pwdagain)) {
			return "regist";
		}
		
		UserDao userDao = new UserDao();
		if(userDao.existsByUserName(username)) {
			return "regist";
		}
		
		UserItem user = new UserItem();
		user.setId(MyUtils.getGUID());
		user.setShowName(username);
		user.setUsername(username);
		user.setPassword(MyUtils.getMD5(password));
		user.setUrl(null);
		user.setGender(0);
		user.setRole(UserItem.Role_Teacher + UserItem.Role_Student);
		user.setSchoolId(null);
		user.setSubjectId(null);
		user.setRegistIp(MyUtils.getRequestAddrIp(getRequest(), "127.0.0.1"));
		user.setRegistTime(new Date());
		user.setLastLoginIp(MyUtils.getRequestAddrIp(getRequest(), "127.0.0.1"));
		user.setLastLoginTime(new Date());
		user.setRemark(null);
		user.setCts(new Date());
		user.setUts(new Date());
		
		if(userDao.insert(user) <= 0) {
			return "regist";
		}
		
		getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
		
		return this.redirect("/study/course");
	}	
	
	/**
	 * 获取登录页面
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGet() {
		
		return "login";
	}
	
	/**
	 * 登录页面提交
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
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
		
		return this.redirect("/");
	}	
	
	/**
	 * 登录页面提交（异步）
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login2", method=RequestMethod.POST)
	@ResponseBody
	public String login2(String username, String password) {
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
	
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
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
		
		return this.redirect("/");
	}	
	
	/**
	 * 注销（异步）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout2", method=RequestMethod.POST)
	@ResponseBody
	public String logout2(HttpServletRequest request) {

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

}
