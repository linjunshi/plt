package com.santrong.plt.fn;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.stream.JsonReader;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.HttpUtils;
import com.santrong.plt.util.MyUtils;

/**
 * @author weinianjie
 * @date 2014年11月10日
 * @time 下午5:38:18
 * @description 爬取微课网的学校数据
 * http://www.vko.com
 * 切入点在个人信息填写时候能填学校
 * urlDemo：http://www.vko.cn/schoolp.json?orgNo=450124&callback=jQuery18301347518153488636_1415611163988&_=1415612445066"
*	第一参数为区域编码，第2参数为jsonp的回调函数，第3参数为缓存清理
*  建表请参考spider.sql
 */

public class SchoolSpiderVKO {
	public static final String baseUrl = "http://www.vko.cn/schoolp.json";
	public static final int threadCount = 5;
	
	static List<String> codeList = null;
	
	static int count = 0;
	
	public static void main(String[] args) {
			try{
				Log.debug("--------------spider begin-----------");
				
				final long begin = System.currentTimeMillis();
				
				Connection con = null;
				try{
					con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/plt" , "root" , "linux" ) ;
					
					// 学校3000多个，一口气搞出来没问题
					codeList = new LinkedList<String>();
					String sql = "select areaCode from web_area";
					Statement stm = con.createStatement() ;
					ResultSet rs = stm.executeQuery(sql);
					while(rs.next()){
						codeList.add(rs.getString("areaCode"));
					}
				}catch(Exception e) {
					Log.printStackTrace(e);
				}finally {
					if(con != null && !con.isClosed()) {
						con.close();
					}
				}
				
				Log.debug("获取区域：" + codeList.size() + "个");
				
				// 开启多线程扫描
				for(int i=0;i<threadCount;i++) {
					new Thread() {
						public void run() {
							String tName = Thread.currentThread().getName();
							Log.debug("thread + " + tName + " start");
							Connection con = null;
							try{
								con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plt" , "root" , "linux" ) ;
								String sql = "insert into spider_school_vko values(?, ?, ?)";
								String code = "";
								while(true) {
									synchronized(codeList) {
										// 线程结束
										if(codeList.size() == 0) {
											Log.debug("thread + " + tName + " quit success");
											Log.debug("thread + " + tName + "use time:" + (System.currentTimeMillis() - begin));
											break;
										}
										
										Log.debug("current[" + tName + "]:" + ++count);
										code = codeList.remove(0);
									}
									
									int r1 = (int)(Math.random()*1000000);
									int r2 = (int)(Math.random()*1000000);
									int r3 = (int)(Math.random()*1000000);
									String param = "orgNo=" + code + "&callback=jQuery" + r1 + "_" + r2 + "&_=" + r3;
									String httpResult = HttpUtils.sendGet(baseUrl, param);
									
									//记录失败
									if(MyUtils.isNull(httpResult)) {
										Log.debug("get-fail:" + count + " and code:" + code);
										continue;
									}else {
										JsonReader reader = null;
										try {
											
											String _str = httpResult.replaceAll("jQuery" + r1 + "_" + r2, "");
											_str = _str.substring(1, _str.length() - 1);
											reader = new JsonReader(new StringReader(_str));
											reader.beginObject();
											while(reader.hasNext()) {
												String a = reader.nextName();
												String b = reader.nextString();
//												Log.debug("a:" + a + "|b:" + b);
												PreparedStatement stm = con.prepareStatement(sql);
												stm.setString(1, a);
												stm.setString(2, code);
												stm.setString(3, b.trim());
												stm.execute();
											}
											
										}catch(Exception e) {
											Log.debug("parse-fail:" + count + " and code:" + code);
											Log.printStackTrace(e);
										} finally {
											try {
												if(reader != null) {
													reader.close();
												}
											} catch (IOException e) {
												Log.printStackTrace(e);
											}
								        }
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
