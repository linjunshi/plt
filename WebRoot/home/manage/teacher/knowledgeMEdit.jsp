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
					<label for="week">所属周：</label>
					<div class="form_field">
						<select name="week" size="1" id="week" class="inline_ele">
							<option <c:if test="${knowledgeItem.week==1}">selected="selected"</c:if> value="1">第一周</option>
							<option <c:if test="${knowledgeItem.week==2}">selected="selected"</c:if> value="2">第二周</option>
							<option <c:if test="${knowledgeItem.week==3}">selected="selected"</c:if> value="3">第三周</option>
							<option <c:if test="${knowledgeItem.week==4}">selected="selected"</c:if> value="4">第四周</option>
							<option <c:if test="${knowledgeItem.week==5}">selected="selected"</c:if> value="5">第五周</option>
							<option <c:if test="${knowledgeItem.week==6}">selected="selected"</c:if> value="6">第六周</option>
							<option <c:if test="${knowledgeItem.week==7}">selected="selected"</c:if> value="7">第七周</option>
							<option <c:if test="${knowledgeItem.week==8}">selected="selected"</c:if> value="8">第八周</option>
							<option <c:if test="${knowledgeItem.week==9}">selected="selected"</c:if> value="9">第九周</option>
							<option <c:if test="${knowledgeItem.week==10}">selected="selected"</c:if> value="10">第十周</option>
							<option <c:if test="${knowledgeItem.week==11}">selected="selected"</c:if> value="11">第十一周</option>
							<option <c:if test="${knowledgeItem.week==12}">selected="selected"</c:if> value="12">第十二周</option>
							<option <c:if test="${knowledgeItem.week==13}">selected="selected"</c:if> value="13">第十三周</option>
							<option <c:if test="${knowledgeItem.week==14}">selected="selected"</c:if> value="14">第十四周</option>
							<option <c:if test="${knowledgeItem.week==15}">selected="selected"</c:if> value="15">第十五周</option>
							<option <c:if test="${knowledgeItem.week==16}">selected="selected"</c:if> value="16">第十六周</option>
							<option <c:if test="${knowledgeItem.week==17}">selected="selected"</c:if> value="17">第十七周</option>
							<option <c:if test="${knowledgeItem.week==18}">selected="selected"</c:if> value="18">第十八周</option>
							<option <c:if test="${knowledgeItem.week==19}">selected="selected"</c:if> value="19">第十九周</option>
							<option <c:if test="${knowledgeItem.week==20}">selected="selected"</c:if> value="20">第二十周</option>
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
