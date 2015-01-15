package com.santrong.plt.webpage.home.entry;

/**
 * @author weinianjie
 * @date 2015年1月15日
 * @time 下午3:34:06
 */
public class LessonUnitItem {
	private String id;
	private String unitName;
	private String subjectId;
	private String gradeId;
	private int term;
	private int priority;
	
	public static final int Term_Up = 0;
	public static final int Term_Donw = 1;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
