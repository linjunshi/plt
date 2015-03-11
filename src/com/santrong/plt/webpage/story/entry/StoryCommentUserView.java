package com.santrong.plt.webpage.story.entry;

import java.util.Date;

import com.santrong.plt.util.MyUtils;

/**
 * @author huangweihua
 * @Date 2015年3月11日
 * @Time 下午10:12:46
 */
public class StoryCommentUserView {
	//story_comment 情景剧评论
	private String id;
	private String userId;
	private String storyId;
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
	public String getStoryId() {
		return storyId;
	}
	public void setStoryId(String storyId) {
		this.storyId = storyId;
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
