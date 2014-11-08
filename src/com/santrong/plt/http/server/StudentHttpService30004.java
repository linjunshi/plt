package com.santrong.plt.http.server;

import java.util.Date;
import java.util.List;

import org.jdom.Element;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.resource.train.dao.TrainHistoryDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;

/**
 * @author huangweihua
 * @date 2014年11月5日  
 * @time 上午11:57:46
 */
public class StudentHttpService30004 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		try{
			String liveID = xml.find("/MsgBody/LiveID").getText();
			String userId = xml.find("/MsgBody/UserID").getText();
			String questionId = xml.find("/MsgBody/HomeWorkID").getText();
			List<Element> resList = xml.finds("/MsgBody/Results/Result");
			
			if (MyUtils.isNotNull(liveID) && MyUtils.isNotNull(userId) && MyUtils.isNotNull(questionId) && resList != null) {
				//有可能是多选题
				int answer = 0;
				for (Element res:resList) {
					answer = answer | MyUtils.stringToInt(res.getText(), 0);
				}
				
				// 获取直播所在的章节
				ChapterDao chapterDao = new ChapterDao();
				ChapterItem chapter = chapterDao.selectByResourceId(liveID);
				if(chapter != null) {
					
					// 获取正确答案
					int rightAnswer = 0;
					TrainQuestionDao tqDao = new TrainQuestionDao();
					TrainQuestionItem tqItem = tqDao.selectById(questionId);
					if (tqItem != null) {
						rightAnswer = tqItem.getAnswer();
					}
					
					// 记录答题
					TrainHistoryDao trainHistoryDao = new TrainHistoryDao();
					if (!trainHistoryDao.exists(userId, liveID, chapter.getId(), questionId)) {
						TrainHistoryItem thItem = new TrainHistoryItem();
						thItem.setId(MyUtils.getGUID());
						thItem.setUserId(userId);
						thItem.setChapterId(chapter.getId());
						thItem.setQuestionId(questionId);
						thItem.setTrainId(liveID);
						thItem.setDel(0);//伪标记默认值为0,1标记为已删除
						thItem.setAnswer(answer);
						thItem.setResult(rightAnswer == answer?1:2);//正确与否1yes2no
						thItem.setCts(new Date());
						thItem.setUts(new Date());
						if (trainHistoryDao.insert(thItem) > 0) {
							rt = 1;
						}
					}else{
						rt = 1;
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
				//sb.append("<!--上报作业结果(30004)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Student_Service_30004).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
