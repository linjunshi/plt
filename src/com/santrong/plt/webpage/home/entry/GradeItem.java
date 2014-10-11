package com.santrong.plt.webpage.home.entry;

import java.util.List;



/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:45:35
 */
public class GradeItem {
	private String id;
	private String gradeName;
	private String levelName;
	private int gradeGroup;
	private String gradeEnName;
	private String levelEnName;
	private int priority;
	
	private List<SubjectItem> subjectList;
	
	public List<SubjectItem> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<SubjectItem> subjectList) {
		this.subjectList = subjectList;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getGradeGroup() {
		return gradeGroup;
	}
	public void setGradeGroup(int gradeGroup) {
		this.gradeGroup = gradeGroup;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
}
