<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_personExams";
</script>
</head>
<body>
	<div class="header">
		<%@ include file="../inc/top_new.jsp"%>
		<div class="con_quest">
			<div class="con_que">
				<h2 class="${luEntry.subjectEnName}">&nbsp;</h2>
				<p>${luEntry.levelName}</p>
				<p>${luEntry.termCnName}</p>
				<p>（${luEntry.unitName}）</p>
			</div>
			<c:forEach items="${questionList}" var="question" varStatus="st">
				<div class="con_pue_sid">questionType
					<p>${question.topic}</p>
				</div>
				<div class="que_but selectControl">
					<p><img src="${ctx}/resource/images/questiontype_${question.questionType}.png" /></p>
					<c:forEach items="${optionsList}" var="oList" varStatus="ot">
						<p>
							<!-- 选择题、判断题 -->
							<a href="javascript:void(0);" option="${oList.option}" answer="${oList.answer}">
								<img src="${ctx}/resource/images/${oList.option}.png" border="0"/>
							</a>
							
						</p>
					</c:forEach>
					<!-- 填空题 -->
					<c:if test="${question.blankFilling}">
					 	<p><input id="answer1" name="answer1" type="text" class="tian_text" /><label></label></p>
					</c:if>
				</div>
				<c:if test="${question.singleSelection}"></c:if>
				<c:if test="${question.mulChoice}"></c:if>
				<c:if test="${question.trueOrFlase}"></c:if>
				<div class="answer clearfix">
					<h2>
						答案：<b><c:if test="${question.blankFilling}">${question.answer}</c:if>
						<c:if test="${!question.blankFilling}"><c:forEach items="${question.answerString}" var="answer" varStatus="at">${answer}</c:forEach></c:if></b>
					</h2>
					<p>详解：${question.remark}</p>
				</div>
				<div class="button_submit">
					<input type="hidden" id="answer" name="answer" value="">
					<input type="hidden" id="questionId" name="questionId" value="${question.id}">
					<input type="hidden" id="questionType" name="questionType" value="${question.questionType}">
					<a href="javascript:void(0);" class="submit"><img src="${ctx}/resource/images/button_submit.png"></a>
				</div>
				<c:if test="${fn:length(query.pageSequence) > 1}">
					<c:if test="${query.pageNum < query.pageCount}">
						<div class="button_next">
							<a href="${ctx}/war/exams?subjectId=${subjectId}&page=${query.pageNum + 1}"><img src="${ctx}/resource/images/button_next.png"></a>
						</div>
					</c:if>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<div class="footer "></div>
</body>
</html>
