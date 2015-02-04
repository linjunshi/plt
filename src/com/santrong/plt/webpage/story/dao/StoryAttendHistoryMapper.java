package com.santrong.plt.webpage.story.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.story.entry.StoryAttendItem;
import com.santrong.plt.webpage.story.entry.StoryItem;

/**
 * @author huangweihua
 * @Date 2015年2月2日 
 * @Time 下午6:05:06
 */
public interface StoryAttendHistoryMapper {

	@Insert("insert into story_attend_history values(#{id}, #{storyName}, #{url}, #{duration}, #{subjectId}, #{remark}, #{limitCount}, #{storyType}, #{ownerId}, #{status}, #{cts}, #{uts})")
	int insert(StoryAttendItem storyAttendItem);
	
	@Update("update story_attend_history set "
			+ "storyName = #{storyName},"
			+ "url = #{url},"
			+ "duration = #{duration},"
			+ "subjectId = #{subjectId},"
			+ "remark = #{remark},"
			+ "limitCount = #{limitCount},"
			+ "storyType = #{storyType},"
			+ "ownerId = #{ownerId},"
			+ "status = #{status},"
			+ "uts = #{uts} "
			+ "where id = #{id}")
	int update(StoryAttendItem storyAttendItem);
	
	@Select("select * from story_attend_history where id = #{id}")
	StoryItem selectById(String id);
	
	@Delete("delete from story_attend_history where id = #{id} ")
	int delete(String id);
}
