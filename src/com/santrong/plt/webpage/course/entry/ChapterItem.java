package com.santrong.plt.webpage.course.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 上午11:57:42
 */
public class ChapterItem {
	private String id;
	private String courseId;
	private String remark;
	private int priority;
	private Date cts;
	private Date uts;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
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
