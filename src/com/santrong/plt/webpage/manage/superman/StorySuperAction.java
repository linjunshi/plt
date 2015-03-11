package com.santrong.plt.webpage.manage.superman;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.manage.SuperManBaseAction;
import com.santrong.plt.webpage.story.dao.StoryDao;
import com.santrong.plt.webpage.story.entry.StoryItem;
import com.santrong.plt.webpage.story.entry.StoryQuery;

/**
 * @author weinianjie
 * @date 2015年2月6日
 * @time 下午3:36:45
 */
@Controller
@RequestMapping("/super/story")
public class StorySuperAction extends SuperManBaseAction {

	@RequestMapping("/config")
	public String questionList() {
		try {
			
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			StoryDao sDao = new StoryDao();
			StoryQuery query = new StoryQuery();
			query.setPageNum(pageNum);
			query.setPageSize(5);
			query.setCount(sDao.selectCountByQuery(query));
			query.setOrderBy("cts");
			query.setOrderRule("desc");
			List<StoryItem> storyList = sDao.selectAllStoryByQuery(query);
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("storyList", storyList);
			request.setAttribute("query", query);
			request.setAttribute("flag", "story_config");
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/superman/storyConfigList";
	}
	
	/**
	 * 打开试题审核界面
	 */
	@RequestMapping(value="/storyConfigEdit", method=RequestMethod.GET)
	public String storyConfigEdit(){
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		try {
			HttpServletRequest request = this.getRequest();
			String storyId = request.getParameter("storyId");
			
			if (MyUtils.isNotNull(storyId)) {
				//打开剧本配置界面
				StoryDao sDao = new StoryDao();
				StoryItem storyItem = sDao.selectById(storyId);
//				// 判断当前用户是否是该课程的所有者
//				if(storyItem == null || !storyItem.getOwnerId().equals(this.currentUser().getId())) {
//					return this.redirect("/");
//				}
				
				request.setAttribute("storyItem", storyItem);
			}
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("flag", "story_config");
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/superman/storyConfigEdit";
	}
	
	/**
	 * 新增、修改一条剧本故事记录
	 * @author huangweihua
	 * @param storyItem
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="/storyConfigEdit", method=RequestMethod.POST)
	public String storyConfigEdit(StoryItem storyForm){
		try {
			HttpServletRequest request = this.getRequest();
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			request.setAttribute("pageNum", pageNum);
			
			if (storyForm != null) {
				
				if (MyUtils.isNull(storyForm.getStoryName())) {
					addError("请您填写剧本名称 ！");
				}
				if (MyUtils.isNull(storyForm.getStoryEname())) {
					addError("请您填写剧本英文名称 ！");
				}
				if (MyUtils.isNull(storyForm.getRemark())) {
					addError("请您填写剧本的简介 ！");
				}
				
				if (!(errorSize() > 0)) {
					// id不为空，执行修改操作
					StoryDao sDao = new StoryDao();
					StoryItem story = sDao.selectById(storyForm.getId());
//					// 判断当前用户是否是该课程的所有者
//					if(storyForm == null || !storyForm.getOwnerId().equals(this.currentUser().getId())) {
//						return this.redirect("/");
//					}
					
					boolean result = false;
					story.setStoryName(storyForm.getStoryName());
					story.setStoryEname(storyForm.getStoryEname());
					story.setRemark(storyForm.getRemark());
					story.setUts(new Date());
					result = sDao.update(story);
					
					if(result){
						return this.redirect("/super/story/config?page="+pageNum);
					} else {
						addError("亲，您操作失败了，请您刷新页面后重新操作！");
						request.setAttribute("storyItem", storyForm);
					}
				}
			} else {
				request.setAttribute("storyItem", storyForm);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/superman/storyConfigEdit";
	}
}
