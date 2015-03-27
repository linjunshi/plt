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
	 * @param confirmPwd
	 * @return
	 */
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String registPost(String gradeId, String username, String password, String confirmPwd, String email) {
		if (MyUtils.isNull(gradeId)) {
			addError("请您选择目前所属年级！");
			return "regist";
		}
		if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password) || StringUtils.isNullOrEmpty(confirmPwd) || StringUtils.isNullOrEmpty(email)) {
			addError("请您输入的用户名、密码和确认密码！");
			return "regist";
		}
		if(!password.equals(confirmPwd)) {
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
			user.setGradeId(gradeId);
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
				activeUrl.append("http://").append(Global.PltDomain).append("/");
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
		
		return this.redirect("/study/syllabus");
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
		
		// 获取需要跳转的页面
		String uri = (String)getRequest().getSession().getAttribute(Global.SessionKey_AfterLoginUri);
		if(uri == null) {
//			uri = "/";
//			uri = "/study/syllabus";
			uri = "/personal/center";
		}
		
		return this.redirect(uri);
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
			return this.redirect("/");
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
	 * 忘记密码
	 * @return
	 */
	@RequestMapping(value = "/forgotPwd", method=RequestMethod.GET)
	public String forgotPwd() {
		return "forgotPwd";
	}
	
	/**
	 * 忘记密码
	 * @return
	 */
	@RequestMapping(value = "/mailPwd", method=RequestMethod.POST)
	public String mailPwd(String email) {
		UserDao dao = new UserDao();
		UserItem user = dao.selectByEmail(email);
		
		if(user != null) {
			
			// 8位小写随机码
			StringBuilder newPwd = new StringBuilder();
			for(int i=0;i<8;i++) {
				char c =(char)((int)(Math.floor(Math.random() * 26)) + 'a');
				newPwd.append(c);
			}
			
			try{
				// 发邮件告知
				StringBuilder sb = new StringBuilder();
				sb.append("用户名是").append(user.getUsername()).append("</br/>");
				sb.append("新密码是").append(newPwd.toString()).append("</br/>");
				MailUtils.sendMail(user.getEmail(), "课云平台帐号新密码", sb.toString());
				
				// 数据库修改
				user.setPassword(MyUtils.getMD5(newPwd.toString()));
				if(dao.update(user) > 0) {
					return "newPwdSuccess";
				}
			}catch(Exception e) {
				Log.printStackTrace(e);
				this.addError("发生了错误");
			}
		}else {
			this.addError("邮箱不存在");
		}
		
		return "forgotPwd";
	}	
}
