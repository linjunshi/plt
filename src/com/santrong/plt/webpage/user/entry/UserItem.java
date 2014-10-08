package com.santrong.plt.webpage.user.entry;

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
	private int role;
	private Date cts;
	private Date uts;
	
	public final static int Role_Student = 1; // 1
	public final  static int Role_Teacher = 2; // 10
	public final  static int Role_School = 4; // 100
	public final  static int Role_Admin = 64; // 100000

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
	
	public boolean isAdmin() {
		return (role & Role_Admin) == Role_Admin;
	}
	
}
