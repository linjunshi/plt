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
Globals.page = "Manage_comment";
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
			            <%@ include file="../../inc/pagination_new.jsp"%>	
					</div>
				</div>
			</div>
		</div>
	</div>
<div class="footer"></div>
</body>
</html>
