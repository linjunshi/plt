package com.santrong.plt.webpage.course.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeLevelEntry;
import com.santrong.plt.webpage.BaseService;
import com.santrong.plt.webpage.home.dao.GradeDao;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.GradeView;
import com.santrong.plt.webpage.home.entry.SubjectItem;

/**
 * @author weinianjie
 * @date 2014年10月11日
 * @time 上午9:56:09
 */
public class CourseService extends BaseService {

	public CourseService(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}
	
	/**
	 * 输出课程搜索页面的过滤器
	 * @param grade
	 * @param subject
	 * @param level
	 */
	public void publishDetailFilter(String grade, String subject, String level) {
		
		// 分类-科目
		SubjectDao subjectDao = new SubjectDao();
		List<SubjectItem> subjectList;
		if(grade.equals("all")) {
			subjectList = subjectDao.selectAll();
		}else {
			subjectList = subjectDao.selectByGradeEnName(grade);
		}
		
		// 分类-类别
		GradeDao gradeDao = new GradeDao();
		List<GradeView> gradeList;
		if(subject.equals("all")) {
			gradeList = gradeDao.selectGrade();
		}else {
			gradeList = gradeDao.selectGradeBySubjectEnName(subject);
		}
		
		// 分类-年级
		List<GradeLevelEntry> levelList = null;
		if(!grade.equals("all")) {
			levelList = GradeDefine.getByGradeEnName(grade).getGradeLevelList();
		}
		
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("gradeList", gradeList);
		request.setAttribute("levelList", levelList);
		request.setAttribute("grade", grade);
		request.setAttribute("subject", subject);
		request.setAttribute("level", level);
	}
	
	public void publishCourseSorter() {
		
	}
}
