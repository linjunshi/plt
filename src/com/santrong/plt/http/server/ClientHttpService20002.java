package com.santrong.plt.http.server;

import java.util.List;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;

/**
 * @author huangweihua
 * @date 2014年11月6日 
 * @time 下午4:50:56
 */
public class ClientHttpService20002 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		String liveID = "";
		List<TrainQuestionItem> tqList = null;
		try{
			liveID = xml.find("/MsgBody/LiveID").getText();
			String userID = xml.find("/MsgBody/MasterID").getText();
			if (MyUtils.isNotNull(liveID) && MyUtils.isNotNull(userID)) {
				TrainQuestionDao tqDao = new TrainQuestionDao();
				tqList = tqDao.selectByUserId(userID);
				if (tqList != null) {
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
				//sb.append("<!--获取作业列表(20002)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Client_Service_20002).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
		        sb.append("<LiveID type=\"string\">").append(liveID).append("</LiveID>");
	        	for (TrainQuestionItem tqItem:tqList) {
	        		sb.append("<HomeWork>");
		        		sb.append("<Name type=\"string\">").append(tqItem.getTopic()).append("</Name>");
		        		sb.append("<ID type=\"string\">").append(tqItem.getId()).append("</ID>");
		        		sb.append("<Type type=\"int\">").append(tqItem.getQuestionType()).append("</Type>");
	        		sb.append("</HomeWork>");
				}
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
