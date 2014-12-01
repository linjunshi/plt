package com.santrong.plt.webpage.teacher.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.teacher.entry.UserEducationItem;

/**
 * @author huangweihua
 * @date   2014年11月14日 
 * @time   上午10:21:57
 */
public interface UserEducationMapper {

	@Insert("insert into user_education values(#{userId}, #{education}, #{positional}, #{graduateSchool})")
	int insert(UserEducationItem userEducationItem);

	@Update("update user_education set "
			+ "education = #{education},"
			+ "positional = #{positional},"
			+ "graduateSchool = #{graduateSchool} "
			+ "where userId = #{userId}")
	int update(UserEducationItem userEducationItem);
	
	@Select("select count(*) as cn from user_education where userId = #{userId}")
	int existsByUserId(String userId);
	
	@Select("select * from user_education where userId = #{userId}")
	UserEducationItem selectByUserId(String userId);
}
