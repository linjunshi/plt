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
	
	//TODO 新增知识点
	@Insert("insert into knowledge values( #{id}, #{level}, #{knowledgeName}, #{subjectId}, #{gradeId}, #{week})")
	int insert(KnowledgeItem knowledgeItem);
	
	//TODO 修改知识点
	@Update("update knowledge set level=#{level},knowledgeName = #{knowledgeName}, subjectId = #{subjectId}, gradeId = #{gradeId},week=#{week} where id = #{id}")
	int update(KnowledgeItem knowledgeItem);
	
	//TODO 删除知识点
	@Delete("delete from knowledge where id = #{id}")
	int deleteById(String id);
	
	@Select("select * from knowledge where id = #{id}")
	KnowledgeItem selectById(String id);
	//TODO 更新知识点
	
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
	@Select("select * from knowledge where gradeId = #{gradeId} and subjectId = #{subjectId}")
	List<KnowledgeItem> selectByGIdAndSId(@Param("gradeId")String gradeId, @Param("subjectId")String subjectId);
}
