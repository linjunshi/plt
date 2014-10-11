package com.santrong.plt.webpage.course.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.CourseVodView;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:54:10
 */
public class CourseVodDao extends BaseDao {
	
	// 首页的直播课列表
	public List<CourseVodView> selectForIndexList(int gradeGroup, String areaCode) {
		CourseVodMapper mapper = this.getMapper(CourseVodMapper.class);
		if(mapper != null) {
			return mapper.selectForIndexList(gradeGroup, areaCode);
		}
		return null;
	}
}
