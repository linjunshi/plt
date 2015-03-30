package com.santrong.plt.weixin.mp.entry;

/**
 * @author huangweihua
 * @date 2015年3月20日 
 * @time 下午5:39:15
 */
public class WeixinJsApiTicketEntry {
	/*
	 * jsapi_ticket是公众号用于调用微信JS接口的临时票据。
	 * 正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取。
	 * 由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，
	 * 影响自身业务，开发者必须在自己的服务全局缓存jsapi_ticket 。
	 */
	private int errcode;
	private String errmsg;
	private String ticket;
	private int expires_in;
	
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
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
}
