<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>

	<div class="form_knowledge">

			<c:if test="${tipError != null && fn:length(tipError)  > 0}">
				<div class="system_tip">
					<c:forEach items="${tipError}" var="tip">
						<p>${tip.msg}</p>
					</c:forEach>
				</div>
			</c:if>
			<form method="post" action="${ctx}/manage/knowledge/addOrModifyKnowledge" class="form_info common_form" id="knowledge_form">
				<input type="hidden" name="id" id="id"  value="${knowledgeItem.id}"/>
				<input type="hidden" name="level" id="level" value="${level}"/>
				<input type="hidden" name="dataId" id="dataId" value="${dataId}"/>
				<input type="hidden" name="addOrEdit" id="addOrEdit" value="${addOrEdit}"/>
				
				<div class="form_item">
					<label for="level">父级节点：</label>
					<div class="form_field">
						<input class="form_text" id="parentName" name="parentName" type="text" value="${parentName}" disabled="disabled">
					 </div>
				</div>
				
				<input id="levelId" name="levelId" type="hidden" value="${knowledgeItem.gradeId}">
				<input id="oldSubjectId" name="oldSubjectId" type="hidden" value="${knowledgeItem.subjectId}">
				<div class="form_item">
					<label for="gradeId">知识点分类：</label>
					<div class="form_field">
						<select id="gradeSelect">
							<c:forEach items="${applicationScope.gradeList}" var="grade" varStatus="st">
							<option value="${grade.gradeEnName}">${grade.gradeName}</option>
							</c:forEach>
						</select>
						<select name="gradeId" id="levelSelect">
						</select>
						<select name="subjectId" id="subjectSelect">
						</select>
						<span class="not-empty" title='此项为必填项'>*</span>
					 </div>
				</div>
				<div class="form_item">
					<label for="knowledgeName">知识点名称：</label>
					<div class="form_field">
						<input class="form_text" id="knowledgeName" name="knowledgeName" type="text" value="${knowledgeItem.knowledgeName}" required>
						<span class="not-empty" title='此项为必填项'>*</span>
					</div>
				</div>
				<div class="form_action">
					<%-- <input class="btn_question" type="submit" value="提交" /> 
					<a class="btn_question" href="${ctx}/manage/knowledge/list">取消</a> --%>
					<a href="javascript:void(0);" class="sure">确定</a>
					<a href="javascript:void(0);" class="close">取消</a>
				</div>
			</form>
	</div>
