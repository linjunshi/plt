package com.santrong.plt.webpage.manage.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.manage.StudentBaseAction;

/**
 * @author weinianjie
 * @date 2014年10月14日
 * @time 上午10:34:09
 */
@Controller
@RequestMapping("/competition/list")
public class CompetitionMAction extends StudentBaseAction {

	/**
	 * 我的练习
	 * @return
	 */
	@RequestMapping("")
	public String home() {
		this.getRequest().setAttribute("flag", "competition");
		return "manage/student/competition";
	}
	
}
