package com.santrong.plt.http.server;

import java.util.Calendar;
import java.util.Date;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.live.dao.LiveDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 下午5:42:15
 */
public class ServerHttpService21001 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		int endYear = 0;
		int endMonth = 0;
		int endDay = 0;
		int endHour = 0;
		int endMinute = 0;
//		int endSeconds = 0;		
		try {
			String userID = xml.find("/MsgBody/UserID").getText();
			String liveID = xml.find("/MsgBody/LiveID").getText();
			
			if(MyUtils.isNotNull(liveID) && MyUtils.isNotNull(userID)) {
				
				LiveDao liveDao = new LiveDao();
				LiveItem live = liveDao.selectById(liveID);
				if(live != null && live.getOwnerId().equals(userID)) {
					
					Date endTime = live.getEndTime();
					if (endTime != null) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(endTime);
						endYear = cal.get(Calendar.YEAR);
						endMonth = cal.get(Calendar.MONTH) + 1;
						endDay = cal.get(Calendar.DAY_OF_MONTH);
						endHour = cal.get(Calendar.HOUR_OF_DAY);
						endMinute = cal.get(Calendar.MINUTE);
//						endSeconds = cal.get(Calendar.SECOND);
					}					
					
					rt = 1;
				}

			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Server_Service_21001).append("</MsgCode>");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("<StopTime>");
				sb.append("<Year type=\"int\">").append(endYear).append("</Year>");
				sb.append("<Month type=\"int\">").append(endMonth).append("</Month>");
				sb.append("<Day type=\"int\">").append(endDay).append("</Day>");
				sb.append("<Hour type=\"int\">").append(endHour).append("</Hour>");
				sb.append("<Minute type=\"int\">").append(endMinute).append("</Minute>");
//				sb.append("<Second type=\"int\">").append(endSeconds).append("</Second>");
				sb.append("</StopTime>");	
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
