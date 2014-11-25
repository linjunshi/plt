package com.santrong.plt.webpage.teacher.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年11月25日
 * @time 下午2:58:30
 */
public class UserTmpItem {
	private String userId;
	private String activeCode;
	private Date cts;
	
	public Date getCts() {
		return cts;
	}
	public void setCts(Date cts) {
		this.cts = cts;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
}
