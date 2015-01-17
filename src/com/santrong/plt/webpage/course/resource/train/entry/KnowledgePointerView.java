package com.santrong.plt.webpage.course.resource.train.entry;

/**
 * @author weinianjie
 * @date 2014年12月25日
 * @time 下午4:37:07
 * @Description 该类用于知识图谱
 */
public class KnowledgePointerView {
	private String id;
	private int code;
	private int level;
	private String knowledgeName;
	private String subjectId;
	private String gradeId;
	private String unitId;
	private int priority;
	
	private int total;// 总回答数
	private int goal;// 回答正确数
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKnowledgeName() {
		return knowledgeName;
	}
	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	/**
	 * 获取正确率
	 * @return
	 */
	public int getAccuracy() {
		if(this.total != 0) {
			return this.goal*100/this.total;
		}
		return 0;
	}
	
	/**
	 * 获取评星1~5
	 * @return
	 */
	public int getStarNum() {
		return this.getAccuracy()/20+1;
	}
}
