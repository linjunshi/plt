package com.santrong.plt.http.server;

import java.util.Date;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.resource.train.dao.TrainHistoryDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;

/**
 * @author huangweihua
 * @date 2014年11月5日  
 * @time 上午11:57:46
 */
public class ClientHttpService20008 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		try{
			String liveID = xml.find("/MsgBody/LiveID").getText();
			String userId = xml.find("/MsgBody/UserID").getText();
			String questionId = xml.find("/MsgBody/HomeWorkID").getText();
			String type = xml.find("/MsgBody/Type").getText();
//			<!--答题是否正确，1正确 0 错误-->
			String isTrue = xml.find("/MsgBody/IsTrue").getText();
//			List<Element> resList = xml.finds("/MsgBody/Result");
//			<!--多选填写位合并的int值，填空填写字符串-->
			String result = xml.find("/MsgBody/Result").getText();

			
			if (MyUtils.isNotNull(liveID) && MyUtils.isNotNull(userId) && MyUtils.isNotNull(questionId) && MyUtils.isNotNull(result)
					 && MyUtils.isNotNull(type) && MyUtils.isNotNull(isTrue)) {
				
//				TODO 如果是选择题、判断题的话，才执行下面的操作
				int tqType = MyUtils.stringToInt(type, 0);
				if (tqType == TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION || tqType == TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE
						|| tqType == TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE) {

					// 获取直播所在的章节
					ChapterDao chapterDao = new ChapterDao();
					ChapterItem chapter = chapterDao.selectByResourceId(liveID);
					if(chapter != null) {
						
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
							thItem.setAnswer(result);//TODO 要求传过来的字符串格式：1,2,4,8
							thItem.setResult(MyUtils.stringToInt(isTrue, 0));//正确与否1yes,0no
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
			}
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--上报作业结果(20008)-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Client_Service_20008).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
