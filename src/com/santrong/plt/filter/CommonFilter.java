package com.santrong.plt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.TaobaoAreaEntry;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @Author weinianjie
 * @Date 2014-7-9
 * @Time 下午12:08:20
 */
public class CommonFilter implements Filter{

	private String[] passUrls;
	
	@Override
	public void destroy() {
		
	}


	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		ThreadUtils.setHttpRequest(request);
		ThreadUtils.setHttpResponse(response);
		
		String url = request.getRequestURI();
		if (url != null) {
			
			// 记录用户访问(过滤掉接口的请求)
			if(!url.startsWith("/http/basic.action")) {
				Log.logRequest(request);
			}
			
			boolean pass = false;
			
			// 是否是开发目录
			if(url.startsWith("/dev/")) {
				pass = true;
			}
			
			// 是否是配置的免登陆页面
			if (passUrls != null) {
				
				for (String suffix : passUrls) {
					if (url.endsWith(suffix)) {
						pass = true;
						break;
					}
				}
			}
			
			// 直接访问的jsp页面，除非配置在web.xml里，其余全部拦截
			// spring把视图移交给jsp页面时候经过servlet拦截，但是不经过filter拦截，所以不会导致无法移交视图
			if(!pass && url.endsWith(".jsp")) {
				response.sendRedirect("/login.action");
			}
//			Log.debug("-----------------:" + url);
			
			// 不是免登录的页面，开始校验登录状态
			if (!pass) {
				UserItem loginUser = (UserItem)request.getSession().getAttribute(Global.SessionKey_LoginUser);
				if(loginUser == null) {
					response.sendRedirect("/login.action");
				}
			}
		}
		
		// 获取用户地理信息
		if(request.getSession().getAttribute(Global.SessionKey_AreaCode) == null) {
			String clientIp = "183.17.255.255";//MyUtils.getRequestAddrIp(request, null);
			String areaCode = Global.AreaCode;
			String city = Global.City;
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
			request.getSession().setAttribute(Global.SessionKey_AreaCode, areaCode);
			request.getSession().setAttribute(Global.SessionKey_City, city);
		}
		
		chain.doFilter(req, resp);
		
		/*
		 * ---------以下是后置执行-------------
		 */
		
		ThreadUtils.closeAll();
	}


	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		String configPassUrls = fConfig.getInitParameter("passUrls");
		if (configPassUrls != null) {
			configPassUrls = configPassUrls.replaceAll("[\\s]", "");
			passUrls = configPassUrls.split(",");
		}
	}

}
