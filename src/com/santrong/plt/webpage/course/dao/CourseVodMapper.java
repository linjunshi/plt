package com.santrong.plt.webpage.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.entry.CourseVodView;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:19:35
 */
public interface CourseVodMapper {
	@Select("select a.id,a.courseName,a.price,a.saleCount,b.subjectName,d.showName from web_course_vod a "
			+ "left join web_subject b on a.subjectId=b.id "
			+ "left join web_grade c on a.gradeId=c.id "
			+ "left join web_user d on a.ownerId=d.id "
			+ "left join web_school e on d.schoolId=e.id "
			+ "where c.gradeGroup=#{gradeGroup} and e.areaCode like #{areaCode} "
			+ "order by a.cts limit 4")
	List<CourseVodView> selectForIndexList(@Param("gradeGroup")int gradeGroup, @Param("areaCode")String areaCode);
}
