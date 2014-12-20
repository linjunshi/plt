<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台"></c:set>
<c:set var="keywords" value="456"></c:set>
<c:set var="description" value="789"></c:set>
<%@ include file="../../../inc/header.jsp"%>
<script type="text/javascript">
	var Globals = {};
	Globals.ctx = "${ctx}";
	Globals.lang = "${lang}";
	Globals.page = "Index_doneTrain";
</script>
</head>
<body>
	<%@ include file="../../../inc/top.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<div class="sh_work_le">
					<p>${train.title}，来自${chapter.remark}，课程名称：${course.courseName}，当前第 <b class="current_index">1</b> 题，一共 <b>${total}</b> 题</p>
					<div class="sh_work_form"></div>
					<div class="sh_form_but">
						<a class="preOne sh_form_grey" href="javascript:void(0);">上一题</a>
						<a class="nextOne<c:if test="${total <= 1}"> sh_form_grey</c:if>"
							href="javascript:void(0);">下一题</a>
					</div>
				</div>
				<form action="${ctx}/train/question" method="post"
					class="sh_work_right">
					<input type="hidden" name="trainId" value="${train.id}" /> <input
						type="hidden" name="chapterId" value="${chapter.id}" /> <input
						type="hidden" name="module" value="${module}" /> <input
						type="hidden" name="total" value="${total}" /> <input
						type="hidden" name="index" value="1" />
					<c:if test="${module == 'result'}">
						<div class="sh_work_result">
							<span>已答${doneCount}题</span><span>错误${wrongCount}题</span>
						</div>
					</c:if>
					<div class="clr">
						<ul>
							<c:forEach items="${indexList}" var="qIndex" varStatus="st">
								<li class="question_index ${qIndex.classString}"><span>${st.count}</span><input
									type="hidden" name="answer" value="${qIndex.answer}" /></li>
							</c:forEach>
						</ul>
					</div>
					<div class="sh_work_color">
						<p>
							未答<em class="pt13">&nbsp;</em>
						</p>
						<p>
							已答<em class="pt15">&nbsp;</em>
						</p>
						<c:if test="${module == 'result'}">
						<p>
							错误<em class="pt14">&nbsp;</em>
						</p>
						</c:if>
					</div>
					<div class="sh_form_but">
						<c:if test="${module == 'answer'}">
							<a href="javascript:void(0);" class="ajax_submit">提交试卷</a>
						</c:if>
						<c:if test="${module == 'result'}">
							<a href="javascript:void(0);" class="ajax_reset">重新做题</a>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../../../inc/friendlylink.jsp"%>
</body>
</html>