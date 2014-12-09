package com.santrong.plt.webpage.course.resource.file.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.criteria.Statement;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.BeanUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.file.entry.FileItem;
import com.santrong.plt.webpage.course.resource.file.entry.FileQuery;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:29:30
 */
public class FileDao extends BaseDao {

	public int insert(FileItem file) {
		FileMapper mapper = this.getMapper(FileMapper.class);
		if(mapper != null) {
			return mapper.insert(file);
		}
		return 0;
	}
	
	public int updateStatus(String id, int status) {
		FileMapper mapper = this.getMapper(FileMapper.class);
		if(mapper != null) {
			return mapper.updateStatus(id, status);
		}
		return 0;
	}
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public FileItem selectById(String id) {
		FileMapper mapper = this.getMapper(FileMapper.class);
		if(mapper != null) {
			return mapper.selectById(id);
		}
		return null;
	}
	
	/**
	 * 删除一个
	 * @param id
	 * @return
	 */
	public boolean deleteById(String id) {
		try {
			FileMapper mapper = this.getMapper(FileMapper.class);
			if(mapper != null) {
				return mapper.deleteById(id) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	public int delByIds(String[] ids) {
		try {
			String _ids = MyUtils.consistIds(ids);
			FileMapper mapper = this.getMapper(FileMapper.class);
			if(mapper != null) {
				return mapper.delByIds(_ids);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 根据查询条件获取列表
	 * @param query
	 * @return
	 */
	public List<FileItem> selectByQuery(FileQuery query) {
		List<FileItem> list = new ArrayList<FileItem>();
		
		try{
			Statement criteria = new Statement("resource_file", "f");
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("f.fileName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 所属用户
			if(MyUtils.isNotNull(query.getOnwerId())) {
				criteria.where(eq("f.ownerId", "?"));
				criteria.setStringParam(query.getOnwerId());
			}			
			
			// 排序
			if(!StringUtils.isNullOrEmpty(query.getOrderBy())) {
				if("desc".equalsIgnoreCase(query.getOrderRule())) {
					criteria.desc("f." + query.getOrderBy());
				}else {
					criteria.asc("f." + query.getOrderBy());
				}
			}
			
			// 分页
			criteria.limit(query.getLimitBegin(), query.getLimitEnd());
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				FileItem item = new FileItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}
	
	/**
	 * 根据查询条件获取数量
	 * @param query
	 * @return
	 */
	public int selectCountByQuery(FileQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("resource_file", "f");
			criteria.setFields("count(*) cn");
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("f.fileName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 所属用户
			if(MyUtils.isNotNull(query.getOnwerId())) {
				criteria.where(eq("f.ownerId", "?"));
				criteria.setStringParam(query.getOnwerId());
			}			
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			rs.next();
			count = rs.getInt("cn");
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return count;
	}
}
