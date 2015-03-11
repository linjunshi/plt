package com.santrong.plt.webpage.story.dao;

import java.util.List;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseDao;
import com.santrong.plt.webpage.story.entry.StoryCommentItem;
import com.santrong.plt.webpage.story.entry.StoryCommentUserView;

/**
 * @author huangweihua
 * @Date 2015年3月10日 
 * @Time 下午6:16:24
 */
public class StoryCommentDao extends BaseDao{
	
	/**
	 * 新增一条评论记录
	 * @param storyCommentItem
	 * @return
	 */
	public boolean insert(StoryCommentItem storyCommentItem){
		try {
			StoryCommentMapper mapper = this.getMapper(StoryCommentMapper.class);
			if (mapper != null) {
				return mapper.insert(storyCommentItem) > 0;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return false;
	}
	
	/**
	 * 查询评论的记录
	 * @param storyEname
	 * @return
	 */
	public List<StoryCommentUserView> selectByStoryEname(String storyEname){
		try {
			StoryCommentMapper mapper = this.getMapper(StoryCommentMapper.class);
			if (mapper != null) {
				return mapper.selectByStoryEname(storyEname);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return null;
	}
	
	/**
	 * 统计评论的人数
	 * @param storyEname
	 * @return
	 */
	public int selectCountByStoryEname(String storyEname){
		try {
			StoryCommentMapper mapper = this.getMapper(StoryCommentMapper.class);
			if (mapper != null) {
				return mapper.selectCountByStoryEname(storyEname);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return 0;
	}
}
