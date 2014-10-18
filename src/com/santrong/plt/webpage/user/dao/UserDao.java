package com.santrong.plt.webpage.user.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.user.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年7月14日
 * @time 下午5:49:51
 */
public class UserDao extends BaseDao{
	
	
	public UserItem selectByUserName(String username) {
		UserMapper mapper = this.getMapper(UserMapper.class);
		if(mapper != null) {
			return mapper.selectByUserName(username);
		}
		return null;
	}
	
	public UserItem selectById(String id) {
		UserMapper mapper = this.getMapper(UserMapper.class);
		if(mapper != null) {
			return mapper.selectById(id);
		}
		return null;
	}
	
	public boolean existsByUserName(String username) {
		UserMapper mapper = this.getMapper(UserMapper.class);
		if(mapper != null) {
			return mapper.existsByUserName(username) > 0;
		}
		return false;
	}	
	
	public int insert(UserItem user) {
		UserMapper mapper = this.getMapper(UserMapper.class);
		if(mapper != null) {
			return mapper.insert(user);
		}
		return 0;
	}
	
	public int update(UserItem user) {
		UserMapper mapper = this.getMapper(UserMapper.class);
		if(mapper != null) {
			return mapper.update(user);
		}
		return 0;
	}
	
	public List<UserItem> selectAll() {
		UserMapper mapper = this.getMapper(UserMapper.class);
		if(mapper != null) {
			return mapper.selectAll();
		}
		return null;
	}
}
