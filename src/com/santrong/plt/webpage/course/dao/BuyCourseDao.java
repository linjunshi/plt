package com.santrong.plt.webpage.course.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.BuyItem;

/**
 * @author weinianjie
 * @date	2014年10月30日 
 * @time	下午4:14:26
 */
public class BuyCourseDao extends BaseDao{
	
	/**
	 * 购买课程
	 * @param BuyItem
	 * @return
	 */
	public int insert(BuyItem buy){
		try {
			BuyCourseMapper mapper = this.getMapper(BuyCourseMapper.class);
			if (mapper != null) {
				return mapper.insert(buy);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}

	/**
	 * 判断是否已经购买该课程
	 * @author weinianjie
	 * @param courseId
	 * @param userId
	 * @return boolean
	 */
	public boolean exists(String courseId, String userId){
		try {
			BuyCourseMapper mapper = this.getMapper(BuyCourseMapper.class);
			if (mapper != null) {
				return mapper.exists(courseId, userId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
}
