package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainItem;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainDao extends BaseDao{

	/**
	 * 新增一条作业的记录
	 * @author huangweihua
	 * @param TrainItem
	 * @return int
	 */
	public int insert(TrainItem trainItem){
		try {
			TrainMapper mapper = this.getMapper(TrainMapper.class);
			if (mapper != null) {
				return mapper.insert(trainItem);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 查询所属用户的所有作业(del = 0)
	 * @author huangweihua
	 * @param userId,del = 0
	 * @return List<TrainItem>
	 */
	public List<TrainItem> selectByUserId(String userId) {
		try {
			TrainMapper mapper = this.getMapper(TrainMapper.class);
			if (mapper != null) {
				return mapper.selectByUserId(userId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 获取某个直播的随堂作业，如果还没有则返回null
	 * @param liveId
	 * @return
	 */
	public TrainItem selectByLiveId(String liveId) {
		try {
			TrainMapper mapper = this.getMapper(TrainMapper.class);
			if (mapper != null) {
				return mapper.selectByLiveId(liveId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
}
