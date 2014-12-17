package com.santrong.plt.webpage.manage.student.entry;

import com.santrong.plt.opt.PageQuery;

/**
 * @author weinianjie
 * @date 2014年10月9日
 * @time 下午4:06:04
 */
public class OrderQuery extends PageQuery{
	private String keywords;
	private String userId;
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
