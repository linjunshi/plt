package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainHistoryView;


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
	
	@Select("select * from resource_train_history where id=#{id}")
	TrainHistoryItem selectById(String id);		
	
	@Select("select * from resource_train_history where questionId=#{questionId} and userId=#{userId} and trainId=#{trainId} and chapterId=#{chapterId}")
	TrainHistoryItem selectByUnique(@Param("questionId")String questionId, @Param("trainId")String trainId, @Param("chapterId")String chapterId, @Param("userId")String userId);	
	
	@Select("select a.id historyId, a.userId, a.trainId, a.questionId, a.chapterId, a.answer historyAnswer, a.result, a.cts, a.uts, b.topic, "
			+ "b.questionType, b. answer questionAnswer, b.opt1, b.opt2, b.opt3, b.opt4 "
			+ "from resource_train_history a "
			+ "left join resource_train_question b on a.questionId=b.id "
			+ "where a.userId=#{userId} and a.trainId=#{trainId} and a.chapterId=#{chapterId}")
	List<TrainHistoryView>  selectUserDoneDetail(@Param("userId")String userId, @Param("trainId")String trainId, @Param("chapterId")String chapterId);
	
	@Select("select a.* from resource_train_history a "
			+ "left join resource_train_to_question b on a.questionId=b.questionId and a.trainId=b.trainId "
			+ "where a.userId=#{userId} and a.trainId=#{trainId} and a.chapterId=#{chapterId} order by b.priority")
	List<TrainHistoryItem>  selectUserHistory(@Param("userId")String userId, @Param("trainId")String trainId, @Param("chapterId")String chapterId);
	
	@Delete("delete from resource_train_history where userId=#{userId} and trainId=#{trainId} and chapterId=#{chapterId}")
	int  deleteUserHistory(@Param("userId")String userId, @Param("trainId")String trainId, @Param("chapterId")String chapterId);
}
