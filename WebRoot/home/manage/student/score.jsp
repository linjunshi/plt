<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台"></c:set>
<c:set var="keywords" value="456"></c:set>
<c:set var="description" value="789"></c:set>
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
						<h2>我的评分</h2>
					</div>
					<div class="sh_collection">

						<div class="time_shaft">
							<div class="time_menu">
								<h2>语文</h2>
								<p>1年级</p>
							</div>
							<div class="time_main 	clr">
								<c:forEach items="${knowledgeTableList}" var="table">
									<h2 class="time_title"><%-- ${table.week} --%>周</h2>
									<ul class="time_list">
										<c:forEach items="${table.knowledgePointerList}" var="pointer">
											<li>
											<p>${pointer.knowledgeName}</p>
											<p>
											<c:forEach begin="1" end="${pointer.starNum}">
												<span><img src="${ctx}/resource/images/siz.png"></span>
											</c:forEach>
											</p>
											<p>
												<a href="#">推荐课程</a>
											</p>
											</li>
										</c:forEach>
									</ul>
								</c:forEach>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>
