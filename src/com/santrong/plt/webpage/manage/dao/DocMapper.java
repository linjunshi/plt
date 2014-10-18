package com.santrong.plt.webpage.manage.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.manage.entry.DocItem;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:34:41
 */
public interface DocMapper {
	@Select("select * from resource_doc where id=#{id}")
	DocItem selectById(String id);
	
	@Delete("delete from resource_doc where id in (${ids})")
	int delByIds(@Param("ids")String ids);
}
