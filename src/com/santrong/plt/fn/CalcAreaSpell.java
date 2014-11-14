package com.santrong.plt.fn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.santrong.plt.fn.utils.CnToSpell;
import com.santrong.plt.log.Log;

/**
 * @author weinianjie
 * @date 2014年11月14日
 * @time 下午2:27:20
 * @description 计算区域的拼音
 */
public class CalcAreaSpell {
	
	public static final int threadCount = 3;
	
	static List<Entry<String, String>> areaList = new LinkedList<Entry<String, String>>();
	
	static int count = 0;	
	
	public static void main(String[] args) {
		
		try{
			Log.debug("--------------program begin-----------");
			
//			codeList.add("110100");// 北京
//			codeList.add("120100");// 天津
//			codeList.add("310100");// 上海
//			codeList.add("500100");// 重庆
			
			final long begin = System.currentTimeMillis();
			
			Connection con = null;
			try{
				con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/plt" , "root" , "linux" ) ;
				
				// 市级区域编码337+4个，一口气搞出来没问题
				String sql = "select areaCode,areaName from web_area where areaCode like '%00' and areaCode not like '%0000'";
				Statement stm = con.createStatement() ;
				ResultSet rs = stm.executeQuery(sql);
				while(rs.next()){
					Entry<String, String> area = new AbstractMap.SimpleEntry<String, String>(rs.getString("areaCode"), rs.getString("areaName"));
					areaList.add(area);
				}
			}catch(Exception e) {
				Log.printStackTrace(e);
			}finally {
				if(con != null && !con.isClosed()) {
					con.close();
				}
			}
			
			Log.debug("获取城市：" + areaList.size() + "个");
			
			// 开启多线程扫描
			for(int i=0;i<threadCount;i++) {
				new Thread() {
					public void run() {
						String tName = Thread.currentThread().getName();
						Log.debug("thread + " + tName + " start");
						Connection con = null;
						try{
							con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plt" , "root" , "linux" ) ;
							String sql = "update web_area set areaEName=? where areaCode=?";
							Entry<String, String> area = null;
							while(true) {
								synchronized(areaList) {
									// 线程结束
									if(areaList.size() == 0) {
										Log.debug("thread + " + tName + " quit success");
										Log.debug("thread + " + tName + "use time:" + (System.currentTimeMillis() - begin));
										break;
									}
									
									Log.debug("current[" + tName + "]:" + ++count);
									area = areaList.remove(0);
								}
								
								try {
									String areaEName = CnToSpell.getFullSpell(area.getValue());
									String last4 = areaEName.substring(areaEName.length() - 4, areaEName.length());
									if(last4.equals("shi ")) {
										areaEName = areaEName.substring(0, areaEName.length() - 5);
									}
									areaEName = areaEName.replaceAll(" ", "");
									PreparedStatement stm = con.prepareStatement(sql);
									stm.setString(1, areaEName);
									stm.setString(2, area.getKey());
									stm.execute();
//													Log.debug(areaEName);
								}catch(Exception e) {
									Log.debug("parse-fail:" + count + " and code:" + area.getKey());
									Log.printStackTrace(e);
								}
							}
						}catch(Exception e) {
							Log.printStackTrace(e);
						}finally{
							try {
								if(con != null && !con.isClosed()) {
									con.close();
								}
							} catch (SQLException e) {
								Log.printStackTrace(e);
							}
						}
					}
				}.start();
			}
			
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
	}
}
