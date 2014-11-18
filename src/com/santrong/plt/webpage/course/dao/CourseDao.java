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
import com.santrong.plt.util.AreaUtils;
import com.santrong.plt.util.BeanUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.CourseBuyQuery;
import com.santrong.plt.webpage.course.entry.CourseCollectQuery;
import com.santrong.plt.webpage.course.entry.CourseDetailView;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseQuery;
import com.santrong.plt.webpage.course.entry.CourseView;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:54:10
 */
public class CourseDao extends BaseDao {
	
	// 根据年级和地区获取课程，首页
	public List<CourseView> selectForIndexList(int gradeGroup, String areaCode) {
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if(mapper != null) {
				return mapper.selectForIndexList(gradeGroup, AreaUtils.lostTail(areaCode) + "%");
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	// 获取课程详细信息
	public CourseDetailView selectDetailById(String id) {
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if(mapper != null) {
				return mapper.selectDetailById(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}

	
	
	/**
	 * 查询某位老师的所有课程信息
	 * @author huangweihua
	 * @param  ownerId
	 * @return List<CourseItem>
	 */
	public List<CourseItem> selectByUserId(String userId){
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if(mapper != null) {
				return mapper.selectByUserId(userId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}

	/**
	 * 根据具体搜索条件查询课程
	 * @param query
	 * @return
	 */
	public 	List<CourseItem> selectByQuery(CourseQuery query) {
		List<CourseItem> list = new ArrayList<CourseItem>();
		
		try{
			Statement criteria = new Statement("course", "a");
			criteria.setFields("a.*");
			criteria.ljoin("subject", "b", "a.subjectId", "b.id");
			criteria.ljoin("grade", "c", "a.gradeId", "c.id");
			criteria.ljoin("user", "d", "a.ownerId", "d.id");
			criteria.ljoin("school", "e", "d.schoolId", "e.id");
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.courseName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 科目条件
			if(MyUtils.isNotNull(query.getSubjectEnName())) {
				criteria.where(eq("b.subjectEnName", "?"));
				criteria.setStringParam(query.getSubjectEnName());
			}
			// 类别条件
			if(MyUtils.isNotNull(query.getGradeEnName())) {
				criteria.where(eq("c.gradeEnName", "?"));
				criteria.setStringParam(query.getGradeEnName());
			}
			// 年级条件
			if(MyUtils.isNotNull(query.getLevelEnName())) {
				criteria.where(eq("c.LevelEnName", "?"));
				criteria.setStringParam(query.getLevelEnName());
			}
			// 是否含有直播
			if(query.isLive()) {
				criteria.where(eq("a.live", "1"));
			}
			// 所属区域
			if(MyUtils.isNotNull(query.getAreaCode())) {
				criteria.where(like("e.areaCode", "?"));
				criteria.setStringParam(AreaUtils.lostTail(query.getAreaCode()) + "%");
			}
			// 所属用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.ownerId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 所属学校
			if(MyUtils.isNotNull(query.getSchoolId())) {
				criteria.where(eq("e.id", "?"));
				criteria.setStringParam(query.getSchoolId());
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
				CourseItem item = new CourseItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}	
	
	/**
	 * 根据具体搜索条件查询课程总数
	 * @param query
	 * @return
	 */
	public 	int selectCountByQuery(CourseQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("course", "a");
			criteria.setFields("count(*) cn");
			criteria.ljoin("subject", "b", "a.subjectId", "b.id");
			criteria.ljoin("grade", "c", "a.gradeId", "c.id");
			criteria.ljoin("user", "d", "a.ownerId", "d.id");
			criteria.ljoin("school", "e", "d.schoolId", "e.id");
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.courseName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 科目条件
			if(MyUtils.isNotNull(query.getSubjectEnName())) {
				criteria.where(eq("b.subjectEnName", "?"));
				criteria.setStringParam(query.getSubjectEnName());
			}
			// 类别条件
			if(MyUtils.isNotNull(query.getGradeEnName())) {
				criteria.where(eq("c.gradeEnName", "?"));
				criteria.setStringParam(query.getGradeEnName());
			}
			// 年级条件
			if(MyUtils.isNotNull(query.getLevelEnName())) {
				criteria.where(eq("c.LevelEnName", "?"));
				criteria.setStringParam(query.getLevelEnName());
			}
			// 是否含有直播
			if(query.isLive()) {
				criteria.where(eq("a.live", "1"));
			}
			// 所属区域
			if(MyUtils.isNotNull(query.getAreaCode())) {
				criteria.where(like("e.areaCode", "?"));
				criteria.setStringParam(AreaUtils.lostTail(query.getAreaCode()) + "%");
			}
			// 所属用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.ownerId", "?"));
				criteria.setStringParam(query.getUserId());
			}				
			// 所属学校
			if(MyUtils.isNotNull(query.getSchoolId())) {
				criteria.where(eq("e.id", "?"));
				criteria.setStringParam(query.getSchoolId());
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
	
	/**
	 * 购买者根据搜索条件查询课程
	 * @param query
	 * @return
	 */
	public 	List<CourseItem> selectByQuery(CourseBuyQuery query) {
		List<CourseItem> list = new ArrayList<CourseItem>();
		
		try{
			Statement criteria = new Statement("course", "a");
			criteria.setFields("a.*");
			criteria.ljoin("web_order", "b", "a.id", "b.courseId");
			criteria.ljoin("subject", "d", "a.subjectId", "d.id");
			criteria.ljoin("grade", "e", "a.gradeId", "e.id");
			
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.courseName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 购买用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("b.userId", "?"));
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
				CourseItem item = new CourseItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}	
	
	/**
	 * 购买者根据搜索条件查询课程总数
	 * @param query
	 * @return
	 */
	public 	int selectCountByQuery(CourseBuyQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("course", "a");
			criteria.setFields("count(*) cn");
			criteria.ljoin("web_order", "b", "a.id", "b.courseId");
			criteria.ljoin("subject", "d", "a.subjectId", "d.id");
			criteria.ljoin("grade", "e", "a.gradeId", "e.id");
			
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("a.courseName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 购买用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("b.userId", "?"));
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
	
	/**
	 * 根据条件查询收藏
	 * @param query
	 * @return
	 */
	public List<CourseItem> selectByQuery(CourseCollectQuery query) {
		List<CourseItem> list = new ArrayList<CourseItem>();
		
		try{
			Statement criteria = new Statement("course_collection", "a");
			criteria.setFields("b.*");
			criteria.ljoin("course", "b", "a.courseId", "b.id");
			
			// 所属用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.userId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 所属课程
			if(MyUtils.isNotNull(query.getCourseId())) {
				criteria.where(eq("a.courseId", "?"));
				criteria.setStringParam(query.getCourseId());
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
				CourseItem item = new CourseItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}
	
	/**
	 * 根据条件查询收藏课程总数
	 * @param query
	 * @return
	 */
	public int selectCountByQuery(CourseCollectQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("course_collection", "a");
			criteria.setFields("count(*) cn");
			criteria.ljoin("course", "b", "a.courseId", "b.id");
			
			// 所属用户
			if(MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.userId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			// 所属课程
			if(MyUtils.isNotNull(query.getCourseId())) {
				criteria.where(eq("a.courseId", "?"));
				criteria.setStringParam(query.getCourseId());
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
	
	/**
	 * 查询某间学校的所有课程信息
	 * @author huangweihua
	 * @param  schoolId
	 * @return List<CourseItem>
	 */
	public List<CourseItem> selectCourseBySchoolId(String schoolId) {
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.selectCourseBySchoolId(schoolId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 点击收藏，修改该课程的收藏数量,自动加1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	public int addCollection(String id){
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.addCollection(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 取消收藏，修改该课程的收藏数量,自动减1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	public int removeCollection(String id){
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.removeCollection(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 新增一条评论时，修改该课程的评论数量,自动加1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	public int addComment(String id){
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.addComment(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 删除一条评论时，修改该课程的评论数量,自动减1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	public int removeComment(String id){
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.removeComment(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 新增一条课程记录
	 * @param courseItem
	 * @return
	 */
	public boolean insert(CourseItem courseItem) {
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.insert(courseItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 修改一条课程记录
	 * @param courseItem
	 * @return
	 */
	public boolean update(CourseItem courseItem) {
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.update(courseItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 查询一条课程记录
	 * @param id
	 * @return
	 */
	public CourseItem selectById(String id) {
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.selectById(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 删除一条课程记录
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id) {
		try {
			CourseMapper mapper = this.getMapper(CourseMapper.class);
			if (mapper != null) {
				return mapper.deleteById(id) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
}
