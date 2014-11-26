package com.santrong.plt.webpage.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.santrong.plt.util.AreaUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.home.dao.AreaDao;
import com.santrong.plt.webpage.home.entry.AreaItem;
import com.santrong.plt.webpage.school.dao.SchoolDao;
import com.santrong.plt.webpage.school.entry.SchoolItem;

/**
 * @author weinianjie
 * @date 2014年11月17日
 * @time 下午7:46:07
 */
@Controller
@RequestMapping("/component")
public class ComponentAction extends BaseAction {
	
	// 修改图片控件
	@RequestMapping("/change/cover")
	public String changeCover(){
		return "/component/changeCover";
	}
	
	// 选择学校控件
	@RequestMapping("/choice/school")
	public String choiceSchool(){
		AreaDao dao = new AreaDao();
		List<AreaItem> provinceList = dao.selectProvince();
		this.getRequest().setAttribute("provinceList", provinceList);
		return "/component/choiceSchool";
	}
	
	// 选择学校控件_根据区域请求内容
	@RequestMapping("/choice/school/data")
	@ResponseBody
	public String choiceSchoolData(String areaCode){
		if(areaCode == null) {
			areaCode = "";
		}
		String _code = AreaUtils.lostTail(areaCode);
		if(_code.length() % 2 == 1) {// 奇数补0成偶数
			_code += "0";
		}
		
		Gson gson = new Gson();
		if(_code.length() >= 6) {// 查学校
			SchoolDao dao = new SchoolDao();
			List<SchoolItem> dataList = dao.selectByAreaCode(areaCode);
			return gson.toJson(dataList);
		}else {// 查区域
			AreaDao dao = new AreaDao();
			List<AreaItem> dataList = dao.selectByFather(areaCode);	
			return gson.toJson(dataList);
		}
	}
	
}
