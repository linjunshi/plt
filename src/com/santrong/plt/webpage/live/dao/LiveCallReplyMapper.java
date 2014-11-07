package com.santrong.plt.webpage.live.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.live.entry.LiveCallReplyItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 下午3:53:38
 */
public interface LiveCallReplyMapper {
	
	/**
	 * 获取直播课堂点名已签到的学生
	 * @param callNameId
	 * @param liveId
	 * @return
	 */
	@Select("select a.*, b.userId from resource_live_call a LEFT JOIN resource_live_reply b on a.id = b.callId where a.callName = #{callNameId} and a.liveId = #{liveId}")
	List<LiveCallReplyItem> selectUserID(@Param("callNameId")String callNameId, @Param("liveId")String liveId);
}
