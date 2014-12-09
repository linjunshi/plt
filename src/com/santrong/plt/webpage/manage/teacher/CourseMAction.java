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
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.dao.ChapterDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.ChapterAndResourceEntry;
import com.santrong.plt.webpage.course.entry.ChapterDetailView;
import com.santrong.plt.webpage.course.entry.ChapterItem;
import com.santrong.plt.webpage.course.entry.ChapterToResourceItem;
import com.santrong.plt.webpage.course.entry.CourseDetailView;
import com.santrong.plt.webpage.course.entry.CourseForm;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.CourseQuery;
import com.santrong.plt.webpage.course.entry.ResourceEntry;
import com.santrong.plt.webpage.course.entry.ResourceType;
import com.santrong.plt.webpage.course.resource.file.dao.FileDao;
import com.santrong.plt.webpage.course.resource.file.entry.FileItem;
import com.santrong.plt.webpage.course.resource.file.entry.FileQuery;
import com.santrong.plt.webpage.course.resource.live.dao.LiveDao;
import com.santrong.plt.webpage.course.resource.live.entry.LiveForm;
import com.santrong.plt.webpage.course.resource.live.entry.LiveItem;
import com.santrong.plt.webpage.course.resource.train.dao.TrainDao;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionQuery;
import com.santrong.plt.webpage.course.resource.train.entry.TrainToQuestionItem;
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
	
	/**
	 * 新增课程获取
	 * @return
	 */
	@RequestMapping("/add")
	public String addCourse(){
		HttpServletRequest request = this.getRequest();
		request.setAttribute("fn", "add");	
		return "/manage/teacher/courseAdd";
	}
	
	/**
	 * 新增课程提交
	 * @param courseForm
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCoursePost(CourseForm courseForm){
		try {
			UserItem user = this.currentUser();
			if (user == null) {
				return this.redirect("account/login");
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
					courseForm.setCourseName(courseForm.getCourseName().trim());
					BeanUtils.copyProperties(courseForm, courseItem);
					
					courseItem.setId(MyUtils.getGUID());
					courseItem.setOwnerId(user.getId());
					courseItem.setEndTime(MyUtils.stringToDate(courseForm.getEndTime(), "yyyy-MM-dd"));
					courseItem.setChapterCount(courseForm.getChapterCount() > 0 ? courseForm.getChapterCount() : 0);
					courseItem.setSaleCount(0);
					courseItem.setCollectCount(0);
					courseItem.setCommentCount(0);
//					courseItem.setStatus(0);//-1:删除，0:未发布，1:发布
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
					courseForm.setCourseName(courseForm.getCourseName().trim());
					BeanUtils.copyProperties(courseForm, courseItem);
					
					courseItem.setEndTime(MyUtils.stringToDate(courseForm.getEndTime(), "yyyy-MM-dd"));
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
	 * 发布课程
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value="/publish", method=RequestMethod.POST)
	@ResponseBody
	public String publish(String courseId) {
		try{
			CourseDao dao = new CourseDao();
			if(dao.publishCourse(courseId) > 0) {
				return SUCCESS;
			}
			
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
	
	/**
	 * 删除一条课程记录（伪删除）
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
			if (MyUtils.isNull(chapterItem.getRemark().trim())) {
				return "章节标题不能为空！";
			}
			if (MyUtils.isNull(chapterItem.getId())) {
				return "修改失败，请刷新页面后重新操作！";
			}
			
			ChapterDao chapterDao = new ChapterDao();
			chapterItem.setRemark(chapterItem.getRemark().trim());
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
				int count = 0;
				count = chapterDao.selectMaxPriority(chapterItem.getCourseId());
				chapterItem.setId(MyUtils.getGUID());
				if (count != 0) {
					chapterItem.setPriority(count + 1);
				} else {
					chapterItem.setPriority(1);
				}
				chapterItem.setRemark(chapterItem.getRemark().trim());
				chapterItem.setCts(new Date());
				chapterItem.setUts(new Date());
				if (chapterDao.insert(chapterItem)) {
					// JavaBean转换成Json字符串
					Gson gson = new Gson();
					return gson.toJson(chapterItem);
				}
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
	
	/**
	 * 点击 “+课件”，打开选择课件的页面
	 * @param keyword
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value="/addResourceFile",method = RequestMethod.GET)
	public String selectFileList(){
		try {
			HttpServletRequest request = getRequest();
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			FileDao fileDao = new FileDao();
			FileQuery query = new FileQuery();
			query.setOnwerId(this.currentUser().getId());
			query.setPageSize(6);//6条记录一页
			query.setPageNum(pageNum);
			query.setOrderBy("cts");
			query.setOrderRule("desc");
			query.setCount(fileDao.selectCountByQuery(query));
			List<FileItem> fileList = fileDao.selectByQuery(query);
			
			String courseId = request.getParameter("courseId");
			String chapterId = request.getParameter("chapterId");
			String oldResourceId = request.getParameter("oldResourceId");
			
			request.setAttribute("query", query);
			request.setAttribute("fileList", fileList);
			request.setAttribute("courseId", courseId);
			request.setAttribute("chapterId", chapterId);
			request.setAttribute("oldResourceId", oldResourceId);
			request.setAttribute("resourceType", ResourceType.Type_File);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/teacher/addResourceFile";
	}
	
	/**
	 * 点击选择，把课件绑定到当前指定的章节里去
	 * @ResponseBody 异步请求
	 * @author huangweihua
	 * @param courseId
	 * @param chapterId
	 * @param resourceId
	 * @return String
	 */
	@RequestMapping(value="/selectFile",method = RequestMethod.GET)
	@ResponseBody
	public String selectFileByAsync() {
		HttpServletRequest request = getRequest();
		String courseId = request.getParameter("courseId");
		String chapterId = request.getParameter("chapterId");
		String resourceId = request.getParameter("resourceId");
		String oldResourceId = request.getParameter("oldResourceId");
		try {
			if (MyUtils.isNotNull(courseId) && MyUtils.isNotNull(chapterId) && MyUtils.isNotNull(resourceId)) {
				FileDao fileDao = new FileDao();
				FileItem fileItem = fileDao.selectById(resourceId);
				
				// 检验当前用户权限
				if(!fileItem.getOwnerId().equals(this.currentUser().getId())) {
					return this.redirect("/study/course");
				}
				
				if (fileItem != null) {
					ChapterDao chapterDao = new ChapterDao();
					// operation="modify" 执行修改操作；否则，执行新增操作
					if (MyUtils.isNotNull(oldResourceId)) {
						chapterDao.removeChapterAndResource(chapterId, oldResourceId);
					}
					
					if (!chapterDao.existsChapterAndResource(chapterId, resourceId)) {
						ChapterToResourceItem ctrItem = new ChapterToResourceItem();
						ctrItem.setId(MyUtils.getGUID());
						ctrItem.setResourceId(fileItem.getId());
						ctrItem.setChapterId(chapterId);
						ctrItem.setResourceType(ResourceType.Type_File);
						ctrItem.setTitle(fileItem.getTitle());
						ctrItem.setPriority(ResourceType.Type_File);
						if (chapterDao.insertChapterToResource(ctrItem)) {
							return "/manage/course/chapterEditor?courseId=" + courseId;
						}
					} else {
						return ERROR_PARAM;
					}
					
				}
			} else {
				return FAIL;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
	
	/**
	 * 打开新增、修改直播的页面
	 * @return
	 */
	@RequestMapping(value="/addResourceLive", method=RequestMethod.GET)
	public String addResourceLive() {
		try {

			HttpServletRequest request = getRequest();
			String courseId = request.getParameter("courseId");
			String chapterId = request.getParameter("chapterId");
			String resourceId = request.getParameter("resourceId");
			
			if (MyUtils.isNotNull(resourceId)) {
				LiveDao liveDao = new LiveDao();
				LiveItem live = liveDao.selectById(resourceId);
				request.setAttribute("live", live);
				request.setAttribute("fn", "modify");
			} else {
				request.setAttribute("fn", "add");
			}
			
			request.setAttribute("courseId", courseId);
			request.setAttribute("chapterId", chapterId);
			request.setAttribute("resourceId", resourceId);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/teacher/addResourceLive";
	}
	/**
	 * 新增、修改直播，表单处理
	 * @param liveForm
	 * @return
	 */
	@RequestMapping(value="/addResourceLive",method = RequestMethod.POST)
	public String addResourceLiveByPost(LiveForm liveForm) {
		HttpServletRequest request = getRequest();
		String courseId = request.getParameter("courseId");
		String chapterId = request.getParameter("chapterId");
		String resourceId = request.getParameter("resourceId");
		try {

			if (liveForm != null) {

				if (MyUtils.isNull(liveForm.getTitle().trim())) {
					addError("请您填写直播名称！");
				}

				if (MyUtils.isNotNull(courseId) && MyUtils.isNotNull(chapterId)) {
					LiveDao liveDao = new LiveDao();
					LiveItem liveItem = new LiveItem();
					if (MyUtils.isNotNull(resourceId)) {
						liveItem = liveDao.selectById(resourceId);
						if (liveItem != null) {
							ThreadUtils.beginTranx();
							// 检验当前用户权限
							if (!liveItem.getOwnerId().equals(this.currentUser().getId())) {
								return this.redirect("/study/course");
							}
							
							liveItem.setTitle(liveForm.getTitle().trim());
							liveItem.setDuration(liveForm.getDuration());
							liveItem.setBeginTime(MyUtils.stringToDate(
									liveForm.getBeginTime(), "yyyy-MM-dd HH:mm"));
							liveItem.setEndTime(MyUtils.stringToDate(
									liveForm.getEndTime(), "yyyy-MM-dd HH:mm"));
							liveItem.setUrl(liveForm.getUrl());
							liveItem.setUts(new Date());
							liveDao.update(liveItem);
							
							ChapterDao chapterDao = new ChapterDao();
							ChapterToResourceItem ctrItem = chapterDao.selectChapterAndResource(chapterId, resourceId);
							ctrItem.setTitle(liveItem.getTitle());
							chapterDao.updateChapterToResource(ctrItem);
							
							ThreadUtils.commitTranx();
						}
					} else {
						ThreadUtils.beginTranx();
						
						liveItem.setId(MyUtils.getGUID());
						liveItem.setTitle(liveForm.getTitle().trim());
						liveItem.setDuration(liveForm.getDuration());
						liveItem.setBeginTime(MyUtils.stringToDate(
								liveForm.getBeginTime(), "yyyy-MM-dd HH:mm"));
						liveItem.setEndTime(MyUtils.stringToDate(
								liveForm.getEndTime(), "yyyy-MM-dd HH:mm"));
						liveItem.setOwnerId(currentUser().getId());
						liveItem.setUrl(liveForm.getUrl());
						liveItem.setCts(new Date());
						liveItem.setUts(new Date());
						liveDao.insert(liveItem);
						
						ChapterDao chapterDao = new ChapterDao();
						ChapterToResourceItem ctrItem = new ChapterToResourceItem();
						ctrItem.setId(MyUtils.getGUID());
						ctrItem.setChapterId(chapterId);
						ctrItem.setResourceId(liveItem.getId());
						ctrItem.setTitle(liveItem.getTitle().trim());
						ctrItem.setResourceType(ResourceType.Type_Live);
						ctrItem.setPriority(ResourceType.Type_Live);
						chapterDao.insertChapterToResource(ctrItem);
						
						ThreadUtils.commitTranx();
					}

				}
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/manage/course/chapterEditor?courseId=" + courseId);
	}
	
	/**
	 * 打开维护试题界面
	 * @return
	 */
	@RequestMapping(value="/addResourceTrain", method=RequestMethod.GET)
	public String addResourceTrain() {
		try {

			HttpServletRequest request = getRequest();
			String courseId = request.getParameter("courseId");
			String chapterId = request.getParameter("chapterId");
			String resourceId = request.getParameter("resourceId");
			
			int pageNum = this.getIntParameter("page");
			
			if(pageNum == 0) {
				// 点击修改的时候，才执行修改操作的时候所要做的事情
				if (MyUtils.isNotNull(resourceId)) {
					TrainDao trainDao = new TrainDao();
					TrainItem train = trainDao.selectById(resourceId);
					request.setAttribute("train", train);
					
					TrainQuestionDao tqDao = new TrainQuestionDao();
					List<TrainToQuestionItem> t2qList = tqDao.selectTrain2QuestionByTrainId(resourceId);
					String questionIds = "";
					for(TrainToQuestionItem ttqItem : t2qList) {
						questionIds += "," + ttqItem.getQuestionId();
					}
					if (questionIds != "") {
						questionIds = questionIds.substring(1);
					}
					// 第一次加载页面的时候（点击修改的时候），才设置该隐藏值到页面上
					request.setAttribute("questionIds", questionIds);
				}
				pageNum = 1;
			}
			
			TrainQuestionDao tqDao = new TrainQuestionDao();
			TrainQuestionQuery query = new TrainQuestionQuery();
			query.setPageNum(pageNum);
			query.setPageSize(2);//设置每页显示的记录条数
			query.setUserId(currentUser().getId());
			query.setDel(0);
			query.setCount(tqDao.selectCountByQuery(query));
			List<TrainQuestionItem> questionList = tqDao.selectByQuery(query);
			
			request.setAttribute("query", query);
			request.setAttribute("questionList", questionList);
			request.setAttribute("courseId", courseId);
			request.setAttribute("chapterId", chapterId);
			request.setAttribute("resourceId", resourceId);
			request.setAttribute("resourceType", ResourceType.Type_Train);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/teacher/addResourceTrain";
	}
	
	/**
	 * 修改维护试题列表
	 * @return
	 */
	@RequestMapping(value="/selectTrainList", method=RequestMethod.POST)
	@ResponseBody
	public String selectTrainList() {
		HttpServletRequest request = getRequest();
		String title = request.getParameter("title").trim();
		String courseId = request.getParameter("courseId");
		String chapterId = request.getParameter("chapterId");
		String resourceId = request.getParameter("resourceId");
		String ids = request.getParameter("ids");
		String [] stringArr = null;
		try {
			if (MyUtils.isNotNull(ids)) {
				stringArr = ids.split(",");
			}
			if (MyUtils.isNotNull(courseId) && MyUtils.isNotNull(chapterId)) {
				if (MyUtils.isNotNull(resourceId)) {
					//修改
					ThreadUtils.beginTranx();
					TrainDao trainDao = new TrainDao();
					TrainItem trainItem = trainDao.selectById(resourceId);
					trainItem.setTitle(title);
					trainItem.setUts(new Date());
					trainDao.update(trainItem);
					
					// TODO 待完善 修改 题库的时候，题目匹对；目前的思路：先把之前的记录全部删除后，再添加
					if (MyUtils.isNotNull(ids)) {
						TrainQuestionDao tqDao = new TrainQuestionDao();
						tqDao.removeAllQuestion4Train(resourceId);
						int priority = 1;
						for (String questionId : stringArr) {
							tqDao.addQuestion2Train(questionId, trainItem.getId(), priority++);
						}
					}
					
					ChapterDao chapterDao = new ChapterDao();
					ChapterToResourceItem ctrItem = chapterDao.selectChapterAndResource(chapterId, resourceId);
					ctrItem.setTitle(title);
					chapterDao.updateChapterToResource(ctrItem);
					ThreadUtils.commitTranx();
				} else {
					//新增
					ThreadUtils.beginTranx();
					TrainDao trainDao = new TrainDao();
					TrainItem trainItem = new TrainItem();
					trainItem.setId(MyUtils.getGUID());
					trainItem.setOwnerId(this.currentUser().getId());
					trainItem.setTitle(title);
					trainItem.setDel(0);
					trainItem.setCts(new Date());
					trainItem.setUts(new Date());
					trainDao.insert(trainItem);
					
					if (MyUtils.isNotNull(ids)) {
						TrainQuestionDao tqDao = new TrainQuestionDao();
						int priority = 1;
						for (String questionId : stringArr) {
							tqDao.addQuestion2Train(questionId, trainItem.getId(), priority++);
						}
					}
					
					ChapterDao chapterDao = new ChapterDao();
					ChapterToResourceItem ctrItem = new ChapterToResourceItem();
					ctrItem.setId(MyUtils.getGUID());
					ctrItem.setChapterId(chapterId);
					ctrItem.setResourceId(trainItem.getId());
					ctrItem.setTitle(title);
					ctrItem.setResourceType(ResourceType.Type_Train);
					ctrItem.setPriority(ResourceType.Type_Train);
					chapterDao.insertChapterToResource(ctrItem);
					
					ThreadUtils.commitTranx();
				}
			}
			
			
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return SUCCESS;
	}
	/**
	 * 异步从章节中把资源移除掉
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/removeResource", method=RequestMethod.GET)
	@ResponseBody
	public String removeResourceByAsync() {
		HttpServletRequest request = getRequest();
		String chapterId = request.getParameter("chapterId");
		String resourceId = request.getParameter("resourceId");
		String resourceType = request.getParameter("resourceType");
		boolean flag = false;
		
		try {
			if (MyUtils.isNotNull(chapterId) && MyUtils.isNotNull(resourceId) && MyUtils.isNotNull(resourceType)) {
				// 打开事务处理
				ThreadUtils.beginTranx();
				if (MyUtils.stringToInt(resourceType) == ResourceType.Type_File) {
				
				} else if (MyUtils.stringToInt(resourceType) == ResourceType.Type_Live) {
					LiveDao liveDao = new LiveDao();
					liveDao.delete(resourceId);
				} else if (MyUtils.stringToInt(resourceType) == ResourceType.Type_Train) {
					
				}
				
				// 移除章节绑定的资源
				ChapterDao chapterDao = new ChapterDao();
				if(chapterDao.removeChapterAndResource(chapterId, resourceId)) {
					flag = true;
				}
				// 提交事务
				ThreadUtils.commitTranx();
				if (flag == true) {
					return SUCCESS;
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
