package com.santrong.plt.webpage.course.resource.live.dao;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveCallItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 上午11:14:01
 */
public class LiveCallDao extends BaseDao {

	
	/**
	 * 新增一条直播点名的记录
	 * @author huangweihua
	 * @param liveCallItem
	 * @return int
	 */
	public int insert(LiveCallItem liveCallItem) {
		LiveCallMapper mapper = this.getMapper(LiveCallMapper.class);
		if(mapper != null) {
			return mapper.insert(liveCallItem);
		}
		return 0;
	}
	
	/**
	 * 查询直播点名的ID
	 * @author huangweihua
	 * @param liveId, callName
	 * @return 
	 */
	public LiveCallItem selectByCall(String liveId, String callName) {
		LiveCallMapper mapper = this.getMapper(LiveCallMapper.class);
		if(mapper != null) {
			return mapper.selectByCall(liveId, callName);
		}
		return null;
	}
}
