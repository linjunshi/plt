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
Globals.page = "Manage_collection";
</script>
</head>
<body>
	<%@ include file="../../inc/top_bg.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<%@ include file="../../inc/leftmenu_student.jsp"%>
				<div class="sh_info_r">
					<div class="sh_title">
						<h2>我的收藏</h2>
					</div>
					<div class="sh_collection">
						<ul>
						    <c:forEach items="${courseList}"  var="course" varStatus="ct">
								<li>
									<div>
										<a href="${ctx}/course/${course.id}.html"><img src="${ctx}/resource/images/005.jpg"></a>
									</div>
									<div class="sh_coll_con">
										<h2>
											<a href="${ctx}/course/${course.id}.html">${course.courseName}</a>
										</h2>
										<p>主讲老师：${course.teacher}</p>
										<p>课程结束时间：<fmt:formatDate value="${course.endTime}" type="date" dateStyle="default"/></p>
										<p>评论人数：${course.commentCount}</p>
									</div>
									<div class="sh_coll_m">
										<p><b>${course.price}</b>元</p>
									</div>
									<div class="sh_coll_but">
										<form action="${ctx}/collection/cancelCollect" method="post" id="cancelCollect" >
											<!-- <a href="javascript:void(0)" id="cancelCollect">取消收藏</a> -->
											<input type="hidden" name="courseId" value="${course.id}"/>
											<input type="submit" value="取消收藏" />
										</form>
									</div>
								</li>
							</c:forEach>
						</ul>
			            <c:set var="basicUrl" value="${ctx}/collection" />
		            	<%@ include file="../../inc/pagination.jsp"%>						
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>
