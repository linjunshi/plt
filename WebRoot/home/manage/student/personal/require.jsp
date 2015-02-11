<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<div>
	<form action="${ctx}/personal/requireFriend" method="post" class="common_form">
		<input type="hidden" name="userId" value="${userId}" />
		<div class="form_item">
			<div class="form_field">
				<textarea name="remark" class="freind_tarea"></textarea>
				<!-- <span class="not-empty" title='此项为必填项'>*</span> -->
			</div>
		</div>
		<div class="form_action">
			<input type="button" class="sure" value="确定" />
			<!-- <a href="javascript:void(0);" class="sure">确定</a>
			<a href="javascript:void(0);" class="close">取消</a> -->
		</div>
	</form>
</div>
