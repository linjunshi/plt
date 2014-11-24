package com.santrong.plt.webpage.manage.teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.course.resource.live.dao.LiveDao;
import com.santrong.plt.webpage.manage.TeacherBaseAction;
import com.santrong.plt.webpage.manage.teacher.entry.TeacherLiveForm;

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
	
	
}
