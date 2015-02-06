package com.santrong.plt.webpage.manage.student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeLevelEntry;
import com.santrong.plt.opt.grade.GradeSubjectEntry;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.dao.WeikeDao;
import com.santrong.plt.webpage.course.entry.CourseBuyQuery;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.OrderItem;
import com.santrong.plt.webpage.course.entry.WeikeOrderView;
import com.santrong.plt.webpage.course.resource.train.dao.KnowledgeDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuery;
import com.santrong.plt.webpage.home.dao.LessonUnitDao;
import com.santrong.plt.webpage.home.entry.LessonUnitItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.manage.student.entry.TrainSimpleView;
import com.santrong.plt.webpage.manage.superman.entry.KnowledgePointerView;
import com.santrong.plt.webpage.manage.superman.entry.KnowledgeTable;

/**
 * @author weinianjie
 * @date 2014年10月14日
 * @time 上午10:34:09
 */
@Controller
@RequestMapping("/study")
public class StudyMAction extends StudentBaseAction {

	/**
	 * 我的知识图谱
	 * @return
	 */
	@RequestMapping("/score")
	public String score(String subject) {
		
		// 学科
		String _subjectId = null;
		String _subject = subject;
		if(MyUtils.isNull(subject)) {
			_subject = "yuwen"; 
		}
		for(GradeSubjectEntry entry : GradeDefine.getByGradeEnName("xiaoxue").getGradeSubjectList()) {
			if(entry.getSubjectEnName().equals(_subject)) {
				_subjectId = entry.getSubjectId();
				break;
			}
		}
		
		// 年级
		 String gradeId = "10000";
		 
		 // 根据学科和年级获取用户的知识点列表
		 KnowledgeDao kDao = new KnowledgeDao();
		 List<KnowledgePointerView> knowledgePointerList = kDao.selectUserKnowledgeMap(this.currentUser().getId(), _subjectId, gradeId);
		 
		 List<KnowledgeTable> knowledgeTableList = new ArrayList<KnowledgeTable>();
		 for(int i=1;i<=20;i++) {
			 KnowledgeTable table = new KnowledgeTable();
//			 table.setWeek(i);
			 for(int j=0;j<knowledgePointerList.size();j++) {
//				 if(knowledgePointerList.get(j).getWeek() == i) {
//					 table.getKnowledgePointerList().add(knowledgePointerList.remove(j));
//					 j--;
//				 }else {
//					 break;
//				 }
			 }
			 knowledgeTableList.add(table);
		 }
		
		 this.getRequest().setAttribute("knowledgeTableList", knowledgeTableList);
		 this.getRequest().setAttribute("subject", _subject);
		 this.getRequest().setAttribute("grade", gradeId);
		
		return "manage/student/score";
	}
	
	/**
	 * 我的课程
	 * @return
	 */
	@RequestMapping("/course")
	public String myCourse() {
		
		HttpServletRequest request = getRequest();
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		CourseDao courseDao = new CourseDao();
		CourseBuyQuery query = new CourseBuyQuery();
		query.setUserId(this.currentUser().getId());
		query.setOrderStatus(OrderItem.Status_Pay);// 已经购买切付款的
		query.setPageSize(12);
		query.setPageNum(pageNum);
		query.setCount(courseDao.selectCountByQuery(query));
		List<CourseItem> courseList = courseDao.selectByQuery(query);
		
		request.setAttribute("courseList", courseList);
		request.setAttribute("query", query);
		
		return "manage/student/myCourse";
	}
	
	/**
	 * 我的直播
	 * @return
	 */
	@RequestMapping("/live")
	public String myLive() {
		return "manage/student/myLive";
	}
	
	
	/**
	 * 我的作业
	 * @return
	 */
	@RequestMapping("/train")
	public String myTrain() {
		HttpServletRequest request = getRequest();
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		TrainDao dao = new TrainDao();
		TrainQuery query =new TrainQuery();
		query.setOwnerId(this.currentUser().getId());
		query.setPageSize(12);
		query.setPageNum(pageNum);
		query.setCount(dao.selectCountByQuery(query));
		List<TrainSimpleView> trainList = dao.selectByQuery(query);
		
		request.setAttribute("trainList", trainList);
		request.setAttribute("query", query);
		return "manage/student/myTrain";
	}
	
	/**
	 * 课程大纲
	 * @return
	 */
	@RequestMapping("/syllabus")
	public String mySyllabus(){
		HttpServletRequest request = getRequest();
		String gradeId = this.currentUser().getGradeId();//获取当前用户所属年级
		String subjectId = request.getParameter("subjectId");
		if (MyUtils.isNull(gradeId)) {
			gradeId = "10000";//一年级
		}
		if (MyUtils.isNull(subjectId)) {
			subjectId = "10000";//语文
		}
		List<LessonUnitItem> lessonUnitList = new ArrayList<LessonUnitItem>();
		LessonUnitDao luDao = new LessonUnitDao();
		List<LessonUnitItem> unitList = luDao.selectTermUnitByGIdAndSId(gradeId, subjectId);// 获取单元列表
		if (unitList != null && unitList.size() > 0) {
			for (LessonUnitItem luItem : unitList) {
				WeikeDao weikeDao = new WeikeDao();
				//"j407932252m5l14va06360x629w28v05"
				List<WeikeOrderView> weikeOrderList = weikeDao.selectWeikeOrderByUnitId(luItem.getId(), this.currentUser().getId());// 获取单元所属微课
				luItem.setWeikeList(weikeOrderList);
				lessonUnitList.add(luItem);
			}
		}
		
		// 获取年级具体对象
		GradeLevelEntry grade = GradeDefine.getLevelByLevelId(gradeId);
		
		request.setAttribute("grade", grade);
		request.setAttribute("lessonUnitList", lessonUnitList);
		request.setAttribute("subjectId", subjectId);
		return "manage/student/syllabus";
	}
	
	
}
