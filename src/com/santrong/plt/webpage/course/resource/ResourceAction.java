package com.santrong.plt.webpage.course.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.webpage.BaseAction;

/**
 * @author huangweihua
 * @date   2014年10月31日 
 * @time   上午10:43:55
 */

@Controller
@RequestMapping("/course_res")
public class ResourceAction extends BaseAction {
	
	@RequestMapping(value="",method=RequestMethod.GET)
	private String openResource(String resId, String resType) {
		
		HttpServletRequest request = getRequest();
		request.setAttribute("resId", resId);
		request.setAttribute("resType", resType);
		
		return "/course/resource/course_res";
	}
}
