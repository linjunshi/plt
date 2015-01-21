package com.santrong.plt.webpage.competition.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.competition.entry.CompetitionAttendItem;
import com.santrong.plt.webpage.competition.entry.CompetitionHistoryItem;
import com.santrong.plt.webpage.competition.entry.CompetitionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;

/**
 * @author weinianjie
 * @date 2014年12月25日
 * @time 下午4:44:45
 */
public class CompetitionDao extends BaseDao {

	/**
	 * 插入答题历史
	 * @param item
	 * @return
	 */
	public int insertHistory(CompetitionHistoryItem item) {
		try {
			CompetitionMapper mapper = this.getMapper(CompetitionMapper.class);
			if (mapper != null) {
				return mapper.insertHistory(item);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 插入竞赛参与
	 * @param item
	 * @return
	 */
	public int insertAttend(CompetitionAttendItem item) {
		try {
			CompetitionMapper mapper = this.getMapper(CompetitionMapper.class);
			if (mapper != null) {
				return mapper.insertAttend(item);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 插入竞赛
	 * @param item
	 * @return
	 */
	public int insert(CompetitionItem item) {
		try {
			CompetitionMapper mapper = this.getMapper(CompetitionMapper.class);
			if (mapper != null) {
				return mapper.insert(item);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
	
	/**
	 * 获取竞赛或者自定义练习的答题历史
	 * @return
	 */
	public List<CompetitionHistoryItem>  selectUserHistory(String userId, String competitionId) {
		try {
			CompetitionMapper mapper = this.getMapper(CompetitionMapper.class);
			if (mapper != null) {
				return mapper.selectUserHistory(userId, competitionId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 根据竞赛Id或者自定义练习Id获取题目列表
	 * @param competitionId
	 * @return
	 */
	public List<TrainQuestionItem> selectQuestionsByCompetitionId(String competitionId) {
		try {
			CompetitionMapper mapper = this.getMapper(CompetitionMapper.class);
			if (mapper != null) {
				return mapper.selectQuestionsByCompetitionId(competitionId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 当前用户是否已经有做过试题的记录了
	 * @param userId
	 * @param competitionId
	 * @return
	 */
	public boolean existDoneExamByUserId(String userId, String competitionId) {
		try {
			CompetitionMapper mapper = this.getMapper(CompetitionMapper.class);
			if (mapper != null) {
				return mapper.existDoneExamByUserId(userId, competitionId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 获取当前用户已经有做过试题的记录
	 * @param userId
	 * @param competitionId
	 * @return
	 */
	public CompetitionAttendItem selectAttendByUserId(String userId, String competitionId) {
		try {
			CompetitionMapper mapper = this.getMapper(CompetitionMapper.class);
			if (mapper != null) {
				return mapper.selectAttendByUserId(userId, competitionId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 是否已经存在历史记录了
	 * @param attendId
	 * @param questionId
	 * @return
	 */
	public boolean existHistory(String attendId, String questionId) {
		try {
			CompetitionMapper mapper = this.getMapper(CompetitionMapper.class);
			if (mapper != null) {
				return mapper.existHistory(attendId, questionId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
}
