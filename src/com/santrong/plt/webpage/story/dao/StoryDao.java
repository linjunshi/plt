package com.santrong.plt.webpage.story.dao;

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
import com.santrong.plt.webpage.story.entry.StoryAttendItem;
import com.santrong.plt.webpage.story.entry.StoryItem;
import com.santrong.plt.webpage.story.entry.StoryQuery;

/**
 * @author huangweihua
 * @Date 2015年2月2日 
 * @Time 下午6:05:06
 */
public class StoryDao extends BaseDao{
	
	public boolean insert(StoryItem storyItem){
		try {
			StoryMapper mapper = this.getMapper(StoryMapper.class);
			if (mapper != null) {
				return mapper.insert(storyItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	} 
	
	public boolean update(StoryItem storyItem){
		try {
			StoryMapper mapper = this.getMapper(StoryMapper.class);
			if (mapper != null) {
				return mapper.update(storyItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	} 
	
	public boolean delete(String id){
		try {
			StoryMapper mapper = this.getMapper(StoryMapper.class);
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
			StoryMapper mapper = this.getMapper(StoryMapper.class);
			if (mapper != null) {
				return mapper.selectById(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	} 
	
	public StoryItem selectByEname(String storyEname){
		try {
			StoryMapper mapper = this.getMapper(StoryMapper.class);
			if (mapper != null) {
				return mapper.selectByEname(storyEname);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	} 	
	
	/**
	 * 获取当前用户的浏览过的剧本故事列表
	 * @param query
	 * @return
	 */
	public 	List<StoryItem> selectStoryHistoryByQuery(StoryQuery query) {
		List<StoryItem> list = new ArrayList<StoryItem>();
		
		try{
			Statement criteria = new Statement("story", "a");
			criteria.setFields("a.*");
			criteria.rjoin("story_attend_history", "b", "a.id", "b.storyId");

			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.storyName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 当前用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("b.userId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 课程类型
			criteria.where(eq("a.storyType", "?"));
			criteria.setIntParam(query.getStoryType());
			// 状态
//			criteria.where(eq("a.status", "?"));
//			criteria.setIntParam(query.getStatus());
			//观看
			criteria.where(eq("b.attendType", StoryAttendItem.Type_View));			
			// 排序
			if(!StringUtils.isNullOrEmpty(query.getOrderBy())) {
				if("desc".equalsIgnoreCase(query.getOrderRule())) {
					if (query.getOrderBy().indexOf('.') != -1 && query.getOrderBy().split("\\.")[0].equals("b")) {
						criteria.desc(query.getOrderBy());
					} else{
						criteria.desc("a." + query.getOrderBy());
					}
				}else {
					if (query.getOrderBy().indexOf('.') != -1 && query.getOrderBy().split("\\.")[0].equals("b")) {
						criteria.asc(query.getOrderBy());
					} else{
						criteria.asc("a." + query.getOrderBy());
					}
				}
			}
			// 分页
			criteria.limit(query.getLimitBegin(), query.getLimitEnd());
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				StoryItem item = new StoryItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}	
	
	/**
	 * 根据搜索条件查询剧本故事总数
	 * @param query
	 * @return
	 */
	public 	int selectCountHistoryByQuery(StoryQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("story", "a");
			criteria.setFields("count(*) cn");
			criteria.rjoin("story_attend_history", "b", "a.id", "b.storyId");

			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.storyName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 当前用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("b.userId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 课程类型
			criteria.where(eq("a.storyType", "?"));
			criteria.setIntParam(query.getStoryType());
			// 状态
//			criteria.where(eq("a.status", "?"));
//			criteria.setIntParam(query.getStatus());
			//观看
			criteria.where(eq("b.attendType", StoryAttendItem.Type_View));			
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
	
	/**
	 * 获取剧本故事的所有记录
	 * @param query
	 * @return
	 */
	public List<StoryItem> selectAllStoryByQuery(StoryQuery query){
		List<StoryItem> list = new ArrayList<StoryItem>();
		try {
			Statement criteria = new Statement("story", "a");
			criteria.setFields("a.*");
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.storyName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 当前用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("b.userId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 课程类型
			criteria.where(eq("a.storyType", "?"));
			criteria.setIntParam(query.getStoryType());
			
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
			while (rs.next()) {
				StoryItem item = new StoryItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return list;
	}
	
	/**
	 * 获取剧本故事的所有记录的总数
	 * @param query
	 * @return
	 */
	public int selectCountByQuery(StoryQuery query){
		int count = 0;
		
		try {
			Statement criteria = new Statement("story", "a");
			criteria.setFields("count(*) cn");
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.storyName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 当前用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("b.userId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 课程类型
			criteria.where(eq("a.storyType", "?"));
			criteria.setIntParam(query.getStoryType());
			
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
			rs.next();
			count = rs.getInt("cn");
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return count;
	}
}
