package com.santrong.plt.webpage.home;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.dao.UserEducationDao;
import com.santrong.plt.webpage.teacher.dao.UserExtendsDao;
import com.santrong.plt.webpage.teacher.entry.UserDetailView;
import com.santrong.plt.webpage.teacher.entry.UserEducationItem;
import com.santrong.plt.webpage.teacher.entry.UserExtendsItem;
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
	
	/**
	 * 打开修改密码页面
	 * @return
	 */
	@RequestMapping("/changePwd")
	public String changePsw() {
		return "manage/pwdChange";
	}
	
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @param comfirmPwd
	 * @return
	 */
	@RequestMapping(value = "/changePwdPost",method=RequestMethod.POST)
	public String changePwdPost(String oldPwd, String newPwd, String comfirmPwd) {
		try {
			if (!MyUtils.isNotNull(oldPwd)) {
				addError("请输入您的原始密码！");
				return "manage/pwdChange";
			}
			if (!MyUtils.isNotNull(newPwd)) {
				addError("请输入您的新密码！");
				return "manage/pwdChange";
			}
			if (!MyUtils.isNotNull(comfirmPwd)) {
				addError("请输入您的确认新密码！");
				return "manage/pwdChange";
			}
			
			UserItem user = this.currentUser();
			
			if(user == null) {
				return "login";
			}
			if(!user.getPassword().equals(MyUtils.getMD5(oldPwd))) {
				addError("您输入的原始密码有误，请重新输入！");
				return "manage/pwdChange";
			}
			if(!newPwd.equals(comfirmPwd)) {
				addError("您输入的新密码和确认新密码不一致，请重新输入！");
				return "manage/pwdChange";
			}
			
			UserDao userDao = new UserDao();
			user.setPassword(MyUtils.getMD5(newPwd));
			user.setUts(new Date());
			if (userDao.update(user) > 0) {
				getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
				addError("恭喜您，已成功修改密码！");
			} else {
				addError("对不起，您修改密码失败了，请重新操作！");
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/pwdChange";
	}
	
	/**
	 * 忘记密码
	 * @return
	 */
	@RequestMapping("/forgotPwd")
	public String forgotPwd() {
		return "manage/forgotPwd";
	}
	
	/**
	 * 打开个人信息页面
	 * @return
	 */
	@RequestMapping("/personalInfo")
	public String personalInfo() {
		try {
			UserItem user = this.currentUser();
			if (user== null) {
				return "login";
			}
			UserDao userDao = new UserDao();
			UserDetailView userDetailView = userDao.selectDetailById(user.getId());
			
			HttpServletRequest request = getRequest();
			request.setAttribute("userDetailView", userDetailView);
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/personalInfo";
	}
	
	/**
	 * 修改个人信息，包括基本信息、教育信息、其他扩展信息
	 * @author		huangweihua
	 * @datetime    2014年11月14日 上午11:36:49
	 * @param 		userDetail
	 * @return
	 */
	@RequestMapping(value = "/changePslInfo", method=RequestMethod.POST)
	public String changePslInfo(UserDetailView userDetail) {
		try {
			int success = 0;
			if (userDetail == null) {
				return "manage/personalInfo";
			}
			
			UserItem user = this.currentUser();
			
			ThreadUtils.beginTranx();
			
			UserDao userDao = new UserDao();
			user.setUsername(userDetail.getUsername());
			user.setShowName(userDetail.getShowName());
			user.setGender(userDetail.getGender());
			user.setRemark(userDetail.getRemark());
			user.setUts(new Date());
			userDao.update(user);
			
			UserEducationDao uEduDao = new UserEducationDao();
			UserEducationItem uEduItem = new UserEducationItem();
			if (uEduDao.existByUserId(user.getId())) {
				uEduItem.setUserId(userDetail.getId());
				uEduItem.setEducation(userDetail.getEducation());
				uEduItem.setPositional(userDetail.getPositional());
				uEduItem.setGraduateSchool(userDetail.getGraduateSchool());
				uEduDao.update(uEduItem);
			} else {
				uEduItem.setUserId(userDetail.getId());
				uEduItem.setEducation(userDetail.getEducation());
				uEduItem.setPositional(userDetail.getPositional());
				uEduItem.setGraduateSchool(userDetail.getGraduateSchool());
				uEduDao.insert(uEduItem);
			}
			
			UserExtendsDao userExtendsDao = new UserExtendsDao();
			UserExtendsItem userExtendsItem = new UserExtendsItem();
			if (userExtendsDao.existsByUserId(user.getId())) {
				userExtendsItem.setUserId(user.getId());
				userExtendsItem.setBirthday(userDetail.getBirthday());
				userExtendsItem.setNativePlace(userDetail.getNativePlace());
				userExtendsDao.update(userExtendsItem);
			} else {
				userExtendsItem.setUserId(user.getId());
				userExtendsItem.setBirthday(userDetail.getBirthday());
				userExtendsItem.setNativePlace(userDetail.getNativePlace());
				userExtendsDao.insert(userExtendsItem);
			}
			
			success = 1;
			ThreadUtils.commitTranx();
			
			if (success == 1) {
				getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
				addError("恭喜您，已成功个人信息！");
			} else {
				addError("对不起，您修改个人信息失败了！");
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/account/personalInfo");
	}
	
}
