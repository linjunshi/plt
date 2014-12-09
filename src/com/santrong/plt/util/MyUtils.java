package com.santrong.plt.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.santrong.plt.log.Log;

/**
 * @author weinianjie
 * @date 2014年7月18日
 * @time 下午5:29:22
 */
public class MyUtils {
	public static final String DF_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String DF_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String DF_yyyy_MM = "yyyy-MM";
	public static final String DF_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String DF_HH_mm_ss = "HH:mm:ss";
	public static final String DF_HH_mm = "HH:mm";

	/**
	 * 不为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		return value == null || value.trim().length() == 0;
	}

	/**
	 * 为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNull(String value) {
		return !isNull(value);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String string, String pattern) {
		if (isNull(string)) {
			return null;
		}
		if (!isNotNull(pattern)) {
			pattern = DF_yyyy_MM_dd_HH_mm_ss;
		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		try {
			return sdf.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (pattern == null) {
			pattern = DF_yyyy_MM_dd_HH_mm_ss;
		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	public static int stringToInt(String str) {
		return stringToInt(str, 0);
	}

	public static int stringToInt(String str, int def) {
		if (str == null || str.equals("")) {
			return def;
		}
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return def;
		}
	}

	/**
	 * 把字节类型转换为自适应类型
	 * 
	 * @param size
	 * @return
	 */
	public static String formatDiskSize(long size) {
		if (size >= 1024) {
			size = size / 1024;
			if (size >= 1024) {
				size = size / 1024;
				if (size >= 1024) {

					double s = (double) size / 1024;
					DecimalFormat df = new DecimalFormat("#.00");
					return df.format(s) + "G";

					// size = size / 1024;
					// return size + "G";
				} else {
					return size + "M";
				}
			} else {
				return size + "K";
			}
		} else {
			return size + "B";
		}
	}

	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getGUID() {
		UUID uuid = UUID.randomUUID();
		String guid = uuid.toString().replace("-", "");
		return guid;
	}

	/**
	 * 获取MD5
	 * 
	 * @param s
	 * @return
	 */
	public final static String getMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取客户端的IP地址，这里主要过滤本地访问时出现的127.0.0.1的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestAddrIp(HttpServletRequest request,
			String localIp) {
		String ip = request.getHeader("x-forwarded-for");
		if (!isNotNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-real-ip");
		}
		if (isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isNull(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (isNull(ip)) {
			ip = "unknown";
		}

		if ("127.0.0.1".equals(ip) || "localhost".equalsIgnoreCase(ip)) {
			ip = request.getLocalAddr();
		}
		if ("127.0.0.1".equals(ip) || "localhost".equalsIgnoreCase(ip)) {
			ip = localIp;
		}
		return ip;
	}

	/**
	 * 获取远程内容
	 */
	public static String getRemoteContent(String remoteUrl) {
		BufferedReader in = null;
		String result = "";
		try {

			Log.info("getRemoteContent-url: " + remoteUrl);
			URL url = new URL(remoteUrl);
			URLConnection conn = url.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.connect();

			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

			Log.info("getRemoteContent-content:" + result);

			return result;

		} catch (Exception e) {
			Log.printStackTrace(e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				Log.printStackTrace(e2);
			}
		}

		return result;
	}

	/**
	 * 组装ids
	 * 
	 * @param ids
	 * @return
	 */
	public static String consistIds(String[] ids) {
		StringBuilder sb = new StringBuilder();
		for (String id : ids) {
			sb.append(",'").append(id).append("'");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
			return sb.toString();
		}

		return null;
	}
}
