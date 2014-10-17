package com.santrong.plt.opt.area;

import java.io.File;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author 孙钰佳
 * @mail sunyujia@yahoo.cn
 * @date 2007-9-24 下午12:06:19
 */
public class IPSeekerTest {
	public static void main(String[] args) {
		IPSeeker ipSeeker = new IPSeeker(new File("E:/workspace/plt/data/QQWry.Dat"));
		System.out.println(ipSeeker.getCountry("8.8.8.8"));
		System.out.println(ipSeeker.getCountry("192.168.10.178"));
		System.out.println(ipSeeker.getCountry("183.17.255.255"));
		System.out.println(ipSeeker.getCountry("61.49.106.113"));
		System.out.println(ipSeeker.getArea("61.49.106.113"));
		System.out.println(ipSeeker.getDetailedIpInfo("61.49.106.113"));
	}
}
