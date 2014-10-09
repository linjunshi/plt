package com.santrong.plt.util;

/**
 * @author weinianjie
 * @date 2014年10月9日
 * @time 下午7:34:40
 */
public class AreaUtils {
	
	public static String lostTail(String areaCode) {
		if(areaCode != null && !areaCode.equals("")) {
			String _areaCode = areaCode;
			while(_areaCode.length() > 0) {
				if(!_areaCode.endsWith("0")) {
					break;
				}
				_areaCode = _areaCode.substring(0, _areaCode.length() - 1);
			}
			return _areaCode;
		}
		return areaCode;
	}
}
