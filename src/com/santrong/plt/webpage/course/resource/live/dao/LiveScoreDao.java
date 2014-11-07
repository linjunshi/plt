package com.santrong.plt.webpage.course.resource.live.dao;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveScoreItem;

/**
 * @author huangweihua
 * @date 2014年11月6日 
 * @time 上午9:59:22
 */
public class LiveScoreDao extends BaseDao {

	/**
	 * 新增一条直播评分记录
	 * @author huangweihua
	 * @param liveScoreItem
	 * @return int
	 */
	public int insert(LiveScoreItem liveScoreItem) {
		LiveScoreMapper mapper = this.getMapper(LiveScoreMapper.class);
		if(mapper != null) {
			return mapper.insert(liveScoreItem);
		}
		return 0;
	}
}
