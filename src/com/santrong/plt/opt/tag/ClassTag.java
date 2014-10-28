package com.santrong.plt.opt.tag;

import com.santrong.plt.opt.ParamHelper;
import com.santrong.plt.util.MyUtils;

/**
 * @author weinianjie
 * @date 2014年10月28日
 * @time 上午11:56:02
 */
public class ClassTag {
	
	public static String fk(ParamHelper param) {
		return "123";
	}
	
	/**
	 * 输出排序按钮的class，升序sortAsc，降序sortDesc，无
	 * @param sortName
	 * @return
	 */
	public static String sort(ParamHelper param, String sortName) {
		if(param != null) {
			if(MyUtils.isNotNull(param.getOrderBy()) || param.getOrderBy().equals(sortName)) {
				if(param.getOrderRule().equals("Asc")) {
					return "sortAsc";
				}else {
					return "sortDesc";
				}
			}
		}
		return "";
	}
	
	/**
	 * 输出选择按钮的class，选中attrSelected，未选
	 * @param paramName
	 * @return
	 */
	public static String select(ParamHelper param, String paramName) {
		if(param != null) {
			if(param.getParamContain(paramName)) {
				return "attrSelected";
			}
		}
		return "";
	}
	
	/**
	 * 绝对相等的输出Class
	 * @param left
	 * @param right
	 * @return
	 */
	public static String staticEq(String left, String right) {
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
	public static String startEq(ParamHelper param, String start, String compare) {
		if(param != null) {
			String a = param.getParamByStart(start);
			if((a == null && compare == null) || (a != null && a.equals(compare))) {
				return "current";
			}
		}
		return "";
	}
}
