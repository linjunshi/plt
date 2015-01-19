package com.santrong.plt.webpage.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.home.dao.LessonUnitDao;
import com.santrong.plt.webpage.home.entry.LessonUnitEntry;
import com.santrong.plt.webpage.home.entry.LessonUnitItem;

/**
 * @author weinianjie
 * @date 2014年11月17日
 * @time 下午7:46:07
 */
@Controller
@RequestMapping("/data")
public class UnitJsonAction extends BaseAction {
	
	// 根据年级和科目获取学期
	@RequestMapping("/unitByGradeAndSubject")
	@ResponseBody
	public String getTermUnitCnNameList(String gradeId, String subjectId) {
		try {
			List<LessonUnitEntry> lueList = new ArrayList<LessonUnitEntry>();
			LessonUnitDao luDao = new LessonUnitDao();
			List<LessonUnitItem> luiList = luDao.selectTermUnitByGIdAndSId(gradeId, subjectId);
			if (luiList != null && luiList.size() > 0) {
				int i = 0;
				int j = 0;
				for (LessonUnitItem luiTiem : luiList) {
					LessonUnitEntry entry = new LessonUnitEntry();
					BeanUtils.copyProperties(luiTiem, entry);
					if (entry.getTerm() == LessonUnitItem.Term_Up) {//上学期
						i++;
						if (i > 1) {
							entry.setTermUnitCnName("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -> " + entry.getUnitName());
						} else {
							entry.setTermUnitCnName(entry.getTermCnName() + " -> " + entry.getUnitName());
						}
					} else if (entry.getTerm() == LessonUnitItem.Term_Down) {//下学期
						j++;
						if (j > 1) {
							entry.setTermUnitCnName("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -> " + entry.getUnitName());
						} else {
							entry.setTermUnitCnName(entry.getTermCnName() + " -> " + entry.getUnitName());
						}
					}
					lueList.add(entry);
				}
			}
			if(luiList != null) {
				Gson gson = new Gson();
				return gson.toJson(lueList);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "error";
	}
}
