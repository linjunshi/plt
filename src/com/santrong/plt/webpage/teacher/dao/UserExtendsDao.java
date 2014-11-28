package com.santrong.plt.webpage.teacher.dao;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.teacher.entry.UserExtendsItem;

/**
 * @author huangweihua
 * @date   2014年11月14日 
 * @time   上午10:33:37
 */
public class UserExtendsDao extends BaseDao{
	
	public boolean insert(UserExtendsItem userExtendsItem) {
		try {
			UserExtendsMapper mapper = this.getMapper(UserExtendsMapper.class);
			return mapper.insert(userExtendsItem) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}

	public boolean existsByUserId(String userId) {
		try {
			UserExtendsMapper mapper = this.getMapper(UserExtendsMapper.class);
			return mapper.existsByUserId(userId) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	public boolean update(UserExtendsItem userExtendsItem) {
		try {
			UserExtendsMapper mapper = this.getMapper(UserExtendsMapper.class);
			return mapper.update(userExtendsItem) > 0;
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	public UserExtendsItem selectByUserId(String userId) {
		try {
			UserExtendsMapper mapper = this.getMapper(UserExtendsMapper.class);
			return mapper.selectByUserId(userId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
}
