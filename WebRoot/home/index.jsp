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
Globals.page = "Index_index";
</script>
</head>
<body>
	<ul>
		<c:forEach items="${navigator}" var="item"><li><a href='${ctx}/${item.pageUrl}'><fmt:message key="menu_${item.menuName}" /></a></li></c:forEach>
	</ul>
	<c:if test="${user != null}">
		welcome(${user.showName})---
		<a href="javascript:void(0);" class="logout" ><fmt:message key="index_user_userlogout"/></a>
	</c:if>
	<c:if test="${user == null}">
		<a href="${ctx }/login.action" ><fmt:message key="index_user_userlogin"/></a>
	</c:if>
</body>
</html>