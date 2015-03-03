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
Globals.page = "Manage_collection";
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
						<h2>我的收藏</h2>
						<!-- <a href="#" class="catalog_box_more">换一换</a> -->
						<ul class="sh_sch_ke ">
						
							<c:forEach items="${weikeList}" var="weike" varStatus="st">
								<li <c:if test="${st.index %4 == 0}">class="maigin_right"</c:if> >
									<a href="${ctx}/weike/${weike.id}.html" target="_blank"><img src="${ctx}${weike.thumbnail}" title="${weike.courseName}" width="220" height="140"></a>
									<h2>${weike.courseName}</h2>
									<p>
										<span class="sch_unm"><b>${weike.price}</b>元</span>
									</p>
									<form action="${ctx}/collection/cancelCollect" method="post" id="cancelCollect" >
										<p class="sh_sch_but">
											<input type="hidden" name="weikeId" value="${weike.id}"/>
											<input type="hidden" name="page" value="${query.pageNum - 1}"/>
											<input type="submit" value="取消收藏" class="col_sh"/>
										</p>
									</form>
								</li>
							</c:forEach>

						</ul>
						<c:set var="basicUrl" value="${ctx}/collection" />
		       			<%@ include file="../../inc/pagination_new.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="../../inc/footer.jsp"%>
</body>
</html>
