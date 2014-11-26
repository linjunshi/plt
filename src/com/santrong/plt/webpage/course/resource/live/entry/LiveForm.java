package com.santrong.plt.webpage.course.resource.live.entry;


/**
 * @author huangweihua
 * @date   2014年11月22日 
 * @time   下午4:00:54
 */
public class LiveForm {
	private String id;
	private String title;
	private String url;
	private String beginTime;
	private String endTime;
	private int duration;
	private String groupId;
	private String ownerId;

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}
