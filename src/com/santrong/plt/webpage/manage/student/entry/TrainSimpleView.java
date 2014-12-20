package com.santrong.plt.webpage.manage.student.entry;

import java.util.Date;

import com.santrong.plt.util.MyUtils;

/**
 * @author weinianjie
 * @date 2014年11月24日
 * @time 上午10:44:51
 */
public class TrainSimpleView {
	private String id;
	private String title;
	private String remark;// 章节描述
	private String courseName;
	private String url;
	private Date cts;// 购买时间
	
	// 获取缩略图
	public String getThumbnail() {
		if(MyUtils.isNotNull(this.url)) {
			return url;
		}else {
			return "/resource/photo/course01.jpg";
		}
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCts() {
		return cts;
	}
	public void setCts(Date cts) {
		this.cts = cts;
	}
}
