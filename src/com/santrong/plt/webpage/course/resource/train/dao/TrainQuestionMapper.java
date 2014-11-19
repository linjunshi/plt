package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;


/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午5:56:58
 */
public interface TrainQuestionMapper {

	@Insert("insert into resource_train_question values(#{id}, #{topic}, #{questionType}, #{opt1}, #{opt2}, #{opt3}, #{opt4}, #{answer}, #{ownerId}, #{del}, #{cts}, #{uts})")
	int insert(TrainQuestionItem trainQuestionItem);
	
	@Select("select * from resource_train_question where ownerId = #{userId} and del = 0")
	List<TrainQuestionItem> selectByUserId(String userId);
	
	@Select("select * from resource_train_question where id = #{id}")
	TrainQuestionItem selectById(String id);
	
	@Insert("insert into resource_train_to_question values(#{trainId},#{questionId})")
	int addQuestion2Train(@Param("questionId")String questionId, @Param("trainId")String trainId);
	
	@Delete("delete from resource_train_to_question where trainId=#{trainId} and questionId=#{questionId}")
	int removeQuestion4Train(@Param("questionId")String questionId, @Param("trainId")String trainId);
	
	@Delete("delete from resource_train_question where id = #{id}")
	int deleteById(String id);
	
	@Update("update resource_train_question set "
			+ "topic = #{topic},"
			+ "questionType = #{questionType},"
			+ "opt1 = #{opt1},"
			+ "opt2 = #{opt2},"
			+ "opt3 = #{opt3},"
			+ "opt4 = #{opt4},"
			+ "answer = #{answer},"
			+ "uts = #{uts}"
			+ " where id = #{id} ")
	int update(TrainQuestionItem question);
	
	@Select("select * from resource_train_question a "
			+ "left join resource_train_to_question b on a.id=b.questionId "
			+ "where b.trainId=#{trainId} limit ${index}, 1")
	TrainQuestionItem selectByTrainIdAndIndex(@Param("trainId")String trainId, @Param("index")int index);
	
	@Select("select count(*) from resource_train_question a "
			+ "left join resource_train_to_question b on a.id=b.questionId "
			+ "where b.trainId=#{trainId}")
	int selectCountByTrainId(@Param("trainId")String trainId);
	
	@Select("select * from resource_train_history where questionId=#{questionId} and trainId=#{trainId} and chapterId=#{chapterId} and userId=#{userId}")
	TrainHistoryItem selectInHistory2(@Param("questionId")String questionId, @Param("trainId")String trainId, @Param("chapterId")String chapterId, @Param("userId")String userId);
	
	@Select("select * from resource_train_history where id=#{id}")
	TrainHistoryItem selectInHistory(String id);
	
	@Update("update resource_train_history set answer=#{answer},result=#{result},uts=#{uts} where id=#{id}")
	int updateInHistory(TrainHistoryItem history);
	
	@Insert("insert into resource_train_history values(#{id}, #{userId}, #{chapterId}, #{trainId}, #{questionId}, answer=#{answer}, result=#{result}, del=${del}, #{cts}, #{uts})")
	int insertInHistory(TrainHistoryItem history);			
	
}
