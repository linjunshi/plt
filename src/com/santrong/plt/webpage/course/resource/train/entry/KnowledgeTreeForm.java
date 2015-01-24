package com.santrong.plt.webpage.course.resource.train.entry;

/**
 * @author huangweihua
 * @date 2015年1月7日 
 * @time 下午1:20:05
 */
public class KnowledgeTreeForm {
	private String id;
	private String pId;
	private String name;
	
	private String gradeId;
	private String subjectId;
	private String unitId;//单元学期
	private int priority;
	private int level;
	private String dataId;//用来保存原来数据库里的id
	private int type;//用来标识树不同级别的类型（见TreeCode.java）
	private String code;//用来保存编码规则，见 com/santrong/plt/util/TreeCode.java
	
	private boolean open;
	private boolean isParent;
	private String iconSkin;//通过CSS样式来设置节点图片样式（展开、折叠节点时，logo可切换）
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
}
