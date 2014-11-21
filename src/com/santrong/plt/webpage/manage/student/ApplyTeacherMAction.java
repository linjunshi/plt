package com.santrong.plt.webpage.manage.student;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.system.Global;
import com.santrong.plt.webpage.manage.StudentBaseAction;
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
		return "manage/student/apply";
	}
	
	/**
	 * 申请成为老师POST
	 * @return
	 */
	@RequestMapping(value="/teacher", method=RequestMethod.POST)
	public String teacherPost(UserItem teacher){
		UserDao dao = new UserDao();
		UserItem user = dao.selectById(teacher.getId());
		user.setSchoolId(teacher.getSchoolId());
		user.setSubjectId(teacher.getSubjectId());
		user.setRemark(teacher.getRemark());
		user.setRole(user.getRole()|UserItem.Role_Teacher);// 加上老师权限
		user.setUts(new Date());
		
		if(dao.update(user) <= 0) {
			this.addError("申请失败");
			this.getRequest().setAttribute("teacher", teacher);
			return "manage/student/apply";
		}
		
		this.getRequest().getSession().setAttribute(Global.SessionKey_LoginUser, user);
		
		return "manage/student/applyPass";
	}	

}
