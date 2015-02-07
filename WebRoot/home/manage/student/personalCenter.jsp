<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
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
			<div class="qu_right_it">
				<h2>${sessionScope.loginUser.showName}</h2>
				<p>你是我的小苹果</p>
				<p>
					财富：012<a href="#">充值</a><span>积分：2365</span>
				</p>
				<p>
					<a href="#">好友<b>12</b></a>｜<a href="#">关注<b>12</b></a>|<a
						href="#">粉丝<b>12</b></a>
				</p>
				<p class="qu_but">
					<a href="#">按钮</a>
				</p>
			</div>
			<div class="qu_tarea clearfix">
				<textarea name="" cols="" rows="" class="qu_tar">有什么心得一起来分享！</textarea>
				<p>
					<span>还可以输入 140 字</span><a href="#">发言</a>
				</p>
			</div>
			<div class="qu_info">
				<h2>全部动态</h2>
			</div>
			
			<c:if test="${fn:length(friendMsgList) == 0}">
				<div class="qu_no_info">
					<p>你还没有任何动态<a href="#">去群里看看</a></p>
				</div>
			</c:if>
			<c:if test="${fn:length(friendMsgList) > 0}">
				<c:forEach items="${friendMsgList}" var="msg">
					<div class="qu_no_info">
						<p>
						<a href="javascript:alert('待定')">${msg.showName}</a>
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
<div class="footer"></div>
</body>
</html>
