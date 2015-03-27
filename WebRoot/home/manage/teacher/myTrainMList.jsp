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
Globals.page = "Manage_myTrainMList";
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
					<h2>试题列表</h2>
					<a href="${ctx}/manage/question/addOrModifyQuestion" class="sh_add">+新增</a>
				</div>
				<div class="sh_collection">
					<table border="1" class="sh_coll_tab">
						<colgroup>
							<col width="50">
							<col width="320">
							<col width="80">
							<col width="80">
							<col width="110">
							<col width="120">
						</colgroup>
						<thead>
							<tr>
								<th>序号</th>
								<th>试题描述</th>
								<th>类型</th>
								<th>状态</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${questionList}"  var="question" varStatus="qt">
								<tr>
									<td>${qt.index + 1 + (query.pageNum-1)*query.pageSize}</td>
									<td>${question.topic}</td>
									<td>${question.typeString}</td>
									<td>${question.statusString}</td>
									<td><fmt:formatDate value="${question.cts}" type="date" dateStyle="default" /></td>
									<td>
										<div class="btn_question_operation" >
											<div class="btn_question_edit">
												<form action="${ctx}/manage/question/addOrModifyQuestion" method="get">
													<input type="hidden" value="${query.pageNum}" name="page" />
													<input type="hidden" value="${question.id}" name="questionId" />
													<input type="submit" value="修改" />
												</form>
											</div>
											<div class="btn_question_delete">
												<form action="${ctx}/manage/question/delete" method="post">
													<input type="hidden" value="${query.pageNum}" name="page" />
													<input type="hidden" value="${question.id}" name="questionId" />
													<input type="submit" value="删除" />
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
									<td colspan="6">
										<c:set var="basicUrl" value="${ctx}/manage/question/list" />
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
