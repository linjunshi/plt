package com.santrong.plt.webpage.school;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.school.dao.SchoolDao;
import com.santrong.plt.webpage.school.entry.SchoolItem;
import com.santrong.plt.webpage.school.entry.SchoolQuery;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/school")
public class SchoolAction extends BaseAction {
	
	/**
	 * 学校首页
	 * @return
	 */
	@RequestMapping(value="")
	public String index() {
		return  catagory("all");
	}	
	
	/**
	 * 按年级搜索的学校
	 * @param grade
	 * @return
	 */
	@RequestMapping("/{grade}")
	public String catagory(@PathVariable String grade) {
		HttpServletRequest request = getRequest();
		AreaEntry area = (AreaEntry)(request.getSession().getAttribute(Global.SessionKey_Area));		
		
		SchoolDao schoolDao = new SchoolDao();
		SchoolQuery schoolQuery = new SchoolQuery();
		schoolQuery.setAreaCode(area.getCityCode());
		List<SchoolItem> schoolList = schoolDao.selectByQuery(schoolQuery);
		
		request.setAttribute("schoolList", schoolList);
		
		return "school/index";
	}
	
	
	/**
	 * 学校详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}.html")
	public String detail(@PathVariable String id) {
		
		return "school/detail";
	}
}
