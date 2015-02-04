<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_friend";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../inc/top_new.jsp"%>
	<div class="content clearfix">
		<div class="questions_list">
			<div class="live_resul clearfix">
				<div class="result_list">
					<ul>
						<c:forEach items="${userList}" var="user">
							<li class="clearfix">
								<img src="${ctx}${user.headPhoto}" width="130" height="110">
								<div class="result_con">
									<h3 >${user.showName}</h3>
									<p class="pt1">${user.levelString}</p>
									<p class="pt2"><a href="javascript:void(0)" rel="${user.id}" class="addFriend">加好友</a></p>
								</div>
							</li>
						</c:forEach>                    
					</ul>
				</div>
		        <c:set var="basicUrl" value="${ctx}/friend" />
		       	<%@ include file="../inc/pagination_new.jsp"%>				
			</div>
		</div>
	</div>
</div>
</body>
</html>