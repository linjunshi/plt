package com.santrong.plt.webpage.course.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午12:05:01
 */
public class BuyItem {
	private String id;
	private String userId;
	private String courseId;
	private Date cts;
	private Date uts;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getUts() {
		return uts;
	}
	public void setUts(Date uts) {
		this.uts = uts;
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
	public Date getCts() {
		return cts;
	}
	public void setCts(Date cts) {
		this.cts = cts;
	}

}
