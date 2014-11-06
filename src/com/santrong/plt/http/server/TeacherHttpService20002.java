package com.santrong.plt.http.server;

import java.util.ArrayList;
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
public class TeacherHttpService20002 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		String liveID = "";
		String callNameID = "";
		List<String> livingUserList = null;
		try{
			liveID = xml.find("/MsgBody/LiveID").getText();
			callNameID = xml.find("/MsgBody/CallNameID").getText();
			if (MyUtils.isNotNull(liveID) && MyUtils.isNotNull(callNameID)) {
				livingUserList = new ArrayList<String>();
				livingUserList.add("10000");
				livingUserList.add("10001");
				livingUserList.add("10002");
				livingUserList.add("10003");
				livingUserList.add("10004");
				livingUserList.add("10005");
				livingUserList.add("10006");
				livingUserList.add("10007");
				livingUserList.add("10008");
				livingUserList.add("10009");
				livingUserList.add("10010");
				
				//获取直播课堂点名已签到的学生
				LiveCallReplyDao liveCallReplyDao = new LiveCallReplyDao();
				List<LiveCallReplyItem> lcrList = liveCallReplyDao.selectUserID(callNameID, liveID);

				
				//获取直播课堂点名未签到的学生
				if(lcrList != null && lcrList.size() > 0) {
					for(int i=0;i<lcrList.size();i++) {
						for(int j=0;j<livingUserList.size();j++) {
							if(lcrList.get(i).getUserId() == livingUserList.get(j)) {
								livingUserList.remove(j);
								break;
							}
						}
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
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20002).append("</MsgCode>");
//				sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
//				sb.append("<!--课程ID-->");
				sb.append("<LiveID type=\"string\">").append(liveID).append("</LiveID>");
				sb.append("<CallNameID type=\"int\">").append(callNameID).append("</CallNameID>");
				sb.append("<UserIDs>");
//				sb.append("<!--未应答用户ID列表-->");
				if(livingUserList != null) {
					for (String id:livingUserList) {
						sb.append("<UserID type=\"string\">").append(id).append("</UserID>");
					}
				}
				sb.append("</UserIDs>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
