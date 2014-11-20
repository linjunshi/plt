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
	
	/**
	 * 通过课程的大章节ID（chapterId），查找是否存在小章节（下级资源节点）
	 * @author huangweihua
	 * @tablename course_chapter_to_resource
	 * @param chapterId
	 * @return boolean
	 */
	public boolean existsResourceByChapterId(String chapterId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.existsResourceByChapterId(chapterId) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 删除一条大章节的记录
	 * @author huangweihua
	 * @tablename course_chapter
	 * @param chapterId
	 * @return
	 */
	public boolean deleteById(String chapterId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.deleteById(chapterId) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 修改一条大章节的记录
	 * @author huangweihua
	 * @tablename course_chapter
	 * @param chapterItem
	 * @return
	 */
	public boolean update(ChapterItem chapterItem) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.update(chapterItem) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 添加一个章节
	 * @author huangweihua
	 * @tablename course_chapter
	 * @param chapterItem
	 * @return
	 */
	public boolean insert(ChapterItem chapterItem) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.insert(chapterItem) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}

	/**
	 * 获取排序号最大的数值
	 * @param courseId
	 * @return
	 */
	public int selectMaxPriority(String courseId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.selectMaxPriority(courseId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
}
