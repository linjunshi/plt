package com.santrong.plt;

import com.santrong.plt.util.MyUtils;






/**
 * @author weinianjie
 * @date 2014年7月24日
 * @time 下午5:28:37
 */
public class Test {
	
	public static void main(String[] args) {
		String text = "http://www.baidu.combacksheepwall";
		System.out.println(MyUtils.getMD5(text).toUpperCase());
//		MD5 md5Tool = new MD5();
//		System.out.println(md5Tool.getMD5ofStr(text));//网银提供的cb_md5.jar加密
		
	}
}
