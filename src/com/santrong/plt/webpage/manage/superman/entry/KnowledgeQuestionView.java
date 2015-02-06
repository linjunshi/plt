package com.santrong.plt.webpage.manage.superman.entry;

/**
 * @author huangweihua
 * @date 2014年12月30日 
 * @time 上午11:07:33
 */
public class KnowledgeQuestionView {
	
	private String questionId;
	private String knowledgeId;
	
	// konwledge 知识点表
	private String knowledgeName;
	private String subjectId;
	private String gradeId;
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String getKnowledgeName() {
		return knowledgeName;
	}
	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
}
