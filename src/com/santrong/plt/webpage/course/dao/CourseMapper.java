package com.santrong.plt.webpage.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.entry.CourseDetailView;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseView;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:19:35
 */
public interface CourseMapper {
	
	@Select("select a.id,a.courseName,a.url,a.price,a.saleCount,b.subjectName,d.showName from course a "
			+ "left join subject b on a.subjectId=b.id "
			+ "left join grade c on a.gradeId=c.id "
			+ "left join user d on a.ownerId=d.id "
			+ "left join school e on d.schoolId=e.id "
			+ "where c.gradeGroup=#{gradeGroup} and e.areaCode like #{areaCode} and status = 1 "
			+ "order by a.cts limit 10")
	List<CourseView> selectForIndexList(@Param("gradeGroup")int gradeGroup, @Param("areaCode")String areaCode);
	
	@Select("select a.*,b.subjectName,c.gradeName,c.levelName from course a "
			+ "left join subject b on a.subjectId=b.id "
			+ "left join grade c on a.gradeId=c.id "
			+ "left join user d on a.ownerId=d.id "
			+ "where a.id=#{id}")
	CourseDetailView selectDetailById(String id);
	
	/**
	 * 查询某位老师的所有课程信息
	 * @author huangweihua
	 * @param  ownerId
	 * @return List<CourseItem>
	 */
	@Select("select * from course where ownerId = #{userid} and status != -1")
	List<CourseItem> selectByUserId(String userid);
	
	/**
	 * 查询某间学校的所有课程信息
	 * @author huangweihua
	 * @param  schoolId
	 * @return List<CourseItem>
	 */
	@Select("select a.*, b.* from course a LEFT JOIN user b on a.ownerId = b.id where b.schoolId = #{schoolId} and a.status != -1 ORDER BY a.cts DESC;")
	List<CourseItem> selectCourseBySchoolId(String schoolId);
	
	/**
	 * 点击收藏，修改该课程的收藏数量,自动加1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	@Update("update course set collectCount = (collectCount + 1) where id = #{id}")
	int addCollection(String id);
	
	/**
	 * 购买人数自加
	 * @param id
	 * @return
	 */
	@Update("update course set saleCount = (saleCount + 1) where id = #{id}")
	int addBuy(String id);	

	/**
	 * 取消收藏，修改该课程的收藏数量,自动减1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	@Update("update course set collectCount = (collectCount - 1) where id = #{id}")
	int removeCollection(String id);	
	
	/**
	 * 发布课程
	 * @param courseId
	 * @return
	 */
	@Update("update course set status=1, uts=now() where id = #{courseId}")
	int publishCourse(String courseId);
	
	/**
	 * 新增一条评论时，修改该课程的评论数量,自动加1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	@Update("update course set commentCount = (commentCount + 1) where id = #{id}")
	int addComment(String id);
	
	/**
	 * 删除一条评论时，修改该课程的评论数量,自动减1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	@Update("update course set commentCount = (commentCount - 1) where id = #{id}")
	int removeComment(String id);
	
	/**
	 * 新增一条课程记录
	 * @param courseItem
	 * @return
	 */
	@Insert("insert into course values(#{id},#{courseName},#{teacher},#{price},#{url},#{live},#{endTime},#{gradeId},#{subjectId},#{remark},#{limitCount},#{saleCount},#{collectCount},#{commentCount},#{chapterCount},#{ownerId},#{status},#{cts},#{uts})")
	int insert(CourseItem courseItem);
	
	/**
	 * 修改一条课程记录
	 * @param courseItem
	 * @return
	 */
	@Update("update course set "
			+ "courseName = #{courseName},"
			+ "teacher = #{teacher},"
			+ "price = #{price},"
			+ "limitCount = #{limitCount},"
			+ "url = #{url},"
			+ "live = #{live},"
			+ "endTime = #{endTime},"
			+ "gradeId = #{gradeId},"
			+ "subjectId = #{subjectId},"
			+ "remark = #{remark},"
//			+ "saleCount = #{saleCount},"
//			+ "collectCount = #{collectCount},"
//			+ "commentCount = #{commentCount},"
//			+ "chapterCount = #{chapterCount},"
			+ "ownerId = #{ownerId},"
			+ "status = #{status},"
			+ "cts = #{cts},"
			+ "uts = #{uts}"
			+ " where id = #{id}")
	int update(CourseItem courseItem);
	
	@Select("select * from course where id = #{id}")
	CourseItem selectById(String id);
	
	@Update("update course set status = -1 where id = #{id}")
	int deleteById(String id);

	@Select("select a.* from course a left join course_chapter b on a.id=b.courseId where b.id=#{chapterId}")
	CourseItem selectByChapterId(String chapterId);
	
	/**
	 * 当该课程的章节中新增一个直播或者课件时，修改该课程的总课时数,自动加1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	@Update("update course set chapterCount = (chapterCount + 1) where id = #{id}")
	int addChapterCount(String id);
	
	/**
	 * 当该课程的章节中删除一个直播或者课件时，修改该课程的总课时数,自动减1
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	@Update("update course set chapterCount = (chapterCount - 1) where id = #{id}")
	int removeChapterCount(String id);
	
	/**
	 * 刷新课程的课时数
	 * @param id
	 * @param chapterCount
	 * @return
	 */
	@Update("update course set chapterCount = #{chapterCount} where id = #{id}")
	int updateChapterCount(@Param("id")String id,@Param("chapterCount")int chapterCount);
}
