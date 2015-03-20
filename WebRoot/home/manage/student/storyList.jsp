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
Globals.page = "Manage_storyList";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
		<div class="content clearfix">
			<%@ include file="../../inc/leftmenu.jsp"%>
			<div class="qu_right">
				<%@ include file="../../inc/person_header.jsp"%>
				<div class="qu_ri_school">
					<div class="sh_ke clearfix">
						<h2>剧本故事</h2>
						<ul class="sh_sch_ke ">
						
							<c:forEach items="${storyList}" var="story" varStatus="st">
								<li <c:if test="${st.index %4 == 0}">class="maigin_right"</c:if> >
									<a href="#" target="_blank"><img src="${ctx}" title="${story.storyName}" width="220" height="140"></a>
									<h2>${story.storyName}</h2>
								</li>
							</c:forEach>

						</ul>
						<c:set var="basicUrl" value="${ctx}/story/list" />
		       			<%@ include file="../../inc/pagination_new.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="../../inc/footer.jsp"%>
</body>
</html>
