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
			<p>
				<input value="1" class="form_radio" type="checkbox">
				<span class="form_ra_text">Ａ</span><span>${question.opt1}</span>
			</p>
			<p>
				<input value="2" class="form_radio" type="checkbox">
				<span class="form_ra_text">Ｂ</span><span>${question.opt2}</span>
			</p>
			<p>
				<input value="4" class="form_radio" type="checkbox">
				<span class="form_ra_text">Ｃ</span><span>${question.opt3}</span>
			</p>
			<p>
				<input value="8" class="form_radio" type="checkbox">
				<span class="form_ra_text">Ｄ</span><span>${question.opt4}</span>
			</p>
		</div>
		<div>
		
		</div>
	</div>
</div>
