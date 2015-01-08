package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeItem;

/**
 * @author huangweihua
 * @date 2014年12月25日
 * @time 下午4:38:42
 */
public interface KnowledgeMapper {
	
	@Insert("insert into knowledge values( #{id}, #{code}, #{level}, #{knowledgeName}, #{subjectId}, #{gradeId}, #{week}, #{priority})")
	int insert(KnowledgeItem knowledgeItem);
	
	@Update("update knowledge set code=#{code},level=#{level},knowledgeName = #{knowledgeName}, subjectId = #{subjectId}, gradeId = #{gradeId},week=#{week},priority=#{priority} where id = #{id}")
	int update(KnowledgeItem knowledgeItem);
	
	@Delete("delete from knowledge where id = #{id}")
	int deleteById(String id);
	
	@Select("select * from knowledge where id = #{id}")
	KnowledgeItem selectById(String id);
	
	@Select("select count(*) from knowledge where knowledgeName = #{knowledgeName} and subjectId = #{subjectId} and gradeId = #{gradeId}")
	int exists(@Param("knowledgeName")String knowledgeName, @Param("subjectId")String subjectId, @Param("gradeId")String gradeId);
	
	@Select("select * from knowledge where knowledgeName = #{knowledgeName}")
	List<KnowledgeItem> selectByName(String knowledgeName);
	
	@Select("select * from knowledge where id in (${ids})")
	List<KnowledgeItem> selectByIds(@Param("ids")String ids);
	
	/**
	 * 根据gradeId（年级）、subjectId（学科），获取多个知识点
	 * @author huangweihua
	 * @param gradeId
	 * @param subjectId
	 * @return
	 */
	@Select("select * from knowledge where gradeId = #{gradeId} and subjectId = #{subjectId} order by code asc")
	List<KnowledgeItem> selectByGIdAndSId(@Param("gradeId")String gradeId, @Param("subjectId")String subjectId);
	
	@Select("select * from knowledge order by code asc")
	List<KnowledgeItem> selectAll();
	
	// 获取用户的知识图表
	@Select("select e.* from competition_attend a "
			+ "left join competition_history b on a.id=b.attendId "
			+ "left join resource_train_question c on b.questionId=c.id "
			+ "left join question_to_knowledge d on c.id=d.questionId "
			+ "right join knowledge e on d.knowledgeId=e.id "
			+ "where a.userId=#{userId} and e.subjectId=#{subjectId} and e.gradeId=#{gradeId} "
			+ "order by e.week asc")
	int selectUserKnowledgeMap(String userId, String subjectId, String gradeId);
}
