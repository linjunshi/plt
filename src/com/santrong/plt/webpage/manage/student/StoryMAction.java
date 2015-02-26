package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.story.dao.StoryAttendHistoryDao;
import com.santrong.plt.webpage.story.dao.StoryDao;
import com.santrong.plt.webpage.story.entry.StoryItem;
import com.santrong.plt.webpage.story.entry.StoryQuery;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2015年2月2日
 * @time 上午11:33:49
 */
@Controller
@RequestMapping("/story")
public class StoryMAction extends StudentBaseAction {

	/**
	 * 剧本故事
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		try {
			// 获取当前用户对象信息
			UserItem user = this.currentUser();
			if (user == null) {
				// 没登陆
				return this.redirectLogin();
			}
			
			HttpServletRequest request = getRequest();
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			StoryDao storyDao = new StoryDao();
			StoryQuery query = new StoryQuery();
			query.setUserId(this.currentUser().getId());//当前用户
//			query.setOwnerId(this.currentUser().getId());
			query.setPageSize(9);
			query.setPageNum(pageNum);
			query.setCount(storyDao.selectCountHistoryByQuery(query));
			query.setOrderBy("b.uts");
			query.setOrderRule("desc");
			List<StoryItem> storyList = storyDao.selectStoryHistoryByQuery(query);
			
			request.setAttribute("storyList", storyList);
			request.setAttribute("query", query);
			request.setAttribute("flag", "story");
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/student/storyList";
	}
	
	/**
	 * 取消关注
	 * @return
	 */
	@RequestMapping(value="/removeAttendHistory", method=RequestMethod.POST)
	@ResponseBody
	public String removeAttendHistory(){
		try {
			HttpServletRequest request = getRequest();
			String attendId = request.getParameter("attendId");
			StoryAttendHistoryDao sahDao = new StoryAttendHistoryDao();
			if (sahDao.delete(attendId)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
}
