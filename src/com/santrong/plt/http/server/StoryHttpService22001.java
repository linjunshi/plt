package com.santrong.plt.http.server;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.story.dao.StoryDao;
import com.santrong.plt.webpage.story.entry.StoryItem;

/**
 * @author huangweihua
 * @date 2015年3月20日 
 * @time 上午11:05:06
 */
public class StoryHttpService22001 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		StoryItem storyItem = null;
		try{
			String storyId = xml.find("/MsgBody/DramaID").getText();
			StoryDao storyDao = new StoryDao();
			storyItem = storyDao.selectById(storyId);
			if(storyItem != null) {
				rt = 1;
			}
			
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--获取剧本名称(22001)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Story_Service_22001).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				if (storyItem != null) {
					sb.append("<DramaName>").append(storyItem.getStoryName()).append("</DramaName>");
				}
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
