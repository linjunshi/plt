package com.santrong.plt.webpage.manage.teacher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.santrong.plt.log.Log;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.resource.train.dao.KnowledgeDao;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeGradeView;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeItem;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeQuery;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeTreeForm;
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
	public String removeKnowledge(String knowledgeId){
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
				if (knowledgeForm.getWeek() > 0) {
					addError("请您选择周！");
				}
				if (!(errorSize() > 0)) {
					if (MyUtils.isNull(knowledgeForm.getId())) {
						
						// id为空，执行新增操作
						KnowledgeDao kDao = new KnowledgeDao();
						if (!kDao.exists(knowledgeForm.getKnowledgeName().trim(), knowledgeForm.getSubjectId(), knowledgeForm.getGradeId())) {
							KnowledgeItem knowledgeItem = new KnowledgeItem();
							knowledgeItem.setId(MyUtils.getGUID());
							knowledgeItem.setKnowledgeName(knowledgeForm.getKnowledgeName().trim());
							knowledgeItem.setGradeId(knowledgeForm.getGradeId());
							knowledgeItem.setSubjectId(knowledgeForm.getSubjectId());
							knowledgeItem.setWeek(knowledgeForm.getWeek());
							if (kDao.insert(knowledgeItem)) {
								addError("添加知识点成功！可以继续添加。");
								return "/manage/teacher/knowledgeMAdd";
							}
						} else {
							addError("该知识点已经存在！");
							return "/manage/teacher/knowledgeMAdd";
						}
					} else {
						
						// id不为空，执行修改操作
						KnowledgeDao kDao = new KnowledgeDao();
						KnowledgeItem knowledgeItem = kDao.selectById(knowledgeForm.getId());
						if (kDao.existsByName(knowledgeItem)) {
							addError("该知识点已经存在！");
							request.setAttribute("knowledgeItem", knowledgeForm);
							request.setAttribute("addOrModify", "modify");
							return "/manage/teacher/knowledgeMAdd";
						}
						knowledgeItem.setKnowledgeName(knowledgeForm.getKnowledgeName().trim());
						knowledgeItem.setGradeId(knowledgeForm.getGradeId());
						knowledgeItem.setSubjectId(knowledgeForm.getSubjectId());
						knowledgeItem.setWeek(knowledgeForm.getWeek());
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
	
	@RequestMapping(value="/tree", method=RequestMethod.GET)
	public String knowledgeTreeShow() {
		return "/manage/teacher/knowledgeMTree";
	}
	
	/**
	 * 获取知识树的所有节点，并转成JSON字符串格式
	 * @return
	 */
	@RequestMapping(value="/getTreeNodes", method=RequestMethod.GET)
	@ResponseBody
	public String getknowledgeTreeNodes() {
		String child = "";
		KnowledgeDao kDao = new KnowledgeDao();
		List<KnowledgeItem> kItemList = kDao.selectAll();
		List<KnowledgeTreeForm> kTreeList = new ArrayList<KnowledgeTreeForm>();
		if (kItemList != null && kItemList.size() > 0) {
			for (KnowledgeItem kItem : kItemList) {
				KnowledgeTreeForm kTree = new KnowledgeTreeForm();
				// 常用固定的属性
				kTree.setId(kItem.getCode());
				kTree.setpId(MyUtils.getParentCode(kItem.getCode()));
				kTree.setName(kItem.getKnowledgeName());
				kTree.setLevel(kItem.getLevel());
				kTree.setGradeId(kItem.getGradeId());
				kTree.setSubjectId(kItem.getSubjectId());
				kTree.setWeek(kItem.getWeek());
				kTree.setPriority(kItem.getPriority());
				kTree.setDataId(kItem.getId());//用来保存原来的ID
				
				// 扩展属性
				if (kItem.getCode() == 1000000000) {//如果是根节点（知识点），就加根节点默认显示图片
					kTree.setIconSkin(KnowledgeTreeForm.pIconRoot);
				}
				if (kItem.getLevel() < 4) {
					kTree.setOpen(true);//是否展开树 true or false
				}
				kTreeList.add(kTree);
			}
			if (kTreeList != null && kTreeList.size() > 0) {
				Gson gson = new Gson();
				child = gson.toJson(kTreeList);
			}
		}
		return child;
	}
	
	@RequestMapping(value="/addKnowledgeTree")
	public String addKnowledgeTree() {
		HttpServletRequest request = this.getRequest();
		String gradeId = request.getParameter("gradeId");
		String subjectId = request.getParameter("subjectId");
		String parentName = request.getParameter("parentName");
		String level = request.getParameter("level");
		String dataId = request.getParameter("dataId");//原来数据库里的ID
		String addOrEdit = request.getParameter("addOrEdit");//新增还是修改
		KnowledgeItem knowledgeItem = null;
		if ("add".equalsIgnoreCase(addOrEdit)) {
			//打开新增页面
			knowledgeItem = new KnowledgeItem();
			knowledgeItem.setGradeId(gradeId);
			knowledgeItem.setSubjectId(subjectId);
			
		} else {
			//打开修改页面
			KnowledgeDao kDao = new KnowledgeDao();
			knowledgeItem = kDao.selectById(dataId);
		}
		request.setAttribute("knowledgeItem", knowledgeItem);
		request.setAttribute("parentName", parentName);
		request.setAttribute("level", level);
		request.setAttribute("dataId", dataId);
		request.setAttribute("addOrEdit", addOrEdit);
		return "/manage/teacher/knowledgeMEdit";
	}
	
	// 异步提交知识点新增修改记录
	@RequestMapping(value="/submitKnowledgeBySync", method=RequestMethod.POST)
	@ResponseBody
	public String submitKnowledgeBySync(){
		try {
			HttpServletRequest request = this.getRequest();
			String dataId = request.getParameter("dataId");//原来数据库里的ID
			String gradeId = request.getParameter("gradeId");
			String subjectId = request.getParameter("subjectId");
			String knowledgeName = request.getParameter("knowledgeName").trim();
			int week = this.getIntParameter("week");
			String addOrEdit = request.getParameter("addOrEdit");//新增还是修改
			
			if (MyUtils.isNull(knowledgeName)) {
				return "知识点名称不允许为空！";
			}
			if (MyUtils.isNull(gradeId) || "null".equalsIgnoreCase(gradeId)) {
				return "请您选择课程级别！";
			}
			if (MyUtils.isNull(subjectId) || "null".equalsIgnoreCase(subjectId)) {
				return "请您选择课程科目！";
			}
			if (!(week > 0)) {
				return "请您选择周！";
			}
			KnowledgeDao kDao = new KnowledgeDao();
			if (kDao.exists(knowledgeName, gradeId, subjectId)) {
				return "亲，该知识点已经存在了哦！";
			}
			if ("add".equalsIgnoreCase(addOrEdit)) {
				//打开新增页面
				KnowledgeItem pkItem = kDao.selectById(dataId);
				if (pkItem != null) {
					KnowledgeItem kItem = new KnowledgeItem();
					kItem.setId(MyUtils.getGUID());
					kItem.setCode(getChildNodeNewCode(pkItem.getCode(), pkItem.getLevel()));//TODO 待完善
					kItem.setLevel(pkItem.getLevel() + 1);
					kItem.setKnowledgeName(knowledgeName);
					kItem.setGradeId(gradeId);
					kItem.setSubjectId(subjectId);
					kItem.setWeek(week);
					kItem.setPriority(getMaxPriority(pkItem.getCode(), pkItem.getLevel()) + 1);//待完善
					if (kDao.insert(kItem)) {
						// 把新增的节点，转成JSON对象传给前端，并动态添加节点
						KnowledgeTreeForm kTreeView = new KnowledgeTreeForm();
						kTreeView.setId(kItem.getCode());
						kTreeView.setpId(pkItem.getCode());
						kTreeView.setName(knowledgeName);
						kTreeView.setLevel(kItem.getLevel());
						kTreeView.setGradeId(kItem.getGradeId());
						kTreeView.setSubjectId(kItem.getSubjectId());
						kTreeView.setWeek(kItem.getWeek());
						kTreeView.setPriority(kItem.getPriority());
						kTreeView.setDataId(kItem.getId());
						if (kItem.getLevel() < 4) {
							kTreeView.setOpen(true);//是否展开树 true or false
						}
						Gson gson = new Gson();
						return gson.toJson(kTreeView);
					}
					
				}
			} else {
				//打开修改页面
				KnowledgeItem pkItem = kDao.selectById(dataId);
				if (pkItem != null) {
					if (pkItem.getLevel() >= 1 ) { //只有第一级才可以修改年级控件
						pkItem.setGradeId(gradeId);
						pkItem.setSubjectId(subjectId);
					}
					pkItem.setKnowledgeName(knowledgeName);
					pkItem.setWeek(week);
					if (kDao.update(pkItem)) {
						KnowledgeTreeForm kTreeView = new KnowledgeTreeForm();
						kTreeView.setId(pkItem.getCode());
						kTreeView.setpId(MyUtils.getParentCode(pkItem.getCode()));
						kTreeView.setName(knowledgeName);
						kTreeView.setLevel(pkItem.getLevel());
						kTreeView.setGradeId(gradeId);
						kTreeView.setSubjectId(subjectId);
						kTreeView.setWeek(week);
						kTreeView.setPriority(pkItem.getPriority());
						kTreeView.setDataId(pkItem.getId());
						if (pkItem.getLevel() < 4) {
							kTreeView.setOpen(true);//是否展开树 true or false
						}
						Gson gson = new Gson();
						return gson.toJson(kTreeView);
					}
				}
			}

			
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
	
	/**
	 * 获取子节点的新编码
	 * @param parentCode
	 * @param parentLevel
	 * @return int
	 */
	public int getChildNodeNewCode(int parentCode, int parentLevel) {
		int nextNodeCode = 1000000000;
		try {
			KnowledgeDao kDao = new KnowledgeDao();
			List<KnowledgeItem> kItemList = kDao.selectByCodeRange(MyUtils.getChildNodeMaxCode(parentCode), MyUtils.getChildNodeMinCode(parentCode), parentLevel + 1);
			if (kItemList != null && kItemList.size() > 0) {
				// TODO 待完善，暂时只考虑不删除中间节点的情况
				int listCount = kItemList.size();
				nextNodeCode = MyUtils.getChildNodeNewCode(parentCode, listCount);
			} else {
				nextNodeCode = MyUtils.getChildNodeNewCode(parentCode, 0);
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return nextNodeCode;
	}
	
	/**
	 * 获取子节点的新编码的最大序号maxPriority
	 * @param parentCode
	 * @param parentLevel
	 * @return int
	 */
	public int getMaxPriority(int parentCode, int parentLevel) {
		int maxPriority = 0;
		try {	
			KnowledgeDao kDao = new KnowledgeDao();
			maxPriority = kDao.selectMaxPriorityByCodeRange(MyUtils.getChildNodeMaxCode(parentCode), MyUtils.getChildNodeMinCode(parentCode), parentLevel + 1);
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return maxPriority;
	}
	
	// 异步提交知识点新增修改记录
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseBody
	public String removeKnowledgeBySync(String knowledgeId){
		try {
			KnowledgeDao kDao = new KnowledgeDao();
			if (kDao.deleteById(knowledgeId)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			Log.printStackTrace(e);
		}
		return FAIL;
	}
}
