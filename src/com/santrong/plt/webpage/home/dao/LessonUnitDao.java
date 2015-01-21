package com.santrong.plt.webpage.home.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.home.entry.LessonUnitEntry;
import com.santrong.plt.webpage.home.entry.LessonUnitItem;

public class LessonUnitDao extends BaseDao {
	
	public List<LessonUnitItem> selectAll() {
		try {
			LessonUnitMapper mapper = this.getMapper(LessonUnitMapper.class);
			if(mapper != null) {
				return mapper.selectAll();
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	public List<LessonUnitItem> selectAllTerm() {
		try {
			LessonUnitMapper mapper = this.getMapper(LessonUnitMapper.class);
			if(mapper != null) {
				return mapper.selectAllTerm();
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}	
	
	/**
	 * 通过年级和科目，获取单元和学期的列表记录
	 * @param gradeId
	 * @param subjectId
	 * @return List<LessonUnitItem>
	 */
	public List<LessonUnitItem> selectTermUnitByGIdAndSId(String gradeId, String subjectId) {
		try {
			LessonUnitMapper mapper = this.getMapper(LessonUnitMapper.class);
			if(mapper != null) {
				return mapper.selectTermUnitByGIdAndSId(gradeId, subjectId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 通过单元反查，年级、科目等信息
	 * @param id
	 * @return
	 */
	public LessonUnitEntry selectGSUById(String unitId) {
		try {
			LessonUnitMapper mapper = this.getMapper(LessonUnitMapper.class);
			if(mapper != null) {
				return mapper.selectGSUById(unitId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
}
