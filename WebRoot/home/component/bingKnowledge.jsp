<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>

<div id="keyun_popWindow" class="tinybox">
	<div class="join_con clearfix">
		<div class="join_tit">
			<p>未绑定：</p>
			<p class="maigin_right">已绑定：</p>
		</div>
		<div>
			<a href="javascript:void(0);" class="join_right_all">&nbsp;</a> 
			<a href="javascript:void(0);" class="join_right_but">&nbsp;</a> 
			<a href="javascript:void(0);" class="join_left_but">&nbsp;</a> 
			<a href="javascript:void(0);" class="join_left_all">&nbsp;</a> 
			<a href="javascript:void(0);" class="join_up_but">&nbsp;</a> 
			<a href="javascript:void(0);" class="join_down_but">&nbsp;</a>
		</div>
		<div class="join_left ">
			<select name="oListboxFrom" id="oListboxFrom" size="15" multiple="true" class="join_list">
				<c:forEach items="${knowledgeList}" var="knowledge">
					<option value="${knowledge.id}">${knowledge.knowledgeName}</option>
				</c:forEach>
			</select>
		</div>
		<div class=" join_right">
			<select name="oListboxTo" id="oListboxTo" multiple="true" size="15" class="join_list">
				<c:forEach items="${bingKnowledgeList}" var="bingknowledge">
					<option value="${bingknowledge.knowledgeId}">${bingknowledge.knowledgeName}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="join_but clearfix">
		<a href="javascript:void(0);" class="sure">确定</a>
		<a href="javascript:void(0);" class="close">取消</a>
	</div>
</div>