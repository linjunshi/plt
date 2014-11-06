package com.santrong.plt.webpage.course.resource.live.entry;

import java.util.Date;

/**
 * @author huangweihua
 * @date	2014年11月4日
 * @time	 下午4:02:14
 */
public class LiveScoreItem {

		// resource_live_score 直播评分
		private String id;
		private String userId;
		private String liveId;
		private int score;
		private Date cts;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getLiveId() {
			return liveId;
		}
		public void setLiveId(String liveId) {
			this.liveId = liveId;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public Date getCts() {
			return cts;
		}
		public void setCts(Date cts) {
			this.cts = cts;
		}

}
