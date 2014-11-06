package com.santrong.plt.webpage.course.resource.live.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.resource.live.entry.LiveItem;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:19:47
 */
public interface LiveMapper {

	
	@Select("select * from resource_live_score")
	List<LiveItem> selectAll();
}
