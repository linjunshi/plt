package com.santrong.plt.http;

import com.santrong.plt.util.HttpUtils;

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
//			sb.append("<MsgCode type=\"int\">").append(HttpDefine.Client_Service_20001).append("</MsgCode>");
		sb.append("</MsgHead>");
		sb.append("<MsgBody>");
//			sb.append("<UserName type=\"string\">").append("admin").append("</UserName>");
//			sb.append("<PassWord type=\"string\">").append("admin").append("</PassWord>");
		sb.append("</MsgBody>");
		sb.append("</ReqMsg>");
		
		System.out.println("send:" + sb.toString());
		String rt = HttpUtils.sendPost("http://127.0.0.1/http/basic", sb.toString());
		System.out.println("receive:" + rt);
	}
	
	public static final String Xml_Header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	public static final String Basic_Server_Test 			= "10000";	// 测试
	
	/*中心服务器*/
	public static final String Server_Service_21001 			= "21001";	// 判断直播是不是某个老师开的(21001)
	public static final String Server_Service_21002 			= "21002";	// 上报老师开始点名(21002)
	public static final String Server_Service_21003 			= "21003";	// 上报老师布置作业(21003)
	
	/*客户端*/
	public static final String Client_Service_20001			= "20001";	// 获取用户信息(20001)学生
	public static final String Client_Service_20002 			= "20002";	// 获取作业列表(20002)老师
	public static final String Client_Service_20003 			= "20003";	// 获取作业结果(20003)老师
	public static final String Client_Service_20004 			= "20004";	// 获取作业内容(20004)老师
	public static final String Client_Service_20005 			= "20005";	// 获取点名结果(20005)老师
	public static final String Client_Service_20006			= "20006";	// 上报课程评价(20006)学生
	public static final String Client_Service_20007			= "20007";	// 上报点名结果(20007)学生
	public static final String Client_Service_20008			= "20008";	// 上报作业结果(20008)学生
}
