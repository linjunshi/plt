package com.santrong.plt.webpage.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.SubjectItem;

/**
 * @author weinianjie
 * @date 2014年11月17日
 * @time 下午7:46:07
 */
@Controller
@RequestMapping("/data")
public class SubjectJsonAction extends BaseAction {
	
	// 根据年级获取学科
	@RequestMapping("/subjectByLevel")
	@ResponseBody
	public String getSubjectByLevel(String gradeId) {
		SubjectDao subjectDao = new SubjectDao();
		List<SubjectItem> subjectList = subjectDao.selectByGradeId(gradeId);
		if(subjectList != null) {
			Gson gson = new Gson();
			return gson.toJson(subjectList);
		}
		return "error";
	}
}
