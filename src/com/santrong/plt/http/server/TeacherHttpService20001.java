package com.santrong.plt.http.server;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午8:35:02
 */
public class TeacherHttpService20001 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		String username = "";
		String userId = "";
		try{
			username = xml.find("/MsgBody/UserName").getText();
			String password = xml.find("/MsgBody/PassWord").getText();
			UserDao userDao = new UserDao();
			UserItem user = userDao.selectByUserName(username);
			if(user != null && user.getPassword().equals(MyUtils.getMD5(password))) {
				userId = user.getId();
				rt = 1;
			}
		}catch(Exception e) {
			Log.printStackTrace(e);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20001).append("</MsgCode>");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				sb.append("<UserName type=\"string\">").append(username).append("</UserName>");
				sb.append("<UserId type=\"string\">").append(userId).append("</UserId>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
