package com.santrong.plt.webpage.manage.teacher;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;
import com.santrong.plt.webpage.course.entry.ChapterDetailView;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.CourseDetailView;
import com.santrong.plt.webpage.course.entry.CourseForm;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseQuery;
import com.santrong.plt.webpage.course.entry.ResourceEntry;
import com.santrong.plt.webpage.manage.TeacherBaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年11月10日
 * @time 下午2:16:14
 */
@Controller
@RequestMapping("/manage/course")
public class CourseMAction extends TeacherBaseAction {

	/**
	 * 课程管理
	 * @return
	 */
	@RequestMapping("")
	public String myCourse() {
		try {
			HttpServletRequest request = getRequest();
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			CourseDao dao = new CourseDao();
			CourseQuery query = new CourseQuery();
			query.setUserId(this.currentUser().getId());
			query.setPageSize(12);
			query.setPageNum(pageNum);
			query.setCount(dao.selectCountByQuery(query));
			List<CourseItem> courseList = dao.selectByQuery(query);
			
			request.setAttribute("courseList", courseList);
			request.setAttribute("query", query);
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/teacher/courseList";
	}
	
	@RequestMapping("/add")
	public String addCourse(){
		HttpServletRequest request = this.getRequest();
		request.setAttribute("fn", "add");	
		return "/manage/teacher/courseAdd";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCoursePost(CourseForm courseForm){
		try {
			UserItem user = this.currentUser();
			if (user == null) {
				return this.redirect("/login");
			}
			
			if (courseForm != null) {
				
				if (MyUtils.isNull(courseForm.getGradeId())) {
					addError("请您选择课程级别！");
				}
				if (MyUtils.isNull(courseForm.getSubjectId())) {
					addError("请您选择课程科目！");
				}
				if (MyUtils.isNull(courseForm.getCourseName())) {
					addError("课程名称不能为空！");
				}
				if (courseForm.getPrice() == null) {
					addError("课程价格不能为空！");
				}
				if (courseForm.getChapterCount() == null) {
					addError("课时数量不能为空！");
				}
				if (courseForm.getLive() == null) {
					addError("是否是直播课程不能为空！");
				}
				
				if(this.errorSize() == 0) {
					CourseDao courseDao = new CourseDao();
					
					CourseItem courseItem = new CourseItem();
					BeanUtils.copyProperties(courseForm, courseItem);
					
					courseItem.setId(MyUtils.getGUID());
					courseItem.setOwnerId(user.getId());
					courseItem.setEndTime(MyUtils.stringToDate(courseForm.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
					courseItem.setChapterCount(courseForm.getChapterCount() > 0 ? courseForm.getChapterCount() : 0);
					courseItem.setSaleCount(0);
					courseItem.setCollectCount(0);
					courseItem.setCommentCount(0);
					courseItem.setCts(new Date());
					courseItem.setUts(new Date());
					if (courseDao.insert(courseItem)) {
						addError("新增课程成功！");
						return this.redirect("/manage/course/chapterEditor?courseId=" + courseItem.getId());
					} 
				}
				
				HttpServletRequest request = this.getRequest();
				request.setAttribute("course", courseForm);
				request.setAttribute("fn", "add");	
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/courseAdd";
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyCourse(String courseId){
		try {
			
			CourseDao courseDao = new CourseDao();
			CourseItem course = courseDao.selectById(courseId);
			
			if(course == null) {
				this.redirect("/study/course");
			}
			
			if(!course.getOwnerId().equals(this.currentUser().getId())) {
				this.redirect("/study/course");
			}
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("course", course);
			request.setAttribute("fn", "modify");
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/courseAdd";
	}
	
	/**
	 * 修改课程信息页面
	 * @param courseItem
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyCoursePost(CourseForm courseForm){
		try {
			
			if (courseForm != null) {
				
				if (MyUtils.isNull(courseForm.getGradeId())) {
					addError("请您选择课程级别！");
				}
				if (MyUtils.isNull(courseForm.getSubjectId())) {
					addError("请您选择课程科目！");
				}
				if (MyUtils.isNull(courseForm.getCourseName())) {
					addError("课程名称不能为空！");
				}
				if (courseForm.getPrice() == null) {
					addError("课程价格不能为空！");
				}
				if (courseForm.getChapterCount() == null) {
					addError("课时数量不能为空！");
				}
				if (courseForm.getLive() == null) {
					addError("是否是直播课程不能为空！");
				}

				if(this.errorSize() == 0) {
					CourseDao courseDao = new CourseDao();
					CourseItem courseItem = courseDao.selectById(courseForm.getId());
					BeanUtils.copyProperties(courseForm, courseItem);
					
					courseItem.setEndTime(MyUtils.stringToDate(courseForm.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
					courseItem.setUts(new Date());
					if (courseDao.update(courseItem)) {
						addError("修改课程成功！");
					} else {
						addError("修改课程失败，请刷新页面后重新操作！");
					}
				}
				this.getRequest().setAttribute("course", courseForm);
				this.getRequest().setAttribute("fn", "modify");		
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		
		return this.redirect("/manage/course/modify?courseId=" + courseForm.getId());
	}
	
	/**
	 * 删除一条课程记录
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteCourse(String courseId){
		try {
			CourseDao courseDao = new CourseDao();
			courseDao.deleteById(courseId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/manage/course");
	}
	
	/**
	 * 打开章节维护页面
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/chapterEditor", method=RequestMethod.GET)
	public String editChapterGet(String courseId) {
		try {
			
			// 课程详细
			CourseDao courseDao = new CourseDao();
			CourseDetailView course = courseDao.selectDetailById(courseId);
			
			if(!course.getOwnerId().equals(this.currentUser().getId())) {
				this.redirect("/study/course");
			}
			
			// 课程章节
			ChapterDao chapterDao = new ChapterDao();
			List<ChapterAndResourceEntry> resourceList = chapterDao.selectByCourseId(courseId);
			
			Map<String, ChapterDetailView> chapterMap = new LinkedHashMap<String, ChapterDetailView>();
			for(ChapterAndResourceEntry cr : resourceList) {
				ChapterDetailView cdv = chapterMap.get(cr.getId());
				if(cdv == null) {
					cdv = new ChapterDetailView();
					cdv.setId(cr.getId());
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
			
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("course", course);
			request.setAttribute("fn", "modify");
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/teacher/chapterList";
	}
	
	/**
	 * 异步删除章节，判断是否有与资源的关联记录，否则不做删除操作
	 * @param chapterId
	 * @return
	 */
	@RequestMapping(value="/removeChapter", method=RequestMethod.POST)
	@ResponseBody
	public String removeChapterByAsync(String chapterId) {
		try {
			if (MyUtils.isNull(chapterId)) {
				return "请选择一个章节！";
			}
			
			ChapterDao chapterDao = new ChapterDao();
			if (chapterDao.existsResourceByChapterId(chapterId)) {
				return "该大章节中存在小章节，不允许删除！";
			}
			if (!chapterDao.deleteById(chapterId)) {
				return "删除失败，请刷新页面后重新操作！";
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 异步修改章节标题
	 * @param chapterId
	 * @return
	 */
	@RequestMapping(value="/modifyChapter", method=RequestMethod.POST)
	@ResponseBody
	public String modifyChapterByAsync(ChapterItem chapterItem) {
		try {
			if (MyUtils.isNull(chapterItem.getRemark())) {
				return "章节标题不能为空！";
			}
			if (MyUtils.isNull(chapterItem.getId())) {
				return "修改失败，请刷新页面后重新操作！";
			}
			
			ChapterDao chapterDao = new ChapterDao();
			chapterItem.setUts(new Date());
			if (!chapterDao.update(chapterItem)) {
				return "修改失败，请刷新页面后重新操作！";
			}
			 
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 异步添加一个课程的章节
	 * @param chapterItem
	 * @return
	 */
	@RequestMapping(value="/addChapter", method=RequestMethod.POST)
	@ResponseBody
	public String addChapterByAsync(ChapterItem chapterItem) {
		try {
			if (MyUtils.isNotNull(chapterItem.getRemark()) && MyUtils.isNotNull(chapterItem.getCourseId())) {
				ChapterDao chapterDao = new ChapterDao();
				chapterDao.selectMaxPriority(chapterItem.getCourseId());
				chapterItem.setId(MyUtils.getGUID());
				if (chapterDao.selectMaxPriority(chapterItem.getCourseId()) != 0) {
					chapterItem.setPriority(chapterDao.selectMaxPriority(chapterItem.getCourseId()) + 1);
				}
				chapterItem.setCts(new Date());
				chapterItem.setUts(new Date());
				if (chapterDao.insert(chapterItem)) {
					Gson gson = new Gson();
					return gson.toJson(chapterItem);
				}
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
	
	
	@RequestMapping("/changeCover")
	public String changeCover(){
		return "/manage/teacher/changeCover";
	}
}
