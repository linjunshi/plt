package com.santrong.plt.webpage.course.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.OrderItem;

/**
 * @author weinianjie
 * @date	2014年10月30日 
 * @time	下午4:14:26
 */
public class OrderDao extends BaseDao{
	
	/**
	 * 新增
	 * @param OrderItem
	 * @return
	 */
	public int insert(OrderItem order){
		try {
			OrderMapper mapper = this.getMapper(OrderMapper.class);
			return mapper.insert(order);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 更新
	 * @param OrderItem
	 * @return
	 */
	public int update(OrderItem order){
		try {
			OrderMapper mapper = this.getMapper(OrderMapper.class);
			return mapper.update(order);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}	
	
	/**
	 * 删除
	 * @param order
	 * @return
	 */
	public int delete(String id){
		try {
			OrderMapper mapper = this.getMapper(OrderMapper.class);
			return mapper.delete(id);
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
			OrderMapper mapper = this.getMapper(OrderMapper.class);
			return mapper.exists(courseId, userId) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	
	public OrderItem selectById(String id){
		try {
			OrderMapper mapper = this.getMapper(OrderMapper.class);
			return mapper.selectById(id);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}		
	
	/**
	 * 根据课程ID和用户ID获取某个订单
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public OrderItem selectByCourseIdAndUserId(String courseId, String userId){
		try {
			OrderMapper mapper = this.getMapper(OrderMapper.class);
			return mapper.selectByCourseIdAndUserId(courseId, userId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}	
	
}
