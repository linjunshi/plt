package com.santrong.plt.webpage.course.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.CourseDetailView;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseView;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:54:10
 */
public class CourseDao extends BaseDao {
	
	// 根据年级和地区获取课程，首页
	public List<CourseView> selectForIndexList(int gradeGroup, String areaCode) {
		CourseMapper mapper = this.getMapper(CourseMapper.class);
		if(mapper != null) {
			return mapper.selectForIndexList(gradeGroup, areaCode);
		}
		return null;
	}
	
	// 获取课程详细信息
	public CourseDetailView selectDetailById(String id) {
		CourseMapper mapper = this.getMapper(CourseMapper.class);
		if(mapper != null) {
			return mapper.selectDetailById(id);
		}
		return null;
	}
	
	// 根据具体搜索条件查询
	public List<CourseItem> selectByQuery() {
		CourseMapper mapper = this.getMapper(CourseMapper.class);
		if(mapper != null) {
			return mapper.selectByQuery();
		}
		return null;
	}
	
}
