package com.santrong.plt.webpage.live.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.live.entry.LiveItem;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 上午10:19:47
 */
public interface LiveMapper {

	
	@Select("select * from resource_live where DATE_FORMAT(cts, '%Y-%m-%d') = DATE_FORMAT(now(), '%Y-%m-%d');")
	List<LiveItem> selectByToday();
	
	@Select("select * from resource_live where id = #{id};")
	LiveItem selectById(String id);
}
