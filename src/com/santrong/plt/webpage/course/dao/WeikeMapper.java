package com.santrong.plt.webpage.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseView;
import com.santrong.plt.webpage.course.entry.WeikeDetailView;
import com.santrong.plt.webpage.course.entry.WeikeOrderView;

/**
 * @author weinianjie
 * @date 2015年1月19日
 * @time 下午3:23:05
 */
public interface WeikeMapper {
	@Select("select a.*,d.url fileUrl from course a "
			+ "left join course_chapter b on a.id=b.courseId "
			+ "left join course_chapter_to_resource c on b.id=c.chapterId "
			+ "left join resource_video d on c.resourceId=d.id "
			+ "where a.id=#{courseId}")
	WeikeDetailView selectByCourseId(String courseId);
	
	
	@Select("select a.id,a.courseName,a.url,a.price,a.saleCount,b.subjectName,d.showName from course a "
			+ "left join subject b on a.subjectId=b.id "
			+ "left join grade c on a.gradeId=c.id "
			+ "left join user d on a.ownerId=d.id "
			+ "left join school e on d.schoolId=e.id "
			+ "where c.gradeGroup=#{gradeGroup} and e.areaCode like #{areaCode} and a.status = 1 and a.courseType = 1 "
			+ "order by a.cts limit 10")
	List<CourseView> selectForIndexList(@Param("gradeGroup")int gradeGroup, @Param("areaCode")String areaCode);	
	
	@Select("select * from course where unitId = #{unitId} and status = 1 and courseType = 1 order by cts desc limit ${index},${pageSize}")
	List<CourseItem> selectWeikeByUnitId(@Param("unitId")String unitId, @Param("index")int index, @Param("pageSize")int pageSize);
	
	@Select("select * from course where gradeId = #{gradeId} and subjectId = #{subjectId} and status = 1 and courseType = 1 order by cts desc limit ${index},${pageSize}")
	List<CourseItem> selectWeikeByGIdAndSId(@Param("gradeId")String gradeId, @Param("subjectId")String subjectId, @Param("index")int index, @Param("pageSize")int pageSize);
	
	/**
	 * 获取当前微课中，含有相同知识点的微课(可以设置查询条数)
	 * @param weikeId 微课的ID
	 * @param index 第几页 （limit index,pageSize，指记录开始的index，从0开始，表示第一条记录；pageSize是指从第index+1条开始，取pageSize条。）
	 * @param pageSize 查询条数
	 * @return
	 */
	@Select("select d.* from course d where d.id in ("
			+ "select DISTINCT c.courseId from course_to_knowledge c where c.knowledgeId in ("
			+ "select a.knowledgeId from course_to_knowledge a LEFT JOIN course b on a.courseId = b.id where id = #{weikeId}"
			+ ") and c.courseId != #{weikeId}"
			+ ") and status = 1 and courseType = 1 ORDER BY d.cts desc limit ${index},${pageSize}")
	List<CourseItem> selectWeikeBySameKnowledges(@Param("weikeId")String weikeId, @Param("index")int index, @Param("pageSize")int pageSize);
	
	/**
	 * 获取当前用户的单元微课与订单情况
	 * @param unitId
	 * @param userId
	 * @return
	 */
	@Select("select e.*,d.id videoId,d.url fileUrl,d.size,d.duration,g.id as orderId,g.userId,g.status as orderStatus "
			+ "from (select a.* from course a where a.courseType = 1 and a.status = 1 and a.unitId = #{unitId})e "
			+ "left join (select f.* from web_order f where f.userId = #{userId}) g ON e.id = g.courseId "
			+ "left join course_chapter b on e.id = b.courseId "
			+ "left join course_chapter_to_resource c on b.id = c.chapterId "
			+ "left join resource_video d on c.resourceId = d.id limit 8 ")
	List<WeikeOrderView> selectWeikeOrderByUnitId(@Param("unitId")String unitId, @Param("userId")String userId);			
}
