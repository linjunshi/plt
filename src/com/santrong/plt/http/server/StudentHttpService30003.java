package com.santrong.plt.http.server;

import java.util.Date;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.live.dao.LiveCallDao;
import com.santrong.plt.webpage.course.resource.live.dao.LiveReplyDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveCallItem;
import com.santrong.plt.webpage.course.resource.live.entry.LiveReplyItem;

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
			String userID = xml.find("/MsgBody/UserID").getText();
			String callNameID = xml.find("/MsgBody/CallNameID").getText();
			String liveID = xml.find("/MsgBody/LiveID").getText();
			
			if (MyUtils.isNotNull(userID) && MyUtils.isNotNull(callNameID) && MyUtils.isNotNull(liveID)) {
				LiveCallDao liveCallDao = new LiveCallDao();
				LiveCallItem liveCallItem = liveCallDao.selectByCall(liveID, callNameID);
				
				if(liveCallItem != null) {
					LiveReplyDao LiveReplyDao = new LiveReplyDao();
					if(!LiveReplyDao.exists(liveCallItem.getId(), userID)) {
						
						LiveReplyItem liveReplyItem = new LiveReplyItem();	
						liveReplyItem.setId(MyUtils.getGUID());
						liveReplyItem.setCallId(liveCallItem.getId());
						liveReplyItem.setUserId(userID);
						liveReplyItem.setCts(new Date());
						if(LiveReplyDao.insert(liveReplyItem) > 0) {
							rt = 1;
						}
					}else {
						rt = 1;
					}
				}
			}
			
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--上报点名结果(30003)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Student_Service_30003).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
