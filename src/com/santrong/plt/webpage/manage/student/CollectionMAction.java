package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseCollectQuery;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年11月5日
 * @time 下午5:07:16
 */
@Controller
@RequestMapping("/collection")
public class CollectionMAction extends StudentBaseAction {

	@RequestMapping("")
	public String myCollection() {
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
		
		CourseDao dao = new CourseDao();
		CourseCollectQuery query = new CourseCollectQuery();
		query.setPageNum(pageNum);
		query.setCount(dao.selectCountByQuery(query));
		List<CourseItem> courseList = dao.selectByQuery(query);
		
		request.setAttribute("courseList", courseList);
		request.setAttribute("query", query);
		
		return "manage/student/myCollection";
	}
}
