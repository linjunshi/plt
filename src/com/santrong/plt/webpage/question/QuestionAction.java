package com.santrong.plt.webpage.question;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.santrong.plt.log.Log;
import com.santrong.plt.system.Global;
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
			
			// 从session中获知已选择题目
			HttpServletRequest request = this.getRequest();
			@SuppressWarnings("unchecked")
			HashSet<String> map = (HashSet<String>)request.getSession().getAttribute(Global.SessionKey_Assemble_Question);
			if(map == null) {
				map = new HashSet<String>();
			}
			Iterator<String> iter = map.iterator();
			while(iter.hasNext()) {
				String qid = iter.next();
				for(int i=0;i<questionList.size();i++) {
					if(questionList.get(i).getId().equals(qid)) {
						questionList.get(i).setAssemble(true);
						break;
					}
				}
			}
			
			request.setAttribute("questionList", questionList);
			request.setAttribute("query", query);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		
		return "question/index";
	}
	
	// 选题或者取消选题
	@SuppressWarnings("unchecked")
	@RequestMapping("/assembleQuestion")
	@ResponseBody
	public String assembleQuestion(String qid) {
		String rt = "";
		try{
			HashSet<String> map = (HashSet<String>)this.getRequest().getSession().getAttribute(Global.SessionKey_Assemble_Question);
			if(map == null) {
				map = new HashSet<String>();
			}
			if(map.contains(qid)) {
				map.remove(qid);
				rt = "remove";
			}else{
				map.add(qid);
				rt = "add";
			}
			this.getRequest().getSession().setAttribute(Global.SessionKey_Assemble_Question, map);
		}catch(Exception e) {
			Log.printStackTrace(e);
			return FAIL;
		}
		return rt;
	}
	
	// 开始做题
	@RequestMapping("begin")
	public String begin() {
		
		return "question/begin";
	}
}
