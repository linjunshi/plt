package com.santrong.plt.webpage.course.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.entry.BuyItem;

/**
 * @author weinianjie
 * @date   2014年10月30日
 * @time   下午3:45:53	
 */
public interface BuyCourseMapper {
	
	@Insert("insert into web_order values(#{id}, #{userId}, #{courseId}, #{cts}, #{uts})")
	int insert(BuyItem buy);
	
	@Select("select count(*) as cn from web_order where courseId=#{courseId} and userId=#{userId}")
	int exists(@Param("courseId") String courseId, @Param("userId") String userId);
}
