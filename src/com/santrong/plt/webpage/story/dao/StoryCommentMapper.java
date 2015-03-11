package com.santrong.plt.webpage.story.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.story.entry.StoryCommentItem;

/**
 * @author huangweihua
 * @Date 2015年3月10日 
 * @Time 下午6:16:24
 */
public interface StoryCommentMapper{
	
	@Insert("insert into story_comment values(#{id}, #{userId}, #{storyId}, #{remark}, #{cts}, #{uts})")
	int insert(StoryCommentItem storyCommentItem);
	
	@Update("update story_comment set "
			+ "remark = #{remark},"
			+ "uts = #{uts} "
			+ " where id = #{id}")
	int update(StoryCommentItem storyCommentItem);
	
	@Select("select a.* from story_comment a left join story b on a.storyId = b.id where b.storyEname = #{storyEname} order by a.cts asc")
	List<StoryCommentItem> selectByStoryEname(String storyEname);
}
