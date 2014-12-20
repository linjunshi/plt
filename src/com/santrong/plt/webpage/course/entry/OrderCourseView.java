package com.santrong.plt.webpage.course.entry;

import java.util.Date;

import com.santrong.plt.util.MyUtils;

/**
 * @author huangweihua
 * @date 2014年12月17日 
 * @time 下午7:36:48
 */
public class OrderCourseView {
	
	// web_order 订单表
	private String id;
	private String userId;
	private String courseId;
	private int price;
	private int status;
	private Date cts;
	private Date uts;
	
	// course 课程
	private String courseName;
	private String url;
	private String teacher;
	private String ownerId;
	private int live;
	private int chapterCount;
	
	// 获取缩略图
	public String getThumbnail() {
		if(MyUtils.isNotNull(this.url)) {
			return url;
		}else {
			return "/resource/photo/course01.jpg";
		}
	}	
		
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getLive() {
		return live;
	}
	public void setLive(int live) {
		this.live = live;
	}
	public int getChapterCount() {
		return chapterCount;
	}
	public void setChapterCount(int chapterCount) {
		this.chapterCount = chapterCount;
	}
}
