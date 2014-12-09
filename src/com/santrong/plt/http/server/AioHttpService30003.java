package com.santrong.plt.http.server;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.file.dao.FileDao;
import com.santrong.plt.webpage.course.resource.file.entry.FileItem;

/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午8:35:02
 */
public class AioHttpService30003 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;// 0失败，1成功
		try{
			String remoteId = xml.find("/MsgBody/UpdateFileStatusReq/RemoteId").getText();
			int status = xml.getInt("/MsgBody/UpdateFileStatusReq/Status", FileItem.File_Push_Status_Error);
			
			FileDao dao = new FileDao();
			if(dao.updateStatus(remoteId, status) > 0) {
				rt = 1;
			}

		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Aio_Service_30003).append("</MsgCode>");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		
		return sb.toString();	
	}

}
