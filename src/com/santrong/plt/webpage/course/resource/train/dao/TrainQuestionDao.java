package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午5:56:58
 */
public class TrainQuestionDao extends BaseDao{

	/**
	 * 上报一条作业历史的记录
	 * @author huangweihua
	 * @param TrainHistoryItem
	 * @return int
	 */
	public boolean insert(TrainQuestionItem trainQuestionItem){
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.insert(trainQuestionItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 查询该用户的所有题目
	 * @author huangweihua
	 * @param TrainHistoryItem
	 * @return int
	 */
	public List<TrainQuestionItem> selectByUserId(String userId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.selectByUserId(userId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	public TrainQuestionItem selectById(String id) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.selectById(id);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 习题添加到作业里
	 * @param questionId
	 * @param trainId
	 * @return
	 */
	public int addQuestion2Train(String questionId, String trainId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.addQuestion2Train(questionId, trainId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 习题从作业中移除
	 * @param questionId
	 * @param trainId
	 * @return
	 */
	public int removeQuestion4Train(String questionId, String trainId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.removeQuestion4Train(questionId, trainId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}	
	
	/**
	 * 从题库中，删除一条试题记录
	 * @author huangweihua
	 * @param id
	 * @return boolean
	 */
	public boolean deleteById(String id) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.deleteById(id) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
}
