package com.santrong.plt.webpage.course.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午12:06:04
 */
public class ChapterDao extends BaseDao {

	/**
	 * 根据课程ID获取章节
	 * @param courseId
	 * @return
	 */
	public List<ChapterAndResourceEntry> selectByCourseId(String courseId) {
		ChapterMapper mapper = this.getMapper(ChapterMapper.class);
		if(mapper != null) {
			return mapper.selectByCourseId(courseId);
		}
		return null;
	}
}
