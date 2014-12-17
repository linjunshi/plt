package com.santrong.plt.webpage.course.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午12:05:01
 */
public class OrderItem {
	private String id;
	private String userId;
	private String courseId;
	private int price;
	private int status;
	private Date cts;
	private Date uts;
	
	public static final int Status_Notpay = 0;// 未支付
	public static final int Status_Pay = 1;// 已支付
	public static final int Status_Cancel = -1;// 已取消
	
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

}
