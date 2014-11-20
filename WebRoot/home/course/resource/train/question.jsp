<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<div id="demo_zone">
	<div class="form_item">
		<label>题型：</label>
		<div class="form_field">${question.typeString}</div>
	</div>
	<div class="form_item">
		<label>标题：</label>
		<div class="form_field">${question.topic}</div>
	</div>
	<div class="form_item">
		<div class="form_field">
			<c:if test="${question.singleSelection}"><!-- 单选题 -->
			<p>
				<input value="1" class="form_radio" name="answer" type="radio">
				<span class="form_ra_text">Ａ</span><span>${question.opt1}</span>
			</p>
			<p>
				<input value="2" class="form_radio" name="answer" type="radio">
				<span class="form_ra_text">Ｂ</span><span>${question.opt2}</span>
			</p>
			<p>
				<input value="4" class="form_radio" name="answer" type="radio">
				<span class="form_ra_text">Ｃ</span><span>${question.opt3}</span>
			</p>
			<p>
				<input value="8" class="form_radio" name="answer" type="radio">
				<span class="form_ra_text">Ｄ</span><span>${question.opt4}</span>
			</p>
			</c:if>
			<c:if test="${!question.singleSelection}"><!-- 多选题 -->
			<p>
				<input value="1" class="form_radio" name="answer" type="checkbox">
				<span class="form_ra_text">Ａ</span><span>${question.opt1}</span>
			</p>
			<p>
				<input value="2" class="form_radio" name="answer" type="checkbox">
				<span class="form_ra_text">Ｂ</span><span>${question.opt2}</span>
			</p>
			<p>
				<input value="4" class="form_radio" name="answer" type="checkbox">
				<span class="form_ra_text">Ｃ</span><span>${question.opt3}</span>
			</p>
			<p>
				<input value="8" class="form_radio" name="answer" type="checkbox">
				<span class="form_ra_text">Ｄ</span><span>${question.opt4}</span>
			</p>			
			</c:if>
		</div>
	</div>
	<c:if test="${history != null}">
	<div>
		<p>正确答案：<c:forEach items="${question.answerString}" var="item">${item}</c:forEach></p>
		<p>回答：<c:forEach items="${history.answerString}" var="item">${item}</c:forEach></p>
	</div>
	</c:if>
</div>
