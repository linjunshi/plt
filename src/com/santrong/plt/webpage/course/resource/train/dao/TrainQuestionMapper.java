package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
