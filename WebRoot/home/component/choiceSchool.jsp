<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<div class="choice_school">
	<div class="group province">
		<c:forEach items="${provinceList}" var="province">
			<a href="javascript:void(0);" id="a_${province.areaCode}">${province.areaName}</a>
		</c:forEach>
	</div>
	<div class="group city" style="display:none">
	</div>
	<div class="group county" style="display:none">
	</div>
	<div class="group school" style="display:none">
	</div>
</div>