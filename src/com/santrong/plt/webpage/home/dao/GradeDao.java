package com.santrong.plt.webpage.home.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.home.entry.GradeItem;
import com.santrong.plt.webpage.home.entry.GradeView;

/**
 * @author weinianjie
 * @date 2014年10月8日
 * @time 下午4:53:17
 */
public class GradeDao extends BaseDao{
	
	public List<GradeItem> selectAll() {
		GradeMapper mapper = this.getMapper(GradeMapper.class);
		if(mapper != null) {
			return mapper.selectAll();
		}
		return null;
	}
	
	public List<GradeItem> selectByGradeGroup(int gradeGroup) {
		GradeMapper mapper = this.getMapper(GradeMapper.class);
		if(mapper != null) {
			return mapper.selectByGradeGroup(gradeGroup);
		}
		return null;
	}
	
	public List<GradeView> selectGrade() {
		GradeMapper mapper = this.getMapper(GradeMapper.class);
		if(mapper != null) {
			return mapper.selectGrade();
		}
		return null;
	}
	
	public List<GradeView> selectGradeBySubjectEnName(String subjectName) {
		GradeMapper mapper = this.getMapper(GradeMapper.class);
		if(mapper != null) {
			return mapper.selectGradeBySubjectEnName(subjectName);
		}
		return null;
	}	
}
