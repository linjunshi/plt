package com.santrong.plt.webpage.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.criteria.Statement;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.BeanUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.CourseBuyQuery;
import com.santrong.plt.webpage.course.entry.OrderCourseView;
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
	
	/**
	 * 根据具体条件查询订单总数量
	 * @param query
	 * @return
	 */
	public int selectCountByQuery(CourseBuyQuery query) {
		int count = 0;
		try {
			Statement criteria = new Statement("web_order", "a");
			criteria.setFields("count(*) cn");
			criteria.ljoin("course", "b", "a.courseId","b.id");
			
			// 关键词
			if (!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(like("b.courseName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 所属用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.userId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 交易状态
			if (query.getOrderStatus() != null) {
				criteria.where(eq("a.status", "?"));
				criteria.setIntParam(query.getOrderStatus());
			}
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			rs.next();
			count = rs.getInt("cn");
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return count;
	}
	
	/**
	 * 根据具体条件查询订单列表
	 * @param query
	 * @return
	 */
	public List<OrderCourseView> selectByQuery(CourseBuyQuery query) {
		List<OrderCourseView> list = new ArrayList<OrderCourseView>();
		try {
			Statement criteria = new Statement("web_order", "a");
			criteria.setFields("a.*,b.courseName,b.live,b.teacher,b.url,b.chaptercount,b.ownerId");
			criteria.ljoin("course", "b", "a.courseId","b.id");
			
			// 关键词
			if (!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(like("b.courseName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 所属用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.userId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 交易状态
			if (query.getOrderStatus() != null) {
				criteria.where(eq("a.status", "?"));
				criteria.setIntParam(query.getOrderStatus());
			}
			
			// 排序
			if (!StringUtils.isNullOrEmpty(query.getOrderBy())) {
				if ("desc".equalsIgnoreCase(query.getOrderRule())) {
					criteria.desc("a." + query.getOrderBy());
				} else {
					criteria.asc("a." + query.getOrderBy());
				}
			}
			
			// 分页
			criteria.limit(query.getLimitBegin(), query.getLimitEnd());
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				OrderCourseView ocView = new OrderCourseView();
				BeanUtils.Rs2Bean(rs, ocView);
				list.add(ocView);
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return list;
	}
}
