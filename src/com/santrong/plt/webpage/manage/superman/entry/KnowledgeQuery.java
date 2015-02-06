package com.santrong.plt.webpage.manage.superman.entry;

import com.santrong.plt.opt.SimpleQuery;

/**
 * @author weinianjie
 * @date 2014年12月25日
 * @time 下午4:41:14
 */
public class KnowledgeQuery extends SimpleQuery {
	private String keywords;
	private String gradeId;
	private String subjectId;
	private String levelEnName;
	private String gradeEnName;
	private String subjectEnName;
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getLevelEnName() {
		return levelEnName;
	}
	public void setLevelEnName(String levelEnName) {
		this.levelEnName = levelEnName;
	}
	public String getGradeEnName() {
		return gradeEnName;
	}
	public void setGradeEnName(String gradeEnName) {
		this.gradeEnName = gradeEnName;
	}
	public String getSubjectEnName() {
		return subjectEnName;
	}
	public void setSubjectEnName(String subjectEnName) {
		this.subjectEnName = subjectEnName;
	}
}
