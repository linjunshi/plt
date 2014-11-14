package com.santrong.plt.webpage.course.entry;

import com.santrong.plt.opt.SimpleQuery;

/**
 * @author weinianjie
 * @date 2014年11月5日
 * @time 下午5:12:49
 */
public class CommentQuery extends SimpleQuery {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
