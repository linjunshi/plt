package com.santrong.plt.http.server;

import java.util.Date;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.live.dao.LiveCallDao;
import com.santrong.plt.webpage.live.dao.LiveReplyDao;
import com.santrong.plt.webpage.live.entry.LiveCallItem;
import com.santrong.plt.webpage.live.entry.LiveReplyItem;

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
			String CallNameID = xml.find("/MsgBody/CallNameID").getText();
			
			if (CallNameID != "") {
				LiveCallDao liveCallDao = new LiveCallDao();
				LiveCallItem liveCallItem = new LiveCallItem();
				liveCallItem = liveCallDao.selectId("10000", CallNameID);
				
				
				LiveReplyDao LiveReplyDao = new LiveReplyDao();
				LiveReplyItem liveReplyItem = new LiveReplyItem();
				if (LiveReplyDao.selectBy(liveCallItem.getId(), userID) == null) {
					liveReplyItem.setId(MyUtils.getGUID());
					liveReplyItem.setCallId(liveCallItem.getId());
					liveReplyItem.setUserId(userID);
					liveReplyItem.setCts(new Date());
					LiveReplyDao.insert(liveReplyItem);
					rt = 1;
				}
			}
			
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
