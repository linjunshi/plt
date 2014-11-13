package com.santrong.plt.http.server;

import java.util.List;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.live.dao.LiveCallReplyDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveCallReplyItem;

/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午8:35:02
 */
public class TeacherHttpService20005 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		String liveID = "";
		String callNameID = "";
		int replyCount = 0;//应答人数
		try{
			liveID = xml.find("/MsgBody/LiveID").getText();
			callNameID = xml.find("/MsgBody/CallNameID").getText();
//			masterID = xml.find("/MsgBody/MasterID").getText();//<!--老师ID-->
			if (MyUtils.isNotNull(liveID) && MyUtils.isNotNull(callNameID)) {
				
				//获取直播课堂点名已签到的学生
				LiveCallReplyDao liveCallReplyDao = new LiveCallReplyDao();
				List<LiveCallReplyItem> lcrList = liveCallReplyDao.selectUserID(callNameID, liveID);
				if(lcrList != null && lcrList.size() > 0) {
					for (int i = 0; i < lcrList.size(); i++) {
						replyCount++;
					}
				}

				rt = 1;
			}
		}catch(Exception e) {
			Log.printStackTrace(e);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
//				sb.append("<!--获取点名结果（只反馈未应答的用户）-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20005).append("</MsgCode>");
//				sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
//				sb.append("<!--课程ID-->");
				sb.append("<LiveID type=\"string\">").append(liveID).append("</LiveID>");
				sb.append("<CallNameID type=\"int\">").append(callNameID).append("</CallNameID>");
//				<!--应答人数-->
				sb.append("<ResponseNum type=\"int\">").append(replyCount).append("</ResponseNum>");
				
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
