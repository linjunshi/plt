package com.santrong.plt.webpage.course.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.CollectionItem;

/**
 * @author huangweihua
 * @date	2014年10月30日 
 * @time	下午4:14:26
 */
public class CollectCourseDao extends BaseDao{
	
	public int insert(CollectionItem collectionItem){
		try {
			CollectCourseMapper mapper = this.getMapper(CollectCourseMapper.class);
			return mapper.insert(collectionItem);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	public boolean exists(String courseId, String userId){
		try {
			CollectCourseMapper mapper = this.getMapper(CollectCourseMapper.class);
			return mapper.exists(courseId, userId) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
}
