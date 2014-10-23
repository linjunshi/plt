package com.santrong.plt.webpage.course.entry;



/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 上午11:59:49
 */
public class ResourceEntry {
	private String id;
	private String title;
	private int type;
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getTypeString() {
		return ResourceType.getDescription(type);
	}
}
