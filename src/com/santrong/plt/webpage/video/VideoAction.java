package com.santrong.plt.webpage.video;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;

/**
 * @author weinianjie
 * @date 2015年1月6日
 * @time 上午10:54:59
 */
@Controller
@RequestMapping("/video")
public class VideoAction extends BaseAction {

	@RequestMapping("")
	public String index() {
		return "video/index";
	}
}
