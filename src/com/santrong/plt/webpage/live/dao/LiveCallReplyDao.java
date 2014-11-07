package com.santrong.plt.webpage.live.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.live.entry.LiveCallReplyItem;

/**
 * @author huangweihua
 * @date 2014年11月5日 
 * @time 上午11:14:01
 */
public class LiveCallReplyDao extends BaseDao {

	/**
	 * 获取直播课堂点名已签到的学生
	 * @param callNameId
	 * @param liveId
	 * @return
	 */
	public List<LiveCallReplyItem> selectUserID(String callNameId, String liveId) {
		try {
			LiveCallReplyMapper mapper = this.getMapper(LiveCallReplyMapper.class);
			if(mapper != null) {
				return mapper.selectUserID(callNameId, liveId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
}
