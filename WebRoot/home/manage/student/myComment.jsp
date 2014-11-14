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
	<%@ include file="../../inc/top.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<%@ include file="../../inc/leftmenu.jsp"%>
	
				<div class="sh_info_r">
					<div class="sh_title">
						<h2>我的评价</h2>
					</div>
					<div class="sh_collection">
						<ul>
						  <c:forEach items="${commentList}" var="comment">
							<li>
								<div class="sh_coll_con">
									<p>
										<span>评价：</span>${comment.remark}
									</p>
									<p>[<fmt:formatDate value="${comment.cts}" type="date" dateStyle="default" />]</p>
								</div>
								<div class="sh_coll_th">
									<p>${comment.teacher}</p>
								</div>
								<div class="sh_coll_m">
									<p>
										<a href="${ctx}/course/${comment.courseId}.html" target="_blank">${comment.courseName}</a>
									</p>
									<p>${comment.price}元</p>
								</div>
								<div class="sh_coll_but">
									<form action="${ctx}/comment/cancelComment" method="post" id="cancelComment">
										<!-- <a href="javascript:void(0)" id="cancelCollect">删除评价</a> -->
										<input type="hidden" name="courseId" value="${comment.courseId}" />
										<input type="hidden" name="commentId" value="${comment.id}" /> 
										<input type="submit" value="删除评价" />
									</form>
								</div>
							</li>
						</c:forEach>
						
						</ul>
						<c:set var="basicUrl" value="${ctx}/comment" />
			            <%@ include file="../../inc/pagination.jsp"%>	
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>