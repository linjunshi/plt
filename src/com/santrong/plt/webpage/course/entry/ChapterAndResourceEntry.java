package com.santrong.plt.webpage.course.entry;


/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 上午11:57:34
 */
public class ChapterAndResourceEntry {
	private String id;
	private String remark;
	
	private String title;
	private String resourceId;
	private int resourceType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public int getResourceType() {
		return resourceType;
	}
	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}
}
