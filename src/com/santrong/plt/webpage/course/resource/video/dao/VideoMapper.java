package com.santrong.plt.webpage.course.resource.video.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.resource.video.entry.VideoItem;

/**
 * @author weinianjie
 * @date 2015年1月19日
 * @time 下午2:55:02
 */
public interface VideoMapper {
	@Select("select * from resource_file where id=#{id}")
	VideoItem selectById(String id);
	
	@Insert("insert into resource_video values (#{id},#{title},#{url},#{size},#{duration},#{ownerId},#{remark},#{cts},#{uts})")
	int insert(VideoItem item);
	
	@Delete("delete from resource_video where id=#{id}")
	int delete(String id);
}
