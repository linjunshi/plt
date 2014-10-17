package com.santrong.plt.webpage.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;

/**
 * @author weinianjie
 * @date 2014年10月14日
 * @time 上午10:34:09
 */
@Controller
@RequestMapping("/study")
public class StudyAction extends BaseAction {

	@RequestMapping("/course")
	public String mycourse() {
		return "manage/mycourse";
	}
	
}
