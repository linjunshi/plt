<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<input type="hidden" name="questionType" value="${question.questionType}" />
<h2>${question.typeString}</h2>
<div class="sh_work_rad">
	<h3><span class="current_index">1</span> 、${question.topic}</h3>
	<p class="sh_option_answer"> 
		<span>请作答：</span>
		<c:if test="${question.singleSelection}">
		<!-- 单选题 -->
			<label><input name="answer" type="radio" value="1" class="sh_work_de" <c:if test="${history.containA}">checked="checked"</c:if> /> A</label>
			<label><input name="answer" type="radio" value="2" class="sh_work_de" <c:if test="${history.containB}">checked="checked"</c:if> /> B</label>
			<label><input name="answer" type="radio" value="4" class="sh_work_de" <c:if test="${history.containC}">checked="checked"</c:if> /> C</label>
			<label><input name="answer" type="radio" value="8" class="sh_work_de" <c:if test="${history.containD}">checked="checked"</c:if> /> D</label>
		</c:if>
		<c:if test="${question.mulChoice}">
		<!-- 多选题 -->
			<label><input name="answer" type="checkbox" value="1" class="sh_work_de" <c:if test="${history.containA}">checked="checked"</c:if> /> A</label>
			<label><input name="answer" type="checkbox" value="2" class="sh_work_de" <c:if test="${history.containB}">checked="checked"</c:if> /> B</label>
			<label><input name="answer" type="checkbox" value="4" class="sh_work_de" <c:if test="${history.containC}">checked="checked"</c:if> /> C</label>
			<label><input name="answer" type="checkbox" value="8" class="sh_work_de" <c:if test="${history.containD}">checked="checked"</c:if> /> D</label>
		</c:if>
		<c:if test="${question.trueOrFlase}">
		<!-- 判断题 -->
			<label><input name="answer" type="radio" value="1" class="sh_work_de" <c:if test="${history.containA}">checked="checked"</c:if> /> 对</label>
			<label><input name="answer" type="radio" value="2" class="sh_work_de" <c:if test="${history.containB}">checked="checked"</c:if> /> 错</label>
		</c:if>
		<c:if test="${question.blankFilling}">
		<!-- 填空题 -->
			<%-- <input class="form_text" name="answer" type="text" value="${history.answer}" /> --%>
			<textarea id="answer" name="answer" >${history.answer}</textarea>
		</c:if>
	</p>
	<c:if test="${history != null}">
		<p class="sh_work_answer <c:if test="${history.result == 1}">right</c:if><c:if test="${history.result == 0}">wrong</c:if>">
			<i class="right">你的答案：</i><span>
			<c:if test="${!question.blankFilling}">
				<c:forEach items="${history.answerString}" var="item">${item}</c:forEach>
			</c:if>
			<c:if test="${question.blankFilling}">${history.answer}</c:if>
			</span>
		</p>
		<p class="sh_right_answer right">
			<i>正确答案：</i><b>
			<c:if test="${!question.blankFilling}">
				<c:forEach items="${question.answerString}" var="item">${item}</c:forEach>
			</c:if>
			<c:if test="${question.blankFilling}">${question.answer}</c:if>
			</b>
		</p>
	</c:if>


</div>
