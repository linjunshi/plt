package com.santrong.plt.http.server;

import java.util.Date;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.live.dao.LiveCallDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveCallItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 下午5:42:15
 */
public class ServerHttpService21002 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		try {
			//此次点名ID号
			String liveID = xml.find("/MsgBody/LiveID").getText();
			//老师ID
//			String masterID = xml.find("/MsgBody/MasterID").getText();
			String namedID = xml.find("/MsgBody/NamedID").getText();
			//此次点名的成员总数
//			String namedNum = xml.find("/MsgBody/namedNum").getText();
			
			if(MyUtils.isNotNull(liveID) && MyUtils.isNotNull(namedID)) {
				
				LiveCallDao liveCallDao = new LiveCallDao();
				if (!liveCallDao.existsByCall(liveID, namedID)) {
					LiveCallItem liveCallItem = new LiveCallItem();
					liveCallItem.setId(MyUtils.getGUID());
					liveCallItem.setLiveId(liveID);
					liveCallItem.setCallName(namedID);
					liveCallItem.setCts(new Date());
					liveCallDao.insert(liveCallItem);
				}
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				// sb.append("<!--上报老师开始点名(21002)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Server_Service_21002).append("</MsgCode>");
			sb.append("</MsgHead>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
