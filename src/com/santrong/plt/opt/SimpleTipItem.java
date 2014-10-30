package com.santrong.plt.opt;

/**
 * @author weinianjie
 * @date 2014年10月30日
 * @time 上午9:31:57
 */
public class SimpleTipItem {
	private int type;
	private String msg;
	
	public static final int Type_Error = 0;
	public static final int Type_Warn = 1;

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
