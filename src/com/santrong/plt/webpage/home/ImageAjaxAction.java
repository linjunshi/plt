package com.santrong.plt.webpage.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.util.FileUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;

/**
 * @author weinianjie
 * @date 2014年11月17日
 * @time 下午7:46:07
 */
@Controller
@RequestMapping("/image")
public class ImageAjaxAction extends BaseAction {
	
	// 图片异步上传
	@RequestMapping("/upload")
	@ResponseBody
	public String getLevelByGrade(HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		sb.append("{'result':");
		String result = FileUtils.uploadRemoteFile(request);
		if(MyUtils.isNotNull(result) && result.startsWith("url")) {
			sb.append("1").append(",").append("'url':").append("'").append(result.split(":")[1]).append("'");
		}else {
			sb.append("0").append(",").append("'error':").append("'").append(result).append("'");
		}
		sb.append("}");
		return sb.toString();
	}
}
