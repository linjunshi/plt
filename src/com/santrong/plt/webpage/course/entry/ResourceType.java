package com.santrong.plt.webpage.course.entry;

import java.lang.reflect.Field;

import com.santrong.plt.log.Log;
import com.santrong.plt.opt.Description;

/**
 * @author weinianjie
 * @date 2014年10月21日
 * @time 上午11:07:42
 */
public class ResourceType {
	
	@Description(cn = "课件", en = "file")
	public static final int Type_File = 1;
	
	@Description(cn = "直播资源", en = "live")
	public static final int Type_Live = 2;
	
	@Description(cn = "学习材料", en = "doc")
	public static final int Type_Doc = 3;
	
	@Description(cn = "测验", en = "train")
	public static final int Type_Train = 4;
	
	static{

	}
	
	// 获取cn
	public static final String getDescriptionCn(int key) {
		Field[] fields = ResourceType.class.getFields();
		for(Field f:fields) {
			Description d = ((Description)f.getAnnotation(Description.class));
			try {
				if(f.getInt(null) == key) {
					return d.cn();
				}
			}catch(Exception e) {
				Log.printStackTrace(e);
			}
		}
		return null;
	}
	
	// 获取en
	public static final String getDescriptionEn(int key) {
		Field[] fields = ResourceType.class.getFields();
		for(Field f:fields) {
			Description d = ((Description)f.getAnnotation(Description.class));
			try {
				if(f.getInt(null) == key) {
					return d.en();
				}
			}catch(Exception e) {
				Log.printStackTrace(e);
			}
		}
		return null;
	}	
}
