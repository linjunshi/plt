package com.santrong.plt.webpage.friend.dao;

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
import com.santrong.plt.webpage.friend.entry.UserQuery;
import com.santrong.plt.webpage.friend.entry.UserRelationItem;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2015年2月3日
 * @time 下午3:12:26
 */
public class UserRelationDao extends BaseDao {


	/**
	 * 查询好友申请的最新消息
	 * @param userId
	 * @return
	 */
	public List<UserRelationItem> selectMsgList(String userId) {
		try{
			UserRelationMapper mapper = this.getMapper(UserRelationMapper.class);
			return mapper.selectMsgList(userId);
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 根据两用户查询关系
	 * @param userId1
	 * @param userId2
	 * @return
	 */
	public UserRelationItem selectByTwoUser(String userId1, String userId2) {
		try{
			UserRelationMapper mapper = this.getMapper(UserRelationMapper.class);
			return mapper.selectByTwoUser(userId1, userId2);
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		return null;		
	}
	
	/**
	 * 新增关系
	 * @param item
	 * @return
	 */
	public int insert(UserRelationItem item) {
		try{
			UserRelationMapper mapper = this.getMapper(UserRelationMapper.class);
			return mapper.insert(item);
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		return 0;	
	}
	
	/**
	 * 用户搜索
	 * @param query
	 * @return
	 */
	public 	List<UserItem> selectByQuery(UserQuery query) {
		List<UserItem> list = new ArrayList<UserItem>();
		
		try{
			Statement criteria = new Statement("user", "a");
			criteria.setFields("a.*");
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.username", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 排出当前登录用户
			if(MyUtils.isNotNull(query.getCurrentUserId())) {
				criteria.where(ne("a.id", "?"));
				criteria.setStringParam(query.getCurrentUserId());
			}
			// 排序
			if(!StringUtils.isNullOrEmpty(query.getOrderBy())) {
				if("desc".equalsIgnoreCase(query.getOrderRule())) {
					criteria.desc("a." + query.getOrderBy());
				}else {
					criteria.asc("a." + query.getOrderBy());
				}
			}
			
			// 分页
			criteria.limit(query.getLimitBegin(), query.getLimitEnd());
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				UserItem item = new UserItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}	
	
	/**
	 * 用户搜索总数
	 * @param query
	 * @return
	 */
	public 	int selectCountByQuery(UserQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("user", "a");
			criteria.setFields("count(*) cn");
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.username", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 排出当前登录用户
			if(MyUtils.isNotNull(query.getCurrentUserId())) {
				criteria.where(ne("a.id", "?"));
				criteria.setStringParam(query.getCurrentUserId());
			}
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			rs.next();
			count = rs.getInt("cn");
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return count;
	}
}
