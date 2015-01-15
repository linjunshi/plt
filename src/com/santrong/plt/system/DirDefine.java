package com.santrong.plt.system;

import com.santrong.plt.util.SystemUtils;

/**
 * @author weinianjie
 * @date 2014年8月8日
 * @time 下午4:22:21
 */
public class DirDefine {
	// 部署环境
	public static final String ShellDir;								// 所有shell脚本目录
	public static final String UpdateFileDir;					// 升级文件放置目录

	
	static {
		
		// 开发环境
		if(SystemUtils.getOsType() == SystemUtils.WINDOWS) {
			ShellDir = "E:/workspace/plt/linuxDir/opt/AIO/Service/webservice/webapp/shell";
			UpdateFileDir = "E:/workspace/data/upload";
			
		// 部署环境
		}else{
			ShellDir = "/opt/AIO/Service/webservice/webapp/shell";
			UpdateFileDir = "/file/upload";
		}
		
	}
}
