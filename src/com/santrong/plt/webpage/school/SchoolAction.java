package com.santrong.plt.webpage.school;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;

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
