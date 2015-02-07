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
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionUserView;
import com.santrong.plt.webpage.course.resource.train.entry.TrainToQuestionItem;
import com.santrong.plt.webpage.manage.superman.entry.KnowledgeQuestionView;

/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午5:56:58
 */
public class TrainQuestionDao extends BaseDao{

	//TODO 作业绑定知识点--批量（一个作业，多个知识点）
	//TODO 作业取消绑定知识点（删除某个作业绑定的所有知识点）
	
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
			
			// 所属年级
			if (MyUtils.isNotNull(query.getGradeId())) {
				criteria.where(eq("a.gradeId","?"));
				criteria.setStringParam(query.getGradeId());
			}
			
			// 所属科目
			if (MyUtils.isNotNull(query.getSubjectId())) {
				criteria.where(eq("a.subjectId","?"));
				criteria.setStringParam(query.getSubjectId());
			}
			
			// 所属单元
			if (MyUtils.isNotNull(query.getUnitId())) {
				criteria.where(eq("a.unitId","?"));
				criteria.setStringParam(query.getUnitId());
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
			
			// 未删除
			criteria.where(ne("a.status", TrainQuestionItem.Status_Del));
			
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
			
			// 所属年级
			if (MyUtils.isNotNull(query.getGradeId())) {
				criteria.where(eq("a.gradeId","?"));
				criteria.setStringParam(query.getGradeId());
			}
			
			// 所属科目
			if (MyUtils.isNotNull(query.getSubjectId())) {
				criteria.where(eq("a.subjectId","?"));
				criteria.setStringParam(query.getSubjectId());
			}
			
			// 所属单元
			if (MyUtils.isNotNull(query.getUnitId())) {
				criteria.where(eq("a.unitId","?"));
				criteria.setStringParam(query.getUnitId());
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
			
			// 未删除
			criteria.where(ne("a.status", TrainQuestionItem.Status_Del));
			
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
	
	/**
	 * 查询所有人未审核的题目(分页)
	 * @author huangweihua
	 * @param TrainQuestionQuery query
	 * @return List<TrainQuestionUserView> list
	 */
	public List<TrainQuestionUserView> selectApproveByQuery(TrainQuestionQuery query) {
		List<TrainQuestionUserView> list = new ArrayList<TrainQuestionUserView>();
		try {
			Statement criteria = new Statement("resource_train_question", "a");
			criteria.setFields("a.*,b.showName showName");
			criteria.ljoin("user", "b", "a.ownerId","b.id");
			
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
			
			// 所属年级
			if (MyUtils.isNotNull(query.getGradeId())) {
				criteria.where(eq("a.gradeId","?"));
				criteria.setStringParam(query.getGradeId());
			}
			
			// 所属科目
			if (MyUtils.isNotNull(query.getSubjectId())) {
				criteria.where(eq("a.subjectId","?"));
				criteria.setStringParam(query.getSubjectId());
			}
			
			// 所属单元
			if (MyUtils.isNotNull(query.getUnitId())) {
				criteria.where(eq("a.unitId","?"));
				criteria.setStringParam(query.getUnitId());
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
			
			// 新建的==待审核
			criteria.where(eq("a.status", TrainQuestionItem.Status_New));
			
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
				TrainQuestionUserView item = new TrainQuestionUserView();
				BeanUtils.Rs2Bean(rs, item);
				list.add(item);
			}
			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return list;
	}
	
	/**
	 * 查询所有人未审核,统计题库中总数
	 * @author huangweihua
	 * @param TrainQuestionQuery query
	 * @return int
	 */
	public int selectApproveCountByQuery(TrainQuestionQuery query) {
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
			
			// 所属年级
			if (MyUtils.isNotNull(query.getGradeId())) {
				criteria.where(eq("a.gradeId","?"));
				criteria.setStringParam(query.getGradeId());
			}
			
			// 所属科目
			if (MyUtils.isNotNull(query.getSubjectId())) {
				criteria.where(eq("a.subjectId","?"));
				criteria.setStringParam(query.getSubjectId());
			}
			
			// 所属单元
			if (MyUtils.isNotNull(query.getUnitId())) {
				criteria.where(eq("a.unitId","?"));
				criteria.setStringParam(query.getUnitId());
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
			
			// 新建的==待审核
			criteria.where(eq("a.status", TrainQuestionItem.Status_New));
			
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
	
	public List<TrainQuestionItem> selectByIds(String[] idList) {
		try {
			String ids = MyUtils.consistIds(idList);
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.selectByIds(ids);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;		
	}
	
	/**
	 * 查找最大的排序
	 * @param questionId
	 * @param trainId
	 * @param priority
	 * @return
	 */
	public int selectMaxPriority(String questionId, String trainId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.selectMaxPriority(questionId, trainId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}	
	
	/**
	 * 习题添加到作业里
	 * @param questionId
	 * @param trainId
	 * @return
	 */
	public int addQuestion2Train(String questionId, String trainId, int priority) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.addQuestion2Train(questionId, trainId, priority);
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
	 * 习题从测验中全部移除
	 * @param trainId
	 * @return
	 */
	public int removeAllQuestion4Train(String trainId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.removeAllQuestion4Train(trainId);
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
	
	/**
	 * 获取作业已经绑定的习题列表
	 * @author huangweihua
	 * @tablename resource_train_to_question 作业关联作业习题表
	 * @param trainId
	 * @return
	 */
	public List<TrainToQuestionItem> selectTrain2QuestionByTrainId(String trainId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.selectTrain2QuestionByTrainId(trainId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 试题绑定知识点
	 * @param questionId
	 * @param knowledgeId
	 * @return
	 */
	public boolean addKnowledge2Question(String questionId, String knowledgeId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.addKnowledge2Question(questionId, knowledgeId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 判断是否存在试题已经绑定知识点
	 * @param questionId
	 * @param knowledgeId
	 * @return
	 */
	public boolean existsKnowledge2Question(String questionId, String knowledgeId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.existsKnowledge2Question(questionId, knowledgeId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 移除试题绑定的所有知识点
	 * @param questionId
	 * @return
	 */
	public boolean removeAllKnowledge4Question(String questionId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.removeAllKnowledge4Question(questionId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 移除试题绑定的知识点
	 * @param questionId
	 * @param knowledgeId
	 * @return
	 */
	public boolean removeKnowledge4Question(String questionId, String knowledgeId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.removeKnowledge4Question(questionId, knowledgeId) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 获取试题已经绑定的知识点列表
	 * @author huangweihua
	 * @tablename question_to_knowledge 试题关联知识点表
	 * @tablename knowledge 知识点表  
	 * @param questionId
	 * @return
	 */
	public List<KnowledgeQuestionView> selectKnowledge2QuestionByQId(String questionId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if (mapper != null) {
				return mapper.selectKnowledge2QuestionByQId(questionId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 获取当前微课中，含有相同知识点的试题(可以设置查询条数)
	 * @author huangweihua
	 * @param weikeId 微课的ID
	 * @param index （limit index,pageSize，指记录开始的index，从0开始，表示第一条记录；pageSize是指从第index+1条开始，取pageSize条。）
	 * @param pageSize 查询条数
	 * @return List<TrainQuestionItem>
	 */
	public List<TrainQuestionItem> selectQuestionBySameKnowledges(String weikeId, int index, int pageSize) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if(mapper != null) {
				return mapper.selectQuestionBySameKnowledges(weikeId, index, pageSize);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 获取当前微课中，含有相同知识点的试题(可以设置查询条数)的总数记录
	 * @author huangweihua
	 * @param weikeId 微课的ID
	 * @return int
	 */
	public int selectCountByWeikeId(String weikeId) {
		try {
			TrainQuestionMapper mapper = this.getMapper(TrainQuestionMapper.class);
			if(mapper != null) {
				return mapper.selectCountByWeikeId(weikeId);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
}
