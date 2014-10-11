package com.santrong.plt.webpage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:21:18
 */
public abstract class BaseService {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public BaseService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
}
