package com.santrong.plt.webpage.live.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.live.entry.LiveItem;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:33:54
 */
public class LiveDao extends BaseDao {

	/**
	 * 获取当天直播列表(today)
	 * @author huangweihua
	 * @param 
	 * @return List<LiveItem>
	 */
	public List<LiveItem> selectByToday() {
		try {
			LiveMapper mapper = this.getMapper(LiveMapper.class);
			if(mapper != null) {
				return mapper.selectByToday();
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 获取直播列表(id)
	 * @author huangweihua
	 * @param id
	 * @return LiveItem
	 */
	public LiveItem selectById(String id) {
		try {
			LiveMapper mapper = this.getMapper(LiveMapper.class);
			if(mapper != null) {
				return mapper.selectById(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
}
