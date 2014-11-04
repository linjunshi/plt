package com.santrong.plt.http;

import com.santrong.plt.util.MyUtils;

/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午8:33:28
 */
public class HttpDefine {
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append(Xml_Header);
		sb.append("<ReqMsg>");
		sb.append("<MsgHead>");
			sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20001).append("</MsgCode>");
		sb.append("</MsgHead>");
		sb.append("<MsgBody>");
			sb.append("<UserName type=\"string\">").append("admin").append("</UserName>");
			sb.append("<PassWord type=\"string\">").append("admin").append("</PassWord>");
		sb.append("</MsgBody>");
	sb.append("</ReqMsg>");
		
		System.out.println("send:" + sb.toString());
		String rt = MyUtils.sendPost("http://127.0.0.1/http/basic", sb.toString());
		System.out.println("receive:" + rt);
	}
	
	public static final String Xml_Header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	public static final String Basic_Server_Test 			= "10000";	// 测试
	public static final String Teacher_Service_20001 			= "20001";	// 登录web服务器
}
