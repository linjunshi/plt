package com.santrong.plt.webpage.course.resource.train.entry;

/**
 * @author huangweihua
 * @date 2014年12月27日 
 * @time 下午4:11:23
 */
public class KnowledgeGradeView {
	
	// knowledge 知识点表
	private String id;
	private String knowledgeName;
	private String subjectId;
	private String gradeId;
	// grade 年级表
	private String gradeName;
	private String levelName;
	private String gradeEnName;
	private String levelEnName;
	// subject 学科表
	private String subjectName;
	private String subjectEnName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getGradeEnName() {
		return gradeEnName;
	}
	public void setGradeEnName(String gradeEnName) {
		this.gradeEnName = gradeEnName;
	}
	public String getLevelEnName() {
		return levelEnName;
	}
	public void setLevelEnName(String levelEnName) {
		this.levelEnName = levelEnName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectEnName() {
		return subjectEnName;
	}
	public void setSubjectEnName(String subjectEnName) {
		this.subjectEnName = subjectEnName;
	}
}
