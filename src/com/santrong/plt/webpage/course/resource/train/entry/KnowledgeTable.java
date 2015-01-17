package com.santrong.plt.webpage.course.resource.train.entry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weinianjie
 * @date 2015年1月10日
 * @time 下午5:36:40
 */
public class KnowledgeTable {
	private String unitId;
	private List<KnowledgePointerView> knowledgePointerList = new ArrayList<KnowledgePointerView>();
	
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public List<KnowledgePointerView> getKnowledgePointerList() {
		return knowledgePointerList;
	}
	public void setKnowledgePointerList(
			List<KnowledgePointerView> knowledgePointerList) {
		this.knowledgePointerList = knowledgePointerList;
	}
}
