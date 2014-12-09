package com.santrong.plt.http.server;

import java.util.Date;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.file.dao.FileDao;
import com.santrong.plt.webpage.course.resource.file.entry.FileItem;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午8:35:02
 */
public class AioHttpService30002 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;// 0失败，1成功
		try{
			String username = xml.find("/MsgBody/AddFileReq/Username").getText();
			String title = xml.find("/MsgBody/AddFileReq/Title").getText();
			long fileSize = xml.getLong("/MsgBody/AddFileReq/FileSize", 0);
			String duration = xml.find("/MsgBody/AddFileReq/Duration").getText();
			String remoteId = xml.find("/MsgBody/AddFileReq/RemoteId").getText();
			
			UserDao userDao = new UserDao();
			UserItem user = userDao.selectByUserName(username);
			if(user != null) {
				
				FileDao dao = new FileDao();
				FileItem file = new FileItem();
				file.setId(remoteId);
				file.setTitle(title);
				file.setUrl("");
				file.setSize(fileSize);
				file.setDuration(duration);
				file.setGroupId("");
				file.setOwnerId(user.getId());
				file.setRemark("");
				file.setStatus(FileItem.File_Push_Status_Pushing);
				file.setCts(new Date());
				file.setUts(new Date());
				if(dao.insert(file) > 0) {
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
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Aio_Service_30002).append("</MsgCode>");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		
		return sb.toString();	
	}

}
