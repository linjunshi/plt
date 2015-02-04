package com.santrong.plt.webpage.friend;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.friend.dao.UserRelationDao;
import com.santrong.plt.webpage.friend.entry.UserQuery;
import com.santrong.plt.webpage.friend.entry.UserRelationItem;
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
	
	// 请求好友Get
	@RequestMapping(value = "/require", method = RequestMethod.GET)
	public String requireGet(String userId) {
		if(this.currentUser() == null) {
			return this.redirectLogin();// 同步返回登录页的方式
		}
		
		this.getRequest().setAttribute("userId", userId);
		return "friend/require";
	}
	
	
	// 请求好友Post
	@RequestMapping(value = "/require", method = RequestMethod.POST)
	@ResponseBody
	public String requirePost(String userId, String remark) {
		if(this.currentUser() == null) {
			return "loginPage";// 异步返回登录页的方式
		}
		// 先查询两人目前的关系
		UserRelationDao dao = new UserRelationDao();
		UserRelationItem relation = dao.selectByTwoUser(this.currentUser().getId(), userId);
		
		if(relation == null) {// 无关系的情况下新增好友请求申请
			relation = new UserRelationItem();
			relation.setUserId1(this.currentUser().getId());
			relation.setUserId2(userId);
			relation.setApplyMsg(remark);
			relation.setReturnMsg("");
			relation.setResult(UserRelationItem.Result_Apply);
			relation.setCts(new Date());
			relation.setUts(new Date());
			if(dao.insert(relation) <= 0) {
				return FAIL;
			}
		}else{// 复杂的关系判定
			// TODO 暂时忽略
		}
		return SUCCESS;
	}	
}
