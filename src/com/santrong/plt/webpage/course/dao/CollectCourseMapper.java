package com.santrong.plt.webpage.course.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.entry.CollectionItem;

/**
 * @author huangweihua
 * @date   2014年10月30日
 * @time   下午3:45:53	
 */
public interface CollectCourseMapper {
	
	@Insert("insert into course_collection values(#{userId}, #{courseId}, #{cts})")
	int insert(CollectionItem collectionItem);
	
	@Select("select count(*) as cn from course_collection where courseId=#{courseId} and userId=#{userId}")
	int exists(@Param("courseId") String courseId, @Param("userId") String userId);
	
	@Delete("delete from course_collection where userId = #{userId} and courseId = #{courseId}")
	int removeCollect(@Param("userId")String userId, @Param("courseId")String courseId);
}
