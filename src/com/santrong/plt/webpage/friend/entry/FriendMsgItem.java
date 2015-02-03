package com.santrong.plt.webpage.friend.entry;

/**
 * @author weinianjie
 * @date 2015年2月3日
 * @time 下午5:32:17
 */
public class FriendMsgItem {
	private String userId;
	private String showName;
	private String msg;
	private int result;
	 
	private int type;// 0作为主动方，1作为被动方

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
