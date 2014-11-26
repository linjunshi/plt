<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/resource/js/lang/message.zh_CN.js"></script>
<title></title>
</head>
<body>
	<c:if test="${rs == 1}">
	激活成功，<a href="${ctx}/">点击跳转首页</a>
	</c:if>
	
	<c:if test="${rs == 0}">
	激活失败，请<a href="${ctx}/account/login">登录账户</a>重新发送激活邮件
	</c:if>
</body>
</html>