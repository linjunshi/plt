package com.santrong.plt.webpage.course.entry;

import java.util.Date;
import java.util.List;


/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:16:39
 */
public class CourseDetailView {
	private String id;
	private String courseName;
	private String teacher;
	private String ownerId;
	private String gradeId;
	private String subjectId;
	private int price;
	private int collectCount;
	private int saleCount;
	private String remark;
	private Date cts;
	private Date uts;
	private String subjectName;
	private String gradeName;
	private String levelName;
	
	private List<ChapterDetailView> chapterDetailList;
	private List<CommentItem> commentList;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ChapterDetailView> getChapterDetailList() {
		return chapterDetailList;
	}
	public void setChapterDetailList(List<ChapterDetailView> chapterDetailList) {
		this.chapterDetailList = chapterDetailList;
	}
	public List<CommentItem> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentItem> commentList) {
		this.commentList = commentList;
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
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
}
