<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<h2>${question.typeString}</h2>
<div class="sh_work_rad">
	<h3><span class="current_index">1</span> 、${question.topic}</h3>
	<p> 
		<span>请作答：</span>
		<c:if test="${question.singleSelection}">
		<!-- 单选题 -->
			<label><input name="answer" type="radio" value="1" class="sh_work_de" /> A</label>
			<label><input name="answer" type="radio" value="2" class="sh_work_de" /> B</label>
			<label><input name="answer" type="radio" value="4" class="sh_work_de" /> C</label>
			<label><input name="answer" type="radio" value="8" class="sh_work_de" /> D</label>
		</c:if>
		<c:if test="${question.mulChoice}">
		<!-- 多选题 -->
			<label><input name="answer" type="checkbox" value="1" class="sh_work_de" /> A</label>
			<label><input name="answer" type="checkbox" value="2" class="sh_work_de" /> B</label>
			<label><input name="answer" type="checkbox" value="4" class="sh_work_de" /> C</label>
			<label><input name="answer" type="checkbox" value="8" class="sh_work_de" /> D</label>
		</c:if>
		<c:if test="${question.trueOrFlase}">
		<!-- 判断题 -->
			<label><input name="answer" type="radio" value="1" class="sh_work_de" /> 对</label>
			<label><input name="answer" type="radio" value="2" class="sh_work_de" /> 错</label>
		</c:if>
		<c:if test="${question.blankFilling}">
		<!-- 填空题 -->
			<input class="form_text" name="answer" type="text" value="${tqItem.answer}" />
		</c:if>
	</p>
	<c:if test="${history != null}">
		<p class="sh_work_answer">
			回答：<span><c:forEach items="${history.answerString}" var="item">${item}</c:forEach></span>
		</p>
		<p>
			正确答案：<b><c:forEach items="${question.answerString}" var="item">${item}</c:forEach></b>
		</p>
	</c:if>


</div>
