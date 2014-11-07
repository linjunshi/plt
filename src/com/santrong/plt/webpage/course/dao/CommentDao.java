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
import com.santrong.plt.webpage.course.entry.CommentItem;
import com.santrong.plt.webpage.course.entry.CommentQuery;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午12:10:43
 */
public class CommentDao extends BaseDao {

	/**
	 * 根据课程ID获取评论
	 * @param courseId
	 * @return
	 */
	public List<CommentItem> selectByCourseId(String courseId) {
		CommentMapper mapper = this.getMapper(CommentMapper.class);
		if(mapper != null) {
			return mapper.selectByCourseId(courseId);
		}
		return null;
	}
	
	/**
	 * 新增一条课程评论记录
	 * @param id，userId，courseId，remark，cts，uts
	 * @return
	 */
	public int insert(CommentItem commentItem){
		CommentMapper mapper = this.getMapper(CommentMapper.class);
		if(mapper != null) {
			return mapper.insert(commentItem);
		}
		return 0;
	}
	
	/**
	 * 删除一条课程评论记录
	 * @param id
	 * @return
	 */
	public int delete(String id){
		CommentMapper mapper = this.getMapper(CommentMapper.class);
		if(mapper != null) {
			return mapper.delete(id);
		}
		return 0;
	}
	
	/**
	 * 根据条件查询评论
	 * @param query
	 * @return
	 */
	public List<CommentItem> selectByQuery(CommentQuery query) {
		List<CommentItem> list = new ArrayList<CommentItem>();
		
		try{
			Statement criteria = new Statement("course_comment", "a");
			
			// 所属用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.userId", "?"));
				criteria.setStringParam(query.getUserId());
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
				CommentItem item = new CommentItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}
	
	/**
	 * 根据条件查询评论总数
	 * @param query
	 * @return
	 */
	public int selectCountByQuery(CommentQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("course_comment", "a");
			criteria.setFields("count(*) cn");
			
			// 所属用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.userId", "?"));
				criteria.setStringParam(query.getUserId());
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
