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
public class ClientHttpService20004 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		String questionID = "";
		TrainQuestionItem tqItem = null;
		try{
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
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Client_Service_20004).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				if (tqItem != null) {
					//sb.append("<!--作业ID-->");
					sb.append("<HomeWorkID type=\"string\">").append(tqItem.getId()).append("</HomeWorkID>");
					//sb.append("<!--题目-->");
					sb.append("<Topic type=\"string\">").append(tqItem.getTopic()).append("</Topic>");
					//sb.append("<!-- 作业类型 1选择题；2、多选题；3、对错题；4、填空题-->");
					sb.append("<Type type=\"int\">").append(tqItem.getQuestionType()).append("</Type>");
					
//					<!--正确答案    作业类型 1选择题；2、多选题；3、对错题；4、填空题-->
//					Item节点内容说明：
//					option: 选择题的选项头，其他题型不用填 ;
//					IsKey: 是否正确答案，1正确 0错误, 填空题 都写1;
//					Text：选择题表示选项内容 ,判断题如果有选项内容 则填 没有就不填,填空题 直接写填空的答案
					if (tqItem.isSingleSelection() || tqItem.isMulChoice()) {
						int answer = tqItem.getAnswer(); 
						int[] answers = TrainQuestionItem.Answers;
						String[] answerOptions = TrainQuestionItem.Answers_Options;
						int isKey = 0;
						String optionContent = "";
						if (answers.length == answerOptions.length) {
							for (int i = 0; i < answers.length; i++) {
								if ((answer&answers[i]) == answers[i]) {
									isKey = 1;
								}
								if (MyUtils.isNotNull(tqItem.getOpt1()) && answerOptions[i].equals("A")) {
									optionContent = tqItem.getOpt1();
								} else if (MyUtils.isNotNull(tqItem.getOpt2()) && answerOptions[i].equals("B")) {
									optionContent = tqItem.getOpt2();
								} else if (MyUtils.isNotNull(tqItem.getOpt3()) && answerOptions[i].equals("C")) {
									optionContent = tqItem.getOpt3();
								} else if (MyUtils.isNotNull(tqItem.getOpt4()) && answerOptions[i].equals("D")) {
									optionContent = tqItem.getOpt4();
								}
								sb.append("<Item type=\"string\" option=\"").append(answerOptions[i]).append("\" IsKey=\"").append(isKey).append("\">").append(optionContent).append("</Item>");
								isKey = 0;
							}
						}
					} else if (tqItem.isTrueOrFlase()){
						
					} else if (tqItem.isBlankFilling()){
						
					}
				}
			sb.append("</MsgBody>");
		sb.append("</RespMsg>");
		return sb.toString();
	}

}
