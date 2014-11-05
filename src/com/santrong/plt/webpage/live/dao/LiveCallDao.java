package com.santrong.plt.webpage.live.dao;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.live.entry.LiveCallItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 上午11:14:01
 */
public class LiveCallDao extends BaseDao {

	/**
	 * 查询直播点名的ID
	 * @author huangweihua
	 * @param liveId, callName
	 * @return 
	 */
	public LiveCallItem selectId(String liveId, String callName) {
		LiveCallMapper mapper = this.getMapper(LiveCallMapper.class);
		if(mapper != null) {
			return mapper.selectId(liveId, callName);
		}
		return null;
	}
}
