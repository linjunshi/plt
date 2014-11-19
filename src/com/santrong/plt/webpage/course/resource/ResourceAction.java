package com.santrong.plt.webpage.course.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;
import com.santrong.plt.webpage.course.entry.ChapterDetailView;
import com.santrong.plt.webpage.course.entry.ResourceEntry;

/**
 * @author huangweihua
 * @date   2014年10月31日 
 * @time   上午10:43:55
 */

@Controller
@RequestMapping("/course_res")
public class ResourceAction extends BaseAction {
	
	@RequestMapping(value="",method=RequestMethod.GET)
	private String openResource(String courseId, String resId, String resType) {
		
		if(MyUtils.isNull(courseId) || MyUtils.isNull(resId) || MyUtils.isNull(resType)) {
			this.redirect("/");
		}
		
		HttpServletRequest request = getRequest();
		
		// 课程章节
		ChapterDao chapterDao = new ChapterDao();
		List<ChapterAndResourceEntry> resourceList = chapterDao.selectByCourseId(courseId);
		
		Map<String, ChapterDetailView> chapterMap = new LinkedHashMap<String, ChapterDetailView>();
		for(ChapterAndResourceEntry cr : resourceList) {
			ChapterDetailView cdv = chapterMap.get(cr.getId());
			if(cdv == null) {
				cdv = new ChapterDetailView();
				cdv.setRemark(cr.getRemark());
			}
			if(cdv.getResourceList() == null) {
				cdv.setResourceList(new ArrayList<ResourceEntry>());
			}
			if(cr.getResourceType() > 0) {
				ResourceEntry resource = new ResourceEntry();
				resource.setTitle(cr.getTitle());
				resource.setId(cr.getResourceId());
				resource.setType(cr.getResourceType());
				cdv.getResourceList().add(resource);
			}
			chapterMap.put(cr.getId(), cdv);
		}
		
		List<ChapterDetailView> chapterList = new ArrayList<ChapterDetailView>();
		for(Iterator<Map.Entry<String, ChapterDetailView>> it = chapterMap.entrySet().iterator();it.hasNext();){
            Entry<String, ChapterDetailView> entry = (Entry<String, ChapterDetailView>)it.next();
            chapterList.add(entry.getValue());
        }		
		
		request.setAttribute("chapterList", chapterList);
		request.setAttribute("resId", resId);
		request.setAttribute("resType", resType);
		
		return "/course/resource/course_res";
	}
}
