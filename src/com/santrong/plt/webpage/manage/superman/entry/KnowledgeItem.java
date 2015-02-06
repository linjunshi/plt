package com.santrong.plt.webpage.manage.superman.entry;

/**
 * @author weinianjie
 * @date 2014年12月25日
 * @time 下午4:37:07
 */
public class KnowledgeItem {
	private String id;
	private int code;
	private int level;
	private String knowledgeName;
	private String subjectId;
	private String gradeId;
	private String unitId;
	private int priority;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
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
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
