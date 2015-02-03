package com.santrong.plt.webpage.friend.entry;

import com.santrong.plt.opt.SimpleQuery;

/**
 * @author weinianjie
 * @date 2015年2月3日
 * @time 下午3:17:23
 */
public class UserQuery extends SimpleQuery {
	private String currentUserId;

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
}
