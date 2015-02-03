package com.santrong.plt.webpage.story.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.story.entry.StoryItem;

/**
 * @author huangweihua
 * @Date 2015年2月2日 
 * @Time 下午6:05:06
 */
public interface StoryMapper {

	@Insert("insert into story values(#{id}, #{storyName}, #{url}, #{duration}, #{subjectId}, #{remark}, #{limitCount}, #{storyType}, #{ownerId}, #{status}, #{cts}, #{uts})")
	int insert(StoryItem storyItem);
	
	@Update("update story set "
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
	int update(StoryItem storyItem);
	
	@Select("select * from story where id = #{id}")
	StoryItem selectById(String id);
	
	@Delete("delete from story where id = #{id} ")
	int delete(String id);
}
