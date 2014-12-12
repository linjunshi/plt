package com.santrong.plt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weinianjie
 * @date 2014年7月31日
 * @time 下午2:54:30
 */
public class ValidateTools {
	
	/**
	 * 校验是否是uuid
	 * @param str
	 * @return
	 */
	public static boolean isGUID(String str) {
		if(str != null) {
			Pattern pat = Pattern.compile("[0-9a-zA-Z]{32}");  
			Matcher mat = pat.matcher(str);  
			return mat.find();
		}
		return false;
	}
	
	/**
	 * 校验是否是必填0或正整数
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		if(str != null) {
			Pattern pat = Pattern.compile("^[0-9]+$");  
			Matcher mat = pat.matcher(str);  
			return mat.find();
		}
		return false;
	}
	
	/**
	 * 校验是否是日期格式：yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str) {
		if(str != null) {
			Pattern pat = Pattern.compile("^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$");  
			Matcher mat = pat.matcher(str);  
			return mat.find();
		}
		return false;
	}
	
	/**
	 * 校验是否是日期时间格式：yyyy-MM-dd Hi:mm:ss
	 * @param str
	 * @return
	 */
	public static boolean isDateTime(String str) {
		if(str != null) {
			Pattern pat = Pattern.compile("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))(\\s(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:([0-5][0-9])))?$");  
			Matcher mat = pat.matcher(str);  
			return mat.find();
		}
		return false;
	}
	
	/**
	 * 校验是否是正确的身份证号码
	 * @param str
	 * @return
	 */
	public static boolean isIdCard(String str) {
		if(str != null) {
			Pattern pat = Pattern.compile("^([0-9]{17}[0-9X]{1})|([0-9]{15})$");  
			Matcher mat = pat.matcher(str);  
			return mat.find();
		}
		return false;
	}
	
	/**
	 * 校验是否是正确的手机号码
	 * @param str
	 * @return
	 */
	public static boolean isPhoneNum(String str) {
		if(str != null) {
			Pattern pat = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");  
			Matcher mat = pat.matcher(str);  
			return mat.find();
		}
		return false;
	}
	
	/**
	 * 校验是否是正确的邮箱地址
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		if(str != null) {
			Pattern pat = Pattern.compile("^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$");  
			Matcher mat = pat.matcher(str);  
			return mat.find();
		}
		return false;
	}
}
