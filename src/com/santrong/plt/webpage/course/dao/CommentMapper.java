package com.santrong.plt.webpage.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.entry.CommentItem;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午12:11:07
 */
public interface CommentMapper {

	@Select("select a.*, b.showName from course_comment a left join user b on a.userId=b.id where courseId=#{courseId} order by cts desc")
	List<CommentItem> selectByCourseId(String courseId);
	
	@Insert("insert into course_comment values(#{id}, #{userId}, #{courseId}, #{remark}, #{cts}, #{uts})")
	int insert(CommentItem commentItem);
	 
}
