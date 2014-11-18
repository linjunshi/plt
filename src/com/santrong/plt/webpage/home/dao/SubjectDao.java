package com.santrong.plt.webpage.home.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.home.entry.SubjectItem;

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
	
	public List<SubjectItem> selectByGradeEnName(String gradeName) {
		SubjectMapper mapper = this.getMapper(SubjectMapper.class);
		if(mapper != null) {
			return mapper.selectByGradeEnName(gradeName);
		}
		return null;
	}	
	
	public List<SubjectItem> selectByGradeId(String gradeId) {
		SubjectMapper mapper = this.getMapper(SubjectMapper.class);
		if(mapper != null) {
			return mapper.selectByGradeId(gradeId);
		}
		return null;
	}		
}
