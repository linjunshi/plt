package com.santrong.plt.webpage.live.dao;

import org.apache.ibatis.annotations.Insert;
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
	
	@Select("select * from resource_live_Reply where userId = #{userId} and userId = #{userId}")
	LiveReplyItem selectBy(String callId, String userId);
}
