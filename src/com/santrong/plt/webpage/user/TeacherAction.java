package com.santrong.plt.webpage.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseQuery;
import com.santrong.plt.webpage.home.dao.GradeDao;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.GradeView;
import com.santrong.plt.webpage.home.entry.SubjectItem;
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserDetailView;
import com.santrong.plt.webpage.user.entry.UserItem;
import com.santrong.plt.webpage.user.entry.UserQuery;

/**
 * @author weinianjie
 * @date 2014年10月10日
 * @time 下午5:01:51
 */
@Controller
@RequestMapping("/teacher")
public class TeacherAction extends BaseAction {
	
	/**
	 * 老师首页
	 * @return
	 */
	@RequestMapping(value="")
	public String teacherIndex() {
		return catagory("all", "all");
	}
	
	
	/**
	 * 按年级搜索的老师
	 * @param grade
	 * @return
	 */
	@RequestMapping("/{grade}")
	public String catagory(@PathVariable String grade) {
		return catagory(grade, "all");
	}
	
	/**
	 * 按年级和科目搜索的老师
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
		
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		// 获取老师
		UserQuery userQuery = new UserQuery();
		userQuery.setAreaCode(area.getCityCode());
		userQuery.setRole(UserItem.Role_Teacher);
		userQuery.setPageSize(15);
		userQuery.setPageNum(pageNum);
		if(MyUtils.isNotNull(subject) && !subject.equals("all")) {
			userQuery.setSubjectEnName(subject);
		}
		if(MyUtils.isNotNull(grade) && !grade.equals("all")) {
			userQuery.setSchoolGrade(GradeDefine.getByGradeEnName(grade).getGradeGroup());
		}
		
		UserDao userDao = new UserDao();
		userQuery.setCount(userDao.selectCountByQuery(userQuery));
		List<UserItem> teacherList = userDao.selectByQuery(userQuery);
		
		request.setAttribute("query", userQuery);
		
		request.setAttribute("grade", grade);
		request.setAttribute("subject", subject);
	
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("gradeList", gradeList);
		
		request.setAttribute("teacherList", teacherList);
		
		return "teacher/index";
	}	
	
	/**
	 * 获取某一位老师的详细信息页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}.html")
	public String detail(@PathVariable String id) {
		HttpServletRequest request = getRequest();
		String grade = request.getParameter("grade");
		String level = request.getParameter("level");
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		/*获取某一位老师的详细信息*/
		UserDao userDao = new UserDao();
		UserDetailView teacher = userDao.selectDetailById(id);
		
		// 获取老师的课程
		CourseDao courseDao = new CourseDao();
		CourseQuery query = new CourseQuery();
		query.setUserId(teacher.getId());
		query.setLevelEnName(level);
		query.setGradeEnName(grade);
		query.setPageSize(3);
		query.setPageNum(pageNum);
		query.setCount(courseDao.selectCountByQuery(query));
		List<CourseItem> courseList = courseDao.selectByQuery(query);
		
		/*把老师的信息传到前端去*/
		request.setAttribute("teacher", teacher);
		request.setAttribute("courseList", courseList);
		request.setAttribute("query", query);
		
		return "teacher/detail";
	}
	
}
