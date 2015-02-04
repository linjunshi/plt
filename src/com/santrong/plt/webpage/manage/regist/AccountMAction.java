package com.santrong.plt.webpage.manage.regist;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MailUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.ValidateTools;
import com.santrong.plt.webpage.manage.RegistBaseAction;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.dao.UserEducationDao;
import com.santrong.plt.webpage.teacher.dao.UserExtendsDao;
import com.santrong.plt.webpage.teacher.entry.UserEducationItem;
import com.santrong.plt.webpage.teacher.entry.UserExtendsItem;
import com.santrong.plt.webpage.teacher.entry.UserItem;
import com.santrong.plt.webpage.teacher.entry.UserTmpItem;

/**
 * @author weinianjie
 * @date 2014年11月25日
 * @time 下午3:53:11
 */
@Controller
@RequestMapping("/account")
public class AccountMAction extends RegistBaseAction {
	
	/**
	 * 重新发送激活邮件GET
	 * @param u
	 * @return
	 */
	@RequestMapping(value="/reactive", method=RequestMethod.GET)
	public String reActiveGet() {
		int rs = 1;
		
		// 激活过的帐号直接跳转
		UserItem user = this.currentUser();
		if(user.isStudent()) {
			rs = 10;
		}

		// 判断激活邮件发送间隔
		if(rs != 10) {
			UserDao dao = new UserDao();
			UserTmpItem tmp = dao.selectTmpByUserId(user.getId());
			if(tmp != null) {
				long t1 = tmp.getCts().getTime();
				long t2 = Calendar.getInstance().getTimeInMillis();
				long radius = (t2-t1)/1000/60;
				if(radius < 1) {// 1分钟以内不能重发
					rs = 0;
				}
			}
		}
		
		this.getRequest().setAttribute("rs", rs);
		
		return "manage/regist/reactive";
	}
	
	/**
	 * 重新发送激活邮件
	 * @param u
	 * @return
	 */
	@RequestMapping(value="/reactive", method=RequestMethod.POST)
	public String reActivePost() {
		UserDao dao = new UserDao();
		UserItem user = this.currentUser();
		
		if(!user.isStudent()) {// 激活成功过了就忽略
			UserTmpItem tmp = dao.selectTmpByUserId(user.getId());
			if(tmp != null) {
				long t1 = tmp.getCts().getTime();
				long t2 = Calendar.getInstance().getTimeInMillis();
				long radius = (t2-t1)/1000/60;
				if(radius >= 1) {// 1分钟以内不能重发
					tmp.setActiveCode(MyUtils.getGUID());
					tmp.setCts(new Date());
					dao.updateTmp(tmp);
					
					// 发送邮件
					try{
						StringBuilder activeUrl = new StringBuilder();
						activeUrl.append("http://").append(Global.PltDomain).append("/");
						if(MyUtils.isNotNull(this.getContext())) {
							activeUrl.append(this.getContext()).append("/");
						}
						activeUrl.append("account/active?u=").append(user.getUsername()).append("&a=").append(tmp.getActiveCode());

						StringBuilder sb = new StringBuilder();
						sb.append("请点击以下链接激活帐号（如不能点击请复制到浏览器地址栏打开）</br/>");
						sb.append("<a href=\"").append(activeUrl.toString()).append("\">").append(activeUrl.toString()).append("</a><br/>");
						sb.append("激活链接24小时有效");				
						MailUtils.sendMail(user.getEmail(), "课云平台帐号激活", sb.toString());
					}catch(Exception e) {
						Log.printStackTrace(e);
					}
					
				}
			}
		}
		
		return this.redirect("/account/reactive");// 所有结果都在这里显示
	}
	
	
	
	/**
	 * 打开修改密码页面
	 * @return
	 */
	@RequestMapping("/changePwd")
	public String changePsw() {
		return "manage/regist/pwdChange";
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
			// 获取当前用户对象信息
			UserItem user = this.currentUser();
			if (user == null) {
				// 没登陆，注意：异步的时候才这样子写，jquery对返回的结果作了判断
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
		return "manage/regist/pwdChange";
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
			request.setAttribute("flag", "personalInfo");
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/regist/personalInfo";
	}
	
	/**
	 * 修改个人信息
	 * @param userForm
	 * @return
	 */
	@RequestMapping(value="/personalInfo",method=RequestMethod.POST)
	public String personalInfoPost(UserItem userForm) {
		try {
			// 获取当前用户对象信息
			UserItem user = this.currentUser();
			if (user == null) {
				// 没登陆
				return this.redirect("/account/login");
			}
			if (userForm == null) {
				return "manage/regist/personalInfo";
			}
			if (MyUtils.isNull(userForm.getShowName())) {
				addError("真实姓名不允许为空！");
			}
			if (userForm.getGender() == 0) {
				addError("性别不允许为空！");
			}
			if (MyUtils.isNull(userForm.getGradeId())) {
				addError("年级不允许为空！");
			}
			if (MyUtils.isNull(userForm.getUsername())) {
				addError("昵称不允许为空！");
			}
			if (!ValidateTools.isIdCard(userForm.getIdCard())) {
				addError("身份证号码不正确！");
			}
			if (!ValidateTools.isPhoneNum(userForm.getPhone())) {
				addError("手机号码不正确！");
			}
			if (!ValidateTools.isEmail(userForm.getEmail())) {
				addError("邮箱地址格式不正确！");
			}
			if (errorSize() < 0) {
				//用户基本信息
				UserDao userDao = new UserDao();
				user.setGradeId(userForm.getGradeId());
				user.setUsername(userForm.getUsername());
				user.setGender(userForm.getGender());
				user.setShowName(userForm.getShowName());
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
			}
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("user", userForm);
			request.setAttribute("flag", "personalInfo");
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/regist/personalInfo";
	}
	
	/**
	 * 打开个人信息-->教育信息页面
	 * @return
	 */
	@RequestMapping("/personalInfoEdu")
	public String personalInfoEdu() {
		try {
			// 获取当前用户对象信息
			UserItem user = this.currentUser();
			if (user == null) {
				// 没登陆
				return this.redirect("/account/login");
			}
			
			UserEducationDao userEducationDao = new UserEducationDao();
			UserEducationItem userEducationItem = userEducationDao.selectByUserId(user.getId());
			
			HttpServletRequest request = getRequest();
			request.setAttribute("userEducation", userEducationItem);
			request.setAttribute("flag", "personalInfo");
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/regist/personalInfoEdu";
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
			request.setAttribute("flag", "personalInfo");
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/regist/personalInfoExtend";
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
			
			if (!ValidateTools.isDate(birthday)) {
				addError("出生日期格式不正确！");
			} else {
				if (MyUtils.isNotNull(birthday) || MyUtils.isNotNull(nativePlace)) {
					//用户扩展信息
					UserExtendsDao userExtendsDao = new UserExtendsDao();
					if (userExtendsDao.existsByUserId(user.getId())) {
						UserExtendsItem userExtendsItem = userExtendsDao.selectByUserId(user.getId());
						userExtendsItem.setUserId(user.getId());
						if (MyUtils.stringToDate(birthday, "yyyy-MM-dd") != null) {
							userExtendsItem.setBirthday(MyUtils.stringToDate(birthday, "yyyy-MM-dd"));
						}
						userExtendsItem.setNativePlace(nativePlace);
						if (userExtendsDao.update(userExtendsItem)) {
	//					getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
							addError("恭喜您，已成功个人信息！");
						} else {
							addError("对不起，您修改个人信息失败了！");
						}
					} else {
						UserExtendsItem userExtendsItem = new UserExtendsItem();
						userExtendsItem.setUserId(user.getId());
						userExtendsItem.setBirthday(MyUtils.stringToDate(birthday, "yyyy/MM/dd"));
						userExtendsItem.setNativePlace(nativePlace);
						if (userExtendsDao.insert(userExtendsItem)) {
	//					getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
							addError("恭喜您，已成功个人信息！");
						} else {
							addError("对不起，您修改个人信息失败了！");
						}
					}
				}
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/account/personalInfoExtend");
	}
	
}
