package com.santrong.plt.webpage.manage.student;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.home.dao.SubjectDao;
import com.santrong.plt.webpage.home.entry.SubjectItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.manage.student.entry.TeacherApplyForm;
import com.santrong.plt.webpage.teacher.dao.UserDao;
import com.santrong.plt.webpage.teacher.entry.UserItem;

/**
 * @author weinianjie
 * @date 2014年10月18日
 * @time 上午11:19:35
 */
@Controller
@RequestMapping("/apply")
public class ApplyTeacherMAction extends StudentBaseAction {
	
	/**
	 * 申请成为老师GET
	 * @return
	 */
	@RequestMapping(value="/teacher", method=RequestMethod.GET)
	public String teacherGet(){
		SubjectDao subjectDao = new SubjectDao();
		List<SubjectItem> subjectList = subjectDao.selectAll();
		this.getRequest().setAttribute("subjectList", subjectList);
		return "manage/student/apply";
	}
	
	/**
	 * 申请成为老师POST
	 * @return
	 */
	@RequestMapping(value="/teacher", method=RequestMethod.POST)
	public String teacherPost(TeacherApplyForm form){
		UserDao dao = new UserDao();
		UserItem user = dao.selectById(form.getUserId());
		user.setSchoolId(form.getSchoolId());
		user.setSubjectId(form.getSubjectId());
		user.setPhone(form.getPhone());
		// TODO 身份证
		user.setRemark(form.getRemark());
		user.setRole(user.getRole()|UserItem.Role_Teacher);// 加上老师权限
		user.setUts(new Date());
		
		if(dao.update(user) <= 0) {
			this.addError("申请失败");
			SubjectDao subjectDao = new SubjectDao();
			List<SubjectItem> subjectList = subjectDao.selectAll();
			this.getRequest().setAttribute("subjectList", subjectList);			
			this.getRequest().setAttribute("form", form);
			return "manage/student/apply";
		}
		
		this.getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
		
		return "manage/student/applyPass";
	}		

}
