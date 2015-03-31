<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="杜巴克教育" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
<c:set var="description" value="杜巴克教育" ></c:set>
<%@ include file="inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_forgotPwd";
</script>
</head>
<body>
<div class="header">
	<%@ include file="inc/top_new.jsp"%>
	<div class="content clearfix">
			<div class="form_page">
				<div id="demo_zone">
					新密码已经发送到您的邮箱了，请及时修改！
				</div>
			</div>
		</div>
	</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>
