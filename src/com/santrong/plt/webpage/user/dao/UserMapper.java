package com.santrong.plt.webpage.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.user.entry.UserDetailView;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @Author weinianjie
 * @Date 2014-7-4
 * @Time 下午10:33:09
 */
public interface UserMapper {
	
    @Select("select * from user where username=#{username}")
    UserItem selectByUserName(@Param("username") String username);
    
    @Select("select * from user where id=#{id}")
    UserItem selectById(String id);
    
    @Select("select count(*) as cn from user where username=#{username}")
    int existsByUserName(String username);
    
    @Insert("insert into user values(#{id}, #{showName}, #{username}, #{password}, #{url}, #{gender}, #{role}, #{schoolId}, #{subjectId}, #{registIp}, #{registTime}, #{lastLoginIp}, #{lastLoginTime}, #{remark}, #{cts}, #{uts})")
    int insert(UserItem user);
    
    @Update("update user set "
    		+ "showName=#{showName}, "
    		+ "username=#{username}, "
    		+ "password=#{password}, "
    		+ "url=#{url}, "
    		+ "gender=#{gender}, "
    		+ "role=#{role}, "
    		+ "schoolId=#{schoolId}, "
    		+ "subjectId=#{subjectId}, "
    		+ "registIp=#{registIp}, "
    		+ "registTime=#{registTime}, "
    		+ "lastLoginIp=#{lastLoginIp}, "
    		+ "lastLoginTime=#{lastLoginTime}, "
    		+ "remark=#{remark}, "
    		+ "cts=#{cts} "
    		+ "where id=#{id}")
    int update(UserItem user);
    
    @Select("select a.*, b.education, b.positional, b.graduateSchool, c.birthday, c.nativePlace, d.subjectName, e.schoolName from user a "
    		+ "LEFT JOIN user_education b on a.id = b.userId "
    		+ "LEFT JOIN user_extends c on a.id = c.userId "
    		+ "LEFT JOIN subject d on d.id = a.subjectId "
    		+ "LEFT JOIN school e on e.id = a.schoolId "
    		+ "where a.id = #{id};")
    UserDetailView selectDetailById(String id);

    // 查询学校里的老师 role
 	@Select("select * from user where schoolId = #{schoolId}")
 	List<UserItem> selectTeacherBySchoolId(String schoolId);
}