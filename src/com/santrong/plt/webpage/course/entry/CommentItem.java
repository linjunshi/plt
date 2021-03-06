package com.santrong.plt.webpage.course.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 上午11:54:09
 */
public class CommentItem {
	private String id;
	private String userId;
	private String courseId;
	private String remark;
	private Date cts;
	private Date uts;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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
