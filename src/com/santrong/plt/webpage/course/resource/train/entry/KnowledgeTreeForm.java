package com.santrong.plt.webpage.course.resource.train.entry;

/**
 * @author huangweihua
 * @date 2015年1月7日 
 * @time 下午1:20:05
 */
public class KnowledgeTreeForm {
	private int id;
	private int pId;
	private String name;
	private String subjectId;
	private String gradeId;
	private int week;
	private int priority;
	private int level;
	private String dataId;//用来保存原来数据库里的id
	
	private boolean open;
	private String iconSkin;//通过CSS样式来设置节点图片样式（展开、折叠节点时，logo可切换）
	
	/**
	 * 根节点的默认icon图标样式
	 */
	public final static String  pIconRoot ="pIconRoot" ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	
}
