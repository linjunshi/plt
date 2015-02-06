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
Globals.page = "Manage_knowledgeMAdd";
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
						<c:if test="${operation == 'add'}"><h2>添加知识点</h2></c:if>
						<c:if test="${operation == 'modify'}"><h2>修改知识点</h2></c:if>
					</div>
					<div class="form_questions">
						<div id="demo_zone">

							<c:if test="${tipError != null && fn:length(tipError)  > 0}">
								<div class="system_tip">
									<c:forEach items="${tipError}" var="tip">
										<p>${tip.msg}</p>
									</c:forEach>
								</div>
							</c:if>
							<form method="post" action="${ctx}/super/knowledge/addOrModifyKnowledge" class="form_info common_form" id="knowledge_form">
								<input type="hidden" value="${knowledgeItem.id}" name="id" id="id"/>
								<input id="levelId" name="levelId" type="hidden" value="${knowledgeItem.gradeId}">
								<input id="oldSubjectId" name="oldSubjectId" type="hidden" value="${knowledgeItem.subjectId}">
								<div class="form_item">
									<label for="gradeId">知识点分类：</label>
									<div class="form_field">
										<select id="gradeSelect">
											<c:forEach items="${applicationScope.gradeList}" var="grade" varStatus="st">
											<option value="${grade.gradeEnName}">${grade.gradeName}</option>
											</c:forEach>
										</select>
										<select name="gradeId" id="levelSelect">
										</select>
										<select name="subjectId" id="subjectSelect">
										</select>
										<span class="not-empty" title='此项为必填项'>*</span>
									 </div>
								</div>
								<div class="form_item">
									<label for="knowledgeName">知识点名称：</label>
									<div class="form_field">
										<input class="form_text" id="knowledgeName" name="knowledgeName" type="text" value="${knowledgeItem.knowledgeName}" required>
										<span class="not-empty" title='此项为必填项'>*</span>
									</div>
								</div>
								<div class="form_action">
									<input class="btn_question" type="submit" value="提交" /> 
									<a class="btn_question" href="${ctx}/super/knowledge/list">取消</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../inc/friendlylink.jsp"%>
	<%@ include file="../../inc/copyright.jsp"%>
</body>
</html>