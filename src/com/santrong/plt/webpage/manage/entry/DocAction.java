package com.santrong.plt.webpage.manage.entry;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.SimpleQuery;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.manage.dao.DocDao;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午11:19:35
 */
@Controller
@RequestMapping("/manage")
public class DocAction extends BaseAction {
	
	/**
	 * 视频管理
	 * @param keyword
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/doc")
	public String list(String keyword, Integer pageNum){
		HttpServletRequest request = getRequest();
		if(pageNum == null) {
			pageNum = 0;
		}
		
		DocDao docDao = new DocDao();
		SimpleQuery query = new SimpleQuery();
		query.setKeywords(keyword);
		query.setPageNum(pageNum);
		query.setOrderBy("cts");
		query.setOrderRule("desc");
		query.setCount(docDao.selectCountByQuery(query));
		List<DocItem> docList = docDao.selectByQuery(query);
		
		request.setAttribute("query", query);
		request.setAttribute("docList", docList);
		
		return "manage/doc/list";
	}

}
