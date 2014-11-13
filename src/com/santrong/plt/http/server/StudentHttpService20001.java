package com.santrong.plt.http.server;

import java.util.List;

import org.jdom.Element;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author huangweihua
 * @date 2014年11月4日 
 * @time 下午3:40:39
 */
public class StudentHttpService20001 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		List<UserItem> userList = null;
		try{
			List<Element> idList = xml.finds("/MsgBody/UserID");
			if (idList != null && idList.size() > 0) {
				String[] userIds = new String[idList.size()];
				int i = 0;
				for(Element e:idList) {
					userIds[i++] = e.getText();					
				}
				
				UserDao userDao = new UserDao();
				userList = userDao.selectByIds(userIds);
				if (userList != null) {
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
				//sb.append("<!--获取用户信息(20001)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Student_Service_20001).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				for(UserItem user:userList){
					sb.append("<User>");
					//<!--用户名-->
					sb.append("<UserName type=\"string\">").append(user.getUsername()).append("</UserName>");
					//<!--用户ID-->
					sb.append("<UserID type=\"string\">").append(user.getId()).append("</UserID>");
					//<!--用户身份 1表示老师，2表示学生-->
					sb.append("<Identity type=\"int\">").append(user.isTeacher()? 1:2).append("</Identity>");
					sb.append("</User>");
				}
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
