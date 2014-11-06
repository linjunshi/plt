package com.santrong.plt.webpage.teacher.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年7月14日
 * @time 下午5:50:38
 */
public class UserItem {
	private String id;
	private String showName;
	private String username;
	private String password;
	private String url;
	private int gender;
	private int role;
	private String schoolId;
	private String subjectId;
	private String registIp;
	private Date registTime;
	private String lastLoginIp;
	private Date lastLoginTime;
	private String remark;
	private Date cts;
	private Date uts;
	
	// 非表字段
	private String schoolName;
	
	public final static int Role_Student = 1; // 1
	public final  static int Role_Teacher = 2; // 10
	public final  static int Role_School = 4; // 100
	public final  static int Role_God = 64; // 100000

	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
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
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isStudent() {
		return (role & Role_Student) == Role_Student;
	}
	
	public boolean isTeacher() {
		return (role & Role_Teacher) == Role_Teacher;
	}
	
	public boolean isSchool() {
		return (role & Role_School) == Role_School;
	}
	
	public boolean isGod() {
		return (role & Role_God) == Role_God;
	}
	
}
