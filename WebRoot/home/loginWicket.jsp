<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>

<div class="user">
	<div class="system_tip_login">
		<p id="tips_msg"></p>
	</div>
	<form class="formlogin common_form" >
		<div class="loginuser">
			<label class="login"><fmt:message key="index_user_username" />：</label>
			<input name="username" type="text" size="20" class="text2" value="${username}" required> <a href="${ctx}/account/regist" target="_blank" class="login_new">注册新用户</a>
		</div>
		<div class="loginuser">
			<label class="login"><fmt:message key="index_user_password" />：</label>
			<input name="password" type="password" size="20" class="text2" required> <a href="${ctx}/account/forgotPwd" target="_blank" class="login_forgot">忘记密码?</a>
		</div>
		<div class="loginbut">
			<input type="button" value="登录" class="btn-login sure" name="commit" /> 
			<input type="button" value="取消" class="login_index close" name="index" onclick="document.location='${ctx}/'" />
		</div>
	</form>

</div>
<%-- <div id="keyun_popWindow" class="tinybox">
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
</div> --%>