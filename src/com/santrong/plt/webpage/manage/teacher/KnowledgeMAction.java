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
import com.santrong.plt.opt.grade.GradeDefine;
import com.santrong.plt.opt.grade.GradeLevelEntry;
import com.santrong.plt.opt.grade.GradeSubjectEntry;
import com.santrong.plt.util.MyUtils;
import com.santrong.plt.webpage.course.resource.train.dao.KnowledgeDao;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeGradeView;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeItem;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeQuery;
import com.santrong.plt.webpage.course.resource.train.entry.KnowledgeTreeForm;
import com.santrong.plt.webpage.home.dao.LessonUnitDao;
import com.santrong.plt.webpage.home.entry.LessonUnitItem;
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
				request.setAttribute("operation", "add");
			} else {
				//打开修改页面
				KnowledgeDao kDao = new KnowledgeDao();
				KnowledgeItem knowledgeItem = kDao.selectById(knowledgeId);
				request.setAttribute("knowledgeItem", knowledgeItem);
				request.setAttribute("operation", "modify");
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
			request.setAttribute("operation", "add");
			
			if (knowledgeForm != null) {
				
				if (MyUtils.isNull(knowledgeForm.getKnowledgeName().trim())) {
					addError("知识点名称不允许为空 ！");
				}
				if (MyUtils.isNull(knowledgeForm.getGradeId())) {
					addError("请您选择课程级别 ！");
				}
				if (MyUtils.isNull(knowledgeForm.getSubjectId())) {
					addError("请您选择课程科目 ！");
				}
//				if (knowledgeForm.getWeek() > 0) {
//					addError("请您选择周 ！");
//				}
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
//							knowledgeItem.setWeek(knowledgeForm.getWeek());
							if (kDao.insert(knowledgeItem)) {
								addError("添加知识点成功 ！可以继续添加。");
								return "/manage/teacher/knowledgeMAdd";
							}
						} else {
							addError("该知识点已经存在 ！");
							return "/manage/teacher/knowledgeMAdd";
						}
					} else {
						
						// id不为空，执行修改操作
						KnowledgeDao kDao = new KnowledgeDao();
						KnowledgeItem knowledgeItem = kDao.selectById(knowledgeForm.getId());
						if (kDao.existsByName(knowledgeItem)) {
							addError("该知识点已经存在 ！");
							request.setAttribute("knowledgeItem", knowledgeForm);
							request.setAttribute("operation", "modify");
							return "/manage/teacher/knowledgeMAdd";
						}
						knowledgeItem.setKnowledgeName(knowledgeForm.getKnowledgeName().trim());
						knowledgeItem.setGradeId(knowledgeForm.getGradeId());
						knowledgeItem.setSubjectId(knowledgeForm.getSubjectId());
//						knowledgeItem.setWeek(knowledgeForm.getWeek());
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
		
		List<KnowledgeTreeForm> kTreeList = new ArrayList<KnowledgeTreeForm>();
		List<KnowledgeTreeForm> levelTreeList = new ArrayList<KnowledgeTreeForm>();
		List<KnowledgeTreeForm> subjectTreeList = new ArrayList<KnowledgeTreeForm>();
		List<KnowledgeTreeForm> termTreeList = new ArrayList<KnowledgeTreeForm>();
		List<KnowledgeTreeForm> unitTreeList = new ArrayList<KnowledgeTreeForm>();
		List<KnowledgeTreeForm> knowledgeTreeList = new ArrayList<KnowledgeTreeForm>();
		
		// 小学 根节点
		KnowledgeTreeForm item1 = new KnowledgeTreeForm();
		item1.setId(KnowledgeTreeForm.gradeEnNames[0]);
		item1.setpId("");
		item1.setName(KnowledgeTreeForm.gradeNames[0]);
		item1.setType(KnowledgeTreeForm.type_studySection);//类型为 学段：xiaoxue
		item1.setIconSkin(KnowledgeTreeForm.pIconRoot);
		item1.setCode(item1.getGradeCode(KnowledgeTreeForm.gradeEnNames[0]));// 1
		item1.setOpen(true);
		item1.setParent(true);
		kTreeList.add(item1);
		
		// 年级
		List<GradeLevelEntry>  levelList = GradeDefine.getByGradeEnName(KnowledgeTreeForm.gradeEnNames[0]).getGradeLevelList();
		for(GradeLevelEntry entry : levelList){
			KnowledgeTreeForm item = new KnowledgeTreeForm();
			item.setId(entry.getLevelId());
			item.setpId(KnowledgeTreeForm.gradeEnNames[0]);
			item.setName(entry.getLevelName());
			item.setCode(KnowledgeTreeForm.gradeCodes[0] + item.getLevelCode(entry.getLevelEnName()));// 11 - 16
			item.setType(KnowledgeTreeForm.type_level);//类型为 年级
			item.setOpen(true);
			item.setParent(true);
			levelTreeList.add(item);
		}
		
		// 学科
		List<GradeSubjectEntry>  subjectList = GradeDefine.getByGradeEnName(KnowledgeTreeForm.gradeEnNames[0]).getGradeSubjectList();
		for(KnowledgeTreeForm k : levelTreeList){
			for(GradeSubjectEntry entry : subjectList){
				KnowledgeTreeForm item = new KnowledgeTreeForm();
				item.setId(k.getId() + "_" + entry.getSubjectId());
				item.setpId(k.getId());
				item.setName(entry.getSubjectName());
				item.setCode(k.getCode() + item.getSubjectCode(entry.getSubjectEnName()));// 111 - 168
				item.setType(KnowledgeTreeForm.type_subject);//类型为 学科
				item.setOpen(true);
				item.setParent(true);
				subjectTreeList.add(item);					
			}		
		}
		
		// 学期
		LessonUnitDao unitDao = new LessonUnitDao();
		List<LessonUnitItem> termList = unitDao.selectAllTerm();
		if (termList != null && termList.size() > 0 && subjectTreeList != null && subjectTreeList.size() > 0) {
			for(KnowledgeTreeForm k : subjectTreeList){
				for(LessonUnitItem  term : termList){
					String[] suid = k.getId().split("_");
					if(term.getGradeId().equals(suid[0]) && term.getSubjectId().equals(suid[1])) {
						KnowledgeTreeForm item = new KnowledgeTreeForm();					
						item.setId(k.getId() + "_" + term.getTerm());
						item.setpId(k.getId());
						item.setName(term.getTermCnName());
						item.setCode(k.getCode() + term.getTerm());// 1111 - 1682
						item.setType(KnowledgeTreeForm.type_term);//类型为 学期
						item.setOpen(true);
						item.setParent(true);
						termTreeList.add(item);
					}
				}
			}
		}
		
		// 单元
		List<LessonUnitItem> unitList = unitDao.selectAll();
		if (unitList != null && unitList.size() > 0 && termTreeList != null && termTreeList.size() > 0) {
			for(KnowledgeTreeForm k : termTreeList){
				for(int i = 0; i < unitList.size(); i++) {
					LessonUnitItem unit = unitList.get(i);
					String[] suid = k.getId().split("_");
					if(unit.getGradeId().equals(suid[0]) && unit.getSubjectId().equals(suid[1]) && String.valueOf(unit.getTerm()).equals(suid[2])) {
						KnowledgeTreeForm item = new KnowledgeTreeForm();
						item.setId(unit.getId());
						item.setpId(k.getId());
						item.setName(unit.getUnitName());
						item.setCode(k.getCode());// 1111 - 1682 ，就是到学期的code
						item.setType(KnowledgeTreeForm.type_unit);//类型为 单元
						item.setOpen(false);
						item.setParent(true);
						unitTreeList.add(item);
						
						unitList.remove(i--);//移除当前已经绑定的单元节点，减少循环次数
					}
				}
			}
		}
		
		// 知识点
		KnowledgeDao kDao = new KnowledgeDao();
		List<KnowledgeItem> kItemList = kDao.selectAll();
		if (kItemList != null && kItemList.size() > 0) {
			for (KnowledgeItem kItem : kItemList) {
				KnowledgeTreeForm item = new KnowledgeTreeForm();
				// 常用固定的属性
				item.setId(String.valueOf(kItem.getCode()));
				if (kItem.getLevel() == 1) {
					item.setpId(kItem.getUnitId());
				} else {
					item.setpId(String.valueOf(MyUtils.getParentCode(kItem.getCode())));
				}
				item.setName(kItem.getKnowledgeName());
				item.setLevel(kItem.getLevel());
				item.setGradeId(kItem.getGradeId());
				item.setSubjectId(kItem.getSubjectId());

				item.setPriority(kItem.getPriority());
				item.setDataId(kItem.getId());//用来保存原来的ID
				
				// 扩展属性
//					if (kItem.getLevel() < 4) {
//						item.setOpen(true);//是否展开树 true or false
//					}
				item.setType(KnowledgeTreeForm.type_knowledge);//类型为 知识点
				knowledgeTreeList.add(item);
			}
		}
		
		kTreeList.addAll(levelTreeList);
		kTreeList.addAll(subjectTreeList);
		kTreeList.addAll(termTreeList);
		kTreeList.addAll(unitTreeList);
		kTreeList.addAll(knowledgeTreeList);

		Gson gson = new Gson();
		String fk = gson.toJson(kTreeList);
		Log.debug("sheep================:" + fk);
		return fk;
		
	}
	
	@RequestMapping(value="/addKnowledgeTree",method=RequestMethod.GET)
	public String addKnowledgeTree() {
		HttpServletRequest request = this.getRequest();
		String gradeId = request.getParameter("gradeId");
		String subjectId = request.getParameter("subjectId");
		String parentId = request.getParameter("parentId");
		String level = request.getParameter("level");
		String dataId = request.getParameter("dataId");//原来数据库里的ID
		String addOrEdit = request.getParameter("addOrEdit");//新增还是修改
		KnowledgeDao kDao = new KnowledgeDao();
		KnowledgeItem parentItem = kDao.selectById(parentId);
		KnowledgeItem knowledgeItem = null;
		
		if ("add".equalsIgnoreCase(addOrEdit)) {
			//打开新增页面
			knowledgeItem = new KnowledgeItem();
			knowledgeItem.setGradeId(gradeId);
			knowledgeItem.setSubjectId(subjectId);
			
		} else {
			//打开修改页面
			knowledgeItem = kDao.selectById(dataId);
		}
		request.setAttribute("knowledgeItem", knowledgeItem);
		request.setAttribute("parentName", parentItem.getKnowledgeName());
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
//			int week = this.getIntParameter("week");
			String addOrEdit = request.getParameter("addOrEdit");//新增还是修改
			
			if (MyUtils.isNull(knowledgeName)) {
				return "知识点名称不允许为空 ！";
			}
			if (MyUtils.isNull(gradeId) || "null".equalsIgnoreCase(gradeId)) {
				return "请您选择课程级别 ！";
			}
			if (MyUtils.isNull(subjectId) || "null".equalsIgnoreCase(subjectId)) {
				return "请您选择课程科目 ！";
			}
//			if (!(week > 0)) {
//				return "请您选择周 ！";
//			}
			KnowledgeDao kDao = new KnowledgeDao();
			if (kDao.exists(knowledgeName, gradeId, subjectId)) {
				return "亲，该知识点已经存在了哦 ！";
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
//					kItem.setWeek(week);
					kItem.setPriority(getMaxPriority(pkItem.getCode(), pkItem.getLevel()) + 1);//待完善
					if (kDao.insert(kItem)) {
						// 把新增的节点，转成JSON对象传给前端，并动态添加节点
						KnowledgeTreeForm kTreeView = new KnowledgeTreeForm();
//						kTreeView.setId(kItem.getCode());
//						kTreeView.setpId(pkItem.getCode());
						kTreeView.setName(knowledgeName);
						kTreeView.setLevel(kItem.getLevel());
						kTreeView.setGradeId(kItem.getGradeId());
						kTreeView.setSubjectId(kItem.getSubjectId());
//						kTreeView.setWeek(kItem.getWeek());
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
//					pkItem.setWeek(week);
					if (kDao.update(pkItem)) {
						KnowledgeTreeForm kTreeView = new KnowledgeTreeForm();
//						kTreeView.setId(pkItem.getCode());
//						kTreeView.setpId(MyUtils.getParentCode(pkItem.getCode()));
						kTreeView.setName(knowledgeName);
						kTreeView.setLevel(pkItem.getLevel());
						kTreeView.setGradeId(gradeId);
						kTreeView.setSubjectId(subjectId);
//						kTreeView.setWeek(week);
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
