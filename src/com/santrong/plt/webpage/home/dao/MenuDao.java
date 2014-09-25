package com.santrong.plt.webpage.home.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.home.entry.MenuItem;
import com.santrong.plt.webpage.home.mapper.MenuMapper;

/**
 * @author weinianjie
 * @date 2014年7月15日
 * @time 上午10:38:33
 */
public class MenuDao extends BaseDao{
	
	public List<MenuItem> selectByParentId(String parentId) {
		
		MenuMapper mapper = this.getMapper(MenuMapper.class);
		if(mapper != null) {
			return mapper.selectByParentId(parentId);
		}
		return null;
	}
}
