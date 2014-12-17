package com.santrong.plt.webpage.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.home.entry.SubjectItem;

/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:48:19
 */
public interface SubjectMapper {
	
	@Select("select * from subject order by priority asc")
	List<SubjectItem> selectAll();
	
	@Select("select distinct a.* from subject a "
			+ "left join grade_to_subject b on a.id=b.subjectId "
			+ "left join grade c on b.gradeId=c.id "
			+ "where c.gradeGroup=#{gradeGroup} order by priority asc")
	List<SubjectItem> selectByGradeGroup(int gradeGroup);
	
	@Select("select distinct a.* from subject a "
			+ "left join grade_to_subject b on a.id=b.subjectId "
			+ "left join grade c on b.gradeId=c.id "
			+ "where c.gradeEnName=#{gradeName} order by priority asc")
	List<SubjectItem> selectByGradeEnName(String gradeName);	
	
	@Select("select distinct a.* from subject a "
			+ "left join grade_to_subject b on a.id=b.subjectId "
			+ "where b.gradeId=#{gradeId} order by priority asc")
	List<SubjectItem> selectByGradeId(String gradeId);	
}
