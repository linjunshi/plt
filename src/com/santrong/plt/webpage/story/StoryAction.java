package com.santrong.plt.webpage.story;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.story.dao.StoryDao;
import com.santrong.plt.webpage.story.entry.StoryItem;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2015年2月26日
 * @time 上午11:41:12
 */
@Controller
@RequestMapping("/story")
public class StoryAction extends BaseAction {
	
	// 情景剧首页
	@RequestMapping("")
	public String index() {
		return "story/index";
	}
	
	// 情景剧详细页
	@RequestMapping("/game/{storyEname}")
	public String game(@PathVariable String storyEname) {
		
		// 强制登录
		UserItem user = this.currentUser();
		if (user == null) {
			return this.redirect("/account/login");
		}		
		
		StoryDao dao = new StoryDao();
		StoryItem story = dao.selectByEname(storyEname);
		if(story == null) {
			return "404";
		}
		
		this.getRequest().setAttribute("story", story);
		return "story/detail";
	}
	
	// 情景剧说明
	@RequestMapping("/direction")
	public String direction() {
		
		return "story/direction";
	}
}
