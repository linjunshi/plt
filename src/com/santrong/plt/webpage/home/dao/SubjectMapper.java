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
	
	@Select("select * from web_subject order by priority desc")
	List<SubjectItem> selectAll();
	
	@Select("select distinct a.* from web_subject a "
			+ "left join web_grade_subject b on a.id=b.subjectId "
			+ "left join web_grade c on b.gradeId=c.id "
			+ "where c.gradeGroup=#{gradeGroup} order by priority desc")
	List<SubjectItem> selectByGradeGroup(int gradeGroup);
}
