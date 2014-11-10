package com.santrong.plt.webpage.course.resource.train.entry;

import com.santrong.plt.opt.PageQuery;

/**
 * @author huangweihua
 * @date   2014年11月10日 
 * @time   下午2:55:45
 */
public class TrainQuestionQuery extends PageQuery{
	
	private String keywords;
	private String userId;
	private int del;
	private int questionType;
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
}
