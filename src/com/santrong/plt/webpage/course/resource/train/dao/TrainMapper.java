package com.santrong.plt.webpage.course.resource.train.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.santrong.plt.webpage.course.resource.train.entry.TrainItem;


/**
 * @author huangweihua
 * @date   2014年11月6日 
 * @time   下午5:07:37
 */
public interface TrainMapper {

	@Insert("insert into resource_train values(#{id}, #{trainName}, #{ownerId}, #{del}, #{cts}, #{uts})")
	int insert(TrainItem trainItem);
	
	/**
	 * 查询所属用户的所有作业
	 * @author huangweihua
	 * @param userId ,del = 0
	 * @return List<TrainItem>
	 */
	@Select("select * from resource_train where ownerId = #{userId} and del = 0")
	List<TrainItem> selectByUserId(@Param("userId")String userId);
	
	@Select("select a.* from resource_train a "
			+ "inner join course_chapter_to_resource b on a.id=b.resourceId "
			+ "inner join course_chapter_to_resource c on b.chapterId=c.chapterId and c.resourceType=4 "
			+ "inner join resource_live d on c.resourceId=d.id "
			+ "where d.id=#{liveId} and a.title='随堂作业'")
	TrainItem selectByLiveId(String liveId);
}
