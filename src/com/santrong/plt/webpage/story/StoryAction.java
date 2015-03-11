package com.santrong.plt.webpage.story;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.story.dao.StoryCommentDao;
import com.santrong.plt.webpage.story.dao.StoryDao;
import com.santrong.plt.webpage.story.entry.StoryCommentItem;
import com.santrong.plt.webpage.story.entry.StoryCommentUserView;
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
		
		int demo = this.getIntParameter("demo");
		
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
		
		// 情景剧评论
		StoryCommentDao scDao = new StoryCommentDao();
		List<StoryCommentUserView> storyCommentList = scDao.selectByStoryEname(storyEname);
		
		// 情景剧评论的总人数
		int commentCount = scDao.selectCountByStoryEname(storyEname);
		
		this.getRequest().setAttribute("demo", demo);
		this.getRequest().setAttribute("story", story);
		this.getRequest().setAttribute("storyCommentList", storyCommentList);
		this.getRequest().setAttribute("commentCount", commentCount);
		return "story/detail";
	}
	
	// 情景剧说明
	@RequestMapping("/direction")
	public String direction() {
		
		return "story/direction";
	}
	
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public String addStoryComment(String storyId, String storyEname, String remark) {
		// 强制登录
		UserItem user = this.currentUser();
		if (user == null) {
			return this.redirect("/account/login");
		}
				
		if(StringUtils.isNullOrEmpty(storyId) || StringUtils.isNullOrEmpty(storyEname) || StringUtils.isNullOrEmpty(remark) ) {
			return this.redirect("/story/game/" + storyEname + "#comment");
		}
		
		StoryCommentDao scDao = new StoryCommentDao();
		StoryCommentItem comment = new StoryCommentItem();
		comment.setId(MyUtils.getGUID());
		comment.setUserId(user.getId());
		comment.setStoryId(storyId);
		comment.setRemark(remark);
		comment.setCts(new Date());
		comment.setUts(new Date());
		scDao.insert(comment);
		
		return this.redirect("/story/game/" + storyEname + "#comment");
	}
}
