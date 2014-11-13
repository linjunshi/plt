package com.santrong.plt.http.server;

import java.util.List;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
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
public class ClientHttpService20003 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		
		TrainQuestionItem tqItem = null;
		List<TrainHistoryItem> thList = null;
		
		int rightCount = 0;//定义答对人数的变量，并初始化为0
		int wrongCount = 0;//定义答错人数的变量，并初始化为0
		
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

						// 获取直播所在的章节
						ChapterDao chapterDao = new ChapterDao();
						ChapterItem chapter = chapterDao.selectByResourceId(liveID);
						if(chapter != null) {
							TrainHistoryDao thDao = new TrainHistoryDao();
							thList = thDao.selectByHistory(chapter.getId(), train.getId(), questionID);
							if (thList != null) {
								for (TrainHistoryItem thItem:thList) {
									if (thItem.getResult() == TrainHistoryItem.ANSWER_IS_RIGHT) {
										rightCount++;
									}else if (thItem.getResult() == TrainHistoryItem.ANSWER_IS_WRONG) {
										wrongCount++;
									}
								}
								rt = 1;
							}
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
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Client_Service_20003).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				sb.append("<HomeWorkID type=\"string\">").append(tqItem.getId()).append("</HomeWorkID>");
//				<!--答对人数-->
				sb.append("<CurrResult>").append(rightCount).append("</CurrResult>");
//				<!--答错人数-->
				sb.append("<WrongResult>").append(wrongCount).append("</WrongResult>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
