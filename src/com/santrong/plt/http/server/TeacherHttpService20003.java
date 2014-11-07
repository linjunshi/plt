package com.santrong.plt.http.server;

import java.util.List;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.train.dao.TrainDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainHistoryDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;

/**
 * @author huangweihua
 * @date 2014年11月6日 
 * @time 下午7:16:32
 */
public class TeacherHttpService20003 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		TrainQuestionItem tqItem = null;
		List<TrainHistoryItem> thList = null;
		try{
			String liveID = xml.find("/MsgBody/LiveID").getText();
			String questionID = xml.find("/MsgBody/HomeWorkID").getText();
			
			if (MyUtils.isNotNull(questionID) && MyUtils.isNotNull(liveID)) {
				
				TrainDao trainDao = new TrainDao();
				TrainItem train = trainDao.selectByLiveId(liveID);
				
				if (train != null) {
					TrainQuestionDao tqDao = new TrainQuestionDao();
					tqItem = tqDao.selectById(questionID);
					if (tqItem != null) {
//					TODO 章节id 习题作业id
						String chapterId = "10000";
						TrainHistoryDao thDao = new TrainHistoryDao();
						thList = thDao.selectByHistory(chapterId, train.getId(), questionID);
						if (thList != null) {
							rt = 1;
						}
					}
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
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20003).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				if (tqItem != null) {
					//sb.append("<!--作业ID-->");
					sb.append("<HomeWorkID type=\"string\">").append(tqItem.getId()).append("</HomeWorkID>");
					//sb.append("<!-- 作业类型 1选择题；2、多选题；4、对错题；8、填空题-->");
					sb.append("<Type type=\"int\">").append(tqItem.getQuestionType()).append("</Type>");
				}
				if (thList != null) {
					//sb.append("<!--作业选项，填空题只有一条记录，需要填空的地方用空格代替-->");
					sb.append("<Users>");
					for (TrainHistoryItem thItem:thList) {
						sb.append("<User>");
							//sb.append("<!--选择、判断题 Options无效-->");
							sb.append("<UserID type=\"string\">").append(thItem.getUserId()).append("</UserID>");
							//sb.append("<!--选择题：多选或者单选答案；对错题：0表示错误，1表示正确；填空题：填空内容-->");
							sb.append("<Result type=\"string\">").append(thItem.getResult()).append("</Result>");
					    sb.append("</User>");
					}
					sb.append("</Users>");
				}
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
