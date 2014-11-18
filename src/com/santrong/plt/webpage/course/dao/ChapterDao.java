package com.santrong.plt.webpage.course.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;
import com.santrong.plt.webpage.course.entry.ChapterItem;

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
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			if(mapper != null) {
				return mapper.selectByCourseId(courseId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 根据资源ID获取所在章节
	 * @param resourceId
	 * @return
	 */
	public ChapterItem selectByResourceId(String resourceId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			if(mapper != null) {
				return mapper.selectByResourceId(resourceId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 获取该课程的所有章节
	 * @param courseId
	 * @return
	 */
	public List<ChapterItem> selectAllByCourseId(String courseId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.selectAllByCourseId(courseId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
}
