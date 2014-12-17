package com.santrong.plt.webpage.course.entry;

import com.santrong.plt.util.MyUtils;


/**
 * @author weinianjie
 * @date 2014年12月17日
 * @time 上午11:08:41
 */
public class ChinaBankPayGateParams {
	private String v_mid;// 商户号
	private String v_oid;// 订单号，当日不能重复
	private String v_amount;// 价格0.01
	private String v_moneytype;// 类型CNY
	private String v_url;// 跳转地址
	private String v_md5info;// md5加密值
	private String v_rcvname;// 购买者
	private String remark1;// 购买者ID
	
	public String getV_mid() {
		return v_mid;
	}
	public void setV_mid(String v_mid) {
		this.v_mid = v_mid;
	}
	public String getV_oid() {
		return v_oid;
	}
	public void setV_oid(String v_oid) {
		this.v_oid = v_oid;
	}
	public String getV_amount() {
		return v_amount;
	}
	public void setV_amount(String v_amount) {
		this.v_amount = v_amount;
	}
	public String getV_moneytype() {
		return v_moneytype;
	}
	public void setV_moneytype(String v_moneytype) {
		this.v_moneytype = v_moneytype;
	}
	public String getV_url() {
		return v_url;
	}
	public void setV_url(String v_url) {
		this.v_url = v_url;
	}
	public String getV_md5info() {
		return v_md5info;
	}
	public void setV_md5info(String v_md5info) {
		this.v_md5info = v_md5info;
	}
	public String getV_rcvname() {
		return v_rcvname;
	}
	public void setV_rcvname(String v_rcvname) {
		this.v_rcvname = v_rcvname;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	/**
	 * 根据网银在线提供的算法计算MD5值
	 * @param key 网银在线平台设置的md5加密的key
	 * @return
	 */
	public String calMd5(String key) {
		// 六大参数无空隙合并
		// MD5加密
		// 转成大写
		return MyUtils.getMD5(v_amount+v_moneytype+v_oid+v_mid+v_url+key).toUpperCase();
//		MD5 md5Tool = new MD5();
//		return md5Tool.getMD5ofStr(v_amount+v_moneytype+v_oid+v_mid+v_url+key);//网银提供的cb_md5.jar加密
		// 经过初步校验，结果一致，故删除cb_md5.jar			
	}
}
