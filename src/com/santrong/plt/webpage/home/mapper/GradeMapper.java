package com.santrong.plt.webpage.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.home.entry.GradeItem;
import com.santrong.plt.webpage.home.entry.SubjectView;

/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:48:06
 */
public interface GradeMapper {
	
	@Select("select * from web_grade order by priority desc")
	List<GradeItem> selectAll();
	
	@Select("select * from web_grade where gradeGroup=#{gradeGroup} order by priority desc")
	List<GradeItem> selectByGradeGroup(int gradeGroup);
	
	@Select("select distinct gradeName, gradeGroup from web_grade order by priority desc")
	List<SubjectView> selectGrade();
}
