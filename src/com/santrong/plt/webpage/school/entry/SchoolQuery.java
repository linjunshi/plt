package com.santrong.plt.webpage.school.entry;

import com.santrong.plt.opt.PageQuery;

/**
 * @author weinianjie
 * @date 2014年10月9日
 * @time 下午4:06:04
 */
public class SchoolQuery extends PageQuery{
	private String keywords;
	private String areaCode;
	private int schoolAbsoluteGrade;
	private int schoolGrade;

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
