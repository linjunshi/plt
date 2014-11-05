package com.santrong.plt.http.server;

import java.util.Date;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.live.dao.LiveDao;
import com.santrong.plt.webpage.live.entry.LiveScoreItem;

/**
 * @author huangweihua
 * @date 2014年11月4日 
 * @time 下午3:40:39
 */
public class StudentHttpService30002 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		try{
			String liveId = xml.find("/MsgBody/LiveID").getText();
			String score = xml.find("/MsgBody/Score").getText();
			
			if (liveId != "" && score != "") {
				LiveDao LiveDao = new LiveDao();
				LiveScoreItem liveScoreItem = new LiveScoreItem();
				
				liveScoreItem.setId(MyUtils.getGUID());
				liveScoreItem.setUserId("10000");
				liveScoreItem.setLiveId(liveId);
				liveScoreItem.setScore(MyUtils.stringToInt(score));
				liveScoreItem.setCts(new Date());
				if (LiveDao.insert(liveScoreItem) > 0) {
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
				//sb.append("<!--上报课程评价(30002)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Student_Service_30002).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</ResMsg>");
		return sb.toString();
	}

}
