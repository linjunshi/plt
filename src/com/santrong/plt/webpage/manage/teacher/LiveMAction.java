package com.santrong.plt.webpage.manage.teacher;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.resource.live.dao.LiveDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveItem;
import com.santrong.plt.webpage.manage.TeacherBaseAction;
import com.santrong.plt.webpage.manage.teacher.entry.TeacherLiveForm;
import com.santrong.plt.webpage.manage.teacher.entry.TeacherToolParams;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:26:52
 */
@Controller
@RequestMapping("/manage/live")
public class LiveMAction extends TeacherBaseAction {
	
	/**
	 * 老师要上的直播课
	 * @param keyword
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("")
	public String list(){
		HttpServletRequest request = getRequest();
		
		LiveDao dao = new LiveDao();
		List<TeacherLiveForm> liveList = dao.selectTeacherLive(this.currentUser().getId());
		
		request.setAttribute("liveList", liveList);
		
		return "manage/teacher/liveList";
	}
	
	/**
	 * 老师打开直播客户端
	 * @param liveId
	 * @return
	 */
	@RequestMapping("/openTool")
	@ResponseBody
	public String openTool(String liveId) {
		if(!MyUtils.isNotNull(liveId)) {
			return "参数错误";
		}
		
		// 获取直播
		LiveDao liveDao = new LiveDao();
		LiveItem live = liveDao.selectById(liveId);
		
		// 获取章节
		ChapterDao chapterDao = new ChapterDao();
		ChapterItem chapter = chapterDao.selectByResourceId(liveId);
		
		// 未知的train或者train没有添加到课程章节里的，无法开始该测试
		if(live ==  null || chapter == null) {
			return "未知课程";
		}
		
		// 获取课程
		CourseDao courseDao = new CourseDao();
		CourseItem course = courseDao.selectByChapterId(chapter.getCourseId());			
		
		TeacherToolParams params = new TeacherToolParams();
		params.setRtmpUrl("rtmp://" + Global.PltDomain + ":1980/");
		params.setWebUrl("http://" + Global.PltDomain + "/http/basic");
		params.setPltHost(Global.PltDomain);
		params.setTeacherId(course.getOwnerId());
		params.setTeacherName(course.getTeacher());
		params.setSourceId(live.getId());
		params.setSourceTitle(live.getTitle());
		params.setBeginTime(live.getBeginTime());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(live.getBeginTime());
		cal.add(Calendar.MINUTE, live.getDuration());
		params.setEndTime(cal.getTime());
		
		Gson gson = new Gson();
		return gson.toJson(params);
	}
}
