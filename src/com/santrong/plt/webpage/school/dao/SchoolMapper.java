package com.santrong.plt.webpage.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.school.entry.SchoolTotalView;


/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:48:19
 */
public interface SchoolMapper {
	
	@Select("select a.id,a.schoolName,count(b.id) teacherCount, count(c.id) courseCount from school a left join user b on a.id=b.schoolId left join course c on b.id=c.ownerId "
			+ "where a.areaCode like '${areaCode}%' and (a.schoolGrade & ${gradeGroup}) = ${gradeGroup} "
			+ "group by a.id limit ${limit}")
	List<SchoolTotalView> selectTotalByGradeGroup(@Param("gradeGroup") int gradeGroup, @Param("areaCode") String areaCode, @Param("limit") int limit);
}
