package com.santrong.plt.webpage.friend.entry;

import java.util.Date;

/**
 * @author weinianjie
 * @date 2015年2月3日
 * @time 下午2:59:48
 */
public class UserRelationItem {
	private String userId1;
	private String userId2;
	private String applyMsg;
	private String returnMsg;
	private int result;
	private Date cts;
	private Date uts;
	
	public static int Result_Apply = 0;// 申请
	public static int Result_Agree = 1;// 同意
	public static int Result_Refuse = 2;// 拒绝
	public static int Result_Ignore = 3;// 忽略
	public static int Result_Black = 10;// 黑名单
	
	
	public String getUserId1() {
		return userId1;
	}
	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}
	public String getUserId2() {
		return userId2;
	}
	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}
	public String getApplyMsg() {
		return applyMsg;
	}
	public void setApplyMsg(String applyMsg) {
		this.applyMsg = applyMsg;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Date getCts() {
		return cts;
	}
	public void setCts(Date cts) {
		this.cts = cts;
	}
	public Date getUts() {
		return uts;
	}
	public void setUts(Date uts) {
		this.uts = uts;
	}
}
