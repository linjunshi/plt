package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainToQuestionItem;
import com.santrong.plt.webpage.manage.superman.entry.KnowledgeQuestionView;


/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午5:56:58
 */
public interface TrainQuestionMapper {

	@Insert("insert into resource_train_question values(#{id}, #{topic}, #{questionType}, #{answer}, #{remark}, #{subjectId}, #{gradeId}, #{unitId}, #{timeLimit}, #{ownerId}, #{level},#{status}, #{cts}, #{uts})")
	int insert(TrainQuestionItem trainQuestionItem);
	
	@Select("select * from resource_train_question where ownerId = #{userId} and status != 100")
	List<TrainQuestionItem> selectByUserId(String userId);	
	
	@Select("select * from resource_train_question where id = #{id}")
	TrainQuestionItem selectById(String id);
	
	@Select("select * from resource_train_question where id in (${ids})")
	List<TrainQuestionItem> selectByIds(@Param("ids")String ids);
	
	@Insert("select max(priority) from resource_train_to_question where questionId=#{questionId} and trainId=#{trainId}")
	int selectMaxPriority(@Param("questionId")String questionId, @Param("trainId")String trainId);
	
	@Insert("insert into resource_train_to_question values(#{trainId}, #{questionId}, #{priority})")
	int addQuestion2Train(@Param("questionId")String questionId, @Param("trainId")String trainId, @Param("priority")int priority);
	
	@Delete("delete from resource_train_to_question where trainId=#{trainId} and questionId=#{questionId}")
	int removeQuestion4Train(@Param("questionId")String questionId, @Param("trainId")String trainId);
	
	@Delete("delete from resource_train_to_question where trainId = #{trainId}")
	int removeAllQuestion4Train(String trainId);
	
//	@Delete("delete from resource_train_question where id = #{id}")
	@Update("update resource_train_question set status = 100 where id = #{id}")
	int deleteById(String id);
	
	@Update("update resource_train_question set "
			+ "topic = #{topic},"
			+ "questionType = #{questionType},"
			+ "answer = #{answer},"
			+ "remark = #{remark},"
			+ "subjectId = #{subjectId},"
			+ "gradeId = #{gradeId},"
			+ "unitId = #{unitId},"
			+ "timeLimit = #{timeLimit},"
			+ "level = #{level},"
			+ "status = #{status},"
			+ "uts = #{uts}"
			+ " where id = #{id} ")
	int update(TrainQuestionItem question);
	
	@Select("select * from resource_train_question a "
			+ "left join resource_train_to_question b on a.id=b.questionId "
			+ "where b.trainId=#{trainId} order by b.priority limit ${index}, 1")
	TrainQuestionItem selectByTrainIdAndIndex(@Param("trainId")String trainId, @Param("index")int index);	
	
	@Select("select a.* from resource_train_question a left join resource_train_to_question b on a.id=b.questionId where b.trainId=#{trainId} order by b.priority")
	List<TrainQuestionItem> selectByTrainId(String trainId);	
	
	@Select("select count(*) from resource_train_question a "
			+ "left join resource_train_to_question b on a.id=b.questionId "
			+ "where b.trainId=#{trainId}")
	int selectCountByTrainId(@Param("trainId")String trainId);

	/**
	 * 获取作业已经绑定的习题列表
	 * @author huangweihua
	 * @tablename resource_train_to_question 作业关联作业习题表
	 * @param trainId
	 * @return
	 */
	@Select("select * from resource_train_to_question where trainId = #{trainId}")
	List<TrainToQuestionItem> selectTrain2QuestionByTrainId(String trainId);

	/**
	 * 试题绑定知识点
	 * @tablename question_to_knowledge 试题关联知识点表
	 * @param questionId
	 * @param knowledgeId
	 * @return
	 */
	@Insert("insert into question_to_knowledge values(#{questionId}, #{knowledgeId})")
	int addKnowledge2Question(@Param("questionId")String questionId, @Param("knowledgeId")String knowledgeId);
	
	/**
	 * 判断是否存在试题已经绑定知识点
	 * @tablename question_to_knowledge 试题关联知识点表
	 * @param questionId
	 * @param knowledgeId
	 * @return
	 */
	@Select("select count(*) from question_to_knowledge where questionId = #{questionId}, knowledgeId = #{knowledgeId}")
	int existsKnowledge2Question(@Param("questionId")String questionId, @Param("knowledgeId")String knowledgeId);
	
	/**
	 * 移除试题已经绑定知识点的记录
	 * @tablename question_to_knowledge 试题关联知识点表
	 * @param questionId
	 * @param knowledgeId
	 * @return
	 */
	@Delete("delete from question_to_knowledge where questionId = #{questionId}, knowledgeId = #{knowledgeId}")
	int removeKnowledge4Question(@Param("questionId")String questionId, @Param("knowledgeId")String knowledgeId);
	
	@Delete("delete from question_to_knowledge where questionId = #{questionId}")
	int removeAllKnowledge4Question(@Param("questionId")String questionId);
	
	/**
	 * 获取试题已经绑定的知识点列表
	 * @author huangweihua
	 * @tablename question_to_knowledge 试题关联知识点表
	 * @tablename knowledge 知识点表  
	 * @param questionId
	 * @return List<KnowledgeQuestionView>
	 */
	@Select("select a.knowledgeName, a.subjectId, a.gradeId, b.*  from knowledge a RIGHT JOIN question_to_knowledge b on a.id = b.knowledgeId where b.questionId = #{questionId} order by a.code asc,a.priority")
	List<KnowledgeQuestionView> selectKnowledge2QuestionByQId(String questionId);
	
	/**
	 * 获取当前微课中，含有相同知识点的试题(可以设置查询条数)
	 * @author huangweihua
	 * @param weikeId 微课的ID
	 * @param index （limit index,pageSize，指记录开始的index，从0开始，表示第一条记录；pageCount是指从第index+1条开始，取pageCount条。）
	 * @param pageSize 查询条数
	 * @return List<TrainQuestionItem>
	 */
	@Select("select d.* from resource_train_question d where d.id in ("
			+ "select DISTINCT c.questionId from question_to_knowledge c where c.knowledgeId in ("
			+ "select a.knowledgeId from course_to_knowledge a LEFT JOIN course b on a.courseId = b.id where id = #{weikeId}"
			+ ")"
			+ ") and d.status = 1 order by d.level ,d.cts desc limit ${index}, ${pageSize}")
	List<TrainQuestionItem> selectQuestionBySameKnowledges(@Param("weikeId")String weikeId, @Param("index")int index, @Param("pageSize")int pageSize);
	
	@Select("select count(*) from resource_train_question d where d.id in ("
			+ "select DISTINCT c.questionId from question_to_knowledge c where c.knowledgeId in ("
			+ "select a.knowledgeId from course_to_knowledge a LEFT JOIN course b on a.courseId = b.id where id = #{weikeId}"
			+ ")"
			+ ") and d.status = 1")
	int selectCountByWeikeId(String weikeId);
	
}
