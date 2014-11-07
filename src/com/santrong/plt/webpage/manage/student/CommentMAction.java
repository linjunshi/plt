package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.course.dao.CommentDao;
import com.santrong.plt.webpage.course.entry.CommentItem;
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
		query.setCount(dao.selectCountByQuery(query));
		List<CommentItem> commentList = dao.selectByQuery(query);
		
		request.setAttribute("commentList", commentList);
		request.setAttribute("query", query);
		
		return "manage/student/myComment";
	}
}
