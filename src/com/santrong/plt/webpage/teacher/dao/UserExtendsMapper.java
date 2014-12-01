package com.santrong.plt.webpage.teacher.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.teacher.entry.UserExtendsItem;

/**
 * @author huangweihua
 * @date   2014年11月14日 
 * @time   上午10:21:57
 */
public interface UserExtendsMapper {

	@Insert("insert into user_extends values(#{userId}, #{birthday}, #{nativePlace})")
	int insert(UserExtendsItem userExtendsItem);

	@Update("update user_education set "
			+ "birthday = #{birthday},"
			+ "nativePlace = #{nativePlace} "
			+ "where userId = #{userId}")
	int update(UserExtendsItem userExtendsItem);
	
	@Select("select count(*) as cn from user_extends where userId = #{userId}")
	int existsByUserId(String userId);
	
	@Select("select * from user_extends where userId = #{userId}")
	UserExtendsItem selectByUserId(String userId);
}
