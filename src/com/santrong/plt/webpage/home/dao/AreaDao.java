package com.santrong.plt.webpage.home.dao;

import java.util.List;

import com.santrong.plt.util.AreaUtils;
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
	
	/**
	 * 根据区域拼音获取区域实体
	 * @param areaName
	 * @return
	 */
	public AreaItem selectByAreaEName(String areaEName) {
		AreaMapper mapper = this.getMapper(AreaMapper.class);
		if(mapper != null) {
			return mapper.selectByAreaEName(areaEName);
		}
		return null;
	}
	
	/**
	 * 获取所有城市
	 * @return
	 */
	public List<AreaItem> selectCity() {
		AreaMapper mapper = this.getMapper(AreaMapper.class);
		if(mapper != null) {
			return mapper.selectCity();
		}
		return null;		
	}
	
	/**
	 * 获取所有省级区域
	 * @return
	 */
	public List<AreaItem> selectProvince() {
		AreaMapper mapper = this.getMapper(AreaMapper.class);
		if(mapper != null) {
			return mapper.selectProvince();
		}
		return null;		
	}	
	
	/**
	 * 根据areaCode模糊查询区域
	 * @param areaCode
	 * @return
	 */
	public List<AreaItem> selectByCodeLike(String areaCode)  {
		AreaMapper mapper = this.getMapper(AreaMapper.class);
		if(mapper != null) {
			return mapper.selectByCodeLike("'" + AreaUtils.lostTail(areaCode) + "%'");
		}
		return null;	
	}
	
	/**
	 * 根据父亲code查询子区域，不含子子区域
	 * @param areaCode
	 * @return
	 */
	public List<AreaItem> selectByFather(String areaCode)  {
		String _code = AreaUtils.lostTail(areaCode);
		if(_code.length() % 2 == 1) {// 奇数补0成偶数
			_code += "0";
		}
		if(_code.length() < 6) {// 两位模糊位
			_code += "__";
		}
		for(int i=_code.length()+1;i<7;i++) {// 余下位补0，最长6位
			_code += "0";
		}
		
		AreaMapper mapper = this.getMapper(AreaMapper.class);
		if(mapper != null) {
			List<AreaItem> list = mapper.selectByCodeLike("'" + _code + "'");
			if(list != null) {
				_code = _code.replace("__", "00");// 移除自身
				for(int i=0;i<list.size();i++) {
					if(_code.equals(list.get(i).getAreaCode())) {
						list.remove(i);
						return list;
					}
				}
			}
		}
		return null;	
	}	

}
