<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>

<div class="user">
	<div class="system_tip_login">
		<p id="tips_msg"></p>
	</div>
	<form class="formlogin common_form" >
		<div class="loginuser">
			<label class="login"><fmt:message key="index_user_username" />：</label>
			<input name="username" type="text" size="20" class="text2" value="${username}" required> 
			<%-- <a href="${ctx}/account/regist" target="_blank" class="login_new">注册新用户</a> --%>
		</div>
		<div class="loginuser">
			<label class="login"><fmt:message key="index_user_password" />：</label>
			<input name="password" type="password" size="20" class="text2" required>
			<%-- <a href="${ctx}/account/forgotPwd" target="_blank" class="login_forgot">忘记密码?</a> --%>
		</div>
		<div class="loginbut">
			<input type="button" value="登录" class="btn-login sure" name="commit" /> 
			<input type="button" value="取消" class="login_index close" name="index"/>
		</div>
	</form>

</div>
