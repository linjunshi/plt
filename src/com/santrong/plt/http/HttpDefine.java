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
	
	/*老师客户端软件*/
	public static final String Teacher_Service_20001 			= "20001";	// 登录web服务器(20001)
	public static final String Teacher_Service_20005 			= "20005";	// 获取服务器时间(20005)
	
	/*flash学生客户端软件*/
	public static final String Student_Service_30001			= "30001";	// 获取用户信息(30001)
	public static final String Student_Service_30002			= "30002";	// 上报课程评价(30002)
	public static final String Student_Service_30003			= "30003";	// 上报点名结果(30003)
	public static final String Student_Service_30004			= "30004";	// 上报作业结果(30004)
	
}
