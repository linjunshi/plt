package com.santrong.plt.webpage.home.entry;

import java.util.List;



/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:45:35
 */
public class SubjectView {
	private String gradeName;
	private int gradeGroup;
	
	private List<SubjectItem> subjectList;

	public int getGradeGroup() {
		return gradeGroup;
	}

	public void setGradeGroup(int gradeGroup) {
		this.gradeGroup = gradeGroup;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public List<SubjectItem> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectItem> subjectList) {
		this.subjectList = subjectList;
	}
	
	
}
