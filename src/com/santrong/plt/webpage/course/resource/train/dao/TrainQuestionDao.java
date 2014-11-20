package com.santrong.plt.webpage.course.resource.train.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.santrong.plt.criteria.Statement;
import com.santrong.plt.log.Log;
import com.santrong.plt.opt.ThreadUtils;
import com.santrong.plt.util.BeanUtils;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionQuery;

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
	 * 查询该用户的所有题目，主要用在老师客户端接口
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
	
	/**
	 * 查询该用户的所有的题目(分页)
	 * @author huangweihua
	 * @param TrainQuestionQuery query
	 * @return List<TrainQuestionItem> list
	 */
	public List<TrainQuestionItem> selectByQuery(TrainQuestionQuery query) {
		List<TrainQuestionItem> list = new ArrayList<TrainQuestionItem>();
		try {
			Statement criteria = new Statement("resource_train_question", "a");
			criteria.setFields("a.*");
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(like("a.topic", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			
			// 所属用户
			if (MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.ownerId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			
			// 题目类型
			if (query.isSingleSelection()) {
				criteria.where(eq("a.questionType", TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION));//单选题 (默认值为1)
			} else if (query.isMulChoice()) {
				criteria.where(eq("a.questionType", TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE));//多选题 (默认值为2)
			} else if (query.isMulChoice()) {
				criteria.where(eq("a.questionType", TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE));//判断题 (默认值为3)
			} else if (query.isMulChoice()) {
				criteria.where(eq("a.questionType", TrainQuestionItem.QUESTION_TYPE_BLANK_FILLING));//填空题 (默认值为4)
			} else {
				System.out.println("warnings : The questionType has no corresponding value.");
			}
			
			// 是否已经标识为已删除（del）
			if (query.getDel() == TrainQuestionItem.IS_NOT_DELETE) {
				criteria.where(eq("a.del", TrainQuestionItem.IS_NOT_DELETE));
			}else if (query.getDel() == TrainQuestionItem.IS_DELETE) {
				criteria.where(eq("a.del", TrainQuestionItem.IS_DELETE));
			}
			
			// 排序
			if (!StringUtils.isNullOrEmpty(query.getOrderRule())) {
				if ("desc".equalsIgnoreCase(query.getOrderRule())) {
					criteria.desc("a." + query.getOrderBy());
				}else{
					criteria.asc("a." + query.getOrderBy());
				}
			}
			
			// 分页
			criteria.limit(query.getLimitBegin(), query.getLimitEnd());
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				TrainQuestionItem item = new TrainQuestionItem();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
						
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return list;
	}
	
	/**
	 * 统计题库中总数
	 * @author huangweihua
	 * @param TrainQuestionQuery query
	 * @return int
	 */
	public int selectCountByQuery(TrainQuestionQuery query) {
		int count = 0;
		try {
			Statement criteria = new Statement("resource_train_question", "a");
			criteria.setFields("count(*) cn");
			
			// 关键词
			if(!StringUtils.isNullOrEmpty(query.getKeywords())) {
				criteria.where(or(like("a.topic", "?")));
				criteria.setStringParam("%" + query.getKeywords() + "%");
			}
			
			// 所属用户
			if (MyUtils.isNotNull(query.getUserId())) {
				criteria.where(eq("a.ownerId", "?"));
				criteria.setStringParam(query.getUserId());
			}
			
			// 题目类型
			if (query.isSingleSelection()) {
				criteria.where(eq("a.questionType", TrainQuestionItem.QUESTION_TYPE_SINGLE_SELECTION));//单选题 (默认值为1)
			} else if (query.isMulChoice()) {
				criteria.where(eq("a.questionType", TrainQuestionItem.QUESTION_TYPE_MULTIPLE_CHOICE));//多选题 (默认值为2)
			} else if (query.isMulChoice()) {
				criteria.where(eq("a.questionType", TrainQuestionItem.QUESTION_TYPE_JUDGE_TRUE_OR_FLASE));//判断题 (默认值为3)
			} else if (query.isMulChoice()) {
				criteria.where(eq("a.questionType", TrainQuestionItem.QUESTION_TYPE_BLANK_FILLING));//填空题 (默认值为4)
			} else {
				System.out.println("warnings : The questionType has no corresponding value.");
			}
			
			// 是否已经标识为已删除（del）
			if (query.getDel() == TrainQuestionItem.IS_NOT_DELETE) {
				criteria.where(eq("a.del", TrainQuestionItem.IS_NOT_DELETE));
			}else if (query.getDel() == TrainQuestionItem.IS_DELETE) {
				criteria.where(eq("a.del", TrainQuestionItem.IS_DELETE));
			}
			
			Connection conn = ThreadUtils.currentConnection();
			PreparedStatement stm = criteria.getRealStatement(conn);
			ResultSet rs = stm.executeQuery();
			rs.next();
			count = rs.getInt("cn");
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return count;
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
	 * 习题从测验中移除
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
	 * 删除一条试题
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
	
	/**
	 * 更新试题
	 * @param question
	 * @return
	 */
	public boolean update(TrainQuestionItem question) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.update(question) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 获取一次测验里的一道习题
	 * @param trainId
	 * @param index
	 * @return
	 */
	public TrainQuestionItem selectByTrainIdAndIndex(String trainId, int index) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.selectByTrainIdAndIndex(trainId, index);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}		
		return null;
	}	
	
	/**
	 * 获取一个测验里的所有习题
	 * @param trainId
	 * @return
	 */
	public List<TrainQuestionItem> selectByTrainId(String trainId) {
		TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
		try{
			if(mapper != null){
				return mapper.selectByTrainId(trainId);
			}
		}catch(Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}	
	
	/**
	 * 获取测验里的习题数
	 * @param trainId
	 * @return
	 */
	public int selectCountByTrainId(String trainId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.selectCountByTrainId(trainId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}		
		return 0;
	}	
	
}
