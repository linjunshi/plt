package com.santrong.plt.webpage.teacher.entry;

import java.util.Date;

/**
 * @author huangweihua
 * @date   2014年11月14日 
 * @time   上午10:05:13
 */
public class UserExtendsItem {

	// user_extends 用户其他扩展信息
	private String userId;
	private Date birthday;
	private String nativePlace;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
}
