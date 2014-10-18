package com.santrong.plt.webpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @Author weinianjie
 * @Date 2014-7-4
 * @Time 下午4:38:21
 */
public abstract class BaseAction {
	protected final static String SUCCESS = "success";
	protected final static String FAIL = "fail";
	protected final static String ERROR_PARAM = "error_param";
	
	protected final static String Redirect = "redirect:";
	
	
	public final UserItem currentUser() {
		UserItem user = (UserItem)ThreadUtils.currentHttpRequest().getSession().getAttribute(Global.SessionKey_LoginUser);
		return user;
	}
	
	public final HttpServletRequest getRequest() {
		return ThreadUtils.currentHttpRequest();
	}
	
	public final HttpServletResponse getResponse() {
		return ThreadUtils.currentHttpResponse();
	}
	
	public final String getContext() {
		return getRequest().getContextPath();
	}
	
	public final String redirect(String url) {
		return Redirect + getContext() + url;
	}
}
