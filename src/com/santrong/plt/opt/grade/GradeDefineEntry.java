package com.santrong.plt.opt.grade;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午4:36:12
 */
public class GradeDefineEntry {
	private String gradeName;
	private int gradeGroup;
	private String gradeEnName;
	private List<GradeLevelEntry> gradeLevelList;
	private List<GradeSubjectEntry> gradeSubjectList;
	
	public String getGradeEnName() {
		return gradeEnName;
	}
	public void setGradeEnName(String gradeEnName) {
		this.gradeEnName = gradeEnName;
	}
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
	public List<GradeLevelEntry> getGradeLevelList() {
		return gradeLevelList;
	}
	public void setGradeLevelList(List<GradeLevelEntry> gradeLevelList) {
		this.gradeLevelList = gradeLevelList;
	}
	public List<GradeSubjectEntry> getGradeSubjectList() {
		return gradeSubjectList;
	}
	public void setGradeSubjectList(List<GradeSubjectEntry> gradeSubjectList) {
		this.gradeSubjectList = gradeSubjectList;
	}
	
	public List<String> getAllSubjectIdAsList() {
		List<String> list = new ArrayList<String>();
		for(GradeSubjectEntry entry : this.gradeSubjectList) {
			list.add(entry.getSubjectId());
		}
		return list;
	}
	
	public String[] getAllSubjectIdAsArray() {
		return this.getAllSubjectIdAsList().toArray(new String[this.getAllSubjectIdAsList().size()]);
	}
	
	public List<String> getAllLevelIdAsList() {
		List<String> list = new ArrayList<String>();
		for(GradeLevelEntry entry : this.gradeLevelList) {
			list.add(entry.getLevelId());
		}
		return list;
	}
	
	public String[] getAllLevelIdAsArray() {
		return this.getAllLevelIdAsList().toArray(new String[this.getAllLevelIdAsList().size()]);
	}
}
