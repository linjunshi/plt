package com.santrong.plt.webpage.home.entry;

/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:45:55
 */
public class SubjectItem {
	private String id;
	private String subjectName;
	private String subjectEnName;
	private int priority;
	
	public String getSubjectEnName() {
		return subjectEnName;
	}
	public void setSubjectEnName(String subjectEnName) {
		this.subjectEnName = subjectEnName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}
