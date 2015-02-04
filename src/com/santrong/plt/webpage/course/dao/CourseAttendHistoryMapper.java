package com.santrong.plt.webpage.course.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.entry.CourseAttendHistoryItem;

/**
 * @author huangweihua
 * @Date 2015年2月2日 
 * @Time 下午2:42:33
 */
public interface CourseAttendHistoryMapper {
	
	@Insert("insert into course_attend_history values(#{id}, #{userId}, #{courseId}, #{attendType}, #{cts}, #{uts})")
	int insert(CourseAttendHistoryItem courseAttendHistoryItem);
	
	@Update("update course_attend_history set "
//			+ "userId = #{userId},"
//			+ "courseId = #{courseId},"
			+ "attendType = #{attendType},"
			+ "uts = #{uts} "
			+ "where id = #{id}")
	int update(CourseAttendHistoryItem courseAttendHistoryItem);
	
	@Select("select count(*) as cn from course_attend_history where userId = #{userId} and courseId = #{courseId}")
	int exists(@Param("userId") String userId, @Param("courseId") String courseId);
	
	@Select("select * from course_attend_history where userId = #{userId} and courseId = #{courseId}")
	CourseAttendHistoryItem select(@Param("userId") String userId, @Param("courseId") String courseId);
	
	@Delete("delete from course_attend_history where id = #{id}")
	int removeAttendHistory(String id);
}
