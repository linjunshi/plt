package com.santrong.plt.webpage.course;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.dao.OrderDao;
import com.santrong.plt.webpage.course.entry.OrderItem;

/**
 * @author weinianjie
 * @date 2014年12月17日
 * @time 下午5:14:37
 */
@Controller
@RequestMapping("/pay")
public class PayAction extends BaseAction {

	/**
	 * 网银在线支付后响应地址
	 * 
	 * @return
	 */
	@RequestMapping(value = "/chinabank/receive")
	public String recevie() {
		try {
			HttpServletRequest request = this.getRequest();
			String v_oid = request.getParameter("v_oid"); // 订单号
//			String v_pmode = request.getParameter("v_pmode"); // 支付方式中文说明，如"中行长城信用卡"
			String v_pstatus = request.getParameter("v_pstatus"); // 支付结果，20支付完成；30支付失败；
//			String v_pstring = request.getParameter("v_pstring"); // 对支付结果的说明，成功时（v_pstatus=20）为"支付成功"，支付失败时（v_pstatus=30）为"支付失败"
			String v_amount = request.getParameter("v_amount"); // 订单实际支付金额
			String v_moneytype = request.getParameter("v_moneytype"); // 币种
			String v_md5str = request.getParameter("v_md5str"); // MD5校验码
//			String remark1 = request.getParameter("remark1"); // 备注1
//			String remark2 = request.getParameter("remark2"); // 备注2

			// v_oid，v_pstatus，v_amount，v_moneytype，key
			String newMd5 = MyUtils.getMD5(v_oid + v_pstatus + v_amount + v_moneytype + "weinianjieblacksheepwall").toUpperCase();

			// md5校验匹配
			if (newMd5.equals(v_md5str)) {
				if ("20".equals(v_pstatus)) {
					// 更新订单状态
					OrderDao orderDao = new OrderDao();
					OrderItem order = orderDao.selectById(v_oid);
					if (order != null) {
						order.setStatus(OrderItem.Status_Pay);
						order.setUts(new Date());
						if (orderDao.update(order) > 0) {
							Log.info("----支付成功了，订单ID：" + v_oid);
							return this.redirect("/course/"
									+ order.getCourseId() + ".html");
						}
					}
				}
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}

		return "500";
	}
}
