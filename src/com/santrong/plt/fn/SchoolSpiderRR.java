package com.santrong.plt.fn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Element;

import com.santrong.plt.log.Log;
import com.santrong.plt.util.HttpUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.util.XmlReader;

/**
 * @author weinianjie
 * @date 2014年11月11日
 * @time 下午3:48:14
 * @description 爬取人人网的初中和高中学校数据
 * http://www.renren.com
 * 切入点在找人页面
 * urlDemo：http://support.renren.com/highschool/6201.html和http://support.renren.com/juniorschool/6201.html
*  建表请参考spider.sql * 
 */
public class SchoolSpiderRR {
	
	public static final String baseUrl = "http://support.renren.com/";
	public static final int threadCount = 3;
	
	static List<String> codeList = new LinkedList<String>();
	
	static int count = 0;	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		try{
			Log.debug("--------------spider begin-----------");
			
			codeList.add("110100");// 北京
			codeList.add("120100");// 天津
			codeList.add("310100");// 上海
			codeList.add("500100");// 重庆
//			codeList.add("522200");// 老版贵州铜仁
//			codeList.add("522400");// 老版贵州毕节
			
			final long begin = System.currentTimeMillis();
			
			Connection con = null;
			try{
				con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/plt" , "root" , "linux" ) ;
				
				// 市级区域编码337+4个，一口气搞出来没问题
				String sql = "select areaCode from web_area where areaCode like '%00' and areaCode not like '%0000' and (areaCode+0) < 700000";
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
							String sql = "insert into spider_school_rr values(?, ?, ?, ?)";
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
								
								String juniorschool = HttpUtils.sendGet(baseUrl + "juniorschool/" + code.substring(0, 4) + ".html", null);
								String highschool = HttpUtils.sendGet(baseUrl + "highschool/" + code.substring(0, 4) + ".html", null);
								String[] schoolArray = {juniorschool, highschool};
								
								//记录失败
								if(MyUtils.isNull(juniorschool) || MyUtils.isNull(highschool)) {
									Log.debug("get-fail:" + count + " and code:" + code);
									continue;
								}else {
									try {
										
										XmlReader xml = new XmlReader();
										for(int i=0;i<schoolArray.length;i++) {
											
											xml.parse("<?xml version=\"1.0\" encoding=\"UTF-8\"?><school>" + schoolArray[i] + "</school>");
											List<Element> ulList = xml.finds("/ul");
											for(int j=1;j<ulList.size();j++) {
												
												String _code = ulList.get(j).getAttributeValue("id").substring(8);
												List<Element> liList = ulList.get(j).getChildren();
												for(Element li:liList) {
													if(li.getName().equals("li")) {// 有可能是<center>暂时没有该区域的学校信息</center>
														Element el = li.getChild("a");
														String itCode = el.getAttributeValue("href");
														PreparedStatement stm = con.prepareStatement(sql);
														stm.setString(1, itCode);
														stm.setString(2, _code);
														stm.setString(3, el.getText());
														stm.setInt(4, i+1);
														stm.execute();
	//													Log.debug(el.getText());
													}
												}
											}											
										}
									}catch(Exception e) {
										Log.debug("parse-fail:" + count + " and code:" + code);
										Log.printStackTrace(e);
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
//最终报错如下
//6590---自治区直辖---新疆维吾尔自治区
//5205---毕节市---贵州---老版5224
//5206---铜仁市---贵州---老版5222
//4603---三沙市---海南
//4690---省直辖---海南
//4290---省直辖---湖北
//4190---省直辖---河南
