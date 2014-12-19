package com.santrong.plt.webpage.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.entry.CommentItem;
import com.santrong.plt.webpage.course.entry.CommentUserView;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午12:11:07
 */
public interface CommentMapper {

	@Select("select a.*, b.showName, b.url from course_comment a left join user b on a.userId=b.id where courseId=#{courseId} order by cts desc")
	List<CommentUserView> selectByCourseId(String courseId);
	
	/**
	 * 新增一条评论
	 * @author huangweihua
	 * @param  commentItem
	 * @return 
	 */
	@Insert("insert into course_comment values(#{id}, #{userId}, #{courseId}, #{remark}, #{cts}, #{uts})")
	int insert(CommentItem commentItem);
	
	/**
	 * 通过主键id，删除一条评论
	 * @author huangweihua
	 * @param  id
	 * @return 
	 */
	@Delete("delete from course_comment where id = #{id}")
	int delete(String id);
	 
}
