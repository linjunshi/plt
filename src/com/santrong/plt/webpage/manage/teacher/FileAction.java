package com.santrong.plt.webpage.manage.teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.course.resource.file.dao.FileDao;
import com.santrong.plt.webpage.course.resource.file.entry.FileItem;
import com.santrong.plt.webpage.course.resource.file.entry.FileQuery;
import com.santrong.plt.webpage.manage.TeacherBaseAction;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午10:26:52
 */
@Controller
@RequestMapping("/manage")
public class FileAction extends TeacherBaseAction {
	
	/**
	 * 视频管理
	 * @param keyword
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/video")
	public String list(String keyword, Integer pageNum){
		HttpServletRequest request = getRequest();
		if(pageNum == null) {
			pageNum = 0;
		}
		
		FileDao fileDao = new FileDao();
		FileQuery query = new FileQuery();
		query.setKeywords(keyword);
		query.setPageNum(pageNum);
		query.setOrderBy("cts");
		query.setOrderRule("desc");
		query.setCount(fileDao.selectCountByQuery(query));
		List<FileItem> fileList = fileDao.selectByQuery(query);
		
		request.setAttribute("query", query);
		request.setAttribute("fileList", fileList);
		
		return "manage/file/list";
	}
	
	
}
