package com.santrong.plt.http.server;

import java.util.List;

import org.jdom.Element;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;

/**
 * @author huangweihua
 * @date 2014年11月5日  
 * @time 上午11:57:46
 */
public class StudentHttpService30004 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		try{
			String liveID = xml.find("/MsgBody/LiveID").getText();
			List<Element> resList = xml.finds("/MsgBody/Results/Result");
			if (resList != null) {
				String[] results = (String[])resList.toArray(new String[resList.size()]);
			
			}
			
			
			/* <!--直播ID-->
			    <LiveID type="string">aaabbbb</LiveID>
			    <Results>
			      <Result type="string">A</Result>
			      <Result type="string">B</Result>
			    </Results>*/
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--上报作业结果(30004)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Student_Service_30004).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
