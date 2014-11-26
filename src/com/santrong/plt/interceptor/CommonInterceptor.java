package com.santrong.plt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.opt.area.AreaService;
import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.BaseAction.RmCode;

/**
 * @Author weinianjie
 * @Date 2014-7-9
 * @Time 下午12:08:20
 */
public class CommonInterceptor implements HandlerInterceptor{

	/**
	 * 找到控制器才会执行，且在执行控制器之前，返回true才会执行下一个拦截器或者控制器
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		ThreadUtils.setHttpRequest(request);
		ThreadUtils.setHttpResponse(response);		
		
		// 登录和角色权限判断，数据权限在各自方法中判断
		if(handler instanceof HandlerMethod){
			
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			if(handlerMethod.getBean() instanceof BaseAction) {
				
				BaseAction action = (BaseAction)handlerMethod.getBean();
				RmCode code = action.preMethod(request, response);
				switch(code) {
				case REQUIRE_LOGIN :
				response.sendRedirect(request.getContextPath() + "/account/login"); // 跳到登录页面
				return false;
				
				case REQUIRE_REGIST_AUTH :
				response.sendRedirect(request.getContextPath() + "/deny"); // 跳到没有权限页面
				return false;
				
				case REQUIRE_STUDENT_AUTH :
				response.sendRedirect(request.getContextPath() + "/account/reactive"); // 跳到重发激活邮件页面
				return false;				
				
				case REQUIRE_TEACHER_AUTH :
				response.sendRedirect(request.getContextPath() + "/apply/teacher"); // 跳到老师申请页面		
				return false;
				
				case REQUIRE_AUTH :
				response.sendRedirect(request.getContextPath() + "/deny"); // 跳到没有权限页面				
				return false;
				
				default:
					break;
				}
			}
		}
		
		// URL特殊处理
		String url = request.getRequestURI();
		if (url != null) {
			
			// 记录用户访问(过滤掉接口的请求)
			if(!url.startsWith("/http/basic.action")) {
				Log.logRequest(request);
				
				// 获取用户地理信息
				if(request.getSession().getAttribute(Global.SessionKey_Area) == null) {
					String clientIp = "183.17.255.255";//MyUtils.getRequestAddrIp(request, null);
					AreaService areaService= new AreaService();
//					AreaEntry area = areaService.getAreaByTaobao(clientIp);
					AreaEntry area = areaService.getIpAreaByCz88(clientIp);
					request.getSession().setAttribute(Global.SessionKey_Area, area);
				}				
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * 移交给视图渲染之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
	}	
	
	/**
	 * 视图渲染后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3) throws Exception {
		ThreadUtils.closeAll();
	}

}
