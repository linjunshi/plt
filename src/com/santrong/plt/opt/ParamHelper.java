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
	
	public ParamHelper getSelf() {
		return this;
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
	
	public List<String> getParamList() {
		return paramList;
	}
	public void setParamList(List<String> paramList) {
		this.paramList = paramList;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public void setOrderRule(String orderRule) {
		this.orderRule = orderRule;
	}
}
