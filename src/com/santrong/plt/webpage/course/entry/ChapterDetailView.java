package com.santrong.plt.webpage.course.entry;

import java.util.List;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午3:18:46
 */
public class ChapterDetailView {
	private String remark;
	private List<ResourceEntry> resourceList;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ResourceEntry> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<ResourceEntry> resourceList) {
		this.resourceList = resourceList;
	}
}
