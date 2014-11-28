package com.santrong.plt.webpage.course.resource.file.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.resource.file.entry.FileItem;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:29:53
 */
public interface FileMapper {
	
	@Select("select * from resource_file where id=#{id}")
	FileItem selectById(String id);
	
	@Delete("delete from resource_file where id in (${ids})")
	int delByIds(@Param("ids")String ids);
	
	@Delete("delete from resource_file where id = #{id}")
	int deleteById(String id);
}
