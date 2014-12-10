package com.santrong.plt.opt.grade;

import java.util.ArrayList;
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
	
	/**
	 * 根据gradeGroup获取年级数据
	 * @param gradeGroup
	 * @return
	 */
	public static GradeDefineEntry getByGradeGroup(int gradeGroup) {
		for(GradeDefineEntry entry : gradeList) {
			if(entry.getGradeGroup() == gradeGroup) {
				return entry;
			}
		}
		return null;
	}
	
	/**
	 * 根据gradeName获取年级数据
	 * @param gradeName
	 * @return
	 */
	public static GradeDefineEntry getByGradeEnName(String gradeEnName) {
		for(GradeDefineEntry entry : gradeList) {
			if(entry.getGradeEnName().equals(gradeEnName)) {
				return entry;
			}
		}
		return null;
	}
	
	/**
	 * 根据levelId获取学阶
	 * @param levelId
	 * @return
	 */
	public static GradeDefineEntry getByLevelId(String levelId) {
		for(GradeDefineEntry entry : gradeList) {
			for (GradeLevelEntry grade : entry.getGradeLevelList()) {
				if (grade.getLevelId().equals(levelId)) {
					return entry;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取全部科目
	 * @return
	 */
	public static List<GradeSubjectEntry> getAllSubject() {
		List<GradeSubjectEntry> list = new ArrayList<GradeSubjectEntry>();
		for(GradeDefineEntry entry : gradeList) {
			for(GradeSubjectEntry subject : entry.getGradeSubjectList()) {
				if(!list.contains(subject)) {
					list.add(subject);
				}
			}
		}
		return list;
	}
}
