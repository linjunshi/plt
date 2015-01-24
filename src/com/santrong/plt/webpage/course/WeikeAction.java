package com.santrong.plt.webpage.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.ParamHelper;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeLevelEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.WeikeDao;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.WeikeDetailView;
import com.santrong.plt.webpage.course.entry.WeikeQuery;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.SubjectItem;

/**
 * @author weinianjie
 * @date 2015年1月19日
 * @time 下午3:24:53
 */
@Controller
@RequestMapping("/weike")
public class WeikeAction extends BaseAction {

	/**
	 * 微课首页
	 * @return
	 */
	@RequestMapping(value="")
	public String index() {
		return catagory("all");
	}
	
	/**
	 * 按年级和科目搜索的微课
	 * @param grade
	 * @param subject
	 * @return
	 */
	@RequestMapping("/{subject}")
	public String catagory(@PathVariable String subject) {
		HttpServletRequest request = getRequest();
		AreaEntry area = (AreaEntry)(request.getSession().getAttribute(Global.SessionKey_Area));
		
		// 条件-科目
		SubjectDao subjectDao = new SubjectDao();
		List<SubjectItem> subjectList = subjectDao.selectByGradeEnName("xiaoxue");
		
		// 条件-年级
		List<GradeLevelEntry> levelList = GradeDefine.getByGradeEnName("xiaoxue").getGradeLevelList();
		
		// 获取参数
		ParamHelper param = new ParamHelper();
		param.init(request);
		
		int page = this.getIntParameter("page");
		if(page == 0) {
			page = 1;
		}

		// 构建查询条件
		WeikeQuery weikeQuery = new WeikeQuery();
		weikeQuery.setStatus(CourseItem.Status_Publish);
		weikeQuery.setPageNum(page);
		weikeQuery.setAreaCode(area.getCityCode());
		weikeQuery.setGradeEnName("xiaoxue");
		if(MyUtils.isNotNull(subject) && !subject.equals("all")) {
			weikeQuery.setSubjectEnName(subject);
		}
		if(MyUtils.isNotNull(param.getKeyword())) {
			weikeQuery.setKeywords(param.getKeyword());
		}
		weikeQuery.setLevelEnName(param.getParamByStart("level"));
		weikeQuery.setLive(param.getParamContain("live"));
	
		
		if(MyUtils.isNotNull(param.getOrderBy())) {
			weikeQuery.setOrderBy(param.getOrderBy());
			weikeQuery.setOrderRule(param.getOrderRule());
		}
		
		// 查询课程列表
		WeikeDao weikeDao = new WeikeDao();
		weikeQuery.setCount(weikeDao.selectCountByQuery(weikeQuery));
		List<CourseItem> courseList = weikeDao.selectByQuery(weikeQuery);
		
		request.setAttribute("query", weikeQuery);
		
		// 参数组
		request.setAttribute("subject", subject);
//		request.setAttribute("param", param);
		ThreadUtils.setParam(param);
		
		// 搜索条件
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("levelList", levelList);		
		
		// 主内容
		request.setAttribute("courseList", courseList);
		
		return "weike/index";
	}
	
	/**
	 * 课程详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}.html")
	public String detail(@PathVariable String id) {
		
		// 课程详细
		WeikeDao weikeDao = new WeikeDao();
		WeikeDetailView course = weikeDao.selectByCourseId(id);
		if(course == null) {
			return "404";
		}
		
		HttpServletRequest request = getRequest();
		request.setAttribute("course", course);
		
		String type = request.getParameter("type");
		if(type != null && type.equals("html5")) {
			return "weike/html5";
		}
		
		return "weike/detail";
	}	
}
