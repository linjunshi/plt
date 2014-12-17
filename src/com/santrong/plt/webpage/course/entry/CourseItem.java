package com.santrong.plt.webpage.course.entry;

import java.util.Date;

import com.santrong.plt.util.MyUtils;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:16:39
 */
public class CourseItem {
	private String id;
	private String courseName;
	private String teacher;
	private int price;
	private int limitCount;
	private int live;
	private Date endTime;
	private String url;
	private String ownerId;
	private String gradeId;
	private String subjectId;
	private int collectCount;
	private int saleCount;
	private int commentCount;
	private int chapterCount;
	private String remark;
	/**
	 * -1:删除，0:未发布，1:发布
	 */
	private int status;// -1:删除，0:未发布，1:发布
	private Date cts;
	private Date uts;
	
	
	public static final int Status_Del = -1;
	public static final int Status_Normal = 0;
	public static final int Status_Publish = 1;
	
	// 获取缩略图
	public String getThumbnail() {
		if(MyUtils.isNotNull(this.url)) {
			return url;
		}else {
			return "/resource/photo/course01.jpg";
		}
	}		
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLive() {
		return live;
	}
	public void setLive(int live) {
		this.live = live;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getChapterCount() {
		return chapterCount;
	}
	public void setChapterCount(int chapterCount) {
		this.chapterCount = chapterCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}
	public int getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
