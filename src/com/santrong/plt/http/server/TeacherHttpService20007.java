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
 * @date 2014年11月6日 
 * @time 上午10:44:10
 */
public class TeacherHttpService20007 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		int beginYear = 0;
		int beginMonth = 0;
		int beginDay = 0;
		int beginHour = 0;
		int beginMinute = 0;
		int beginSeconds = 0;
		int endYear = 0;
		int endMonth = 0;
		int endDay = 0;
		int endHour = 0;
		int endMinute = 0;
		int endSeconds = 0;
		LiveItem liveItem = null;
		try{
			String liveID = xml.find("/MsgBody/LiveID").getText();
			if (MyUtils.isNotNull(liveID)) {
				LiveDao liveDao = new LiveDao();
				liveItem = liveDao.selectById(liveID);
				if (liveItem != null) {
					Calendar cal = Calendar.getInstance();
					Date beginTime = liveItem.getBeginTime();
					if (beginTime != null) {
						cal.setTime(beginTime);
						beginYear = cal.get(Calendar.YEAR);
						beginMonth = cal.get(Calendar.MONTH) + 1;
						beginDay = cal.get(Calendar.DAY_OF_MONTH);
						beginHour = cal.get(Calendar.HOUR_OF_DAY);
						beginMinute = cal.get(Calendar.MINUTE);
						beginSeconds = cal.get(Calendar.SECOND);
					}
					Date endTime = liveItem.getEndTime();
					if (endTime != null) {
						cal.setTime(endTime);
						endYear = cal.get(Calendar.YEAR);
						endMonth = cal.get(Calendar.MONTH) + 1;
						endDay = cal.get(Calendar.DAY_OF_MONTH);
						endHour = cal.get(Calendar.HOUR_OF_DAY);
						endMinute = cal.get(Calendar.MINUTE);
						endSeconds = cal.get(Calendar.SECOND);
					}
					rt = 1;
				}
			}
		}catch(Exception e) {
			Log.printStackTrace(e);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--获取直播信息(20007)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20007).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				sb.append("<Lives>");
					if (liveItem != null) {
						//sb.append("<!--课程ID-->");
						sb.append("<LiveID type=\"string\">").append(liveItem.getId()).append("</LiveID>");
						//sb.append("<!--课程名称-->");
						sb.append("<LiveName type=\"string\">").append(liveItem.getTitle()).append("</LiveName>");
						//sb.append("<!--开课时间-->");
						sb.append("<StartTime>");
							sb.append("<Year type=\"int\">").append(beginYear).append("</Year>");
							sb.append("<Month type=\"int\">").append(beginMonth).append("</Month>");
							sb.append("<Day type=\"int\">").append(beginDay).append("</Day>");
							sb.append("<Hour type=\"int\">").append(beginHour).append("</Hour>");
							sb.append("<Minute type=\"int\">").append(beginMinute).append("</Minute>");
							sb.append("<Second type=\"int\">").append(beginSeconds).append("</Second>");
						sb.append("</StartTime>");
						//sb.append("<!--结束时间-->");
						sb.append("<StopTime>");
							sb.append("<Year type=\"int\">").append(endYear).append("</Year>");
							sb.append("<Month type=\"int\">").append(endMonth).append("</Month>");
							sb.append("<Day type=\"int\">").append(endDay).append("</Day>");
							sb.append("<Hour type=\"int\">").append(endHour).append("</Hour>");
							sb.append("<Minute type=\"int\">").append(endMinute).append("</Minute>");
							sb.append("<Second type=\"int\">").append(endSeconds).append("</Second>");
						sb.append("</StopTime>");
					}
				sb.append("</Lives>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
