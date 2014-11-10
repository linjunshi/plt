package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.webpage.course.dao.CollectCourseDao;
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
	
	/**
	 * 1、点击取消收藏,修改该课程的收藏数量,自动减1；</br>
	 * 2、并从收藏表中移除该条收藏记录
	 * @author huangweihua
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/cancelCollect", method=RequestMethod.POST)
	public String cancelCollect(String courseId) {
		try {
			UserItem userItem = this.currentUser();
			
			ThreadUtils.beginTranx();
			
			//1、点击取消收藏,修改该课程的收藏数量,自动减1；
			CourseDao courseDao = new CourseDao();
			courseDao.removeCollection(courseId);
			
			//2、并从收藏表中移除该条收藏记录
			CollectCourseDao ccDao = new CollectCourseDao();
			ccDao.removeCollect(userItem.getId(), courseId);
			
			ThreadUtils.commitTranx();
			
		} catch (Exception e) {
			ThreadUtils.rollbackTranx();
			Log.printStackTrace(e);
		}
		return this.redirect("/collection");
	}
}
