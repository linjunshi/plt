package com.santrong.plt.webpage.manage.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.course.dao.OrderDao;
import com.santrong.plt.webpage.course.entry.CourseBuyQuery;
import com.santrong.plt.webpage.course.entry.OrderCourseView;
import com.santrong.plt.webpage.course.entry.OrderItem;
import com.santrong.plt.webpage.manage.StudentBaseAction;
import com.santrong.plt.webpage.teacher.entry.UserItem;

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
		try {
			// 获取当前用户对象信息
			UserItem user = this.currentUser();
			if (user == null) {
				// 没登陆
				return this.redirect("/account/login");
			}
			
			HttpServletRequest request = getRequest();
			int pageNum = this.getIntParameter("page");
			Integer status = this.getIntParameter("status");
			if(pageNum == 0) {
				pageNum = 1;
			}
			if (status == null) {
				status = OrderItem.Status_Pay;// 已经购买切付款的
			}
			//下面的替换成订单查询
			OrderDao orderDao = new OrderDao();
			CourseBuyQuery query = new CourseBuyQuery();
			query.setUserId(this.currentUser().getId());
//			query.setOrderStatus(status);
			query.setPageSize(12);
			query.setPageNum(pageNum);
			query.setCount(orderDao.selectCountByQuery(query));
			query.setOrderBy("cts");
			List<OrderCourseView> orderList = orderDao.selectByQuery(query);
			
			request.setAttribute("orderList", orderList);
			request.setAttribute("query", query);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "manage/student/orderList";
	}
}
