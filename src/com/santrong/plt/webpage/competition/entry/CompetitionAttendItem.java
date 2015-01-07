package com.santrong.plt.webpage.competition.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2015年1月7日
 * @time 下午4:12:46
 */
public class CompetitionAttendItem {
	private String id;
	private String userId;
	private String competitionId;
	private Date cts;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCompetitionId() {
		return competitionId;
	}
	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}
	public Date getCts() {
		return cts;
	}
	public void setCts(Date cts) {
		this.cts = cts;
	}
}
