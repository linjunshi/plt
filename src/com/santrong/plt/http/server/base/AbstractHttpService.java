package com.santrong.plt.http.server.base;

import com.santrong.plt.util.XmlReader;

/**
 * @Author weinianjie
 * @Date 2014-7-7
 * @Time 下午10:29:13
 */
public interface AbstractHttpService {
	
	String excute(XmlReader xml);
	
}
