package com.santrong.plt.http.server;

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
public class TeacherHttpService20004 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
//		String liveID = "";
		String questionID = "";
		TrainQuestionItem tqItem = null;
		try{
//			liveID = xml.find("/MsgBody/LiveID").getText();
			questionID = xml.find("/MsgBody/HomeWorkID").getText();
			if (MyUtils.isNotNull(questionID)) {
				TrainQuestionDao tqDao = new TrainQuestionDao();
				tqItem = tqDao.selectById(questionID);
				if (tqItem != null) {
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
				//sb.append("<!--获取作业内容(20004)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20004).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				if (tqItem != null) {
					//sb.append("<!--作业ID-->");
					sb.append("<HomeWorkID type=\"string\">").append(tqItem.getId()).append("</HomeWorkID>");
					//sb.append("<!--题目-->");
					sb.append("<Topic type=\"string\">").append(tqItem.getTopic()).append("</Topic>");
					//sb.append("<!-- 作业类型 1选择题；2、多选题；4、对错题；8、填空题-->");
					sb.append("<Type type=\"int\">").append(tqItem.getQuestionType()).append("</Type>");
					//sb.append("<!--作业选项，填空题只有一条记录，需要填空的地方用空格代替-->");
					sb.append("<Items>");
					//sb.append("<!-- 选择、判断题 Options无效-->");
					sb.append("<Option type=\"string\">").append("A").append("</Option>");
					sb.append("<Item type=\"string\">").append(tqItem.getOpt1()).append("</Item>");
					sb.append("</Items>");
					sb.append("<Items>");
					sb.append("<Option type=\"string\">").append("B").append("</Option>");
					sb.append("<Item type=\"string\">").append(tqItem.getOpt2()).append("</Item>");
					sb.append("</Items>");
					sb.append("<Items>");
					sb.append("<Option type=\"string\">").append("C").append("</Option>");
					sb.append("<Item type=\"string\">").append(tqItem.getOpt3()).append("</Item>");
					sb.append("</Items>");
					sb.append("<Items>");
					sb.append("<Option type=\"string\">").append("D").append("</Option>");
					sb.append("<Item type=\"string\">").append(tqItem.getOpt4()).append("</Item>");
					sb.append("</Items>");
				}
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
