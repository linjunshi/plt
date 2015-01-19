package com.santrong.plt.webpage.course.resource.video.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.video.entry.VideoItem;

/**
 * @author weinianjie
 * @date 2015年1月19日
 * @time 下午2:55:16
 */
public class VideoDao extends BaseDao {
	
	// 根据视频资源id查询视频资源
	public VideoItem selectById(String id) {
		try {
			VideoMapper mapper = this.getMapper(VideoMapper.class);
			if (mapper != null) {
				return mapper.selectById(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	public int insert(VideoItem item) {
		try {
			VideoMapper mapper = this.getMapper(VideoMapper.class);
			if (mapper != null) {
				return mapper.insert(item);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	public int delete(String id) {
		try {
			VideoMapper mapper = this.getMapper(VideoMapper.class);
			if (mapper != null) {
				return mapper.delete(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
}
