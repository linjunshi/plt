package com.santrong.plt.http.server;

import java.util.Calendar;

import com.santrong.plt.http.HttpDefine;
import com.santrong.plt.http.server.base.AbstractHttpService;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.XmlReader;

/**
 * @author huangweihua
 * @date 2014年11月4日 
 * @time 上午11:10:41
 */
public class TeacherHttpService20005 implements AbstractHttpService{

	@Override
	public String excute(XmlReader xml) {
		int rt = 0;
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		int seconds = 0;
		try{
			Calendar calendar = Calendar.getInstance();
			if (calendar != null) {
				year = calendar.get(Calendar.YEAR);//年
				month = calendar.get(Calendar.MONTH) + 1;//月
				day = calendar.get(Calendar.DAY_OF_MONTH);//日
				hour = calendar.get(Calendar.HOUR_OF_DAY);//时
				minute = calendar.get(Calendar.MINUTE);//分
				seconds = calendar.get(Calendar.SECOND);//秒
				rt = 1;
			}
			/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("time now:" + df.format(calendar.getTime()));
			System.out.println("YEAR:" + year);
			System.out.println("month:" + month);
			System.out.println("day:" + day);
			System.out.println("hour:" + hour);
			System.out.println("minute:" + minute);
			System.out.println("seconds:" + seconds);*/
		}catch(Exception e) {
			Log.printStackTrace(e);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(HttpDefine.Xml_Header);
		sb.append("<ResMsg>");
			sb.append("<MsgHead>");
				//sb.append("<!--获取当前服务器时间-->");
				sb.append("<MsgCode type=\"int\">").append(HttpDefine.Teacher_Service_20005).append("</MsgCode>");
				//sb.append("<!--0表示失败，1表示成功-->");
				sb.append("<ResultCode type=\"int\">").append(rt).append("</ResultCode>");
			sb.append("</MsgHead>");
			sb.append("<MsgBody>");
				//sb.append("<!--获取当前服务器时间-->");
				sb.append("<ServerTime>");
					sb.append("<Year type=\"int\">").append(year).append("</Year>");
					sb.append("<Month type=\"int\">").append(month).append("</Month>");
					sb.append("<Day type=\"int\">").append(day).append("</Day>");
					sb.append("<Hour type=\"int\">").append(hour).append("</Hour>");
					sb.append("<Minute type=\"int\">").append(minute).append("</Minute>");
					sb.append("<Second type=\"int\">").append(seconds).append("</Second>");
				sb.append("</ServerTime>");
			sb.append("</MsgBody>");
		sb.append("</ResMsg>");
		return sb.toString();
	}

}
