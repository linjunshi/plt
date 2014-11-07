package com.santrong.plt.webpage.course.resource.live.entry;

import java.util.Date;

/**
 * @author huangweihua
 * @date	2014年11月5日 
 * @time	下午4:05:02
 */
public class LiveCallReplyItem {

		// resource_live_call 直播点名
		private String id;
		private String liveId;
		private String callName;
		private Date cts;
		
		// resource_live_reply 直播答到
//		private String replyid;
//		private String callId;
		private String userId;
//		private Date replycts;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLiveId() {
			return liveId;
		}
		public void setLiveId(String liveId) {
			this.liveId = liveId;
		}
		public String getCallName() {
			return callName;
		}
		public void setCallName(String callName) {
			this.callName = callName;
		}
		public Date getCts() {
			return cts;
		}
		public void setCts(Date cts) {
			this.cts = cts;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
}
