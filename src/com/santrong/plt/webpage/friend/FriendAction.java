package com.santrong.plt.webpage.friend;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.friend.dao.UserRelationDao;
import com.santrong.plt.webpage.friend.entry.UserQuery;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2015年2月3日
 * @time 下午2:58:40
 */
@Controller
@RequestMapping("/friend")
public class FriendAction extends BaseAction {

	// 用户搜索页
	@RequestMapping("")
	public String index() {
		
		int page = this.getIntParameter("page");
		if(page == 0) {
			page = 1;
		}
		
		UserQuery query = new UserQuery();
		query.setPageNum(page);
		if(this.currentUser() != null) {
			query.setCurrentUserId(this.currentUser().getId());
		}
		
		UserRelationDao dao = new UserRelationDao();
		query.setCount(dao.selectCountByQuery(query));
		List<UserItem> userList = dao.selectByQuery(query);
		
		this.getRequest().setAttribute("query", query);
		this.getRequest().setAttribute("userList", userList);
		
		return "friend/index";
	}
	
}
