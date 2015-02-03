package com.santrong.plt.webpage.course.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.CourseAttendHistoryItem;

/**
 * @author huangweihua
 * @Date 2015年2月2日 
 * @Time 下午2:51:39
 */
public class CourseAttendHistoryDao extends BaseDao{

	/**
	 * 添加一条课程浏览记录
	 * @param courseAttendHistoryItem
	 * @return
	 */
	public boolean insert(CourseAttendHistoryItem courseAttendHistoryItem){
		try {
			CourseAttendHistoryMapper mapper = this.getMapper(CourseAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.insert(courseAttendHistoryItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 修改课程浏览时间
	 * @param courseAttendHistoryItem
	 * @return
	 */
	public boolean update(CourseAttendHistoryItem courseAttendHistoryItem){
		try {
			CourseAttendHistoryMapper mapper = this.getMapper(CourseAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.update(courseAttendHistoryItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 判断是否已经存在浏览过的历史记录
	 * @param userId
	 * @param courseId
	 * @param attendType
	 * @return
	 */
	public boolean exists(String userId, String courseId){
		try {
			CourseAttendHistoryMapper mapper = this.getMapper(CourseAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.exists(userId, courseId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 获取一条已经存在浏览过的历史记录
	 * @param userId
	 * @param courseId
	 * @param attendType
	 * @return
	 */
	public CourseAttendHistoryItem select(String userId, String courseId){
		try {
			CourseAttendHistoryMapper mapper = this.getMapper(CourseAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.select(userId, courseId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 移除一条历史记录
	 * @param id
	 * @return
	 */
	public boolean removeAttendHistory(String id){
		try {
			CourseAttendHistoryMapper mapper = this.getMapper(CourseAttendHistoryMapper.class);
			if (mapper != null) {
				return mapper.removeAttendHistory(id) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
}
