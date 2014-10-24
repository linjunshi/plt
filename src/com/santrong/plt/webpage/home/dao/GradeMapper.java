package com.santrong.plt.webpage.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.home.entry.GradeItem;
import com.santrong.plt.webpage.home.entry.GradeView;

/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:48:06
 */
public interface GradeMapper {
	
	@Select("select * from grade order by priority desc")
	List<GradeItem> selectAll();
	
	@Select("select * from grade where gradeGroup=#{gradeGroup} order by priority desc")
	List<GradeItem> selectByGradeGroup(int gradeGroup);
	
	@Select("select distinct gradeName, gradeGroup, gradeEnName from grade order by priority desc")
	List<GradeView> selectGrade();
	
	@Select("select distinct a.gradeName, a.gradeGroup, a.gradeEnName from grade a "
			+ "left join grade_to_subject b on a.id=b.gradeId "
			+ "left join subject c on b.subjectId=c.id "
			+ "where c.subjectEnName=#{subjectName} "
			+ "order by a.priority desc")
	List<GradeView> selectGradeBySubjectEnName(String subjectName);	
}
