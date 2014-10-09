package com.santrong.plt.webpage.home.entry;

import java.util.List;

/**
 * @author weinianjie
 * @date 2014年10月9日
 * @time 下午7:20:10
 */
public class SchoolView {
	private String gradeName;
	private int gradeGroup;
	private List<SchoolItem> schoolList;
	
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public int getGradeGroup() {
		return gradeGroup;
	}
	public void setGradeGroup(int gradeGroup) {
		this.gradeGroup = gradeGroup;
	}
	public List<SchoolItem> getSchoolList() {
		return schoolList;
	}
	public void setSchoolList(List<SchoolItem> schoolList) {
		this.schoolList = schoolList;
	}
	
}
