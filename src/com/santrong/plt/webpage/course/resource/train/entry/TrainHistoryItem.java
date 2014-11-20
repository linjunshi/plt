package com.santrong.plt.webpage.course.resource.train.entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainHistoryItem {

//	resource_train_history 作业历史
	private String id;
	private String userId;
	private String chapterId;
	private String trainId;
	private String questionId;
	private int answer;
	private int result;
	private int del;
	private Date cts;
	private Date uts;
	
	/**
	 * 答题正确，结果为1
	 */
	public static final int ANSWER_IS_RIGHT		=	1;
	/**
	 * 答题错误，结果为0
	 */
	public static final int ANSWER_IS_WRONG		=	0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getChapterId() {
		return chapterId;
	}
	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public Date getCts() {
		return cts;
	}
	public void setCts(Date cts) {
		this.cts = cts;
	}
	public Date getUts() {
		return uts;
	}
	public void setUts(Date uts) {
		this.uts = uts;
	}
	
	
	public List<String> getAnswerString() {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<TrainQuestionItem.Answers.length;i++) {
			if((answer & TrainQuestionItem.Answers[i]) == TrainQuestionItem.Answers[i]) {
				list.add(TrainQuestionItem.Answers_Options[i]);
			}
		}
		return list;
	}
}
