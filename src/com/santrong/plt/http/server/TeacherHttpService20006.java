package com.santrong.plt.http.server;

import java.util.List;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.live.dao.LiveDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 上午11:10:41
 */
public class TeacherHttpService20006 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		List<LiveItem> liveList = null;
		try{
			LiveDao liveDao = new LiveDao();
			liveList = liveDao.selectByToday();
			if (liveList != null && liveList.size() > 0) {
				rt = 1;
			}
		
		}catch(Exception e) {
			Log.printStackTrace(e);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--获取当天直播列表(20006)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20006).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				sb.append("<Lives>");
					for (LiveItem liveItem:liveList) {
						sb.append("<Live>");
							//sb.append("<!--课程ID-->");
							sb.append("<LiveID type=\"string\">").append(liveItem.getId()).append("</LiveID>");
							//sb.append("<!--课程名称-->");
							sb.append("<LiveName type=\"string\">").append(liveItem.getTitle()).append("</LiveName>");
						sb.append("</Live>");
					}
				sb.append("</Lives>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
