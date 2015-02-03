package com.santrong.plt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.santrong.plt.log.Log;







/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午5:28:37
 */
public class Test {
	
	
	public static void main(String[] args) {
		Connection  conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/plt", "root", "linux");
			PreparedStatement st = conn.prepareStatement("select * from user limit 1");
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int a = rs.findColumn("id");
				Log.debug(a);
			}
		} catch (Exception e) {
			try{
				if(conn != null && !conn.isClosed()){
					conn.close();
				}
			}catch(Exception ex) {
				
			}
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//	ChinaBankPayGateParams chinaBank = new ChinaBankPayGateParams();
//	chinaBank.setV_mid("23142561");
//	chinaBank.setV_oid("7a9ffcce6b1e4e17ad8ff0a8777813cb");// 订单号
//	chinaBank.setV_amount("0.01");// 订单实际支付金额
//	chinaBank.setV_moneytype("CNY");// 币种
//	chinaBank.setV_url("http://plttest.santrong.com/pay/chinabank/receive");// 跳转地址
//	chinaBank.setV_md5info(chinaBank.calMd5("weinianjieblacksheepwall"));
//	
//	System.out.print(chinaBank.getV_md5info());
//}
}
