package com.santrong.plt.http.server;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;

/**
 * @author huangweihua
 * @date 2014年11月4日  
 * @time 下午4:46:07
 */
public class StudentHttpService30003 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		try{
			String UserID = xml.find("/MsgBody/UserID").getText();
			String CallNameID = xml.find("/MsgBody/CallNameID").getText();
			
			
			
			
			
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<ResMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--上报点名结果(30003)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Student_Service_30003).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</ResMsg>");
		return sb.toString();
	}

}
