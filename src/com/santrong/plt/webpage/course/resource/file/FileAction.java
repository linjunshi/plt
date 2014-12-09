package com.santrong.plt.webpage.course.resource.file;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.resource.file.dao.FileDao;
import com.santrong.plt.webpage.course.resource.file.entry.FileItem;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/file")
public class FileAction extends BaseAction {

	@RequestMapping("")
	public String home(String resId) {
		
		//TODO 权限校验
		
		// 获取直播
		FileDao fileDao = new FileDao();
		FileItem vod = fileDao.selectById(resId);
		
		// 获取章节
		ChapterDao chapterDao = new ChapterDao();
		ChapterItem chapter = chapterDao.selectByResourceId(resId);
		
		// 未知的train或者train没有添加到课程章节里的，无法开始该测试
		if(vod ==  null || chapter == null) {
			this.redirect("/");
		}
		
		// 获取课程
		CourseDao courseDao = new CourseDao();
		CourseItem course = courseDao.selectByChapterId(chapter.getCourseId());	

		HttpServletRequest request = this.getRequest();
		request.setAttribute("vod", vod);
		request.setAttribute("chapter", chapter);
		request.setAttribute("course", course);
		
		return "course/resource/file/detail";
	}
}
