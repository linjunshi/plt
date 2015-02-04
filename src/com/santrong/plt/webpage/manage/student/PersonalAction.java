package com.santrong.plt.webpage.manage.student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.friend.dao.UserRelationDao;
import com.santrong.plt.webpage.friend.entry.FriendMsgItem;
import com.santrong.plt.webpage.friend.entry.UserRelationItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;
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
	
	@RequestMapping("/friend")
	public String friend() {
		
		UserRelationDao dao = new UserRelationDao();
		List<UserItem> userList = dao.selectFriendList(this.currentUser().getId());
		
		this.getRequest().setAttribute("userList", userList);
		return "manage/student/personal/friend";
	}
	
}
