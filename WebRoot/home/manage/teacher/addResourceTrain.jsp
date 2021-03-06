<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>

<input type="hidden" id="courseId" name="courseId" value="${courseId}" />
<input type="hidden" id="chapterId" name="chapterId" value="${chapterId}" />
<input type="hidden" id="resourceId" name="resourceId" value="${resourceId}" />
<input type="hidden" id="resourceType" name="resourceType" value="${resourceType}" />
<div class="sh_title">
	<h2>作业试题维护</h2>
    <a href="${ctx}/manage/course/chapterEditor?courseId=${courseId}" class="sh_add">&#60;&#60;返回上级</a>
</div>
<div class="sh_collection">
	<div class="sh_col_form">
		<span>测验名：</span>
		<input class="form_text pt15" type="text" id="title" name="title" value="${train.title}" />&nbsp;<span class="not-empty" title='此项为必填项'>*</span>
	</div>
	<table border="1" class="sh_coll_tab">
		<colgroup>
			<col width="50">
			<col width="50">
			<col width="460">
			<col width="200">
			<col width="200">
		</colgroup>
		<thead>
			<tr>
				<th>选择</th>
				<th>序号</th>
				<th>试题描述</th>
				<th>试题类型</th>
				<th>创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${questionList}"  var="question" varStatus="qt">
				<tr>
					<td><input value="${question.id}" class="form_radio" id="${question.id}" name="question_checkbox" type="checkbox"></td>
					<td>${qt.index + 1 + (query.pageNum-1)*query.pageSize}</td>
					<td>${question.topic}</td>
					<td>
					<c:if test="${question.questionType==1}">单选题</c:if>
					<c:if test="${question.questionType==2}">多选题</c:if>
					<c:if test="${question.questionType==3}">判断题</c:if>
					<c:if test="${question.questionType==4}">填空题</c:if>
					</td>
					<td><fmt:formatDate value="${question.cts}" type="date" dateStyle="default" /></td>
				</tr>
			</c:forEach>
		</tbody>
		<c:if test="${fn:length(query.pageSequence) > 1}">
			<tfoot>
				<tr>
					<td colspan="5">
						<c:set var="basicUrl" value="${ctx}/manage/course/addResourceTrain?courseId=${courseId}&chapterId=${chapterId}&resourceId=${resourceId}" />
	          			<%@ include file="../../inc/pagination2.jsp"%>
					</td>
				</tr>
			</tfoot>
		</c:if>
	</table>
	<div class="form_height">
		<a class="selectTrainQuestion" id="selectTrainQuestion" href="javascript:void(0);">确定</a>
		<!-- <a class="selectTQCancel" id="selectTQCancel" href="javascript:void(0);">取消选择</a> -->
	</div>
	<input type="hidden" id="questionIds" name="questionIds" value="${questionIds}"/>
</div>        