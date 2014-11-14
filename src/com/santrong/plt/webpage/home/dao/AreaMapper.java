package com.santrong.plt.webpage.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.home.entry.AreaItem;

/**
 * @author weinianjie
 * @date 2014年10月17日
 * @time 下午2:37:32
 */
public interface AreaMapper {
	
	@Select("select * from web_area where areaName=#{areaName}")
	AreaItem selectByAreaName(String areaName) ;
	
	@Select("select * from web_area where areaEName=#{areaEName}")
	AreaItem selectByAreaEName(String areaEName) ;	
	
	@Select("select * from web_area where areaType='市' order by areaEName asc")
	List<AreaItem> selectCity() ;	
}
