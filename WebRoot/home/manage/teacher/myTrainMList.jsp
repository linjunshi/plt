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
Globals.page = "Manage_myTrainMList";
</script>
</head>
<body>
	<%@ include file="../../inc/top_bg.jsp"%>
	<div id="container_box">
    	<div id="container_content">
			<div class="sectionMain clr">
		        <%@ include file="../../inc/leftmenu_teacher.jsp"%>
				<div class="sh_info_r">
					<div class="sh_title">
						<h2>试题列表</h2>
						<a href="${ctx}/manage/question/addOrModifyQuestion" class="sh_add">+新增</a>
					</div>
					<div class="sh_collection">
						<table border="1" class="sh_coll_tab">
							<colgroup>
								<col width="60">
								<col width="360">
								<col width="100">
								<col width="100">
								<col width="150">
								<col width="200">
							</colgroup>
							<thead>
								<tr>
									<th>序号</th>
									<th>试题描述</th>
									<th>试题类型</th>
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
										<td>
											<c:if test="${question.status == 0}">未审核</c:if>
											<c:if test="${question.status == 1}">已审核</c:if>
											<c:if test="${question.status == 2}">审核不通过</c:if>
											<c:if test="${question.status == 100}">已删除</c:if>
										</td>
										<td><fmt:formatDate value="${question.cts}" type="date" dateStyle="default" /></td>
										<td>
											<div class="btn_question_operation <c:if test='${question.status==0}'>status</c:if>" >
												<c:if test="${question.status==0}">
													<div class="btn_question_status">
														<form action="${ctx}/manage/question/auditing" method="get">
															<input type="hidden" value="${question.id}" name="questionId" />
															<input type="submit" value="审核" />
														</form>
													</div>
												</c:if>
												<div class="btn_question_edit">
													<form action="${ctx}/manage/question/addOrModifyQuestion" method="get">
														<input type="hidden" value="${question.id}" name="questionId" />
														<input type="submit" value="修改" />
													</form>
												</div>
												<div class="btn_question_delete">
													<form action="${ctx}/manage/question/delete" method="post">
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
				            				<%@ include file="../../inc/pagination.jsp"%>
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
	<%@ include file="../../inc/friendlylink.jsp"%>
	<%@ include file="../../inc/copyright.jsp"%>
</body>
</html>