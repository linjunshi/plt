package com.santrong.plt.webpage.live.dao;

import org.apache.ibatis.annotations.Insert;

import com.santrong.plt.webpage.live.entry.LiveScoreItem;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:19:47
 */
public interface LiveMapper {

	@Insert("insert into user values(#{id}, #{liveId}, #{userId}, #{score}, #{cts})")
	int insert(LiveScoreItem liveScoreItem);
	
}
