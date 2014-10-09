package com.santrong.plt.webpage.home.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.home.entry.SubjectItem;
import com.santrong.plt.webpage.home.mapper.SubjectMapper;

/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:57:31
 */
public class SubjectDao extends BaseDao{

	public List<SubjectItem> selectAll() {
		SubjectMapper mapper = this.getMapper(SubjectMapper.class);
		if(mapper != null) {
			return mapper.selectAll();
		}
		return null;
	}
	
	public List<SubjectItem> selectByGradeGroup(int gradeGroup) {
		SubjectMapper mapper = this.getMapper(SubjectMapper.class);
		if(mapper != null) {
			return mapper.selectByGradeGroup(gradeGroup);
		}
		return null;
	}
}
