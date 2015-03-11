<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 剧本 故事" ></c:set>
<%@ include file="../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_storyConfigList";
</script>

</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
	<div class="content clearfix">
		<%@ include file="../../inc/leftmenu.jsp"%>
		<div class="qu_right">
			<%@ include file="../../inc/person_header.jsp"%>
			<div class="sh_info_r">
					<div class="sh_title">
						<h2>剧本故事配置列表</h2>
					</div>
					<div class="sh_collection">
						<table border="1" class="sh_coll_tab">
							<colgroup>
								<col width="40">
								<col width="110">
								<col width="90">
								<col width="60">
								<col width="300">
								<col width="100">
								<col width="60">
							</colgroup>
							<thead>
								<tr>
									<th>序号</th>
									<th>剧本名称</th>
									<th>英文名称</th>
									<th>时长</th>
									<th>剧本简介</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${storyList}"  var="story" varStatus="st">
									<tr>
										<td>${st.index + 1 + (query.pageNum-1)*query.pageSize}</td>
										<td>${story.storyName}</td>
										<td>${story.storyEname}</td>
										<td>${story.duration}</td>
										<td>${fn:substring(story.remark, 0, 90)}......</td>
										<td><fmt:formatDate value="${story.cts}" type="date" dateStyle="default" /></td>
										<td>
											<div class="btn_question_operation" >
												<div class="btn_question_status">
													<form action="${ctx}/super/story/storyConfigEdit" method="get">
														<input type="hidden" value="${query.pageNum}" name="page" />
														<input type="hidden" value="${story.id}" name="storyId" />
														<input type="submit" value="编辑" />
													</form>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<c:if test="${fn:length(query.pageSequence) > 1}">
								<tfoot>
									<tr>
										<td colspan="7">
											<c:set var="basicUrl" value="${ctx}/super/story/config" />
				            				<%@ include file="../../inc/pagination_new.jsp"%>
										</td>
									</tr>
								</tfoot>
							</c:if>
						</table>
					</div>
				</div>
		</div>
	</div>
</div>
<%@ include file="../../inc/footer.jsp"%>
</body>
</html>
