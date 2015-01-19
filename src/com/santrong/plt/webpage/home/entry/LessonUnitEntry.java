package com.santrong.plt.webpage.home.entry;

/**
 * @author weinianjie
 * @date 2015年1月19日 
 * @time 下午3:35:45
 */
public class LessonUnitEntry {
	private String id;
	private String unitName;
	private String subjectId;
	private String gradeId;
	private int term;
	private int priority;
	
	private String termUnitCnName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
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
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getTermUnitCnName() {
		return termUnitCnName;
	}
	public void setTermUnitCnName(String termUnitCnName) {
		this.termUnitCnName = termUnitCnName;
	}
	public String getTermCnName() {
		switch (this.term) {
		case LessonUnitItem.Term_Up:
			return "上学期";
		case LessonUnitItem.Term_Down:
			return "下学期";
		}
		return "";
	}
	
}
