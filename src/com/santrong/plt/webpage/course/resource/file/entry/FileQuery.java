package com.santrong.plt.webpage.course.resource.file.entry;

import com.santrong.plt.opt.PageQuery;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:39:57
 */
public class FileQuery extends PageQuery {
	private String keywords;
	private String onwerId;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getOnwerId() {
		return onwerId;
	}

	public void setOnwerId(String onwerId) {
		this.onwerId = onwerId;
	}
}
