package com.santrong.plt.webpage.competition.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.competition.entry.CompetitionAttendItem;
import com.santrong.plt.webpage.competition.entry.CompetitionHistoryItem;
import com.santrong.plt.webpage.competition.entry.CompetitionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;

/**
 * @author weinianjie
 * @date 2014年12月25日
 * @time 下午4:45:05
 */
public interface CompetitionMapper {
	
	@Insert("insert into competition_history values(#{id}, #{attendId}, #{questionId}, #{answer}, #{result}, #{cts}, #{uts})")
	int insertHistory(CompetitionHistoryItem item);
	
	@Insert("insert into competition_attend values(#{id}, #{userId}, #{competitionId}, #{cts})")
	int insertAttend(CompetitionAttendItem item);
	
	@Insert("insert into competition values(#{id}, #{title}, #{remark}, #{beginTime}, #{flag}, #{ownerId}, #{del}, #{cts}, #{uts})")
	int insert(CompetitionItem item);
	
	@Select("select a.* from competition_history a "
			+ "left join competition_attend b on a.attendId=b.id"
			+ "left join competition_to_question c on a.questionId=c.questionId and b.competitionId=c.competitionId "
			+ "where b.userId=#{userId} and b.cometitionId=#{competitionId} order by c.priority")
	List<CompetitionHistoryItem>  selectUserHistory(@Param("userId")String userId, @Param("competitionId")String competitionId);
	
	@Select("select a.* from resource_train_question a left join competition_to_question b on a.id=b.questionId where b.competitionId=#{competitionId}")
	List<TrainQuestionItem> selectQuestionsByCompetitionId(String competitionId);
	
	@Select("select count(*) from competition_attend where userId = #{userId} and competitionId is null")
	int existDoneExamByUserId(@Param("userId")String userId);
	
	@Select("select * from competition_attend where userId = #{userId} and competitionId is null limit 1")
	CompetitionAttendItem selectAttendByUserId(@Param("userId")String userId);
	
	@Select("select count(*) from competition_history where attendId = #{attendId} and questionId = #{questionId}")
	int existHistory(@Param("attendId")String attendId, @Param("questionId")String questionId);
	
	@Select("select * from competition_history where attendId = #{attendId} and questionId = #{questionId} limit 1")
	CompetitionHistoryItem selectHistoryByAttendId(@Param("attendId")String attendId, @Param("questionId")String questionId);
	
	@Update("update competition_history set "
			+ "attendId = #{attendId},"
			+ "questionId = #{questionId},"
			+ "answer = #{answer},"
			+ "result = #{result},"
			+ "uts = #{uts} "
			+ " where id = #{id}")
	int updateHistory(CompetitionHistoryItem competitionHistoryItem);
	
}
