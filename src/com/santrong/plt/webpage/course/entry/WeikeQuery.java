package com.santrong.plt.webpage.course.entry;

import com.santrong.plt.opt.PageQuery;

/**
 * @author weinianjie
 * @date 2014年10月9日
 * @time 下午4:06:04
 */
public class WeikeQuery extends PageQuery{
	private String keywords;
	private String areaCode;
	private String levelEnName;
	private String gradeEnName;
	private String subjectEnName;
	private boolean live;
	private String userId;
	private String schoolId;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public String getSubjectEnName() {
		return subjectEnName;
	}

	public void setSubjectEnName(String subjectEnName) {
		this.subjectEnName = subjectEnName;
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

	public String getLevelEnName() {
		return levelEnName;
	}

	public void setLevelEnName(String levelEnName) {
		this.levelEnName = levelEnName;
	}

	public String getGradeEnName() {
		return gradeEnName;
	}

	public void setGradeEnName(String gradeEnName) {
		this.gradeEnName = gradeEnName;
	}
}
