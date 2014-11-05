package com.santrong.plt.webpage.live.dao;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.live.entry.LiveScoreItem;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:33:54
 */
public class LiveDao extends BaseDao {

	/**
	 * 新增一条直播评分记录
	 * @author huangweihua
	 * @param liveScoreItem
	 * @return int
	 */
	public int insert(LiveScoreItem liveScoreItem) {
		LiveMapper mapper = this.getMapper(LiveMapper.class);
		if(mapper != null) {
			return mapper.insert(liveScoreItem);
		}
		return 0;
	}
}
