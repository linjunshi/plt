package com.santrong.plt.webpage.course.entry;

/**
 * @author huangweihua
 * @date   2014年11月21日 
 * @time   上午10:57:17
 */
public class ChapterToResourceItem {
	
	// course_chapter_to_resource 课程章节关联资源
	private String id;
	private String title;
	private String chapterId;
	private String resourceId;
	private int resourceType;
	private int priority;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getChapterId() {
		return chapterId;
	}
	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
