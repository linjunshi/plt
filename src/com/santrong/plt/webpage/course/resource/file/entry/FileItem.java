package com.santrong.plt.webpage.course.resource.file.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:29:20
 */
public class FileItem {
	private String id;
	private String title;
	private String url;
	private long size;
	private String duration;
	private String groupId;
	private String ownerId;
	private String remark;
	private int status;
	private Date cts;
	private Date uts;
	
	public static final int File_Push_Status_Wating = 0;
	public static final int File_Push_Status_Pushing = 1;
	public static final int File_Push_Status_Error = 2;
	public static final int File_Push_Status_Done = 3;	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
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
