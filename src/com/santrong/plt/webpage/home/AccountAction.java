package com.santrong.plt.webpage.home;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.log.Log;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年11月6日
 * @time 下午4:29:21
 */
@Controller
@RequestMapping("/account")
public class AccountAction extends BaseAction {

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
			addError("请您输入的用户名、密码和确认密码！");
			return "regist";
		}
		if(!password.equals(pwdagain)) {
			addError("您输入的密码和确认密码不一致！");
			return "regist";
		}
		
		UserDao userDao = new UserDao();
		if(userDao.existsByUserName(username)) {
			addError("您输入的用户名已经存在！");
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
			addError("请您输入用户名和密码");
			return "login";
		}
		
		UserDao userDao = new UserDao();
		UserItem user = userDao.selectByUserName(username);
		
		if(user == null) {
			addError("您输入的用户名不存在！");
			return "login";
		}
		
		if(!user.getPassword().equals(MyUtils.getMD5(password))) {
			addError("您输入的密码有误，请重新输入！");
			return "login";
		}

		getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
		
		return this.redirect("/");
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
	
	@RequestMapping("/changePwd")
	public String changePsw() {
		
		return "/manage/pwChange";
	}
}
