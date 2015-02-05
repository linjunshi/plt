package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.course.dao.CourseAttendHistoryDao;
import com.santrong.plt.webpage.course.dao.WeikeDao;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseOrderAttendView;
import com.santrong.plt.webpage.course.entry.WeikeQuery;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author huaweihua
 * @date 2015年2月2日 
 * @time 下午4:14:00
 */
@Controller
@RequestMapping("/history")
public class CourseHistoryMAction extends StudentBaseAction {

	/**
	 * 浏览历史记录
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
			
			WeikeDao weikeDao = new WeikeDao();
			WeikeQuery query = new WeikeQuery();
			query.setUserId(this.currentUser().getId());
			query.setPageSize(9);
			query.setShowSize(4);
			query.setPageNum(pageNum);
			query.setStatus(CourseItem.Status_Publish);//已经发布的
			query.setCount(weikeDao.selectCountHistoryByQuery(query));
			query.setOrderBy("b.uts");
			query.setOrderRule("desc");
			List<CourseOrderAttendView> weikeList = weikeDao.selectCourseHistoryByQuery(query);
			
			request.setAttribute("weikeList", weikeList);
			request.setAttribute("query", query);
			request.setAttribute("flag", "history");
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/student/historyList";
	}
	
	@RequestMapping(value="/removeAttendHistory", method=RequestMethod.POST)
	@ResponseBody
	public String removeAttendHistory(){
		try {
			HttpServletRequest request = getRequest();
			String attendId = request.getParameter("attendId");
			CourseAttendHistoryDao cahDao = new CourseAttendHistoryDao();
			if (cahDao.removeAttendHistory(attendId)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
}
