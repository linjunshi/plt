package com.santrong.plt.webpage.question;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santrong.plt.log.Log;
import com.santrong.plt.webpage.BaseAction;
import com.santrong.plt.webpage.course.resource.train.dao.TrainQuestionDao;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionItem;
import com.santrong.plt.webpage.course.resource.train.entry.TrainQuestionQuery;

/**
 * @author weinianjie
 * @date 2015年1月6日
 * @time 上午10:54:59
 */
@Controller
@RequestMapping("/question")
public class QuestionAction extends BaseAction {

	@RequestMapping("")
	public String index() {
		try {
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			TrainQuestionDao tqDao = new TrainQuestionDao();
			TrainQuestionQuery query = new TrainQuestionQuery();
			query.setPageNum(pageNum);
			query.setDel(0);
			query.setCount(tqDao.selectCountByQuery(query));
			query.setOrderBy("cts");
			List<TrainQuestionItem> questionList = tqDao.selectByQuery(query);
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("questionList", questionList);
			request.setAttribute("query", query);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		
		return "question/index";
	}
}
