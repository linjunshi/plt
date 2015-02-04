package com.santrong.plt.webpage.story.entry;

import com.santrong.plt.opt.PageQuery;

/**
 * @author huangweihua
 * @date 2015年2月3日 
 * @time 下午4:59:03
 */
public class StoryQuery extends PageQuery{
	
	private String keywords;
	private int storyType;//课程类型
	private String ownerId;
	private String subjectId;
	private int status;
	private String userId;//所属用户
	private String storyId;//所属剧本
	private int attendType;
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getStoryType() {
		return storyType;
	}
	public void setStoryType(int storyType) {
		this.storyType = storyType;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStoryId() {
		return storyId;
	}
	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}
	public int getAttendType() {
		return attendType;
	}
	public void setAttendType(int attendType) {
		this.attendType = attendType;
	}
	
}
