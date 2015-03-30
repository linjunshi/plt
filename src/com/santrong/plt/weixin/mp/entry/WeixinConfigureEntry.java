package com.santrong.plt.weixin.mp.entry;
/**
 * @author huangweihua
 * @date 2015年3月28日 
 * @time 下午5:27:59
 */
public class WeixinConfigureEntry {

	private String appId;// 必填，公众号的唯一标识
	private String timestamp;// 必填，生成签名的时间戳
	private String nonceStr;// 必填，生成签名的随机串
	private String signature;// 必填，签名，见附录1
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
