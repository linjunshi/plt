package com.santrong.plt.webpage.course.dao;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.entry.WeikeDetailView;

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
}
