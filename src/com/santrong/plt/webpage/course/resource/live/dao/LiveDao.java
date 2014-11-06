package com.santrong.plt.webpage.course.resource.live.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveItem;

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
	public List<LiveItem> selectAll() {
		LiveMapper mapper = this.getMapper(LiveMapper.class);
		if(mapper != null) {
			return mapper.selectAll();
		}
		return null;
	}
}
