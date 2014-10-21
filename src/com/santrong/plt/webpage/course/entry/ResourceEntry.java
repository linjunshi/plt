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
	
	public static final int Type_Video = 1;
	public static final int Type_Live = 2;
	public static final int Type_Doc = 3;
	public static final int Type_Train = 4;
	
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
}
