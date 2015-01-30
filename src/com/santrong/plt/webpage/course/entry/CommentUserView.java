package com.santrong.plt.webpage.course.entry;

import java.util.Date;

import com.santrong.plt.util.MyUtils;

/**
 * @author huangweihua
 * @date 2014年11月8日 
 * @time 上午11:20:33
 */
public class CommentUserView {
	private String id;
	private String userId;
	private String courseId;
	private String remark;
	private Date cts;
	private Date uts;
	
	private String showName;
	private String url;

	// 获取头像
	public String getHeadPhoto() {
		if(MyUtils.isNotNull(this.url)) {
			return url;
		}else {
			return "/resource/photo/touxiang.png";
		}
	}
		
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
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
