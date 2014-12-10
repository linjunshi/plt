package com.santrong.plt.webpage.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeDefineEntry;
import com.santrong.plt.webpage.BaseAction;

/**
 * @author weinianjie
 * @date 2014年11月17日
 * @time 下午7:46:07
 */
@Controller
@RequestMapping("/data")
public class GradeJsonAction extends BaseAction {
	
	// 根据学阶获取年级
	@RequestMapping("/levelByGrade")
	@ResponseBody
	public String getLevelByGrade(String gradeEnName) {
		GradeDefineEntry entry = GradeDefine.getByGradeEnName(gradeEnName);
		if(entry != null) {
			Gson gson = new Gson();
			return gson.toJson(entry.getGradeLevelList());
		}
		return "error";
	}
	
	// 根据年级id获取学阶
		@RequestMapping("/gradeByLevelId")
		@ResponseBody
		public String getGradeByLevelId(String levelId) {
			GradeDefineEntry entry = GradeDefine.getByLevelId(levelId);
			if(entry != null) {
				Gson gson = new Gson();
				return gson.toJson(entry);
			}
			return "error";
		}
}
