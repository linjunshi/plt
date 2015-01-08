package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryView;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public class TrainHistoryDao extends BaseDao{

	/**
	 * 上报一条作业历史的记录
	 * @author huangweihua
	 * @param CompetitionHistoryItem
	 * @return int
	 */
	public int insert(TrainHistoryItem trainHistoryItem){
		try {
			TrainHistoryMapper mapper = this.getMapper(TrainHistoryMapper.class);
			if (mapper != null) {
				return mapper.insert(trainHistoryItem);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 查询当前直播课程某个章节，某位学生是否已经上报过课堂作业答案的记录
	 * @author huangweihua
	 * @param userId
	 * @param trainId
	 * @param chapterId
	 * @param questionId
	 * @return boolean
	 */
	public boolean exists(String userId, String trainId, String chapterId, String questionId) {
		try {
			TrainHistoryMapper mapper = this.getMapper(TrainHistoryMapper.class);
			if (mapper != null) {
				return mapper.exists(userId, trainId, chapterId, questionId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 查询当前直播课程某个章节某一道题，所有学生提交的答案结果情况
	 * @author huangweihua
	 * @param chapterId
	 * @param trainId
	 * @param questionId
	 * @return List<TrainHistoryItem>
	 */
	public List<TrainHistoryItem> selectByHistory(String chapterId, String trainId, String questionId){
		try {
			TrainHistoryMapper mapper = this.getMapper(TrainHistoryMapper.class);
			if (mapper != null) {
				return mapper.selectByHistory(chapterId, trainId, questionId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 获取一条作业历史
	 * @param id
	 * @return
	 */
	public TrainHistoryItem selectById(String id) {
		try {
			TrainHistoryMapper mapper = this.getMapper(TrainHistoryMapper.class);
			if (mapper != null) {
				return mapper.selectById(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}				
		return null;
	}
	
	/**
	 * 获取一条作业历史
	 * @param questionId
	 * @param trainId
	 * @param chapterId
	 * @param userId
	 * @return
	 */
	public TrainHistoryItem selectByUnique(String questionId, String trainId, String chapterId, String userId) {
		try {
			TrainHistoryMapper mapper = this.getMapper(TrainHistoryMapper.class);
			if (mapper != null) {
				return mapper.selectByUnique(questionId, trainId, chapterId, userId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}				
		return null;
	}	
	
	/**
	 * 获取某个测验某个学生的全部答题详细
	 * @return
	 */
	public List<TrainHistoryView>  selectUserDoneDetail(String userId, String trainId, String chapterId) {
		try {
			TrainHistoryMapper mapper = this.getMapper(TrainHistoryMapper.class);
			if (mapper != null) {
				return mapper.selectUserDoneDetail(userId, trainId, chapterId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 获取某个测验某个学生的答题历史
	 * @return
	 */
	public List<TrainHistoryItem>  selectUserHistory(String userId, String trainId, String chapterId) {
		try {
			TrainHistoryMapper mapper = this.getMapper(TrainHistoryMapper.class);
			if (mapper != null) {
				return mapper.selectUserHistory(userId, trainId, chapterId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}	
	
	/**
	 * 删除用户测验记录
	 * @param userId
	 * @param trainId
	 * @param chapterId
	 * @return
	 */
	public int deleteUserHistory(String userId, String trainId, String chapterId) {
		try {
			TrainHistoryMapper mapper = this.getMapper(TrainHistoryMapper.class);
			if (mapper != null) {
				return mapper.deleteUserHistory(userId, trainId, chapterId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	
}
