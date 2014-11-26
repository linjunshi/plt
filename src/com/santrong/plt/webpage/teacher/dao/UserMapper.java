package com.santrong.plt.webpage.teacher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.teacher.entry.UserCourseView;
import com.santrong.plt.webpage.teacher.entry.UserDetailView;
import com.santrong.plt.webpage.teacher.entry.UserItem;
import com.santrong.plt.webpage.teacher.entry.UserTmpItem;

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
    
    /**
	 * 查询多个用户信息
	 * @param ids
	 * @return
	 */
    @Select("select * from user where id in (${ids})")
    List<UserItem> selectByIds(@Param("ids")String ids);
    
    /**
	 * 查询多个用户信息,不包括ids里的用户
	 * @param ids
	 * @return
	 */
    @Select("select * from user where id not in (${ids})")
    List<UserItem> selectByNotInIds(@Param("ids")String ids);
    
    @Select("select count(*) as cn from user where username=#{username}")
    int existsByUserName(String username);
    
    @Select("select count(*) as cn from user where email=#{email}")
    int existsByEmail(String email);
    
    @Select("select count(*) as cn from user where phone=#{phone}")
    int existsByPhone(String phone);
    
    @Insert("insert into user values(#{id}, #{showName}, #{username}, #{password}, #{url}, #{gender}, #{role}, #{schoolId}, #{subjectId}, #{email}, #{phone}, #{idCard}, #{registIp}, #{registTime}, #{lastLoginIp}, #{lastLoginTime}, #{remark}, #{cts}, #{uts})")
    int insert(UserItem user);

    @Insert("insert into user_tmp values(#{userId}, #{activeCode}, #{cts})")
    int insertTmp(UserTmpItem tmp);
    
    @Select("select * from user_tmp where userId=#{userId}")
    UserTmpItem selectTmpByUserId(String userId);
    
    @Update("update user_tmp set activeCode=#{activeCode},cts=#{cts} where userId=#{userId}")
    int updateTmp(UserTmpItem tmp);
    
    @Update("update user set "
    		+ "showName=#{showName}, "
    		+ "username=#{username}, "
    		+ "password=#{password}, "
    		+ "url=#{url}, "
    		+ "gender=#{gender}, "
    		+ "role=#{role}, "
    		+ "schoolId=#{schoolId}, "
    		+ "subjectId=#{subjectId}, "
    		+ "email=#{email}, "
    		+ "phone=#{phone}, "
    		+ "idCard=#{idCard}, "
    		+ "registIp=#{registIp}, "
    		+ "registTime=#{registTime}, "
    		+ "lastLoginIp=#{lastLoginIp}, "
    		+ "lastLoginTime=#{lastLoginTime}, "
    		+ "remark=#{remark}, "
    		+ "uts=#{uts} "
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
 	
 	/**
 	 * 查询课程所有者的信息，包含姓名，所属学校，教学科目，性别，课程数，注册时间
 	 * @author huangweihua
 	 * @param id
 	 * @return
 	 */
 	@Select("select a.*, b.schoolName, b.schoolEnName, b.schoolGrade, b.areaCode, d.courseCount, e.subjectName, e.subjectEnName, e.priority, f.education, f.positional, f.graduateSchool from user a "
 			+ "LEFT JOIN school b on a.schoolId = b.id "
 			+ "LEFT JOIN (select count(c.ownerId) courseCount ,c.ownerId from course c GROUP BY c.ownerId having c.ownerId = #{id}) d on d.ownerId = a.id "
 			+ "LEFT JOIN subject e on e.id = a.subjectId "
 			+ "LEFT JOIN user_education f on f.userId = a.id "
 			+ "where a.id = #{id};")
 	UserCourseView selectTeacherByUserId(String id);

}
