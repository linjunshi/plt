<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<div>
	<form action="${ctx}/friend/require" method="post">
		<input type="hidden" name="userId" value="${userId}" />
		<textarea name="remark"></textarea>
		<input type="button" class="submit" value="确认" />
	</form>
</div>
