package com.santrong.plt.webpage.teacher.entry;

import java.util.Date;

import com.santrong.plt.util.MyUtils;

/**
 * @author huangweihua
 * @date   2014年10月31日 
 * @time   下午3:16:43
 */
public class UserCourseView {

	// user 用户信息
	private String id;
	private String showName;
	private String username;
	private String url;
	private int gender;
	private int role;
	private String schoolId;
	private String subjectId;
	private String registIp;
	private Date registTime;
	private String lastLoginIp;
	private String lastLoginTime;
	private String remark;
	private Date cts;
	private Date uts;
	
	// user_education 用户教育信息
	private int education;
	private int positional;
	private String graduateSchool;
	
	// course 课程所有者的课程数量
	private String courseCount;
	
	// school 学校信息，学校名称
	private String schoolName;
	private String schoolEnName;
	private String areaCode;
	private int schoolGrade;

	// subject 学科
	private String subjectName;
	private String subjectEnName;
	private int priority;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getRegistIp() {
		return registIp;
	}
	public void setRegistIp(String registIp) {
		this.registIp = registIp;
	}
	public Date getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCts() {
		return cts;
	}
	public void setCts(Date cts) {
		this.cts = cts;
	}
	public Date getUts() {
		return uts;
	}
	public void setUts(Date uts) {
		this.uts = uts;
	}
	public int getEducation() {
		return education;
	}
	public void setEducation(int education) {
		this.education = education;
	}
	public int getPositional() {
		return positional;
	}
	public void setPositional(int positional) {
		this.positional = positional;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	public String getCourseCount() {
		return courseCount;
	}
	public void setCourseCount(String courseCount) {
		this.courseCount = courseCount;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolEnName() {
		return schoolEnName;
	}
	public void setSchoolEnName(String schoolEnName) {
		this.schoolEnName = schoolEnName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public int getSchoolGrade() {
		return schoolGrade;
	}
	public void setSchoolGrade(int schoolGrade) {
		this.schoolGrade = schoolGrade;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectEnName() {
		return subjectEnName;
	}
	public void setSubjectEnName(String subjectEnName) {
		this.subjectEnName = subjectEnName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	// 获取头像
	public String getHeadPhoto() {
		if(MyUtils.isNotNull(this.url)) {
			return url;
		}else {
			return "/resource/photo/touxiang.png";
		}
	}
}
