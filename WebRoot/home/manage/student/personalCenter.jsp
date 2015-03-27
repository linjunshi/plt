<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="杜巴克" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_personalCenter";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
	<div class="content clearfix">
		<%@ include file="../../inc/leftmenu.jsp"%>
		<div class="qu_right">
			<%@ include file="../../inc/person_header.jsp"%>
			<div class="qu_info">
				<h2>全部动态</h2>
			</div>
			
			<c:if test="${fn:length(friendMsgList) == 0}">
				<div class="qu_no_info">
					<p>你还没有任何动态</p>
				</div>
			</c:if>
			<c:if test="${fn:length(friendMsgList) > 0}">
				<c:forEach items="${friendMsgList}" var="msg">
					<div class="qu_no_info">
						<p>
						<%-- <a href="javascript:void(0);">${msg.showName}</a> --%>
						<span style="color:#1682e6; padding:0 10px;">${msg.showName}</span>
						<c:if test="${msg.type == 1}">请求添加你为好友<a href="${ctx}/personal/require?userId=${msg.userId}&type=1">同意</a>|<a href="${ctx}/personal/require?userId=${msg.userId}&type=2">拒绝</a></c:if>
						<c:if test="${msg.type == 0}">${msg.action}</c:if>
						<span>消息：${msg.msg}</span>
						</p>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>
<%@ include file="../../inc/footer.jsp"%>
</body>
</html>
