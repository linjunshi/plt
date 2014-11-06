package com.santrong.plt.webpage.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年11月6日
 * @time 下午4:43:34
 */
public class TeacherBaseAction extends BaseAction {
	
	/**
	 * 前置执行方法
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public final void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserItem user = (UserItem)request.getSession().getAttribute(Global.SessionKey_LoginUser);
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/account/login"); // 跳到登录页面
			return;
		}
		
		if(!user.isTeacher()) {
			response.sendRedirect(request.getContextPath() + "/account/login"); // 跳到没有权限页面
			return;
		}
	}
}
