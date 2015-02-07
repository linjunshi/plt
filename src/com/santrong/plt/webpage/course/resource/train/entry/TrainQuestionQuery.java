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
	private String gradeId;
	private String subjectId;
	private String unitId;
	private int questionType;
	private int status;
	
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
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * 是否是单选题
	 * @return boolean
	 */
	public boolean isSingleSelection() {
		return (questionType == TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION);
	}
	
	/**
	 * 是否是多选题
	 * @return boolean
	 */
	public boolean isMulChoice() {
		return (questionType == TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE);
	}
	
	/**
	 * 是否是判断题
	 * @return boolean
	 */
	public boolean isTrueOrFlase() {
		return (questionType == TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE);
	}
	
	/**
	 * 是否是填空题
	 * @return boolean
	 */
	public boolean isBlankFilling() {
		return (questionType == TrainQuestionItem.QUESTION_TYPE_BLANK_FILLING);
	}
	
}
