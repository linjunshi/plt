package com.santrong.plt.webpage.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;
import com.santrong.plt.webpage.course.entry.ChapterItem;

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
	 * 通过课程的大章节ID（chapterId），查找是否存在小章节（下级资源节点）
	 * @param chapterId
	 * @return int
	 */
	@Select("select count(*) from course_chapter_to_resource where chapterId = #{chapterId}")
	int existsResourceByChapterId(String chapterId);
	
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
	 * @param chapterId
	 * @return
	 */
	@Select("select max(priority) from course_chapter where courseId = #{courseId}")
	int selectMaxPriority(String courseId);
}
