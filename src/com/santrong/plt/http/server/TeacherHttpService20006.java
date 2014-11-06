package com.santrong.plt.http.server;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;

/**
 * @author huangweihua
 * @date 2014年11月4日 
 * @time 上午11:10:41
 */
public class TeacherHttpService20006 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		try{

		
		
		
		}catch(Exception e) {
			Log.printStackTrace(e);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--获取当前服务器时间-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20006).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				//sb.append("<!--获取当前服务器时间-->");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
