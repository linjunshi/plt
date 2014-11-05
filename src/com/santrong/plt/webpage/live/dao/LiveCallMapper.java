package com.santrong.plt.webpage.live.dao;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.live.entry.LiveCallItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 上午11:04:41
 */
public interface LiveCallMapper {

	@Select("select * from resource_live_call where liveId = #{liveId} and callName = #{callName}")
	LiveCallItem selectId(String liveId, String callName);
	
}
