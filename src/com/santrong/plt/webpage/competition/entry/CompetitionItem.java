package com.santrong.plt.webpage.competition.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2014年12月25日
 * @time 下午4:44:34
 */
public class CompetitionItem {
	private String id;
	private String title;
	private String remark;
	private Date beginTime;
	private int flag; // 0正常竞赛，1个人练习
	private String ownerId;
	private int del;
	private Date cts;
	private Date uts;
	
	public static final int Flag_Group = 0;
	public static final int Flag_Person = 1;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
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
