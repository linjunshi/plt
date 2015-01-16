package com.santrong.plt.webpage.course.resource.train.entry;

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
	private int week;
	private int priority;
	
//	code编码规范
//	xxxx xx xx xx
//	1.小学1，初中2，高中3
//	2.一年级1，二年级2。。。。
//	3.语文1，数学2，英语3。。。
//	4.上学期1，下学期2
//	5~6.一级知识点01开始
//	7~8.二级知识点01开始
//	9~10.三级知识点001开始
//	
//	level映射
//	111 01 00 000定义为1
	
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
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
