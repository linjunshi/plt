package com.santrong.plt.http.server;

import java.util.Date;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;
import com.santrong.plt.webpage.course.resource.live.dao.LiveDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveItem;
import com.santrong.plt.webpage.course.resource.train.dao.TrainDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 下午5:42:15
 */
public class ServerHttpService21003 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		try {
			String liveID = xml.find("/MsgBody/LiveID").getText();
			String homeWorkID = xml.find("/MsgBody/HomeWorkID").getText();
			
			if(MyUtils.isNotNull(liveID) && MyUtils.isNotNull(homeWorkID)) {
				
				// 尝试获取该直播对应的随堂作业
				TrainDao trainDao = new TrainDao();
				TrainItem train = trainDao.selectByLiveId(liveID);
				
				ThreadUtils.beginTranx();
				
				// 如果还没有插入过随堂作业，则插入一次作业
				boolean insertRT = true;
				if(train == null) {
					LiveDao liveDao = new LiveDao();
					LiveItem liveItem = liveDao.selectById(liveID);
					
					train = new TrainItem();
					train.setId(MyUtils.getGUID());
					train.setOwnerId(liveItem.getOwnerId());
					train.setDel(0);
					train.setTitle("随堂作业");
					train.setCts(new Date());
					train.setUts(new Date());
					if(trainDao.insert(train) <= 0) {
						insertRT = false;
					}
				}
				
				// 习题绑定到作业
				if(insertRT) {
					TrainQuestionDao tqDao = new TrainQuestionDao();
					if(tqDao.addQuestion2Train(homeWorkID, train.getId()) <= 0) {
						Log.mark("server push question to platform fail:questionId=" + homeWorkID + ",trainId=" + train.getId());
					}
				}
				
				ThreadUtils.commitTranx();

			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
			ThreadUtils.rollbackTranx();
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<RespMsg>");
			sb.append("<MsgHead>");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Server_Service_21003).append("</MsgCode>");
			sb.append("</MsgHead>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
