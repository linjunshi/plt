package com.santrong.plt.webpage.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.ParamHelper;
import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeLevelEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.dao.CommentDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;
import com.santrong.plt.webpage.course.entry.ChapterDetailView;
import com.santrong.plt.webpage.course.entry.CommentItem;
import com.santrong.plt.webpage.course.entry.CourseDetailView;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseQuery;
import com.santrong.plt.webpage.course.entry.ResourceEntry;
import com.santrong.plt.webpage.home.dao.GradeDao;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.GradeView;
import com.santrong.plt.webpage.home.entry.SubjectItem;
import com.santrong.plt.webpage.school.dao.SchoolDao;
import com.santrong.plt.webpage.school.entry.SchoolItem;
import com.santrong.plt.webpage.school.entry.SchoolQuery;
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserItem;
import com.santrong.plt.webpage.user.entry.UserQuery;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/course")
public class CourseAction extends BaseAction {
	
	/**
	 * 课程首页
	 * @return
	 */
	@RequestMapping(value="")
	public String index() {
		return catagory("all", "all");
	}
	
	/**
	 * 按年级搜索的课程
	 * @param grade
	 * @return
	 */
	@RequestMapping("/{grade}")
	public String catagory(@PathVariable String grade) {
		return catagory(grade, "all");
	}
	
	/**
	 * 按年级和科目搜索的课程
	 * @param grade
	 * @param subject
	 * @return
	 */
	@RequestMapping("/{grade}/{subject}")
	public String catagory(@PathVariable String grade, @PathVariable String subject) {
		HttpServletRequest request = getRequest();
		AreaEntry area = (AreaEntry)(request.getSession().getAttribute(Global.SessionKey_Area));
		
		// 条件-科目
		SubjectDao subjectDao = new SubjectDao();
		List<SubjectItem> subjectList = null;
		if(grade.equals("all")) {
			subjectList = subjectDao.selectAll();
		}else {
			subjectList = subjectDao.selectByGradeEnName(grade);
		}
		
		// 条件-类别
		GradeDao gradeDao = new GradeDao();
		List<GradeView> gradeList = null;
		if(subject.equals("all")) {
			gradeList = gradeDao.selectGrade();
		}else {
			gradeList = gradeDao.selectGradeBySubjectEnName(subject);
		}
		
		// 条件-年级
		List<GradeLevelEntry> levelList = null;
		if(!grade.equals("all")) {
			levelList = GradeDefine.getByGradeEnName(grade).getGradeLevelList();
		}		
		
		
		
		// 获取参数
		ParamHelper param = new ParamHelper();
		param.init(request);

		// 构建查询条件
		CourseQuery courseQuery = new CourseQuery();
		courseQuery.setAreaCode(area.getCityCode());
		if(MyUtils.isNotNull(subject) && !subject.equals("all")) {
			courseQuery.setSubjectEnName(subject);
		}
		if(MyUtils.isNotNull(grade) && !grade.equals("all")) {
			courseQuery.setGradeEnName(grade);
		}
		courseQuery.setLevelEnName(param.getParamByStart("level"));
		courseQuery.setLive(param.getParamContain("live"));
		
		if(MyUtils.isNotNull(param.getOrderBy())) {
			courseQuery.setOrderBy(param.getOrderBy());
			courseQuery.setOrderRule(param.getOrderRule());
		}
		
		// 查询课程列表
		CourseDao courseDao = new CourseDao();
		List<CourseItem> courseList = courseDao.selectByQuery(courseQuery);
		
		// 学校列表
		SchoolDao schoolDao = new SchoolDao();
		SchoolQuery schoolQuery = new SchoolQuery();
		schoolQuery.setAreaCode(area.getCityCode());
		schoolQuery.setPageSize(4);
		List<SchoolItem> schoolList = schoolDao.selectByQuery(schoolQuery);
		
		// 老师列表
		UserDao userDao = new UserDao();
		UserQuery userQuery = new UserQuery();
		userQuery.setAreaCode(area.getCityCode());
		userQuery.setPageSize(4);
		List<UserItem> teacherList = userDao.selectByQuery(userQuery);
		
		
		
		// 参数组
		request.setAttribute("grade", grade);
		request.setAttribute("subject", subject);
		request.setAttribute("param", param);
		
		// 搜索条件
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("gradeList", gradeList);
		request.setAttribute("levelList", levelList);		
		
		// 主内容
		request.setAttribute("courseList", courseList);
		request.setAttribute("schoolList", schoolList);
		request.setAttribute("teacherList", teacherList);
		
		return "course/index";
	}
	
	/**
	 * 课程详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}.html")
	public String detail(@PathVariable String id) {
		
		// 课程详细
		CourseDao courseDao = new CourseDao();
		CourseDetailView course = courseDao.selectDetailById(id);
		if(course == null) {
			return "404";
		}

		// 课程章节
		ChapterDao chapterDao = new ChapterDao();
		List<ChapterAndResourceEntry> resourceList = chapterDao.selectByCourseId(course.getId());
		
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
		course.setChapterDetailList(chapterList);
		
		// 课程评价
		CommentDao commentDao = new CommentDao();
		List<CommentItem> commentList = commentDao.selectByCourseId(course.getId());
		course.setCommentList(commentList);
		
		// 课程老师
		UserDao userDao = new UserDao();
		UserItem teacher = userDao.selectById(course.getOwnerId());
		
		// 老师名下其他课程
		
		HttpServletRequest request = getRequest();
		request.setAttribute("course", course);
		request.setAttribute("teacher", teacher);
		
		return "course/detail";
	}
	
}
