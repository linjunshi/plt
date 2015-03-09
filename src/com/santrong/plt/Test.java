package com.santrong.plt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;









/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午5:28:37
 */
public class Test {
	
	
	public static void main(String[] args) {
		String url = "http://webstock.finance.qq.com/stockapp/zixuanguweb/stocklist?callback=NEWSTOCKPROXY14254506616520&app=web&range=group&uin=304468211";
		String cookie = "skey=@KY98nsPQD";
//		uin=o0304468211; skey=@KXNJZI1lP; ptisp=ctc; pgv_info=ssid=s3144851352; pt2gguin=o0304468211; RK=HXl63zzMcX; ptcz=ce4aee9e405aefdb1bb5f00ec8818a53a5fd3db0bccc4a56ce4b9804c21e8c14; pgv_pvid=2697986328; o_cookie=304468211
		String rs = sendGet(url, null, cookie);
		System.out.println(rs);
	}
	
	public static String sendGet(String url, String param, String cookie) {
        String result = "";
        BufferedReader in = null;
        try {
        	String urlNameString = url;
        	if(param != null && param != "") {
        		urlNameString = urlNameString + "?" + param;
        	}
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            if(cookie != null && cookie != "") {
            	connection.setRequestProperty("Cookie", cookie);
            }
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;		
	}
	
//	public static void main(String[] args) {
//	ChinaBankPayGateParams chinaBank = new ChinaBankPayGateParams();
//	chinaBank.setV_mid("23142561");
//	chinaBank.setV_oid("7a9ffcce6b1e4e17ad8ff0a8777813cb");// 订单号
//	chinaBank.setV_amount("0.01");// 订单实际支付金额
//	chinaBank.setV_moneytype("CNY");// 币种
//	chinaBank.setV_url("http://plttest.santrong.com/pay/chinabank/receive");// 跳转地址
//	chinaBank.setV_md5info(chinaBank.calMd5("weinianjieblacksheepwall"));
//	
//	System.out.print(chinaBank.getV_md5info());
//}
}
