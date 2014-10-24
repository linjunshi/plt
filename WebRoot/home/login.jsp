<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="123" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_login";
</script>
</head>
<body>

	<form action="${ctx}/login" method="post">
		<p><fmt:message key="index_user_username"/>：<input type="text" name="username" /></p>
		<p><fmt:message key="index_user_password"/>：<input type="text" name="password" /></p>
		<p><input type="submit" /></p>
	</form>
</body>
</html>