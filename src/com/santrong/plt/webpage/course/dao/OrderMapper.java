package com.santrong.plt.webpage.course.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.entry.OrderItem;

/**
 * @author weinianjie
 * @date   2014年10月30日
 * @time   下午3:45:53	
 */
public interface OrderMapper {
	
	@Insert("insert into web_order values(#{id}, #{userId}, #{courseId}, #{price}, #{status}, #{cts}, #{uts})")
	int insert(OrderItem order);
	
	@Update("update web_order set status=#{status},uts=#{uts} where id=#{id}")
	int update(OrderItem order);	
	
	@Select("select count(*) as cn from web_order where courseId=#{courseId} and userId=#{userId}")
	int exists(@Param("courseId") String courseId, @Param("userId") String userId);
	
	@Select("select * from web_order where id=#{id}")
	OrderItem selectById(String id);	
	
	@Select("select * from web_order where courseId=#{courseId} and userId=#{userId}")
	OrderItem selectByCourseIdAndUserId(@Param("courseId") String courseId, @Param("userId") String userId);
}
