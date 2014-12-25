package com.santrong.plt.webpage.course.resource.train.entry;

import com.santrong.plt.opt.SimpleQuery;

/**
 * @author weinianjie
 * @date 2014年12月25日
 * @time 下午4:41:14
 */
public class KnowledgeQuery extends SimpleQuery {
	private String subjectId;
	private String gradeId;
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
}
