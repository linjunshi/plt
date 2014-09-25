package com.santrong.plt.ftp;

import java.util.HashMap;

import com.santrong.plt.log.Log;

/**
 * @author weinianjie
 * @date 2014年9月23日
 * @time 上午10:58:05
 */
public class DemoEventListener implements EventListener{

	@Override
	public void afterFinish(HashMap<String, Object> mapper) {
		Log.info(mapper.get("key"));
	}
	
}
