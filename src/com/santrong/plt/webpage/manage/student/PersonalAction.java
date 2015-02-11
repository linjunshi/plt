package com.santrong.plt.webpage.manage.student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.manage.student.dao.UserRelationDao;
import com.santrong.plt.webpage.manage.student.entry.FriendMsgItem;
import com.santrong.plt.webpage.manage.student.entry.UserQuery;
import com.santrong.plt.webpage.manage.student.entry.UserRelationItem;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2015年2月4日
 * @time 下午5:19:42
 */
@Controller
@RequestMapping("/personal")
public class PersonalAction extends StudentBaseAction {
	
	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping("/center")
	public String personalCenter(){
		// 我的好友申请
		UserRelationDao userRelationDao = new UserRelationDao();
		List<UserRelationItem> relationList = userRelationDao.selectMsgList(this.currentUser().getId());
		List<FriendMsgItem> friendMsgList = new ArrayList<FriendMsgItem>();
		if(relationList != null) {// 转换数据结构
			for(UserRelationItem u:relationList) {
					FriendMsgItem item = new FriendMsgItem();
					if(this.currentUser().getId().equals(u.getUserId1())) {// 作为发起人
						item.setUserId(u.getUserId2());
						item.setShowName(u.getShowName2());
						item.setMsg(u.getReturnMsg());
						item.setType(0);
					}else {// 作为接受人
						item.setUserId(u.getUserId1());
						item.setShowName(u.getShowName1());
						item.setMsg(u.getApplyMsg());
						item.setType(1);
					}
					item.setResult(u.getResult());
					friendMsgList.add(item);
			}
		}
		
		HttpServletRequest request = getRequest();
		request.setAttribute("flag", "center");
		request.setAttribute("friendMsgList", friendMsgList);		
		return "manage/student/personalCenter";
	}
	
	/**
	 * 好友请求应答
	 * @param userId
	 * @param type
	 * @return
	 */
	@RequestMapping("/require")
	public String require(String userId, Integer type) {
		if(MyUtils.isNull(userId) || type == null) {
			return "500";
		}
		
		UserRelationDao dao = new UserRelationDao();
		UserRelationItem item = dao.selectByTwoUser(this.currentUser().getId(), userId);
		if(item == null) {
			return "500";
		}
		item.setResult(type);
		item.setUts(new Date());
		if(dao.update(item) <= 0) {
			return "500";
		}
		
		return this.redirect("/personal/center");
	}
	
	/**
	 * 解除好友关系
	 * @param userId
	 * @return
	 */
	@RequestMapping("/cancelFriend")
	public String cancelFriend(String userId) {
		if(MyUtils.isNull(userId)) {
			return "500";
		}
		
		UserRelationDao dao = new UserRelationDao();
		if(dao.delete(this.currentUser().getId(), userId) <= 0) {
			return "500";
		}
		
		return this.redirect("/personal/friend");
	}	
	
	
	
	/**
	 * 我的好友
	 * @return
	 */
	@RequestMapping("/friend")
	public String friend() {
		
		UserRelationDao dao = new UserRelationDao();
		List<UserItem> userList = dao.selectFriendList(this.currentUser().getId());
		
		this.getRequest().setAttribute("userList", userList);
		this.getRequest().setAttribute("flag", "friend");
		return "manage/student/personal/friend";
	}
	
	// 用户搜索页
	@RequestMapping("/findFriend")
	public String findFriend() {
		
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
		this.getRequest().setAttribute("flag", "friend");
		
		return "manage/student/personal/findFriend";
	}
	
	// 请求好友Get
	@RequestMapping(value = "/requireFriend", method = RequestMethod.GET)
	public String requireGet(String userId) {
		if(this.currentUser() == null) {
			return this.redirectLogin();// 同步返回登录页的方式
		}
		
		this.getRequest().setAttribute("userId", userId);
		return "manage/student/personal/require";
	}
	
	
	// 请求好友Post
	@RequestMapping(value = "/requireFriend", method = RequestMethod.POST)
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
