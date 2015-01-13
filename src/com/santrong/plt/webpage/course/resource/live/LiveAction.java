package com.santrong.plt.webpage.course.resource.live;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.dao.OrderDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.resource.live.dao.LiveDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/live")
public class LiveAction extends StudentBaseAction {

	@RequestMapping("")
	public String home(String courseId, String resId) {
		
		// 获取章节
		ChapterDao chapterDao = new ChapterDao();
		ChapterItem chapter = chapterDao.selectByResourceId(resId);
		
		//TODO 权限校验，判断当前用户是否已经购买该课程
		OrderDao orderDao = new OrderDao();
		if(!orderDao.existsByCourseIdAndUserId(courseId, this.currentUser().getId())) {//没有购买不能看直播
			return this.redirect("/");
		}
				
		// 获取直播
		LiveDao liveDao = new LiveDao();
		LiveItem live = liveDao.selectById(resId);
		
		// 未知的train或者train没有添加到课程章节里的，无法开始该测试
		if(live ==  null || chapter == null) {
			return this.redirect("/");
		}
		
		// 获取课程
		CourseDao courseDao = new CourseDao();
		CourseItem course = courseDao.selectByChapterId(chapter.getCourseId());
		
		HttpServletRequest request = this.getRequest();
		request.setAttribute("live", live);
		request.setAttribute("chapter", chapter);
		request.setAttribute("course", course);
		
		return "course/resource/live/detail";
	}
	
	@RequestMapping(value = "/isPay", method=RequestMethod.POST)
	@ResponseBody
	public String isPay(String courseId, String resId) {
		//TODO 权限校验，判断当前用户是否已经购买该课程
		OrderDao orderDao = new OrderDao();
		if(orderDao.existsByCourseIdAndUserId(courseId, this.currentUser().getId())) {//没有购买不能看直播
			return SUCCESS;
		}
		return FAIL;
	}
}
