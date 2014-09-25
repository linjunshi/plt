package com.santrong.plt.tcp.client.base;

import com.santrong.plt.util.XmlReader;

/**
 * @Author weinianjie
 * @Date 2014-7-12
 * @Time 下午2:33:02
 */
public interface TcpImpl {
	String toXml();
	
	void resolveXml(XmlReader xml);
	
	String getHost();
	
	int getPort();
	
}
