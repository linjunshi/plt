package com.santrong.plt.opt.tag;

import com.santrong.plt.opt.ParamHelper;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.MyUtils;

/**
 * @author weinianjie
 * @date 2014年10月28日
 * @time 上午11:56:02
 */
public class LinkTag {
	
	
	/**
	 * 获得过滤器和排序器组成的全链接
	 * @return
	 */
	public static String full() {
		ParamHelper param = ThreadUtils.getParam();
		if(param != null) {
			StringBuilder sb = new StringBuilder();
			if(param.getParamList().size() != 0) {
				sb.append(param.getFilterName()).append("=");
				for(String t:param.getParamList()) {
					sb.append(t).append("_");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			return linkAddSort(param, sb);
		}
		return "";
	}
	
	/**
	 * 某个字符串开始的属性被移除的链接
	 * @param key
	 * @return
	 */
	public static String startRemove(String key) {
		ParamHelper param = ThreadUtils.getParam();
		if(param != null) {
			StringBuilder sb = new StringBuilder();
			if(param.getParamList().size() != 0) {
				for(String t:param.getParamList()) {
					if(!t.startsWith(key)) {
						sb.append(t).append("_");
					}
				}
				if(sb.length() != 0) {
					sb.insert(0, "=").insert(0, param.getFilterName());
					sb.deleteCharAt(sb.length() - 1);
				}
			}
			return linkAddSort(param, sb);
			}
		return "";
	}
	
	/**
	 * 某个字符串开始的属性被替换的链接，如果属性查找不到则新增
	 * @param key
	 * @param val
	 * @return
	 */	
	public static String startReplace(String key, String val) {
		ParamHelper param = ThreadUtils.getParam();
		if(param != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(param.getFilterName()).append("=");
			boolean finded = false;
			if(param.getParamList().size() != 0) {
				for(String t:param.getParamList()) {
					if(!t.startsWith(key)) {
						sb.append(t).append("_");
					}else {
						sb.append(val).append("_");
						finded = true;
					}
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			if(!finded) {
				if(sb.charAt(sb.length() - 1) != '=') {
					sb.append("_");
				}
				sb.append(val);
			}
			return linkAddSort(param, sb);
		}
		return "";
	}
	
	/**
	 * 某个字符串的属性存在取反的链接
	 * @param key
	 * @return
	 */
	public static String containSwitch(String key) {
		ParamHelper param = ThreadUtils.getParam();
		if(param != null) {
			StringBuilder sb = new StringBuilder();
			boolean finded = false;
			if(param.getParamList().size() != 0) {
				for(String t:param.getParamList()) {
					if(t.equals(key)) {
						finded = true;
					}else {
						sb.append(t).append("_");
					}
				}
			}
			if(sb.length() != 0 && sb.charAt(sb.length() - 1) == '_') {
				sb.deleteCharAt(sb.length() - 1);
			}
			if(!finded) {
				if(sb.length() != 0 && sb.charAt(sb.length() - 1) != '=') {
					sb.append("_");
				}
				sb.append(key);
			}
			if(sb.length() != 0) {
				sb.insert(0, "=").insert(0, param.getFilterName());
			}
			return linkAddSort(param, sb);
		}
		return "";
	}
	
	/**
	 * 获取排序器的链接，默认（null）--取消，相同（key）--取反，不同（key）--取升序（默认升序）
	 * @param key
	 * @return
	 */
	public static String sort(String key) {
		ParamHelper param = ThreadUtils.getParam();
		if(param != null) {
			StringBuilder sb = new StringBuilder();
			if(param.getParamList().size() != 0) {
				sb.append(param.getFilterName()).append("=");
				for(String t:param.getParamList()) {
					sb.append(t).append("_");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			if(MyUtils.isNotNull(key)) {
				if(sb.length() != 0) {
					sb.append("&");
				}
				sb.append(param.getSortName()).append("=");
				if(key.equals(param.getOrderBy())) {// 相同
					sb.append(param.getOrderBy());
					if(param.getOrderRule().equals("Desc")) {
						sb.append("Asc");
					}
				}else {// 不同
					sb.append(key);
				}
			}
			
			// 插入关键词
			if(MyUtils.isNotNull(param.getKeyword())) {
				if(sb.length() != 0) {
					sb.append("&");
				}
				sb.append(param.getKeyName() + "=" + param.getKeyword());
			}			
			
			if(sb.length() != 0) {
				sb.insert(0, "?");
			}
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 取消排序（默认排序）的链接
	 * @return
	 */
	public static String sortWithout() {
		ParamHelper param = ThreadUtils.getParam();
		if(param != null) {
			StringBuilder sb = new StringBuilder();
			if(param.getParamList().size() != 0) {
				sb.append(param.getFilterName()).append("=");
				for(String t:param.getParamList()) {
					sb.append(t).append("_");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			
			// 插入关键词
			if(MyUtils.isNotNull(param.getKeyword())) {
				if(sb.length() != 0) {
					sb.append("&");
				}
				sb.append(param.getKeyName() + "=" + param.getKeyword());
			}			
			
			if(sb.length() != 0) {
				sb.insert(0, "?");
			}			
			return sb.toString();
		}
		return "";
	}
	
	// 过滤器附加排序器链接
	private static String linkAddSort(ParamHelper param, StringBuilder sb) {
		if(param != null && sb != null) {
			if(MyUtils.isNotNull(param.getOrderBy())) {
				if(sb.length() != 0) {
					sb.append("&");
				}
				sb.append(param.getSortName()).append("=").append(param.getOrderBy());
				if(param.getOrderRule().equals("Asc")) {
					sb.append("Asc");
				}
			}
			
			// 插入关键词
			if(MyUtils.isNotNull(param.getKeyword())) {
				if(sb.length() != 0) {
					sb.append("&");
				}
				sb.append(param.getKeyName() + "=" + param.getKeyword());
			}
			
			if(sb.length() != 0) {
				sb.insert(0, "?");
			}
			return sb.toString();
		}
		return "";
	}
}
