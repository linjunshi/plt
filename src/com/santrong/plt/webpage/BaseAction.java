package com.santrong.plt.webpage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.santrong.plt.opt.SimpleTipItem;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.teacher.entry.UserItem;

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
	
	public enum RmCode {
		PASS,// 通过
		REQUIRE_LOGIN,// 需要登录
		REQUIRE_AUTH// 需要权限
	}
	
	// 控制器方法的前置方法
	public RmCode preMethod(HttpServletRequest request, HttpServletResponse response) {
		return RmCode.PASS;
	}
	
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
	
	@SuppressWarnings("unchecked")
	public final void addError(String msg) {
		if(MyUtils.isNotNull(msg)) {
			HttpServletRequest request = this.getRequest();
			List<SimpleTipItem> list = (ArrayList<SimpleTipItem>)request.getAttribute(Global.RequestKey_TipError);
			if(list == null) {
				list = new ArrayList<SimpleTipItem>();
			}
			SimpleTipItem tip = new SimpleTipItem();
			tip.setType(SimpleTipItem.Type_Error);
			tip.setMsg(msg);
			list.add(tip);
			request.setAttribute(Global.RequestKey_TipError, list);
		}
	}
	
	public final boolean getBooleanParameter(String param) {
		try{
			return Boolean.parseBoolean(this.getRequest().getParameter(param));
		}catch(Exception e) {}
		return false;
	}
	
	public final int getIntParameter(String param) {
		try{
			return Integer.parseInt(this.getRequest().getParameter(param));
		}catch(Exception e) {}
		return 0;
	}
}
