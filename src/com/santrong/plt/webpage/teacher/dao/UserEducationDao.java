package com.santrong.plt.webpage.teacher.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.teacher.entry.UserEducationItem;

/**
 * @author huangweihua
 * @date   2014年11月14日 
 * @time   上午10:33:37
 */
public class UserEducationDao extends BaseDao {
	
	public boolean insert(UserEducationItem userEducationItem) {
		try {
			UserEducationMapper mapper = this.getMapper(UserEducationMapper.class);
			return mapper.insert(userEducationItem) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	public boolean existByUserId(String userId) {
		try {
			UserEducationMapper mapper = this.getMapper(UserEducationMapper.class);
			return mapper.existsByUserId(userId) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	public boolean update(UserEducationItem userEducationItem) {
		try {
			UserEducationMapper mapper = this.getMapper(UserEducationMapper.class);
			return mapper.update(userEducationItem) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	public UserEducationItem selectByUserId(String userId) {
		try {
			UserEducationMapper mapper = this.getMapper(UserEducationMapper.class);
			return mapper.selectByUserId(userId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
}
