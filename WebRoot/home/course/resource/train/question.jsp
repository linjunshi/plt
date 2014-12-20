<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<h2>${question.typeString}</h2>
<div class="sh_work_rad">
	<h3><span class="current_index">1</span> 、${question.topic}</h3>

	<c:if test="${question.singleSelection}">
		<!-- 单选题 -->
		<p>
			<input name=answer type="radio" value="1" class="sh_work_de" /> <label>A</label>
			<span>${question.opt1}</span>
		</p>
		<p>
			<input name="answer" type="radio" value="2" class="sh_work_de" /> <label>B</label>
			<span>${question.opt2}</span>
		</p>
		<p>
			<input name="answer" type="radio" value="4" class="sh_work_de" /> <label>C</label>
			<span>${question.opt3}</span>
		</p>
		<p>
			<input name="answer" type="radio" value="8" class="sh_work_de" /> <label>D</label>
			<span>${question.opt4}</span>
		</p>
	</c:if>
	<c:if test="${!question.singleSelection}">
		<!-- 多选题 -->
		<p>
			<input name="answer" type="checkbox" value="1" class="sh_work_de" />
			<label>A</label> <span>${question.opt1}</span>
		</p>
		<p>
			<input name="answer" type="checkbox" value="2" class="sh_work_de" />
			<label>B</label> <span>${question.opt2}</span>
		</p>
		<p>
			<input name="answer" type="checkbox" value="4" class="sh_work_de" />
			<label>C</label> <span>${question.opt3}</span>
		</p>
		<p>
			<input name="answer" type="checkbox" value="8" class="sh_work_de" />
			<label>D</label> <span>${question.opt4}</span>
		</p>
	</c:if>

	<c:if test="${history != null}">
		<p class="sh_work_answer">
			回答：<span><c:forEach items="${history.answerString}" var="item">${item}</c:forEach></span>
		</p>
		<p>
			正确答案：<b><c:forEach items="${question.answerString}" var="item">${item}</c:forEach></b>
		</p>
	</c:if>


</div>
