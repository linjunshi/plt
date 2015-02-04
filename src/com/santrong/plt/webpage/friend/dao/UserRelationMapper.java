package com.santrong.plt.webpage.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.friend.entry.UserRelationItem;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2015年2月3日
 * @time 下午3:12:07
 */
public interface UserRelationMapper {

	@Select("select a.*,b.showName showName1,c.showName showName2 from user_relation a "
			+ "left join user b on a.userId1=b.id "
			+ "left join user c on a.userId2=c.id "
			+ "where (a.userId1=#{userId} and result != 0) or (a.userId2=#{userId} and result = 0) order by uts desc limit 6")//作为主动方，申请的信息忽略，作为被动方，非申请的信息忽略
	List<UserRelationItem> selectMsgList(String userId);
	
	@Select("select b.* from user_relation a "
			+ "right join user b on a.userId1=b.id or a.userId2=b.id "
			+ "where b.id=#{userId} and a.result=1")
	List<UserItem> selectFriendList(String userId);
	
	@Select("select * from user_relation where (userId1=#{userId1} and userId2=#{userId2}) or (userId1=#{userId2} and userId2=#{userId1})")
	UserRelationItem selectByTwoUser(@Param("userId1")String userId1, @Param("userId2")String userId2);
	
	@Insert("insert into user_relation values(#{userId1},#{userId2},#{applyMsg},#{returnMsg},#{result},#{cts},#{uts})")
	int insert(UserRelationItem item);
	
	@Update("update user_relation set applyMsg=#{applyMsg},returnMsg=#{returnMsg},result=#{result},uts=#{uts} where userId1=#{userId1} and userId2=#{userId2}")
	int update(UserRelationItem item);
}
