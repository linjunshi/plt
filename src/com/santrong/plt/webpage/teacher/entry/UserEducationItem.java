package com.santrong.plt.webpage.teacher.entry;

/**
 * @author huangweihua
 * @date   2014年11月14日 
 * @time   上午10:02:21
 */
public class UserEducationItem {

	// user_education 用户教育信息
	private String userId;
	private int education;
	private int positional;
	private String graduateSchool;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
}
