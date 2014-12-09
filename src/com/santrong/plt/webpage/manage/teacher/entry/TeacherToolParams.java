package com.santrong.plt.webpage.manage.teacher.entry;

import java.util.Date;

import com.santrong.plt.util.MyUtils;

/**
 * @author weinianjie
 * @date 2014年12月9日
 * @time 上午10:48:04
 */
// 老师客户端参数
public class TeacherToolParams {
       private String rtmpUrl;
       private String webUrl;
       private String pltHost;
       private String teacherId;
       private String teacherName;
       private String sourceId;
       private String sourceTitle;
       private Date beginTime;
       private Date endTime;
       
	public String getRtmpUrl() {
		return rtmpUrl;
	}
	public void setRtmpUrl(String rtmpUrl) {
		this.rtmpUrl = rtmpUrl;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getPltHost() {
		return pltHost;
	}
	public void setPltHost(String pltHost) {
		this.pltHost = pltHost;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceTitle() {
		return sourceTitle;
	}
	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getBeginTimeString() {
		return MyUtils.dateToString(this.beginTime, MyUtils.DF_yyyy_MM_dd_HH_mm);
	}
	
	public String getEndTimeString() {
		return MyUtils.dateToString(this.endTime, MyUtils.DF_yyyy_MM_dd_HH_mm);
	}
}
