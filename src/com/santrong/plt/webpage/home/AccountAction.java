package com.santrong.plt.webpage.home;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MailUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.dao.UserEducationDao;
import com.santrong.plt.webpage.teacher.dao.UserExtendsDao;
import com.santrong.plt.webpage.teacher.entry.UserDetailView;
import com.santrong.plt.webpage.teacher.entry.UserEducationItem;
import com.santrong.plt.webpage.teacher.entry.UserExtendsItem;
import com.santrong.plt.webpage.teacher.entry.UserItem;
import com.santrong.plt.webpage.teacher.entry.UserTmpItem;

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
	public String registPost(String username, String password, String pwdagain, String email) {
		if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password) || StringUtils.isNullOrEmpty(pwdagain) || StringUtils.isNullOrEmpty(email)) {
			addError("请您输入的用户名、密码和确认密码！");
			return "regist";
		}
		if(!password.equals(pwdagain)) {
			addError("您输入的密码和确认密码不一致！");
			return "regist";
		}
		
		UserDao userDao = new UserDao();
		if(userDao.existsByUserName(username)) {
			addError("用户名已经被注册！");
			return "regist";
		}
		
		if(userDao.existsByEmail(email)) {
			addError("邮箱已经被注册！");
			return "regist";
		}		
		
		try{
			ThreadUtils.beginTranx();
			
			UserItem user = new UserItem();
			user.setId(MyUtils.getGUID());
			user.setShowName(username);
			user.setUsername(username);
			user.setPassword(MyUtils.getMD5(password));
			user.setUrl(null);
			user.setGender(0);
//			user.setRole(UserItem.Role_Regist | UserItem.Role_Student);
			user.setRole(UserItem.Role_Regist);
			user.setSchoolId(null);
			user.setSubjectId(null);
			user.setEmail(email);
			user.setPhone(null);
			user.setRegistIp(MyUtils.getRequestAddrIp(getRequest(), "127.0.0.1"));
			user.setRegistTime(new Date());
			user.setLastLoginIp(MyUtils.getRequestAddrIp(getRequest(), "127.0.0.1"));
			user.setLastLoginTime(new Date());
			user.setRemark(null);
			user.setCts(new Date());
			user.setUts(new Date());
			
			UserTmpItem tmp = new UserTmpItem();
			tmp.setUserId(user.getId());
			tmp.setActiveCode(MyUtils.getGUID());
			tmp.setCts(new Date());
			
			if(userDao.insert(user) <= 0 || userDao.insertTmp(tmp) <= 0) {
				ThreadUtils.rollbackTranx();
				return "regist";
			}
			
			ThreadUtils.commitTranx();
			getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
		
			// 发送邮件
			try{
				StringBuilder activeUrl = new StringBuilder();
				activeUrl.append("http://").append(this.getRequest().getRemoteAddr()).append("/");
				if(MyUtils.isNotNull(this.getContext())) {
					activeUrl.append(this.getContext()).append("/");
				}
				activeUrl.append("account/active?u=").append(username).append("&a=").append(tmp.getActiveCode());

				StringBuilder sb = new StringBuilder();
				sb.append("请点击以下链接激活帐号（如不能点击请复制到浏览器地址栏打开）</br/>");
				sb.append("<a href=\"").append(activeUrl.toString()).append("\">").append(activeUrl.toString()).append("</a><br/>");
				sb.append("激活链接24小时有效");				
				MailUtils.sendMail(email, "课云平台帐号激活", sb.toString());
			}catch(Exception e) {
				Log.printStackTrace(e);
			}
		
		}catch(Exception e) {
			ThreadUtils.rollbackTranx();
			Log.printStackTrace(e);
		}
		
		return this.redirect("/study/course");
	}	
	
	/**
	 * 帐号激活
	 * @param u
	 * @param a
	 * @return
	 */
	@RequestMapping(value="/active", method=RequestMethod.GET)
	public String active(String u, String a) {
		int rs = 0;
		UserDao dao = new UserDao();
		UserItem user = dao.selectByUserName(u);
		
		if(user != null) {
			UserTmpItem tmp = dao.selectTmpByUserId(user.getId());
			if(tmp != null) {
				if(tmp.getActiveCode().equals(a)) {
					long t1 = tmp.getCts().getTime();
					long t2 = Calendar.getInstance().getTimeInMillis();
					long radius = (t2-t1)/1000/3600;
					if(radius < 24) {// 24小时内激活
						user.setRole(user.getRole() | UserItem.Role_Student);// 加上学生权限
						if(dao.update(user) > 0) {
							getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);// 标识登录状态
							rs = 1;
						}
					}
				}
			}
		}
		
		this.getRequest().setAttribute("rs", rs);
		
		return "/manage/regist/activeResult";
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
			
			UserItem user = this.currentUser();
			if(user == null) {
				return this.redirect("/account/login");
			}
			
			if (MyUtils.isNull(oldPwd)) {
				addError("请输入您的原始密码！");
			}
			if (MyUtils.isNull(newPwd)) {
				addError("请输入您的新密码！");
			}
			if (MyUtils.isNull(comfirmPwd)) {
				addError("请输入您的确认新密码！");
			}
			if(!user.getPassword().equals(MyUtils.getMD5(oldPwd))) {
				addError("您输入的原始密码有误，请重新输入！");
			}
			if(!newPwd.equals(comfirmPwd)) {
				addError("您输入的新密码和确认新密码不一致，请重新输入！");
			}
			if (!(errorSize() > 0)) {
				UserDao userDao = new UserDao();
				user.setPassword(MyUtils.getMD5(newPwd));
				user.setUts(new Date());
				if (userDao.update(user) > 0) {
					getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
					addError("恭喜您，已成功修改密码！");
				} else {
					addError("对不起，您修改密码失败了，请重新操作！");
				}
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
	 * 打开个人信息-->基本信息页面
	 * @return
	 */
	@RequestMapping("/personalInfo")
	public String personalInfo() {
		try {
			UserItem user = this.currentUser();
			if (user== null) {
				return this.redirect("/account/login");
			}
//			UserDao userDao = new UserDao();
//			UserDetailView userDetailView = userDao.selectDetailById(user.getId());
			
			HttpServletRequest request = getRequest();
			request.setAttribute("user", user);
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/personalInfo";
	}
	
	@RequestMapping(value="/personalInfo",method=RequestMethod.POST)
	public String personalInfoPost(UserItem userForm) {
		try {
			UserItem user = this.currentUser();
			if (user== null) {
				return this.redirect("/account/login");
			}
			if (userForm == null) {
				return "manage/personalInfo";
			}
			
			//用户基本信息
			UserDao userDao = new UserDao();
			user.setShowName(userForm.getShowName());
			user.setGender(userForm.getGender());
			user.setUsername(userForm.getUsername());
			user.setIdCard(userForm.getIdCard());
			user.setPhone(userForm.getPhone());
			user.setEmail(userForm.getEmail());
			user.setUrl(userForm.getUrl());
			user.setRemark(userForm.getRemark());
			user.setUts(new Date());
			if (userDao.update(user) > 0) {
				getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
//					addError("恭喜您，已成功个人信息！");
			} else {
				addError("对不起，您修改个人信息失败了！");
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/account/personalInfo");
	}
	
	/**
	 * 打开个人信息-->教育信息页面
	 * @return
	 */
	@RequestMapping("/personalInfoEdu")
	public String personalInfoEdu() {
		try {
			UserItem user = this.currentUser();
			if (user== null) {
				return this.redirect("/account/login");
			}
			
			UserEducationDao userEducationDao = new UserEducationDao();
			UserEducationItem userEducationItem = userEducationDao.selectByUserId(user.getId());
			
			HttpServletRequest request = getRequest();
			request.setAttribute("userEducation", userEducationItem);
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/personalInfoEdu";
	}
	
	@RequestMapping(value="/personalInfoEdu",method=RequestMethod.POST)
	public String personalInfoEduPost(UserEducationItem userEduForm) {
		try {
			UserItem user = this.currentUser();
			if (user== null) {
				return this.redirect("/account/login");
			}
			
			//用户教育信息
			UserEducationDao uEduDao = new UserEducationDao();
			UserEducationItem uEduItem = new UserEducationItem();
			if (uEduDao.existByUserId(user.getId())) {
				uEduItem.setUserId(user.getId());
				uEduItem.setEducation(userEduForm.getEducation());
				uEduItem.setPositional(userEduForm.getPositional());
				uEduItem.setGraduateSchool(userEduForm.getGraduateSchool());
				if (uEduDao.update(uEduItem)) {
//					getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
					addError("恭喜您，已成功个人信息！");
				} else {
					addError("对不起，您修改个人信息失败了！");
				}
			} else {
				uEduItem.setUserId(user.getId());
				uEduItem.setEducation(userEduForm.getEducation());
				uEduItem.setPositional(userEduForm.getPositional());
				uEduItem.setGraduateSchool(userEduForm.getGraduateSchool());
				if (uEduDao.insert(uEduItem)) {
//					getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
					addError("恭喜您，已成功个人信息！");
				} else {
					addError("对不起，您修改个人信息失败了！");
				}
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/account/personalInfoEdu");
	}
	
	/**
	 * 打开个人信息-->扩展信息页面
	 * @return
	 */
	@RequestMapping("/personalInfoExtend")
	public String personalInfoExtend() {
		try {
			UserItem user = this.currentUser();
			if (user== null) {
				return this.redirect("/account/login");
			}
			
			UserExtendsDao userExtendsDao = new UserExtendsDao();
			UserExtendsItem userExtendsItem = userExtendsDao.selectByUserId(user.getId());
			
			HttpServletRequest request = getRequest();
			request.setAttribute("userExtends", userExtendsItem);
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/personalInfoExtend";
	}
	
	@RequestMapping(value="/personalInfoExtend",method=RequestMethod.POST)
	public String personalInfoExtendPost() {
		try {
			UserItem user = this.currentUser();
			if (user== null) {
				return this.redirect("/account/login");
			}
			HttpServletRequest request = getRequest();
			String birthday = request.getParameter("birthday");
			String nativePlace = request.getParameter("nativePlace");
			
			if (MyUtils.isNotNull(birthday) || MyUtils.isNotNull(nativePlace)) {
				//用户扩展信息
				UserExtendsDao userExtendsDao = new UserExtendsDao();
				UserExtendsItem userExtendsItem = new UserExtendsItem();
				if (userExtendsDao.existsByUserId(user.getId())) {
					userExtendsItem.setUserId(user.getId());
					userExtendsItem.setBirthday(MyUtils.stringToDate(birthday, "yyyy-MM-dd HH:mm:ss"));
					userExtendsItem.setNativePlace(nativePlace);
					if (userExtendsDao.update(userExtendsItem)) {
//					getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
						addError("恭喜您，已成功个人信息！");
					} else {
						addError("对不起，您修改个人信息失败了！");
					}
				} else {
					userExtendsItem.setUserId(user.getId());
					userExtendsItem.setBirthday(MyUtils.stringToDate(birthday, "yyyy-MM-dd HH:mm:ss"));
					userExtendsItem.setNativePlace(nativePlace);
					if (userExtendsDao.insert(userExtendsItem)) {
//					getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
						addError("恭喜您，已成功个人信息！");
					} else {
						addError("对不起，您修改个人信息失败了！");
					}
				}
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/account/personalInfoExtend");
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
			UserItem user = this.currentUser();
			if (user== null) {
				return this.redirect("/account/login");
			}
			if (userDetail == null) {
				return "manage/personalInfo";
			}
			
			HttpServletRequest request = getRequest();
			String fn = request.getParameter("fn");
			
			if (fn.equals("base")) {
				//用户基本信息
				UserDao userDao = new UserDao();
				user.setShowName(userDetail.getShowName());
				user.setGender(userDetail.getGender());
				user.setUsername(userDetail.getUsername());
				user.setIdCard(userDetail.getIdCard());
				user.setPhone(userDetail.getPhone());
				user.setEmail(userDetail.getEmail());
				user.setUrl(userDetail.getUrl());
				user.setRemark(userDetail.getRemark());
				user.setUts(new Date());
				if (userDao.update(user) > 0) {
					getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
					addError("恭喜您，已成功个人信息！");
				} else {
					addError("对不起，您修改个人信息失败了！");
				}
				return this.redirect("/account/personalInfo");
			} else if (fn.equals("education")) {
				//用户教育信息
				UserEducationDao uEduDao = new UserEducationDao();
				UserEducationItem uEduItem = new UserEducationItem();
				if (uEduDao.existByUserId(user.getId())) {
					uEduItem.setUserId(userDetail.getId());
					uEduItem.setEducation(userDetail.getEducation());
					uEduItem.setPositional(userDetail.getPositional());
					uEduItem.setGraduateSchool(userDetail.getGraduateSchool());
					if (uEduDao.update(uEduItem)) {
//						getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
						addError("恭喜您，已成功个人信息！");
					} else {
						addError("对不起，您修改个人信息失败了！");
					}
					return this.redirect("/account/personalInfoEdu");
				} else {
					uEduItem.setUserId(userDetail.getId());
					uEduItem.setEducation(userDetail.getEducation());
					uEduItem.setPositional(userDetail.getPositional());
					uEduItem.setGraduateSchool(userDetail.getGraduateSchool());
					if (uEduDao.insert(uEduItem)) {
//						getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
						addError("恭喜您，已成功个人信息！");
					} else {
						addError("对不起，您修改个人信息失败了！");
					}
					return this.redirect("/account/personalInfoEdu");
				}
			} else if (fn.equals("extend")) {
				//用户扩展信息
				UserExtendsDao userExtendsDao = new UserExtendsDao();
				UserExtendsItem userExtendsItem = new UserExtendsItem();
				if (userExtendsDao.existsByUserId(user.getId())) {
					userExtendsItem.setUserId(user.getId());
					userExtendsItem.setBirthday(userDetail.getBirthday());
					userExtendsItem.setNativePlace(userDetail.getNativePlace());
					if (userExtendsDao.update(userExtendsItem)) {
//						getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
						addError("恭喜您，已成功个人信息！");
					} else {
						addError("对不起，您修改个人信息失败了！");
					}
					return this.redirect("/account/personalInfoExtend");
				} else {
					userExtendsItem.setUserId(user.getId());
					userExtendsItem.setBirthday(userDetail.getBirthday());
					userExtendsItem.setNativePlace(userDetail.getNativePlace());
					if (userExtendsDao.insert(userExtendsItem)) {
//						getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
						addError("恭喜您，已成功个人信息！");
					} else {
						addError("对不起，您修改个人信息失败了！");
					}
					return this.redirect("/account/personalInfoExtend");
				}
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/account/personalInfo");
	}
	
}
