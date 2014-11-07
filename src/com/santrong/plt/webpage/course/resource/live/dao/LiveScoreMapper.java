package com.santrong.plt.webpage.course.resource.live.dao;

import org.apache.ibatis.annotations.Insert;

import com.santrong.plt.webpage.course.resource.live.entry.LiveScoreItem;

/**
 * @author huangweihua
 * @date 2014年11月6日 
 * @time 上午9:58:39
 */
public interface LiveScoreMapper {

	@Insert("insert into resource_live_score values(#{id}, #{liveId}, #{userId}, #{score}, #{cts})")
	int insert(LiveScoreItem liveScoreItem);
	
}
