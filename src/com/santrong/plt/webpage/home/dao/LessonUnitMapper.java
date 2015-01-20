package com.santrong.plt.webpage.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.home.entry.LessonUnitItem;

/**
 * @author huangweihua
 * @date 2015年1月16日 
 * @time 下午4:08:24
 */
public interface LessonUnitMapper {
	
	@Select("select * from lesson_unit order by gradeId asc,subjectId asc,term asc,priority asc")
	List<LessonUnitItem> selectAll();
	
	@Select("select * from lesson_unit group by gradeId,subjectId,term order by gradeId asc,subjectId asc,term asc,priority asc")
	List<LessonUnitItem> selectAllTerm();
	
	@Select("select * from lesson_unit where gradeId = #{gradeId} and subjectId = #{subjectId} order by gradeId asc,subjectId asc,term asc,priority asc")
	List<LessonUnitItem> selectTermUnitByGIdAndSId(@Param("gradeId")String gradeId, @Param("subjectId")String subjectId);
	
}