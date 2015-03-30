package com.santrong.plt.weixin.mp.entry;

/**
 * 接口访问凭证
 * @author huangweihua
 * @date 2015年3月20日 
 * @time 下午4:39:15
 */
public class WeixinAccessTokenEntry {
	
	/*
	 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。
	 * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
	 * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
	 */
	
	private String access_token;// 获取到的凭证
	private int expires_in;// 凭证有效时间，单位：秒 
	
	private int errcode;
	private String errmsg;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
