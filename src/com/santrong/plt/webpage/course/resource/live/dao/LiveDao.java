package com.santrong.plt.webpage.course.resource.live.dao;

import java.util.List;

import com.santrong.plt.log.Log;
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
		try {
			LiveMapper mapper = this.getMapper(LiveMapper.class);
			if(mapper != null) {
				return mapper.selectAll();
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
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
	
	/**
	 * 删除一条直播记录(id)
	 * @author huangweihua
	 * @param id
	 * @return boolean
	 */
	public boolean delete(String id) {
		try {
			LiveMapper mapper = this.getMapper(LiveMapper.class);
			if(mapper != null) {
				return mapper.delete(id) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}	
	
	/**
	 * 新增一条直播记录
	 * @author huangweihua
	 * @param liveItem
	 * @return boolean
	 */
	public boolean insert(LiveItem liveItem) {
		try {
			LiveMapper mapper = this.getMapper(LiveMapper.class);
			if(mapper != null) {
				return mapper.insert(liveItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 修改一条直播记录
	 * @author huangweihua
	 * @param liveItem
	 * @return boolean
	 */
	public boolean update(LiveItem liveItem) {
		try {
			LiveMapper mapper = this.getMapper(LiveMapper.class);
			if(mapper != null) {
				return mapper.update(liveItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
}
