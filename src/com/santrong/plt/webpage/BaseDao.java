package com.santrong.plt.webpage;


import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;

import com.santrong.plt.criteria.SqlOperator;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.SimpleDataSource;
import com.santrong.plt.opt.ThreadUtils;


/**
 * @Author weinianjie
 * @Date 2014-7-4
 * @Time 下午11:32:53
 */
public abstract class BaseDao extends SqlOperator{
	
	private org.apache.ibatis.session.SqlSession 				sqlSession;
	private org.springframework.jdbc.core.JdbcTemplate		 	jdbcTemplate;
	
	/**
	 * 获取Ibatis的Mapper工具
	 * @return
	 */
	protected <T> T getMapper(Class<T> T) {
		
		if(sqlSession == null) {
			try {
				
				sqlSession = ThreadUtils.currentSqlSession();
				
			} catch (SQLException e) {
				Log.error(e);
			}
		}
		
		return sqlSession.getMapper(T);
	}
	
	/**
	 * 获取jdbcTemplate的实例
	 * @return
	 */
	protected JdbcTemplate getJdbcTemplate() {
		
		if(jdbcTemplate == null) {
			
			jdbcTemplate = new JdbcTemplate(new SimpleDataSource());
			
		}
		
		return jdbcTemplate;
	}
}
