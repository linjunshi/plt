package com.santrong.plt.webpage.school.entry;

/**
 * @author weinianjie
 * @date 2014年10月9日
 * @time 下午3:58:15
 */
public class SchoolItem {
	private String id;
	private String schoolName;
	private String schoolEnName;
	private String areaCode;
	private int schoolGrade;
	
	public String getSchoolEnName() {
		return schoolEnName;
	}
	public void setSchoolEnName(String schoolEnName) {
		this.schoolEnName = schoolEnName;
	}
	public int getSchoolGrade() {
		return schoolGrade;
	}
	public void setSchoolGrade(int schoolGrade) {
		this.schoolGrade = schoolGrade;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
