package com.santrong.plt.weixin.mp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.santrong.plt.log.Log;
import com.santrong.plt.weixin.mp.thread.WeixinTokenThread;

/**
 * 初始化servlet
 * @author huangweihua
 * @date 2015年3月24日 下午6:12:29
 */
public class InitWXMPServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		// 获取web.xml中配置的参数
		WeixinTokenThread.appid = getInitParameter("appid");
		WeixinTokenThread.appsecret = getInitParameter("appsecret");

		Log.info("weixin api appid : " + WeixinTokenThread.appid);
		Log.info("weixin api appsecret : " + WeixinTokenThread.appsecret);

		// 未配置appid、appsecret时给出提示
		if ("".equals(WeixinTokenThread.appid) || "".equals(WeixinTokenThread.appsecret)) {
			Log.error("appid and appsecret configuration error, please check carefully.");
		} else {
			// 启动定时获取access_token的线程
			new Thread(new WeixinTokenThread()).start();
		}
	}
}
