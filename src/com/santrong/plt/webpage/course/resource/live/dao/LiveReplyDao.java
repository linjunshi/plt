package com.santrong.plt.webpage.course.resource.live.dao;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveReplyItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 上午11:14:01
 */
public class LiveReplyDao extends BaseDao {

	/**
	 * 新增一条直播答到的记录
	 * @author huangweihua
	 * @param liveReplyItem
	 * @return 
	 */
	public int insert(LiveReplyItem liveReplyItem) {
		LiveReplyMapper mapper = this.getMapper(LiveReplyMapper.class);
		if(mapper != null) {
			return mapper.insert(liveReplyItem);
		}
		return 0;
	}
	
	/**
	 * 查询是否存在
	 * @author huangweihua
	 * @param callId userId
	 * @return 
	 */
	public boolean exists(String callId, String userId) {
		LiveReplyMapper mapper = this.getMapper(LiveReplyMapper.class);
		if(mapper != null) {
			return mapper.exists(callId, userId) > 0;
		}
		return false;
	}
}
