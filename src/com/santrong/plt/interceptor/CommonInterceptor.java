package com.santrong.plt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.TaobaoAreaEntry;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;

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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		ThreadUtils.setHttpRequest(request);
		ThreadUtils.setHttpResponse(response);
		
		String url = request.getRequestURI();
		if (url != null) {
			
			// 记录用户访问(过滤掉接口的请求)
			if(!url.startsWith("/http/basic.action")) {
				Log.logRequest(request);
			}
			
		}
		
		// 获取用户地理信息
		if(request.getSession().getAttribute(Global.SessionKey_AreaCode) == null) {
			String areaCode = Global.AreaCode;
			String city = Global.City;
			try{
				String clientIp = "183.17.255.255";//MyUtils.getRequestAddrIp(request, null);
				if(clientIp != null) {
					String areaServerAddr = "http://ip.taobao.com/service/getIpInfo.php";
					String json = MyUtils.getRemoteContent(areaServerAddr + "?ip=" + clientIp);
					if(MyUtils.isNotNull(json)) {
						Gson gson = new Gson();
						TaobaoAreaEntry entry = gson.fromJson(json, TaobaoAreaEntry.class);
						if(entry.getCode() == 0) {
							areaCode = entry.getData().getCity_id();
							city = entry.getData().getCity();
						}
					}
					
				}
			}catch(Exception e) {
				Log.printStackTrace(e);
			}finally {
				request.getSession().setAttribute(Global.SessionKey_AreaCode, areaCode);
				request.getSession().setAttribute(Global.SessionKey_City, city);
			}
		}
		
		return true;
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
