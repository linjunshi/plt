package com.santrong.plt.javabean;

import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseCollectQuery;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.manage.student.dao.UserRelationDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2015年2月27日
 * @time 上午10:11:47
 */
public class UserBean {
	
	
	/**
	 * 获取好友数量
	 * @return
	 */
	public int getFriendCount() {
		UserItem user = (UserItem)ThreadUtils.currentHttpRequest().getSession().getAttribute(Global.SessionKey_LoginUser);
		if(user != null) {
			UserRelationDao dao = new UserRelationDao();
			return dao.getFriendCount(user.getId());
		}
		return 0;
	}
	
	/**
	 * 获取收藏的微课数量
	 * @return
	 */
	public int getCollectionWeikeCount() {
		UserItem user = (UserItem)ThreadUtils.currentHttpRequest().getSession().getAttribute(Global.SessionKey_LoginUser);
		if(user != null) {
			CourseDao dao = new CourseDao();
			CourseCollectQuery query = new CourseCollectQuery();
			query.setUserId(user.getId());
			query.setStatus(CourseItem.Status_Publish);//已经发布
			query.setCourseType(CourseItem.CourseType_Weike);//微课
			return dao.selectCountByQuery(query);
		}
		return 0;		
	}
}
