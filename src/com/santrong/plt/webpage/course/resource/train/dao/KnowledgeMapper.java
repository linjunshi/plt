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
	@Insert("insert into knowledge values( #{id}, #{code}, #{level}, #{knowledgeName}, #{subjectId}, #{gradeId}, #{week}, #{priority})")
	int insert(KnowledgeItem knowledgeItem);
	
	//TODO 修改知识点
	@Update("update knowledge set code=#{code},level=#{level},knowledgeName = #{knowledgeName}, subjectId = #{subjectId}, gradeId = #{gradeId},week=#{week},priority=#{priority} where id = #{id}")
	int update(KnowledgeItem knowledgeItem);
	
	//TODO 删除知识点
	@Delete("delete from knowledge where id = #{id}")
	int deleteById(String id);
	
	@Select("select * from knowledge where id = #{id}")
	KnowledgeItem selectById(String id);
	//TODO 更新知识点
	
	@Select("select count(*) from knowledge where knowledgeName = #{knowledgeName} and gradeId = #{gradeId} and subjectId = #{subjectId} ")
	int exists(@Param("knowledgeName")String knowledgeName, @Param("gradeId")String gradeId, @Param("subjectId")String subjectId);
	
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
	@Select("select * from knowledge where gradeId = #{gradeId} and subjectId = #{subjectId} order by code asc,priority asc")
	List<KnowledgeItem> selectByGIdAndSId(@Param("gradeId")String gradeId, @Param("subjectId")String subjectId);
	
	@Select("select * from knowledge order by code asc, priority")
	List<KnowledgeItem> selectAll();
	
	/**
	 * 根据code范围和level树的级别，获取知识点的列表记录
	 * @param maxCode
	 * @param minCode
	 * @param level
	 * @return
	 */
	@Select("select * from knowledge where code <= #{maxCode} and code >= #{minCode} and level = #{level} order by code asc,priority asc")
	List<KnowledgeItem> selectByCodeRange(@Param("maxCode")int maxCode, @Param("minCode")int minCode, @Param("level")int level);
	
	@Select("select max(priority) from knowledge where code <= #{maxCode} and code >= #{minCode} and level = #{level} order by code asc")
	int selectMaxPriorityByCodeRange(@Param("maxCode")int maxCode, @Param("minCode")int minCode, @Param("level")int level);
	
}
