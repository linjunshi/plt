package com.santrong.plt.webpage.live.entry;

import java.util.Date;

/**
 * @author huangweihua
 * @date	2014年11月5日 
 * @time	上午11:01:37
 */
public class LiveCallItem {

		// resource_live_call 直播点名
		private String id;
		private String liveId;
		private String callName;
		private Date cts;
		
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
}
