package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseBuyQuery;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.OrderItem;
import com.santrong.plt.webpage.course.resource.train.dao.TrainDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuery;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.manage.student.entry.TrainSimpleView;

/**
 * @author weinianjie
 * @date 2014年10月14日
 * @time 上午10:34:09
 */
@Controller
@RequestMapping("/manage/competition")
public class CompetitionMAction extends StudentBaseAction {

	/**
	 * 我的练习
	 * @return
	 */
	@RequestMapping("")
	public String home() {
		return "manage/student/competition";
	}
	
}
