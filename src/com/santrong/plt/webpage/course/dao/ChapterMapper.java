package com.santrong.plt.webpage.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.ChapterToResourceItem;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午12:06:23
 */
public interface ChapterMapper {
	
	@Select("select a.id, a.remark, b.title, b.resourceId, b.resourceType from course_chapter a "
			+ "left join course_chapter_to_resource b on a.id=b.chapterId "
			+ "where a.courseId=#{courseId} order by a.priority asc, b.priority asc")
	List<ChapterAndResourceEntry> selectByCourseId(String courseId);
	
	@Select("select a.* from course_chapter a left join course_chapter_to_resource b on a.id=b.chapterId where b.resourceId=#{resourceId}")
	ChapterItem selectByResourceId(String resourceId);
	
	@Select("select * from course_chapter where courseId = #{courseId} ")
	List<ChapterItem> selectAllByCourseId(String courseId);
	
	/**
	 * 统计课程有多少课时（章节）
	 * @param courseId
	 * @return
	 */
	@Select("select count(*) from course_chapter where courseId = #{courseId} ")
	int selectCountByCourseId(String courseId);
	
	/**
	 * 删除一条大章节的记录
	 * @param chapterId
	 * @return
	 */
	@Delete("delete from course_chapter where id = #{chapterId}")
	int deleteById(String chapterId);
	
	/**
	 * 通过ID，修改章节的标题
	 * @param chapterItem
	 * @return
	 */
	@Update("update course_chapter set "
//			+ "courseId = #{courseId},"
			+ "remark = #{remark},"
//			+ "priority = #{priority},"
			+ "uts = #{uts}"
			+ " where id = #{id}")
	int update(ChapterItem chapterItem);
	
	/**
	 * 新增一个章节
	 * @param chapterItem
	 * @return
	 */
	@Insert("insert into course_chapter values ( #{id}, #{courseId}, #{remark}, #{priority}, #{cts}, #{uts} )")
	int insert(ChapterItem chapterItem);
	
	/**
	 * 获取排序号最大的数值
	 * @param courseId
	 * @return
	 */
	@Select("select ifnull(max(priority),0) from course_chapter where courseId = #{courseId}")
	int selectMaxPriority(String courseId);
	
	/**
	 * 通过课程的大章节ID（chapterId），查找是否存在小章节（下级资源节点）
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @return int
	 */
	@Select("select count(*) from course_chapter_to_resource where chapterId = #{chapterId}")
	int existsResourceByChapterId(String chapterId);
	
	/**
	 * 新增一条某个章节与资源的关联记录
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterToResourceItem
	 * @return
	 */
	@Insert("insert into course_chapter_to_resource values ( #{id}, #{title}, #{chapterId}, #{resourceId}, #{resourceType}, #{priority} )")
	int insertChapterToResource(ChapterToResourceItem chapterToResourceItem);
	
	/**
	 * 修改一条某个章节与资源的关联记录
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterToResourceItem
	 * @return
	 */
	@Insert("update course_chapter_to_resource set title = #{title} where id = #{id}")
	int updateChapterToResource(ChapterToResourceItem chapterToResourceItem);
	
	/**
	 * 获取当前课程章节关联资源表中，排序号最大的数值
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @return
	 */
	@Select("select ifnull(max(priority),0) from course_chapter_to_resource where chapterId = #{chapterId}")
	int selectCRMaxPriority(String chapterId);
	
	/**
	 * 判断是否已经存在同种资源的同一条记录，避免重复绑定资源
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @param resourceId
	 * @return
	 */
	@Select("select count(*) from course_chapter_to_resource where chapterId = #{chapterId} and resourceId = #{resourceId}")
	int existsChapterAndResource(@Param("chapterId")String chapterId,@Param("resourceId")String resourceId);
	
	/**
	 * 查询章节绑定的资源记录
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @param resourceId
	 * @return
	 */
	@Select("select * from course_chapter_to_resource where chapterId = #{chapterId} and resourceId = #{resourceId}")
	ChapterToResourceItem selectChapterAndResource(@Param("chapterId")String chapterId,@Param("resourceId")String resourceId);
	
	/**
	 * 移除一条章节绑定资源的记录
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param chapterId
	 * @param resourceId
	 * @return
	 */
	@Delete("delete from course_chapter_to_resource where chapterId = #{chapterId} and resourceId = #{resourceId}")
	int removeChapterAndResource(@Param("chapterId")String chapterId,@Param("resourceId")String resourceId);
	
	/**
	 * 判断同一个资源是否已经绑定多个章节
	 * @tablename course_chapter_to_resource 课程章节关联资源表
	 * @param resourceId
	 * @return int
	 */
	@Select("select count(*) from course_chapter_to_resource where resourceId = #{resourceId}")
	int selectCountByResourceId(String resourceId);
}
