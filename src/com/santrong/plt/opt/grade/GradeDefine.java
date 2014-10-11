package com.santrong.plt.opt.grade;

import java.util.List;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午4:33:47
 */
public class GradeDefine {
	// 在starUpListener中初始化;
	public static List<GradeDefineEntry> gradeList;
	
	public static final int Grade_Primary_School = 1;
	public static final int Grade_Junior_Middle_School = 2;
	public static final int Grade_Senior_Middle_School = 4;
	
	public static GradeDefineEntry getByGradeGroup(int gradeGroup) {
		for(GradeDefineEntry entry : gradeList) {
			if(entry.getGradeGroup() == gradeGroup) {
				return entry;
			}
		}
		return null;
	}
}
