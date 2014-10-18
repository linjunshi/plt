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
Globals.page = "Index_regist";
</script>
</head>
<body>

	<form action="${ctx}/regist" method="post">
		<p>用户名：<input type="text" name="username" /></p>
		<p>密码：<input type="password" name="password" /></p>
		<p>重复密码：<input type="password" name="pwdagain" /></p>
		<p><input type="submit" /></p>
	</form>
</body>
</html>