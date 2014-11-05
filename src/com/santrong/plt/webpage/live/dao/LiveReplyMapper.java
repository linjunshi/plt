package com.santrong.plt.webpage.live.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.live.entry.LiveReplyItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 上午11:04:41
 */
public interface LiveReplyMapper {

	@Insert("insert into resource_live_reply values(#{id}, #{callId}, #{userId}, #{ctx})")
	int insert(LiveReplyItem liveReplyItem);
	
	@Select("select count(*) from resource_live_Reply where callId = #{callId} and userId = #{userId}")
	int exists(@Param("callId")String callId, @Param("userId")String userId);
}
