package com.santrong.plt.webpage.manage.regist;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.util.MailUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.manage.RegistBaseAction;
import com.santrong.plt.webpage.teacher.dao.UserDao;
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
						activeUrl.append("http://").append(this.getRequest().getRemoteAddr()).append("/");
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
}
