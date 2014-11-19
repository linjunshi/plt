package com.santrong.plt.webpage.course.resource.doc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/doc")
public class DocAction extends BaseAction {

	@RequestMapping("")
	public String home(String resId) {
		return "course/resource/doc/detail";
	}
}
