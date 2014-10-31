package com.santrong.plt.webpage.school.entry;

/**
 * @author weinianjie
 * @date 2014年10月30日
 * @time 下午3:22:09
 */
public class SchoolTotalView {
	private String id;
	private String schoolName;
	private int teacherCount;
	private int courseCount;
	
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
	public int getTeacherCount() {
		return teacherCount;
	}
	public void setTeacherCount(int teacherCount) {
		this.teacherCount = teacherCount;
	}
	public int getCourseCount() {
		return courseCount;
	}
	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}
}
