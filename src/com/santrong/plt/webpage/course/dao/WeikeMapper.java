package com.santrong.plt.webpage.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseView;
import com.santrong.plt.webpage.course.entry.WeikeDetailView;

/**
 * @author weinianjie
 * @date 2015年1月19日
 * @time 下午3:23:05
 */
public interface WeikeMapper {
	@Select("select a.*,d.url fileUrl from course a "
			+ "left join course_chapter b on a.id=b.courseId "
			+ "left join course_chapter_to_resource c on b.id=c.chapterId "
			+ "left join resource_video d on c.resourceId=d.id "
			+ "where a.id=#{courseId}")
	WeikeDetailView selectByCourseId(String courseId);
	
	
	@Select("select a.id,a.courseName,a.url,a.price,a.saleCount,b.subjectName,d.showName from course a "
			+ "left join subject b on a.subjectId=b.id "
			+ "left join grade c on a.gradeId=c.id "
			+ "left join user d on a.ownerId=d.id "
			+ "left join school e on d.schoolId=e.id "
			+ "where c.gradeGroup=#{gradeGroup} and e.areaCode like #{areaCode} and a.status = 1 and a.courseType = 1 "
			+ "order by a.cts limit 10")
	List<CourseView> selectForIndexList(@Param("gradeGroup")int gradeGroup, @Param("areaCode")String areaCode);	
	
	@Select("select * from course where unitId = #{unitId} and status = 1 and courseType = 1 order by cts desc limit 8")
	List<CourseItem> selectWeikeByUnitId(String unitId);
	
	@Select("select * from course where gradeId = #{gradeId} and subjectId = #{subjectId} and status = 1 and courseType = 1 order by cts desc limit 8")
	List<CourseItem> selectWeikeByGIdAndSId(@Param("gradeId")String gradeId, @Param("subjectId")String subjectId);
	
	
}
