package com.santrong.plt.weixin.mp.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.santrong.plt.log.Log;
import com.santrong.plt.system.Global;
import com.santrong.plt.weixin.mp.entry.WeixinAccessTokenEntry;
import com.santrong.plt.weixin.mp.entry.WeixinJsApiTicketEntry;

/**
 * @author huangweihua
 * @Date 2015年3月18日 
 * @Time 上午9:32:30
 */
public class WeixinUtil {

	// 获取access_token的接口地址（GET） 限2000（次/天）  有效期7200秒
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
    // 获取jsapi_ticket的接口地址（GET） 限2000（次/天）有效期7200秒  
    public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    
//	public static void main(String[] args) {
//        String jsapi_ticket = "jsapi_ticket";
//
//        // 注意 URL 一定要动态获取，不能 hardcode
//        String url = "http://127.0.0.1/weixin/demo";
//        
//        Map<String, String> ret = sign(jsapi_ticket, url);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
//
//    };

    public static Map<String, String> getSignPackage(String url) {
        Map<String, String> ret = new HashMap<String, String>();
        
        String appId = Global.appid;
        String jsapi_ticket = Global.jsapiticket.getTicket();
        String nonce_str = create_nonce_str();// 必填，生成签名的随机串
        String timestamp = create_timestamp();// 必填，生成签名的时间戳
        String string1;
        String signature = "";// 必填，签名，见附录1
        
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        ret.put("url", url);
        ret.put("appId", appId);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("timestamp", timestamp);
        ret.put("nonceStr", nonce_str);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    /** 
     * 获取access_token <br>
     * 公众号可以使用AppID和AppSecret调用本接口来获取access_token。AppID和AppSecret可在微信公众平台官网-开发者中心页中获得（需要已经成为开发者，且帐号没有异常状态）。注意调用所有微信接口时均需使用https协议。
     * <br>1、http请求方式: GET <br>
     * <b>https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET</b><br><br>
     * 2、正常情况下，微信会返回下述JSON数据包给公众号：<br>
     * <b>{"access_token":"ACCESS_TOKEN","expires_in":7200}</b><br><br>
     * 3、错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:<br>
     * <b>{"errcode":40013,"errmsg":"invalid appid"}</b><br>
     * @param appid 凭证 
     * @param appsecret 密钥 
     * @return 
     */  
    public static WeixinAccessTokenEntry getAccessToken(String appid, String appsecret) {  
    	WeixinAccessTokenEntry accessToken = null;
    	Gson gson = new Gson();
    	
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
        String json = httpRequest(requestUrl, "GET", null);  
        // 如果请求成功  
        if (null != json) {
            try {
                accessToken = gson.fromJson(json, WeixinAccessTokenEntry.class);//取值的时候就从父类一层一层调子类成员（重要）
            } catch (JsonParseException e) {
                // 获取token失败
            	accessToken = null;
                Log.error("获取access_token失败 ,返回的JSON字符串：" + json);
            }  
        }  
        return accessToken;
    }
    
    /**
     * 获取权限签名jsapi_ticket
     * @param accessToken 公众号的全局唯一票据
     * @return
     */
    public static WeixinJsApiTicketEntry getJsApiTicket(String accessToken) {
    	WeixinJsApiTicketEntry jsapiTicket = null;
    	Gson gson = new Gson();
    	String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
    	String json = httpRequest(requestUrl, "GET", null);
    	// 如果请求成功
    	if (json != null) {
			try {
				jsapiTicket = gson.fromJson(json, WeixinJsApiTicketEntry.class);
			} catch (JsonParseException e) {
				jsapiTicket = null;
				// 获取jsapi_ticket失败
				jsapiTicket = null;
                Log.error("获取jsapi_ticket失败 ,返回的JSON字符串：" + json);
			}
		}
    	return jsapiTicket;
    }
    
    /** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return String(json字符串)
     */ 
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			
		} catch (ConnectException ce) {
			Log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return buffer.toString();
	}
}
