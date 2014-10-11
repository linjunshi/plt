package com.santrong.plt.webpage.home.entry;

import java.util.List;



/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:45:35
 */
public class GradeView {
	private String gradeName;
	private int gradeGroup;
	private String gradeEnName;
	
	private List<SubjectItem> subjectList;

	public String getGradeEnName() {
		return gradeEnName;
	}
	public void setGradeEnName(String gradeEnName) {
		this.gradeEnName = gradeEnName;
	}
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
