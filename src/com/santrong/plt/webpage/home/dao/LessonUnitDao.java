package com.santrong.plt.webpage.home.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
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
	
}
