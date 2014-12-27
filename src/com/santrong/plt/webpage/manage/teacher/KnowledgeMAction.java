package com.santrong.plt.webpage.manage.teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.resource.train.dao.KnowledgeDao;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeGradeView;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeItem;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeQuery;
import com.santrong.plt.webpage.manage.TeacherBaseAction;

@Controller
@RequestMapping("/manage/knowledge")
public class KnowledgeMAction  extends TeacherBaseAction{
	
	@RequestMapping("/list")
	public String knowledgeList() {
		try {
			int pageNum = this.getIntParameter("page");
			if(pageNum == 0) {
				pageNum = 1;
			}
			
			KnowledgeDao kDao = new KnowledgeDao();
			KnowledgeQuery query = new KnowledgeQuery();
			query.setPageSize(16);
			query.setPageNum(pageNum);
			query.setCount(kDao.selectCountByQuery(query));
			query.setOrderBy("gradeId");
			query.setOrderRule("desc");
			List<KnowledgeGradeView> knowledgeList = kDao.selectByQuery(query);
			
			HttpServletRequest request = this.getRequest();
			request.setAttribute("knowledgeList", knowledgeList);
			request.setAttribute("query", query);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/knowledgeMList";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String questionDelete(String knowledgeId){
		try {
			KnowledgeDao kDao = new KnowledgeDao();
			if (kDao.deleteById(knowledgeId)) {
				return this.redirect("/manage/knowledge/list");
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return this.redirect("/manage/knowledge/list");
	}
	
	/**
	 * 打开新增、修改知识点维护的页面
	 * @return String
	 */
	@RequestMapping(value="/addOrModifyKnowledge", method=RequestMethod.GET)
	public String addOrModifyKnowledge(){
		try {
			HttpServletRequest request = this.getRequest();
			String knowledgeId = request.getParameter("knowledgeId");
			if (MyUtils.isNull(knowledgeId)) {
				//打开新增页面
				request.setAttribute("addOrModify", "add");
			} else {
				//打开修改页面
				KnowledgeDao kDao = new KnowledgeDao();
				KnowledgeItem knowledgeItem = kDao.selectById(knowledgeId);
				request.setAttribute("knowledgeItem", knowledgeItem);
				request.setAttribute("addOrModify", "modify");
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/knowledgeMAdd";
	}
	
	/**
	 * 新增、修改一条知识点记录
	 * @author huangweihua
	 * @param knowledgeForm
	 * @return
	 */
	@RequestMapping(value="/addOrModifyKnowledge", method=RequestMethod.POST)
	public String addOrModifyKnowledge(KnowledgeItem knowledgeForm){
		try {
			HttpServletRequest request = this.getRequest();
			//打开新增页面
			request.setAttribute("addOrModify", "add");
			
			if (knowledgeForm != null) {
				
				if (MyUtils.isNull(knowledgeForm.getKnowledgeName().trim())) {
					addError("知识点名称不允许为空！");
				}
				if (MyUtils.isNull(knowledgeForm.getGradeId())) {
					addError("请您选择课程级别！");
				}
				if (MyUtils.isNull(knowledgeForm.getSubjectId())) {
					addError("请您选择课程科目！");
				}
				if (!(errorSize() > 0)) {
					if (MyUtils.isNull(knowledgeForm.getId())) {
						
						// id为空，执行新增操作
						KnowledgeDao kDao = new KnowledgeDao();
						KnowledgeItem knowledgeItem = new KnowledgeItem();
						knowledgeItem.setId(MyUtils.getGUID());
						knowledgeItem.setKnowledgeName(knowledgeForm.getKnowledgeName().trim());
						knowledgeItem.setGradeId(knowledgeForm.getGradeId());
						knowledgeItem.setSubjectId(knowledgeForm.getSubjectId());
						if (kDao.insert(knowledgeItem)) {
							addError("添加知识点成功！可以继续添加。");
							return "/manage/teacher/knowledgeMAdd";
						}
					} else {
						
						// id不为空，执行修改操作
						KnowledgeDao kDao = new KnowledgeDao();
						KnowledgeItem knowledgeItem = kDao.selectById(knowledgeForm.getId());
						knowledgeItem.setKnowledgeName(knowledgeForm.getKnowledgeName().trim());
						knowledgeItem.setGradeId(knowledgeForm.getGradeId());
						knowledgeItem.setSubjectId(knowledgeForm.getSubjectId());
						if(kDao.update(knowledgeItem)){
							return this.redirect("/manage/knowledge/list");
						}
					}
				} else {
					request.setAttribute("knowledgeItem", knowledgeForm);
				}
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return "/manage/teacher/knowledgeMAdd";
	}
}
