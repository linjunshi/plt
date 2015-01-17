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
	private int type;
	private String code;
	
	private boolean open;
	private boolean isParent;
	private String iconSkin;//通过CSS样式来设置节点图片样式（展开、折叠节点时，logo可切换）
	
	/**
	 * 根节点的默认icon图标样式
	 */
	public final static String  pIconRoot ="pIconRoot" ;

	/**
	 * 学段：xiaoxue chuzhong gaozhong
	 */
	public final static int type_studySection = 1;// 学段：xiaoxue chuzhong gaozhong
	/**
	 * 年级:xiaoxue 1-6
	 */
	public final static int type_level = 2;// 年级:xiaoxue 1-6
	/**
	 * 学科
	 */
	public final static int type_subject = 3;// 学科
	/**
	 * 学期
	 */
	public final static int type_term = 4;// 学期
	/**
	 * 单元
	 */
	public final static int type_unit = 5;// 单元
	/**
	 * 知识点
	 */
	public final static int type_knowledge = 6;// 知识点
	
//	 	TODO 编码规则
//		code编码规范
//		xxxx xx xx xx
//		1.小学1，初中2，高中3
//		2.一年级1，二年级2。。。。
//		3.语文1，数学2，英语3。。。
//		4.上学期1，下学期2
//		5~6.一级知识点01开始
//		7~8.二级知识点01开始
//		9~10.三级知识点01开始
//		
//		level映射
//		1111 01 00 00定义为1
	
	/**
	 * {"小学", "初中", "高中"}
	 */
	public final static String[] gradeNames = {"小学", "初中", "高中"};
	/**
	 * {"xiaoxue", "chuzhong", "gaozhong"}
	 */
	public final static String[] gradeEnNames = {"xiaoxue", "chuzhong", "gaozhong"};
	/**
	 * {"1", "2", "3"}
	 */
	public final static String[] gradeCodes = {"1", "2", "3"};
	
	/**
	 * {"一年级", "二年级", "三年级", "四年级", "五年级", "六年级"}
	 */
	public final static String[] levelNames = {"一年级", "二年级", "三年级", "四年级", "五年级", "六年级"};
	/**
	 * {"level1", "level2", "level3","level4", "level5", "level6"}
	 */
	public final static String[] levelEnNames = {"level1", "level2", "level3","level4", "level5", "level6"};
	/**
	 * {"1", "2", "3", "4", "5", "6"}
	 */
	public final static String[] levelCodes = {"1", "2", "3", "4", "5", "6"};
	
	
	/**
	 * {"语文", "数学", "英语", "物理", "化学", "生物", "地理", "政治"}
	 */
	public final static String[] subjectNames = {"语文", "数学", "英语", "物理", "化学", "生物", "地理", "政治"};
	/**
	 * {"yuwen", "shuxue", "yingyu","wuli", "huaxue", "shengwu", "dili", "zhengzhi"}
	 */
	public final static String[] subjectEnNames = {"yuwen", "shuxue", "yingyu","wuli", "huaxue", "shengwu", "dili", "zhengzhi"};
	/**
	 * {"1", "2", "3", "4", "5", "6", "7", "8"}
	 */
	public final static String[] subjectCodes = {"1", "2", "3", "4", "5", "6", "7", "8"};
	
	/**
	 * {"上学期", "下学期"}
	 */
	public final static String[] termNames = {"上学期", "下学期"};
	/**
	 * {"termup", "termdown"}
	 */
	public final static String[] termEnNames = {"termup", "termdown"};
	/**
	 * {"1", "2"}
	 */
	public final static String[] termCodes = {"1", "2"};
	
	
	public String getGradeCode(String gradeEnName){
		for (int i = 0; i < gradeEnNames.length; i++) {
			if (gradeEnName.equalsIgnoreCase(gradeEnNames[i])) {
				return gradeCodes[i];
			}
		}
		return "1";
	}
	
	public String getLevelCode(String levelEnName){
		for (int i = 0; i < levelEnNames.length; i++) {
			if (levelEnName.equalsIgnoreCase(levelEnNames[i])) {
				return levelCodes[i];
			}
		}
		return "1";
	}
	
	public String getSubjectCode(String subjectEnName){
		for (int i = 0; i < subjectEnNames.length; i++) {
			if (subjectEnName.equalsIgnoreCase(subjectEnNames[i])) {
				return subjectCodes[i];
			}
		}
		return "1";
	}
	
	
	
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
