package com.santrong.plt.webpage.home.dao;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.home.entry.AreaItem;

/**
 * @author weinianjie
 * @date 2014年10月17日
 * @time 下午2:38:26
 */
public class AreaDao extends BaseDao {
	
	/**
	 * 根据区域名称获取区域实体
	 * @param areaName
	 * @return
	 */
	public AreaItem selectByAreaName(String areaName) {
		AreaMapper mapper = this.getMapper(AreaMapper.class);
		if(mapper != null) {
			return mapper.selectByAreaName(areaName);
		}
		return null;
	}

}
