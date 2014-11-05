package com.santrong.plt.webpage.live.entry;

import java.util.Date;

/**
 * @author huangweihua
 * @date	2014年11月5日 
 * @time	上午11:01:37
 */
public class LiveReplyItem {

		// resource_live_reply 直播答到
		private String id;
		private String callId;
		private String userId;
		private Date cts;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCallId() {
			return callId;
		}
		public void setCallId(String callId) {
			this.callId = callId;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public Date getCts() {
			return cts;
		}
		public void setCts(Date cts) {
			this.cts = cts;
		}
}
