package com.santrong.plt.webpage.course.resource.train.dao;

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
import com.santrong.plt.webpage.course.resource.train.entry.TrainItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuery;
import com.santrong.plt.webpage.manage.student.entry.TrainSimpleView;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainDao extends BaseDao{

	/**
	 * 新增一条作业的记录
	 * @author huangweihua
	 * @param TrainItem
	 * @return int
	 */
	public int insert(TrainItem trainItem){
		try {
			TrainMapper mapper = this.getMapper(TrainMapper.class);
			if (mapper != null) {
				return mapper.insert(trainItem);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 查询所属用户的所有作业(del = 0)
	 * @author huangweihua
	 * @param userId,del = 0
	 * @return List<TrainItem>
	 */
	public List<TrainItem> selectByUserId(String userId) {
		try {
			TrainMapper mapper = this.getMapper(TrainMapper.class);
			if (mapper != null) {
				return mapper.selectByUserId(userId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	public TrainItem selectById(String trainId) {
		try {
			TrainMapper mapper = this.getMapper(TrainMapper.class);
			if (mapper != null) {
				return mapper.selectById(trainId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}	
	
	/**
	 * 获取某个直播的随堂作业，如果还没有则返回null
	 * @param liveId
	 * @return
	 */
	public TrainItem selectByLiveId(String liveId) {
		try {
			TrainMapper mapper = this.getMapper(TrainMapper.class);
			if (mapper != null) {
				return mapper.selectByLiveId(liveId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 根据条件查询作业
	 * @param query
	 * @return
	 */
	public List<TrainSimpleView> selectByQuery(TrainQuery query) {
		List<TrainSimpleView> list = new ArrayList<TrainSimpleView>();
		
		try{
			Statement criteria = new Statement("resource_train", "a");
			criteria.setFields("a.id,b.title,c.remark,d.courseName,e.cts");
			criteria.ljoin("course_chapter_to_resource", "b", "a.id", "b.resourceId");
			criteria.ljoin("course_chapter", "c", "b.chapterId", "c.id");
			criteria.ljoin("course", "d", "c.courseId", "d.id");
			criteria.ljoin("web_order", "e", "d.id", "e.courseId");
			
			// 未删除
			criteria.where(ne("a.del", "1"));
			
			// 所属用户
			if(MyUtils.isNotNull(query.getOwnerId())) {
				criteria.where(eq("e.userId", "?"));
				criteria.setStringParam(query.getOwnerId());
			}
			// 排序
			if(!StringUtils.isNullOrEmpty(query.getOrderBy())) {
				if("desc".equalsIgnoreCase(query.getOrderRule())) {
					criteria.desc("e." + query.getOrderBy());
				}else {
					criteria.asc("e." + query.getOrderBy());
				}
			}
			
			// 分页
			criteria.limit(query.getLimitBegin(), query.getLimitEnd());
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				TrainSimpleView item = new TrainSimpleView();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}
	
	/**
	 * 根据条件查询作业总数
	 * @param query
	 * @return
	 */
	public int selectCountByQuery(TrainQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("resource_train", "a");
			criteria.setFields("count(*) cn");
			criteria.ljoin("course_chapter_to_resource", "b", "a.id", "b.resourceId");
			criteria.ljoin("course_chapter", "c", "b.chapterId", "c.id");
			criteria.ljoin("course", "d", "c.courseId", "d.id");
			criteria.ljoin("web_order", "e", "d.id", "e.courseId");			
			
			// 未删除
			criteria.where(ne("a.del", "1"));			
			
			// 所属用户
			if(MyUtils.isNotNull(query.getOwnerId())) {
				criteria.where(eq("e.userId", "?"));
				criteria.setStringParam(query.getOwnerId());
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
