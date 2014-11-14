package com.santrong.plt.webpage.course.entry;

import com.santrong.plt.opt.SimpleQuery;

/**
 * @author weinianjie
 * @date 2014年11月5日
 * @time 下午5:12:49
 */
public class CourseCollectQuery extends SimpleQuery {
	private String userId;
	private String courseId;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
