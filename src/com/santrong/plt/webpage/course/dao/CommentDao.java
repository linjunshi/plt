package com.santrong.plt.webpage.course.dao;

import java.util.List;

import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.course.entry.CommentItem;

/**
 * @author weinianjie
 * @date 2014年10月20日
 * @time 下午12:10:43
 */
public class CommentDao extends BaseDao {

	/**
	 * 根据课程ID获取评论
	 * @param courseId
	 * @return
	 */
	public List<CommentItem> selectByCourseId(String courseId) {
		CommentMapper mapper = this.getMapper(CommentMapper.class);
		if(mapper != null) {
			return mapper.selectByCourseId(courseId);
		}
		return null;
	}
	
	/**
	 * 新增一条课程评论记录
	 * @param id，userId，courseId，remark，cts，uts
	 * @return
	 */
	public int insert(CommentItem commentItem){
		CommentMapper mapper = this.getMapper(CommentMapper.class);
		if(mapper != null) {
			return mapper.insert(commentItem);
		}
		return 0;
	}
	
	/**
	 * 删除一条课程评论记录
	 * @param id
	 * @return
	 */
	public int delete(String id){
		CommentMapper mapper = this.getMapper(CommentMapper.class);
		if(mapper != null) {
			return mapper.delete(id);
		}
		return 0;
	}
}
