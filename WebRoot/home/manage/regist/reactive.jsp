<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../../inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_index";
</script>
</head>
<body>
	<%@ include file="../../inc/top_bg.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<%@ include file="../../inc/leftmenu_regist.jsp"%>
				<div class="sh_info_r">
					<div class="sh_title">
						<h2>帐号激活</h2>
					</div>
					<c:if test="${rs == 1}">
					<form action="${ctx}/account/reactive" method="post">
						<input type="submit" value="重新发送激活邮件"/>
					</form>
					</c:if>
					
					<c:if test="${rs == 0}">
					激活邮件发送成功，请登录注册邮箱激活帐号，1分钟以后可以重新发送
					</c:if>
					
					<c:if test="${rs == 10}">
					帐号已经激活
					</c:if>	
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>