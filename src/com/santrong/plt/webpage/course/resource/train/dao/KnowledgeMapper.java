package com.santrong.plt.webpage.course.resource.train.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeItem;

/**
 * @author weinianjie
 * @date 2014年12月25日
 * @time 下午4:38:42
 */
public interface KnowledgeMapper {
	
	//TODO 新增知识点
	@Insert("insert into knowledge values( #{id}, #{knowledgeName}, #{subjectId}, #{gradeId})")
	int insert(KnowledgeItem knowledgeItem);
	
	//TODO 修改知识点
	@Update("update knowledge set knowledgeName = #{knowledgeName}, subjectId = #{subjectId}, gradeId = #{gradeId} where id = #{id}")
	int update(KnowledgeItem knowledgeItem);
	
	//TODO 删除知识点
	@Delete("delete from knowledge where id = #{id}")
	int deleteById(String id);
	
	@Select("select * from knowledge where id = #{id}")
	KnowledgeItem selectById(String id);
	//TODO 更新知识点
	
}
