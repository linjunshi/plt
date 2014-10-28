package com.santrong.plt.opt;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.santrong.plt.util.MyUtils;

/**
 * @author weinianjie
 * @date 2014年10月27日
 * @time 下午4:48:41
 * @description 解析例如filter=level1_live_hasSchool&sort=price类型的参数，排序仅支持单个，升序为sort=xxxAsc，降序为sort=xxx，无排序没该属性
 */
public class ParamHelper {
	private List<String> paramList = new ArrayList<String>();
	private String orderBy = "";
	private String orderRule = "";
	
	private String filterName = "";
	private String sortName = "";
	
	/**
	 * 初始化
	 * @param request
	 * @param filterName
	 * @param sortName
	 */
	public void init(HttpServletRequest request, String filterName, String sortName) {
		String attr = request.getParameter(filterName);
		if(MyUtils.isNotNull(attr)) {
			String[] attrArr = attr.split("_");
			for(String t:attrArr) {
				if(MyUtils.isNotNull(t)) {
					this.paramList.add(t);
				}
			}
		}
		
		attr = request.getParameter(sortName);
		if(MyUtils.isNotNull(attr)) {
			if(attr.endsWith("Asc")) {
				this.orderRule = "Asc";
				attr = attr.substring(0, attr.length() - 3);
			}else {
				this.orderRule = "Desc";
			}
			this.orderBy = attr;
		}
		
		this.filterName = filterName;
		this.sortName = sortName;
	}
	
	public void init(HttpServletRequest request) {
		this.init(request, "filter", "sort");
	}
	
	/**
	 * 根据参数开头名获取参数
	 * @param key
	 * @return
	 */
	public String getParamByStart(String key) {
		for(String t:this.paramList) {
			if(t.startsWith(key)) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * 根据参数结尾名获取参数
	 * @param key
	 * @return
	 */
	public String getParamByEnd(String key) {
		for(String t:this.paramList) {
			if(t.endsWith(key)) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * 是否包含指定的参数
	 * @param key
	 * @return
	 */
	public boolean getParamContain(String key) {
		return this.paramList.contains(key);
	}

	/**
	 * 获取排序名称
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 获取排序法则
	 * @return
	 */
	public String getOrderRule() {
		return orderRule;
	}
	
	//-----------------------------以下开始为页面输出-------------------------------
	
	/**
	 * 输出排序按钮的class，升序sortAsc，降序sortDesc，无
	 * @param sortName
	 * @return
	 */
	public String getSortClass(String sortName) {
		if(MyUtils.isNotNull(this.orderBy) || this.orderBy.equals(sortName)) {
			if(this.orderRule.equals("Asc")) {
				return "sortAsc";
			}else {
				return "sortDesc";
			}
		}
		return "";
	}
	
	/**
	 * 输出选择按钮的class，选中attrSelected，未选
	 * @param paramName
	 * @return
	 */
	public String getSelectClass(String paramName) {
		if(this.getParamContain(paramName)) {
			return "attrSelected";
		}
		return "";
	}
	
	/**
	 * 绝对相等的输出Class
	 * @param left
	 * @param right
	 * @return
	 */
	public String getStaticEqClass(String left, String right) {
		if((left == null && right == null) || (left != null && left.equals(right))) {
			return "current";
		}
		return "";
	}
	
	/**
	 * 开始相等的输出Class
	 * @param start
	 * @param compare
	 * @return
	 */
	public String getStartEqClass(String start, String compare) {
		String a = this.getParamByStart(start);
		if((a == null && compare == null) || (a != null && a.equals(compare))) {
			return "current";
		}
		return "";
	}
	
	/**
	 * 获得过滤器和排序器组成的全链接
	 * @return
	 */
	public String getFullLink() {
		StringBuilder sb = new StringBuilder();
		if(this.paramList.size() != 0) {
			sb.append(this.filterName).append("=");
			for(String t:this.paramList) {
				sb.append(t).append("_");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return linkAddSort(sb);
	}
	
	/**
	 * 某个字符串开始的属性被移除的链接
	 * @param key
	 * @return
	 */
	public String getStartRemoveLink(String key) {
		StringBuilder sb = new StringBuilder();
		if(this.paramList.size() != 0) {
			sb.append(this.filterName).append("=");
			for(String t:this.paramList) {
				if(!t.startsWith(key)) {
					sb.append(t).append("_");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return linkAddSort(sb);
	}
	
	/**
	 * 某个字符串开始的属性被替换的链接，如果属性查找不到则新增
	 * @param key
	 * @param val
	 * @return
	 */	
	public String getStartReplaceLink(String key, String val) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.filterName).append("=");
		boolean finded = false;
		if(this.paramList.size() != 0) {
			for(String t:this.paramList) {
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
		return linkAddSort(sb);
	}
	
	/**
	 * 某个字符串的属性存在取反的链接
	 * @param key
	 * @return
	 */
	public String getContainSwitchLink(String key) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.filterName).append("=");
		boolean finded = false;
		if(this.paramList.size() != 0) {
			for(String t:this.paramList) {
				if(t.equals(key)) {
					finded = true;
				}else {
					sb.append(t).append("_");
				}
			}
		}
		if(sb.charAt(sb.length() - 1) == '_') {
			sb.deleteCharAt(sb.length() - 1);
		}
		if(!finded) {
			if(sb.charAt(sb.length() - 1) != '=') {
				sb.append("_");
			}
			sb.append(key);
		}
		return linkAddSort(sb);
	}
	
	/**
	 * 获取排序器的链接，默认（null）--取消，相同（key）--取反，不同（key）--取升序（默认升序）
	 * @param key
	 * @return
	 */
	public String getSortLink(String key) {
		StringBuilder sb = new StringBuilder();
		if(this.paramList.size() != 0) {
			sb.append(this.filterName).append("=");
			for(String t:this.paramList) {
				sb.append(t).append("_");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		if(MyUtils.isNotNull(key)) {
			if(sb.length() != 0) {
				sb.append("&");
			}
			sb.append(this.sortName).append("=");
			if(key.equals(this.orderBy)) {// 相同
				sb.append(this.orderBy);
				if(this.orderRule.equals("Desc")) {
					sb.append("Asc");
				}
			}else {// 不同
				sb.append(key);
			}
		}
		if(sb.length() != 0) {
			sb.insert(0, "?");
		}
		return sb.toString();
	}
	
	// 过滤器附加排序器链接
	private String linkAddSort(StringBuilder sb) {
		if(sb != null) {
			if(MyUtils.isNotNull(this.orderBy)) {
				if(sb.length() != 0) {
					sb.append("&");
				}
				sb.append(this.sortName).append("=").append(this.orderBy);
				if(this.orderRule.equals("Asc")) {
					sb.append("Asc");
				}
			}
			if(sb.length() != 0) {
				sb.insert(0, "?");
			}
			return sb.toString();
		}
		return null;
	}
	
	
}
