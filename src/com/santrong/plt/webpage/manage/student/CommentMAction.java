package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.dao.CommentDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CommentCourseView;
import com.santrong.plt.webpage.course.entry.CommentQuery;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年11月5日
 * @time 下午5:07:16
 */
@Controller
@RequestMapping("/comment")
public class CommentMAction extends StudentBaseAction {

	@RequestMapping("")
	public String myComment() {
		UserItem user = this.currentUser();
		if(user == null) {
			// 没登陆
			return this.redirect("/login");
		}
		
		HttpServletRequest request = getRequest();
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		CommentDao dao = new CommentDao();
		CommentQuery query = new CommentQuery();
		query.setPageNum(pageNum);
		query.setUserId(user.getId());
		query.setCount(dao.selectCountByQuery(query));
		List<CommentCourseView> commentList = dao.selectByQuery(query);
		
		request.setAttribute("commentList", commentList);
		request.setAttribute("query", query);
		
		return "manage/student/myComment";
	}
	
	/**
	 * 1、点击删除评论,从评论表中移除该条收藏记录;</br>
	 * 2、并修改该课程的评论数量,自动减1；
	 * @author huangweihua
	 * @param commentId
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/cancelComment")
	public String cancelComment(String commentId, String courseId) {
		if (MyUtils.isNotNull(commentId) && MyUtils.isNotNull(courseId)) {
			ThreadUtils.beginTranx();
			
			// 1、点击删除评论,从评论表中移除该条收藏记录;
			CommentDao commentDao = new CommentDao();
			commentDao.delete(commentId);

			// 并修改该课程的评论数量,自动减1；
			CourseDao courseDao = new CourseDao();
			courseDao.removeComment(courseId);
			
			ThreadUtils.commitTranx();
		}
		return this.redirect("/comment");
	}
}
