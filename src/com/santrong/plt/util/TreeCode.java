package com.santrong.plt.util;

/**
 * @author huangweihua
 * @Date 2015年1月17日 
 * @Time 下午4:13:51
 * @deprecated<br>code编码规范
	<br>xxxx xx xx xx
	<br>1.小学1，初中2，高中3
	<br>2.一年级1，二年级2。。。。
	<br>3.语文1，数学2，英语3。。。
	<br>4.上学期1，下学期2
	<br>5~6.一级知识点01开始
	<br>7~8.二级知识点01开始
	<br>9~10.三级知识点01开始
	<br>level映射 : 1111 01 00 00定义为1
 */
public class TreeCode {
	
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
	
	// 	TODO 编码规则
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
	
	/**
	 * 获取树的学阶级别的编码
	 * @param gradeEnName
	 * @return
	 */
	public static String getGradeCode(String gradeEnName){
		for (int i = 0; i < gradeEnNames.length; i++) {
			if (gradeEnName.equalsIgnoreCase(gradeEnNames[i])) {
				return gradeCodes[i];
			}
		}
		return "1";
	}
	
	/**
	 * 获取树的年级级别的编码
	 * @param levelEnName
	 * @return
	 */
	public static String getLevelCode(String levelEnName){
		for (int i = 0; i < levelEnNames.length; i++) {
			if (levelEnName.equalsIgnoreCase(levelEnNames[i])) {
				return levelCodes[i];
			}
		}
		return "1";
	}
	
	/**
	 * 获取树的科目级别的编码
	 * @param subjectEnName
	 * @return
	 */
	public static String getSubjectCode(String subjectEnName){
		for (int i = 0; i < subjectEnNames.length; i++) {
			if (subjectEnName.equalsIgnoreCase(subjectEnNames[i])) {
				return subjectCodes[i];
			}
		}
		return "1";
	}

	/**
	 * 补全后面的0，共十位
	 * @param code
	 * @return
	 */
	public static String getFullCode(String code) {
		String newCode = "1111000000";
		if (MyUtils.isNotNull(code)) {
			int len = code.length();
			for (int i = 0; i < 10 - len; i++) {
				code = code + "0";
			}
			newCode = code;
		}
		return newCode;
	}
	
	/**
	 * 获取父节点
	 * @param code
	 * @return
	 */
	public static int getParentCode(int code) {
		// 10 00 00 00 00 
		String str = Integer.toString(code);
		int pId = 1000000000;
		if (str.length() == 10) {
			if ("00".equals(str.substring(2, 4))) {
				pId = 1000000000;
			} else if ("00".equals(str.substring(4, 6))) {
				pId = Integer.parseInt(str.substring(0, 2) + "00000000");
			} else if ("00".equals(str.substring(6, 8))) {
				pId = Integer.parseInt(str.substring(0, 4) + "000000");
			} else if ("00".equals(str.substring(8, 10))) {
				pId = Integer.parseInt(str.substring(0, 6) + "0000");
			} else {
				pId = Integer.parseInt(str.substring(0, 8) + "00");
			}
		}
		return pId;
	}
	
	/**
	 * 获取父节点
	 * @param code
	 * @return
	 */
	public static String getParentCode2(int code) {
		// 10 00 00 00 00 
		String str = Integer.toString(code);
		String pId = "1000000000";
		if (str.length() == 10) {
			if ("00".equals(str.substring(2, 4))) {
				pId = "1000000000";
			} else if ("00".equals(str.substring(4, 6))) {
				pId = str.substring(0, 2) + "00000000";
			} else if ("00".equals(str.substring(6, 8))) {
				pId = str.substring(0, 4) + "000000";
			} else if ("00".equals(str.substring(8, 10))) {
				pId = str.substring(0, 6) + "0000";
			} else {
				pId = str.substring(0, 8) + "00";
			}
		}
		return pId;
	}
	/**
	 * 根据父节点，获取下级节点的最大code
	 * @param parentCode
	 * @return
	 */
	public static int getChildNodeMaxCode(int parentCode) {
		String str = Integer.toString(parentCode);
		int maxCode = 1099999999;
		if (str.length() == 10) {
			if ("00".equals(str.substring(2, 4))) {
				maxCode = Integer.parseInt(str.substring(0, 2) + "99000000");
			} else if ("00".equals(str.substring(4, 6))) {
				maxCode = Integer.parseInt(str.substring(0, 4) + "990000");
			} else if ("00".equals(str.substring(6, 8))) {
				maxCode = Integer.parseInt(str.substring(0, 6) + "9900");
			} else if ("00".equals(str.substring(8, 10))) {
				maxCode = Integer.parseInt(str.substring(0, 8) + "99");
			}
		}
		return maxCode;
	}
	
	/**
	 * 根据父节点，获取下级节点的最小code
	 * @param parentCode
	 * @return
	 */
	public static int getChildNodeMinCode(int parentCode) {
		String str = Integer.toString(parentCode);
		int minCode = 1000000000;
		if (str.length() == 10) {
			if ("00".equals(str.substring(2, 4))) {
				minCode = Integer.parseInt(str.substring(0, 2) + "01000000");
			} else if ("00".equals(str.substring(4, 6))) {
				minCode = Integer.parseInt(str.substring(0, 4) + "010000");
			} else if ("00".equals(str.substring(6, 8))) {
				minCode = Integer.parseInt(str.substring(0, 6) + "0100");
			} else if ("00".equals(str.substring(8, 10))) {
				minCode = Integer.parseInt(str.substring(0, 8) + "01");
			}
		}
		return minCode;
	}
	
	/**
	 * 获取同级的下一个节点code
	 * @param code
	 * @return
	 */
	public static int getNextNodeCode(int code) {
		int nextCode = 1000000000;
		String str = Integer.toString(code);
		if (str.length() == 10) {
			if ("00".equals(str.substring(2, 4))) {
				nextCode = Integer.parseInt((Integer.parseInt(str.substring(0, 2)) + 1 ) + "00000000");
			} else if ("00".equals(str.substring(4, 6))) {
				nextCode = Integer.parseInt((Integer.parseInt(str.substring(0, 4)) + 1 ) + "000000");
			} else if ("00".equals(str.substring(6, 8))) {
				nextCode = Integer.parseInt((Integer.parseInt(str.substring(0, 6)) + 1 ) + "0000");
			} else if ("00".equals(str.substring(8, 10))) {
				nextCode = Integer.parseInt((Integer.parseInt(str.substring(0, 8)) + 1 ) + "00");
			}
		}
		return nextCode;
	}
	
	public static int getChildNodeNewCode(int parentCode , int childCount) {
		int nextCode = 1000000000;
		String str = Integer.toString(parentCode);
		if (str.length() == 10) {
			if ("00".equals(str.substring(2, 4))) {
				nextCode = Integer.parseInt((Integer.parseInt(str.substring(0, 4)) + childCount + 1 ) + "000000");
			} else if ("00".equals(str.substring(4, 6))) {
				nextCode = Integer.parseInt((Integer.parseInt(str.substring(0, 6)) + childCount + 1 ) + "0000");
			} else if ("00".equals(str.substring(6, 8))) {
				nextCode = Integer.parseInt((Integer.parseInt(str.substring(0, 8)) + childCount + 1 ) + "00");
			} else if ("00".equals(str.substring(8, 10))) {
				nextCode = Integer.parseInt(str.substring(0, 10)) + childCount + 1 ;
			}
		}
		return nextCode;
	}
}
