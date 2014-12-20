package com.santrong.plt;

import com.santrong.plt.system.Global;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.entry.ChinaBankPayGateParams;






/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午5:28:37
 */
public class Test {
	
	public static void main(String[] args) {
		ChinaBankPayGateParams chinaBank = new ChinaBankPayGateParams();
		chinaBank.setV_mid("23142561");
		chinaBank.setV_oid("7a9ffcce6b1e4e17ad8ff0a8777813cb");// 订单号
		chinaBank.setV_amount("0.01");// 订单实际支付金额
		chinaBank.setV_moneytype("CNY");// 币种
		chinaBank.setV_url("http://plttest.santrong.com/pay/chinabank/receive");// 跳转地址
		chinaBank.setV_md5info(chinaBank.calMd5("weinianjieblacksheepwall"));
		
		System.out.print(chinaBank.getV_md5info());
	}
}
