package com.santrong.plt.webpage.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @Author weinianjie
 * @Date 2014-7-4
 * @Time 下午10:33:09
 */
public interface UserMapper {
	
    @Select("select * from web_user where username=#{username}")
    UserItem selectByUserName(@Param("username") String username);
    
    @Select("select * from web_user where id=#{id}")
    UserItem selectById(String id);
    
    @Update("update web_user set showName=#{showName}, username=#{username}, password=#{password}, role=#{role}, cts=#{cts} where id=#{id}")
    int update(UserItem user);
    
    @Select("select * from web_user limit 4;")
    List<UserItem> selectAll();
    
}