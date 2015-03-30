package com.santrong.plt.weixin.mp.thread;

import com.santrong.plt.log.Log;
import com.santrong.plt.system.Global;
import com.santrong.plt.weixin.mp.entry.WeixinAccessTokenEntry;
import com.santrong.plt.weixin.mp.entry.WeixinJsApiTicketEntry;
import com.santrong.plt.weixin.mp.util.WeixinUtil;

/**
* 定时获取微信access_token的线程
*
* @author huangweihua
* @date 2015年3月24日 下午5:58:50
*/
public class WeixinTokenThread implements Runnable{
	
	// 第三方用户唯一凭证
	public static String appid = "";
	// 第三方用户唯一凭证密钥
	public static String appsecret = "";
//	public static AccessTokenEntry accessToken = null;
//	public static JsApiTicketEntry jsapiticket = null;
	
	//记录系统启动开始，获取了access_token的次数
	public static int getTokenCount = 0;
	
	@SuppressWarnings("unused")
	@Override
	public void run() {
		while (true) {
			try {
				// 获取access_token的对象
				WeixinAccessTokenEntry accessToken = WeixinUtil.getAccessToken(appid, appsecret);
				// 获取jsapi_ticket的对象
				WeixinJsApiTicketEntry jsapiticket = WeixinUtil.getJsApiTicket(accessToken.getAccess_token());
				
				if (accessToken != null) {
					Global.appid = appid;
					Global.appsecret = appsecret;
					Global.accessToken = accessToken;
					Global.jsapiticket = jsapiticket;
					
					Log.info("记录系统启动开始，获取了access_token的次数  : " + (++getTokenCount));
					Log.info("获取access_token成功，有效时长" + accessToken.getExpires_in() +"秒，access_token : "+ accessToken.getAccess_token() );
					Log.info("获取jsapi_ticket成功，有效时长" + jsapiticket.getExpires_in() +"秒，ticket : "+ jsapiticket.getTicket() );
					
					// 休眠7000秒
					Thread.sleep((accessToken.getExpires_in() - 200) * 1000);
					Thread.sleep((jsapiticket.getExpires_in() - 200) * 1000);
				} else {
					// 如果access_token为null，60秒后再获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					Log.error(e1);
				}
				Log.error(e);
			}
		}
	}
}
