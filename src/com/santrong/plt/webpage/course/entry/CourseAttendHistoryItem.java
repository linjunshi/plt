package com.santrong.plt.webpage.course.entry;

import java.util.Date;

/**
 * @author huangweihua
 * @Date 2015年2月2日 
 * @Time 下午2:38:37
 */
public class CourseAttendHistoryItem {

	private String id;
	private String userId;
	private String courseId;
	private int attendType;
	private Date cts;
	private Date uts;
	
	public static  int Type_View = 0;// 观看
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public int getAttendType() {
		return attendType;
	}
	public void setAttendType(int attendType) {
		this.attendType = attendType;
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
	
}
