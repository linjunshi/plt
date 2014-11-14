package com.santrong.plt.webpage.school;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeDefineEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseQuery;
import com.santrong.plt.webpage.school.dao.SchoolDao;
import com.santrong.plt.webpage.school.entry.SchoolItem;
import com.santrong.plt.webpage.school.entry.SchoolQuery;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;
import com.santrong.plt.webpage.teacher.entry.UserQuery;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/school")
public class SchoolAction extends BaseAction {
	
	/**
	 * 学校首页
	 * @return
	 */
	@RequestMapping(value="")
	public String index() {
		return  catagory("all");
	}	
	
	/**
	 * 按年级搜索的学校
	 * @param grade
	 * @return
	 */
	@RequestMapping("/{grade}")
	public String catagory(@PathVariable String grade) {
		HttpServletRequest request = getRequest();
		AreaEntry area = (AreaEntry)(request.getSession().getAttribute(Global.SessionKey_Area));		
		
		// 关键字
		String keyword = request.getParameter("q");		
		
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		SchoolDao schoolDao = new SchoolDao();
		SchoolQuery schoolQuery = new SchoolQuery();
		schoolQuery.setAreaCode(area.getCityCode());
		if(MyUtils.isNotNull(grade) && !grade.equals("all")) {
			schoolQuery.setSchoolGrade(GradeDefine.getByGradeEnName(grade).getGradeGroup());
		}
		schoolQuery.setPageSize(15);
		schoolQuery.setPageNum(pageNum);
		schoolQuery.setKeywords(keyword);
		schoolQuery.setCount(schoolDao.selectCountByQuery(schoolQuery));
		List<SchoolItem> schoolList = schoolDao.selectByQuery(schoolQuery);
		
		request.setAttribute("schoolList", schoolList);
		request.setAttribute("grade", grade);
		request.setAttribute("query", schoolQuery);
		
		return "school/index";
	}
	
	
	/**
	 * 学校详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}.html")
	public String detail(@PathVariable String id) {
		HttpServletRequest request = this.getRequest();
		String grade = request.getParameter("grade");
		String level = request.getParameter("level");
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		// 获取学校和学校里的年级
		SchoolDao schoolDao = new SchoolDao();
		SchoolItem school = schoolDao.selectById(id);
		if(school == null) {
			return "404";
		}
		List<GradeDefineEntry> gradeList = new ArrayList<GradeDefineEntry>();
		for(GradeDefineEntry g:GradeDefine.gradeList){
			if((school.getSchoolGrade() & g.getGradeGroup()) == g.getGradeGroup()) {
				gradeList.add(g);
			}
		}
		
		// 查询学校里的老师和学生
		UserDao userDao = new UserDao();
		UserQuery userQuery = new UserQuery();
		userQuery.setRole(UserItem.Role_Teacher);
		userQuery.setSchoolId(school.getId());
		userQuery.setPageSize(6);
		List<UserItem> teacherList = userDao.selectByQuery(userQuery);
		
		// 查询学校里所有开设的课程
		CourseDao courseDao = new CourseDao();
		CourseQuery courseQuery = new CourseQuery();
		courseQuery.setGradeEnName(grade);
		courseQuery.setLevelEnName(level);
		courseQuery.setSchoolId(school.getId());
		courseQuery.setPageNum(pageNum);
		courseQuery.setPageSize(8);
		courseQuery.setCount(courseDao.selectCountByQuery(courseQuery));
		List<CourseItem> courseList = courseDao.selectByQuery(courseQuery);

		request.setAttribute("school", school);
		request.setAttribute("gradeList", gradeList);
		request.setAttribute("teacherList", teacherList);
		request.setAttribute("courseList", courseList);
		request.setAttribute("query", courseQuery);
		
		return "school/detail";
	}
	
}
