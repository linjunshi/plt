<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_index";
</script>
</head>
<body>
	<div class="wrap">
		
		<%@ include file="../inc/top.jsp"%>
		
		<div class="test_content">school id is : ${id}</div>
		
		<%@ include file="../inc/friendlylink.jsp"%>
		<%@ include file="../inc/copyright.jsp"%>
	</div>
</body>
</html>