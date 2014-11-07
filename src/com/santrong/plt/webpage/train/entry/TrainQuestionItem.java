package com.santrong.plt.webpage.train.entry;

import java.util.Date;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainQuestionItem {

//	resource_train_question 作业习题
	private String id;
	private String topic;
	private int questionType;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private int answer;
	private String ownerId;
	private int del;
	private Date cts;
	private Date uts;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getOpt3() {
		return opt3;
	}
	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}
	public String getOpt4() {
		return opt4;
	}
	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
}
