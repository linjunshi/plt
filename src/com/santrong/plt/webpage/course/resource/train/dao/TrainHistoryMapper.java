package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;


/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午3:44:18
 */
public interface TrainHistoryMapper {

	@Insert("insert into resource_train_history values(#{id}, #{userId}, #{chapterId}, #{trainId}, #{questionId}, #{answer}, #{result}, #{del}, #{cts}, #{uts})")
	int insert(TrainHistoryItem trainHistoryItem);
	
	/**
	 * 查询当前直播课程某个章节，某位学生是否已经上报过课堂作业答案的记录(del = 0)
	 * @author huangweihua
	 * @param userId
	 * @param trainId
	 * @param chapterId
	 * @param questionId
	 * @return int
	 */
	@Select("select count(*) from resource_train_history where "
			+ "userId = #{userId} and "
			+ "trainId = #{trainId} and "
			+ "chapterId = #{chapterId} and "
			+ "questionId = #{questionId} and "
			+ "del = 0")
	int exists(@Param("userId")String userId, @Param("trainId")String trainId, @Param("chapterId")String chapterId, @Param("questionId")String questionId);
	
	@Select("select a.* from resource_train_question a LEFT JOIN resource_train_to_question b on a.id = b.questionId "
			+ "LEFT JOIN resource_train c on c.id = b.trainId "
			+ "where a.chapterId = #{chapterId} and a.trainId = #{trainId} and a.questionId = #{questionId} and del = 0")
	List<TrainHistoryItem> selectByHistory(@Param("chapterId")String chapterId, @Param("trainId")String trainId, @Param("questionId")String questionId);
}
