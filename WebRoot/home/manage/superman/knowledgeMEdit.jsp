<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>

	<div class="form_knowledge_edit">

			<c:if test="${tipError != null && fn:length(tipError)  > 0}">
				<div class="system_tip">
					<c:forEach items="${tipError}" var="tip">
						<p>${tip.msg}</p>
					</c:forEach>
				</div>
			</c:if>
			<form method="post" action="" class="form_info common_form" id="knowledge_form">
				<input type="hidden" id="id" name="id" value="${knowledgeItem.id}"/>
				<input type="hidden" id="gradeId" name="gradeId" value="${knowledgeItem.gradeId}">
				<input type="hidden" id="subjectId" name="subjectId" value="${knowledgeItem.subjectId}">
				<input type="hidden" id="unitId" name="unitId" value="${knowledgeItem.unitId}">
				<input type="hidden" id="type" name="type" value="${type}">
				<%-- <input type="hidden" id="level" name="level" value="${level}"> --%>
				<input type="hidden" id="code" name="code" value="${knowledgeItem.code}">
				<input type="hidden" name="dataId" id="dataId" value="${dataId}"/>
				<input type="hidden" name="addOrEdit" id="addOrEdit" value="${addOrEdit}"/>
				
				<div class="form_item">
					<label for="knowledgeName">知识点名称：</label>
					<div class="form_field">
						<input class="form_text" id="knowledgeName" name="knowledgeName" type="text" value="${knowledgeItem.knowledgeName}" required>
						<span class="not-empty" title='此项为必填项'>*</span>
					</div>
				</div>
				<div class="form_action">
					<a href="javascript:void(0);" class="sure">确定</a>
					<a href="javascript:void(0);" class="close">取消</a>
				</div>
			</form>
	</div>
