package com.santrong.plt.webpage.manage.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:33:12
 */
public class DocItem {	  
	private String id;
	private String title;
	private String url;
	private int docType;
	private String groupId;
	private String ownerId;
	private String remark;
	private Date cts;
	private Date uts;
	   
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
	public int getDocType() {
		return docType;
	}
	public void setDocType(int docType) {
		this.docType = docType;
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
}
