package com.santrong.plt.webpage.course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
public class CourseAction extends BaseAction {
	@RequestMapping("")
	public String detail() {
		return "";
	}
}
