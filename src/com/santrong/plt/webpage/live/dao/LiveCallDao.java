package com.santrong.plt.webpage.live.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.live.entry.LiveCallItem;

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
		try {
			LiveCallMapper mapper = this.getMapper(LiveCallMapper.class);
			if(mapper != null) {
				return mapper.insert(liveCallItem);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
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
		try {
			LiveCallMapper mapper = this.getMapper(LiveCallMapper.class);
			if(mapper != null) {
				return mapper.selectByCall(liveId, callName);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
}
