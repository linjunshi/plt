package com.santrong.plt.http.server;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午8:35:02
 */
public class AioHttpService30001 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;// 0失败，1成功
		try{
			String username = xml.find("/MsgBody/LoginReq/Username").getText();
			String pwdmd5 = xml.find("/MsgBody/LoginReq/Pwdmd5").getText();
			
			UserDao dao = new UserDao();
			UserItem user = dao.selectByUserName(username);
			if(user != null) {
				if(user.getPassword().equals(pwdmd5)) {
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
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Aio_Service_30001).append("</MsgCode>");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		
		return sb.toString();	
	}

}
