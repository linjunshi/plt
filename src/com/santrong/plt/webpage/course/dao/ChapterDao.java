package com.santrong.plt.webpage.course.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.ChapterToResourceItem;

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
			if (mapper != null) {
				return mapper.selectAllByCourseId(courseId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 统计课程有多少课时（章节）
	 * @param courseId
	 * @return
	 */
	public int selectCountByCourseId(String courseId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			if (mapper != null) {
				return mapper.selectCountByCourseId(courseId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 通过课程的大章节ID（chapterId），查找是否存在小章节（下级资源节点）
	 * @author huangweihua
	 * @tablename course_chapter_to_resource 课程章节关联资源表
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
	
	/**
	 * 新增一条某个章节与资源的关联记录
	 * @author huangweihua
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterToResourceItem
	 * @return boolean
	 */
	public boolean insertChapterToResource(ChapterToResourceItem chapterToResourceItem) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.insertChapterToResource(chapterToResourceItem) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 修改一条某个章节与资源的关联记录
	 * @author huangweihua
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterToResourceItem
	 * @return boolean
	 */
	public boolean updateChapterToResource(ChapterToResourceItem chapterToResourceItem) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.updateChapterToResource(chapterToResourceItem) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 获取当前课程章节关联资源表中，排序号最大的数值
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @return
	 */
	public int selectCRMaxPriority(String chapterId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.selectCRMaxPriority(chapterId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 判断是否已经存在同种资源的同一条记录，避免重复绑定资源
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @param resourceId
	 * @return
	 */
	public boolean existsChapterAndResource(String chapterId, String resourceId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.existsChapterAndResource(chapterId, resourceId) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 查询章节绑定的资源记录
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @param resourceId
	 * @return
	 */
	public ChapterToResourceItem selectChapterAndResource(String chapterId, String resourceId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.selectChapterAndResource(chapterId, resourceId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 移除一条章节绑定资源的记录
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @param resourceId
	 * @return
	 */
	public boolean removeChapterAndResource(String chapterId, String resourceId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.removeChapterAndResource(chapterId, resourceId) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 判断同一个资源是否已经绑定多个章节
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param resourceId
	 * @return int
	 */
	public int selectCountByResourceId(String resourceId) {
		try {
			ChapterMapper mapper = this.getMapper(ChapterMapper.class);
			return mapper.selectCountByResourceId(resourceId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
}
