package com.santrong.plt.webpage.course.entry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.Description;

/**
 * @author weinianjie
 * @date 2014年10月21日
 * @time 上午11:07:42
 */
public class ResourceType {
	
	private static Map<Integer, String> map = new HashMap<Integer, String>();

	@Description("视频文件")
	public static final int Type_Video = 1;
	
	@Description("直播资源")
	public static final int Type_Live = 2;
	
	@Description("学习材料")
	public static final int Type_Doc = 3;
	
	@Description("测验")
	public static final int Type_Train = 4;
	
	static{
		Field[] fields = ResourceType.class.getFields();
		for(Field f:fields) {
			Description d = ((Description)f.getAnnotation(Description.class));
			try {
				map.put(f.getInt(null), d.value());
			}catch(Exception e) {
				Log.printStackTrace(e);
			}
		}
	}
	
	public static final String getDescription(int key) {
		return map.get(key);
	}
}
