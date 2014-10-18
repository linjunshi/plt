package com.santrong.plt.webpage.manage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.criteria.Statement;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.SimpleQuery;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.BeanUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.manage.entry.DocItem;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:34:24
 */
public class DocDao extends BaseDao {

	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public DocItem selectById(String id) {
		DocMapper mapper = this.getMapper(DocMapper.class);
		if(mapper != null) {
			return mapper.selectById(id);
		}
		return null;
	}
	
	/**
	 * 删除多个
	 * @param ids
	 * @return
	 */
	public int delByIds(String[] ids) {
		String _ids = MyUtils.consistIds(ids);
		DocMapper mapper = this.getMapper(DocMapper.class);
		if(mapper != null) {
			return mapper.delByIds(_ids);
		}
		return 0;
	}
	
	/**
	 * 根据查询条件获取列表
	 * @param query
	 * @return
	 */
	public List<DocItem> selectByQuery(SimpleQuery query) {
		List<DocItem> list = new ArrayList<DocItem>();
		
		try{
			Statement criteria = new Statement("resource_doc", "d");
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("d.docName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			
			// 排序
			if(!StringUtils.isNullOrEmpty(query.getOrderBy())) {
				if("desc".equalsIgnoreCase(query.getOrderRule())) {
					criteria.desc("d." + query.getOrderBy());
				}else {
					criteria.asc("d." + query.getOrderBy());
				}
			}
			
			// 分页
			criteria.limit(query.getLimitBegin(), query.getLimitEnd());
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				DocItem item = new DocItem();
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
	public int selectCountByQuery(SimpleQuery query) {
		int count = 0;
		
		try{
			Statement criteria = new Statement("resource_doc", "d");
			criteria.setFields("count(*) cn");
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("d.docName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
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
