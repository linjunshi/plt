package com.santrong.plt.http.server;

import java.util.ArrayList;
import java.util.List;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.live.dao.LiveCallReplyDao;
import com.santrong.plt.webpage.live.entry.LiveCallReplyItem;
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserItem;

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
		List<UserItem> userList = null;
		try{
			liveID = xml.find("/MsgBody/LiveID").getText();
			callNameID = xml.find("/MsgBody/CallNameID").getText();
			if (liveID != "" && callNameID != "") {
				LiveCallReplyDao liveCallReplyDao = new LiveCallReplyDao();
				//获取直播课堂点名已签到的学生
				List<LiveCallReplyItem> lcrList = liveCallReplyDao.selectUserID(callNameID, liveID);
				List<String> userIdList = new ArrayList<String>();
				for(LiveCallReplyItem lcrItem:lcrList){
					userIdList.add(lcrItem.getUserId());
				}
				String[] userIds = (String[])userIdList.toArray(new String[userIdList.size()]);
				
				//获取直播课堂点名未签到的学生
				UserDao userDao = new UserDao();
				userList = userDao.selectNotInByIds(userIds);
				if (userList != null) {
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
//					sb.append("<!--未应答用户ID列表-->");	
				if (rt == 1) {
					for (UserItem userItem:userList) {
						if (userItem.isStudent()) {
							sb.append("<UserID type=\"string\">").append(userItem.getId()).append("</UserID>");
						}
					}
				}else{
					sb.append("<UserID type=\"string\">").append("</UserID>");
				}
				sb.append("<UserIDs>");
			sb.append("</MsgBody>");
		sb.append("</ResMsg>");
		return sb.toString();
	}

}
