package com.santrong.plt.webpage.course;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.opt.ParamHelper;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.opt.area.AreaEntry;
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeLevelEntry;
import com.santrong.plt.system.Global;
import com.santrong.plt.util.ClientUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.TreeCode;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.CommentDao;
import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.dao.WeikeDao;
import com.santrong.plt.webpage.course.entry.CommentItem;
import com.santrong.plt.webpage.course.entry.CommentUserView;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.WeikeDetailView;
import com.santrong.plt.webpage.course.entry.WeikeQuery;
import com.santrong.plt.webpage.home.dao.LessonUnitDao;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.LessonUnitEntry;
import com.santrong.plt.webpage.home.entry.SubjectItem;
import com.santrong.plt.webpage.teacher.entry.UserItem;

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
		List<SubjectItem> subjectList = subjectDao.selectByGradeEnName(TreeCode.gradeEnNames[0]);
		
		// 条件-年级
		List<GradeLevelEntry> levelList = GradeDefine.getByGradeEnName(TreeCode.gradeEnNames[0]).getGradeLevelList();
		
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
		weikeQuery.setGradeEnName(TreeCode.gradeEnNames[0]);
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
		
		HttpServletRequest request = getRequest();
		
		String type = "unitExams";//单元测试，personExams 个人练习
		
		// 课程详细
		WeikeDao weikeDao = new WeikeDao();
		WeikeDetailView weike = weikeDao.selectByCourseId(id);

		if(weike ==  null) {
			return "404";
		}
		
		if (MyUtils.isNotNull(weike.getUnitId())) {
			// 所属年级、学科、单元
			LessonUnitDao luDao = new LessonUnitDao();
			LessonUnitEntry luEntry = new LessonUnitEntry();
			luEntry = luDao.selectGSUById(weike.getUnitId());
			request.setAttribute("luEntry", luEntry);
			
			// 其他相关的微课
			List<CourseItem> weikeList = weikeDao.selectWeikeByUnitId(weike.getUnitId());//获取同一单元的微课
			if (weikeList == null || weikeList.size() == 0) {
				weikeList = weikeDao.selectWeikeByGIdAndSId(weike.getGradeId(), weike.getSubjectId());//获取同年级和同学科的微课
			}
			request.setAttribute("weikeList", weikeList);
		}
		
		// 课程评价
		CommentDao commentDao = new CommentDao();
		List<CommentUserView> commentList = commentDao.selectByCourseId(id);
		weike.setCommentList(commentList);
		
		request.setAttribute("type", type);
		request.setAttribute("weike", weike);
		request.setAttribute("isMobile", ClientUtils.isMobile(request));
		
		return "weike/detail";
	}
	
	/**
	 * 课程详细页面-课程评价回复功能
	 * @author huangweihua
	 * @param courseId, remark
	 * @return
	 */
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	public String postNewReply(String weikeId ,String remark) {
		
		if(StringUtils.isNullOrEmpty(weikeId) || StringUtils.isNullOrEmpty(remark) ) {
			return this.redirect("/weike/" + weikeId + ".html#comment");
		}
		/*课程评论页面，判断用户是否登录改成使用BaseAction中的currentUser方法，拿到user后判断user是否为空来判断用户登录状态*/
		// 获取当前用户对象信息
		UserItem user = this.currentUser();
		if (user == null) {
			// 没登陆
			return this.redirect("/account/login");
		}
		
		//打开事务
		ThreadUtils.beginTranx();
		
		// 往课程评论表插入一条记录
		CommentDao commentDao = new CommentDao();
		CommentItem commentItem = new CommentItem();
		commentItem.setId(MyUtils.getGUID());
		commentItem.setUserId(user.getId());
		commentItem.setCourseId(weikeId);
		commentItem.setRemark(remark);
		commentItem.setCts(new Date());
		commentItem.setUts(new Date());
		commentDao.insert(commentItem);
		
		// 往课程评论表插入一条记录成功后，修改课程主表course中的课程评论数量commentCount，自动加1
		CourseDao courseDao = new CourseDao();
		courseDao.addComment(weikeId);
		
		//关闭事务
		ThreadUtils.commitTranx();
		
		return this.redirect("/weike/" + weikeId + ".html#comment");
	}
}
