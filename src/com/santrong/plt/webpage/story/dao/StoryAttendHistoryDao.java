package com.santrong.plt.webpage.story.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.story.entry.StoryAttendItem;
import com.santrong.plt.webpage.story.entry.StoryItem;

/**
 * @author huangweihua
 * @Date 2015年2月2日 
 * @Time 下午6:05:06
 */
public class StoryAttendHistoryDao extends BaseDao{
	
	public boolean insert(StoryAttendItem storyAttendItem){
		try {
			StoryAttendHistoryMapper mapper = this.getMapper(StoryAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.insert(storyAttendItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	} 
	
	public boolean update(StoryAttendItem storyAttendItem){
		try {
			StoryAttendHistoryMapper mapper = this.getMapper(StoryAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.update(storyAttendItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	} 
	
	public boolean delete(String id){
		try {
			StoryAttendHistoryMapper mapper = this.getMapper(StoryAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.delete(id) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	} 
	
	public StoryItem selectById(String id){
		try {
			StoryAttendHistoryMapper mapper = this.getMapper(StoryAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.selectById(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	} 
}
