package com.santrong.plt.webpage.teacher.entry;

import com.santrong.plt.opt.PageQuery;

/**
 * @author weinianjie
 * @date 2014年10月9日
 * @time 下午4:06:04
 */
public class UserQuery extends PageQuery{
	private String keywords;
	private String areaCode;
	private String subjectEnName;
	private String schoolId;
	private int schoolAbsoluteGrade;
	private int schoolGrade;
	private int absoluteRole;
	private int role;
	
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSubjectEnName() {
		return subjectEnName;
	}

	public void setSubjectEnName(String subjectEnName) {
		this.subjectEnName = subjectEnName;
	}

	public int getAbsoluteRole() {
		return absoluteRole;
	}

	public void setAbsoluteRole(int absoluteRole) {
		this.absoluteRole = absoluteRole;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getSchoolAbsoluteGrade() {
		return schoolAbsoluteGrade;
	}

	public void setSchoolAbsoluteGrade(int schoolAbsoluteGrade) {
		this.schoolAbsoluteGrade = schoolAbsoluteGrade;
	}

	public int getSchoolGrade() {
		return schoolGrade;
	}

	public void setSchoolGrade(int schoolGrade) {
		this.schoolGrade = schoolGrade;
	}
}
