package com.santrong.plt.webpage.school;

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
import com.santrong.plt.webpage.school.dao.SchoolDao;
import com.santrong.plt.webpage.school.entry.SchoolItem;
import com.santrong.plt.webpage.school.entry.SchoolQuery;
import com.santrong.plt.webpage.user.dao.UserDao;
import com.santrong.plt.webpage.user.entry.UserItem;

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
		
		// 查询学校里的老师和学生
		UserDao userDao = new UserDao();
		List<UserItem> teacher = userDao.selectTeacherBySchoolId(id);
		
		// 查询学校里所有开设的课程
		CourseDao courseDao = new CourseDao();
		List<CourseItem> course = courseDao.selectCourseBySchoolId(id);

		HttpServletRequest request = getRequest();
		request.setAttribute("teacher", teacher);
		request.setAttribute("course", course);
		
		return "school/detail";
	}
	
}
