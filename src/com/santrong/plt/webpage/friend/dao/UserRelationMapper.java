package com.santrong.plt.webpage.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.friend.entry.UserRelationItem;

/**
 * @author weinianjie
 * @date 2015年2月3日
 * @time 下午3:12:07
 */
public interface UserRelationMapper {

	@Select("select a.*,b.showName showName1,c.showName showName2 from user_relation a "
			+ "left join user b on a.userId1=b.id "
			+ "left join user c on a.userId2=c.id "
			+ "where a.userId1=#{userId} or a.userId2=#{userId} order by uts desc limit 6")
	List<UserRelationItem> selectMsgList(String userId);
}
