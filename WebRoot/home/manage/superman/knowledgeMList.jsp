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
Globals.page = "Manage_knowledgeMList";
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
						<h2>知识点列表</h2>
						<a href="${ctx}/super/knowledge/addOrModifyKnowledge" class="sh_add">+新增</a>
					</div>
					<div class="sh_collection">
						<table border="1" class="sh_coll_tab">
							<colgroup>
								<col width="60">
								<col width="320">
								<col width="130">
								<col width="130">
								<col width="130">
								<col width="200">
							</colgroup>
							<thead>
								<tr>
									<th>序号</th>
									<th>知识点</th>
									<th>所属学段</th>
									<th>所属年级</th>
									<th>所属科目</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${knowledgeList}"  var="knowledge" varStatus="kl">
									<tr>
										<td>${kl.index + 1 + (query.pageNum-1)*query.pageSize}</td>
										<td>${knowledge.knowledgeName}</td>
										<td>${knowledge.gradeName}</td>
										<td>${knowledge.levelName}</td>
										<td>${knowledge.subjectName}</td>
										<td class="btn_question_operation">
										<div class="btn_question_update">
											<form action="${ctx}/super/knowledge/addOrModifyKnowledge" method="get">
												<input type="hidden" value="${knowledge.id}" name="knowledgeId" />
												<input type="submit" value="修改" />
											</form>
										</div>
										<div class="btn_question_delete">
											<form action="${ctx}/super/knowledge/delete" method="post">
												<input type="hidden" value="${knowledge.id}" name="knowledgeId" />
												<input type="submit" value="删除" />
											</form>
										</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<c:if test="${fn:length(query.pageSequence) > 1}">
								<tfoot>
									<tr>
										<td colspan="6">
											<c:set var="basicUrl" value="${ctx}/super/knowledge/list" />
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