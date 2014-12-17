package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.webpage.course.dao.CourseDao;
import com.santrong.plt.webpage.course.entry.CourseBuyQuery;
import com.santrong.plt.webpage.course.entry.CourseItem;
import com.santrong.plt.webpage.course.entry.OrderItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;

/**
 * @author weinianjie
 * @date 2014年12月17日
 * @time 下午5:55:42
 */
@Controller
@RequestMapping("/order")
public class OrderMAction extends StudentBaseAction {

	/**
	 * 交易记录
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		
		HttpServletRequest request = getRequest();
		int pageNum = this.getIntParameter("page");
		if(pageNum == 0) {
			pageNum = 1;
		}
		
		
		//下面的替换成订单查询
//		CourseDao courseDao = new CourseDao();
//		CourseBuyQuery query = new CourseBuyQuery();
//		query.setUserId(this.currentUser().getId());
//		query.setOrderStatus(OrderItem.Status_Pay);// 已经购买切付款的
//		query.setPageSize(12);
//		query.setPageNum(pageNum);
//		query.setCount(courseDao.selectCountByQuery(query));
//		List<CourseItem> courseList = courseDao.selectByQuery(query);
//		
//		request.setAttribute("orderList", orderList);
//		request.setAttribute("query", query);
		
		return "manage/student/orderList";
	}
}
