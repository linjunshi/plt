package com.santrong.plt.webpage.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.criteria.Statement;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.AreaUtils;
import com.santrong.plt.util.BeanUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.school.entry.SchoolItem;
import com.santrong.plt.webpage.school.entry.SchoolQuery;

/**
 * @author weinianjie
 * @date 2014年10月9日
 * @time 下午4:00:31
 */
public class SchoolDao extends BaseDao {
	
	public 	List<SchoolItem> selectByQuery(SchoolQuery query) {
		List<SchoolItem> list = new ArrayList<SchoolItem>();
		
		try{
			Statement criteria = new Statement("school", "s");
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(
						like("s.schoolName", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			// 类型包含
			if(query.getSchoolGrade() > 0) {
				criteria.where(eq("(s.schoolGrade & ?)", "?"));
				criteria.setIntParam(query.getSchoolGrade());
				criteria.setIntParam(query.getSchoolGrade());
			}
			// 类型绝对等
			if(query.getSchoolAbsoluteGrade() > 0) {
				criteria.where(eq("s.schoolGrade", "?"));
				criteria.setIntParam(query.getSchoolAbsoluteGrade());
			}
			// 所属区域
			if(MyUtils.isNotNull(query.getAreaCode())) {
				criteria.where(like("s.areaCode", "?"));
				criteria.setStringParam(AreaUtils.lostTail(query.getAreaCode()) + "%");
			}
			// 排序
			if(!StringUtils.isNullOrEmpty(query.getOrderBy())) {
				if("desc".equalsIgnoreCase(query.getOrderRule())) {
					criteria.desc("s." + query.getOrderBy());
				}else {
					criteria.asc("s." + query.getOrderBy());
				}
			}
			
			// 分页
			criteria.limit(query.getLimitBegin(), query.getLimitEnd());
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				SchoolItem item = new SchoolItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		}catch(Exception e){
			Log.printStackTrace(e);
		}
		
		return list;
	}
}
