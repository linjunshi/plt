<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_index";
</script>
</head>
<body>
	<%@ include file="inc/top.jsp"%>
		<div class="form_page">
			<div id="demo_zone">
				新密码已经发送到邮箱，自己去看，及时修改
			</div>
	</div>

	<%@ include file="inc/friendlylink.jsp"%>
</body>
</html>