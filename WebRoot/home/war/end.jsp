<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台"></c:set>
<c:set var="keywords" value="456"></c:set>
<c:set var="description" value="789"></c:set>
<%@ include file="../inc/header.jsp"%>
<script type="text/javascript">
	var Globals = {};
	Globals.ctx = "${ctx}";
	Globals.lang = "${lang}";
	Globals.page = "Index_questionEnd";
</script>
</head>
<body>
	<%@ include file="../inc/top.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<div class="sh_work_le">
					<div class="sh_work_form">
						<c:forEach items="${questionList}" var="question" varStatus="st">
							<h2>${question.typeString}</h2>
							<div class="sh_work_rad">
								<h3><span class="current_index">${st.count}</span> 、${question.topic}</h3>						
								<c:if test="${question.singleSelection}">
									<!-- 单选题 -->
									<p>
										<input name="answer${st.count}" type="radio" value="1" class="sh_work_de" disabled="disabled" /> <label>A</label>
										<span>${question.opt1}</span>
									</p>
									<p>
										<input name="answer${st.count}" type="radio" value="2" class="sh_work_de" disabled="disabled" /> <label>B</label>
										<span>${question.opt2}</span>
									</p>
									<p>
										<input name="answer${st.count}" type="radio" value="4" class="sh_work_de" disabled="disabled" /> <label>C</label>
										<span>${question.opt3}</span>
									</p>
									<p>
										<input name="answer${st.count}" type="radio" value="8" class="sh_work_de" disabled="disabled" /> <label>D</label>
										<span>${question.opt4}</span>
									</p>
								</c:if>
								<c:if test="${!question.singleSelection}">
									<!-- 多选题 -->
									<p>
										<input name="answer${st.count}" type="checkbox" value="1" class="sh_work_de" disabled="disabled" />
										<label>A</label> <span>${question.opt1}</span>
									</p>
									<p>
										<input name="answer${st.count}" type="checkbox" value="2" class="sh_work_de" disabled="disabled" />
										<label>B</label> <span>${question.opt2}</span>
									</p>
									<p>
										<input name="answer${st.count}" type="checkbox" value="4" class="sh_work_de" disabled="disabled" />
										<label>C</label> <span>${question.opt3}</span>
									</p>
									<p>
										<input name="answer${st.count}" type="checkbox" value="8" class="sh_work_de" disabled="disabled" />
										<label>D</label> <span>${question.opt4}</span>
									</p>
								</c:if>		
							</div>			
						</c:forEach>
					</div>
				</div>
				<form action="${ctx}/" method="post" class="sh_work_right">
					<div class="sh_work_result">
						<span>已答${doneCount}题</span><span>错误${wrongCount}题</span>
					</div>
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
						<p>
							错误<em class="pt14">&nbsp;</em>
						</p>
					</div>
					<div class="sh_form_but">
						<!-- <a href="javascript:void(0);" class="ajax_reset">重新做题</a> -->
						<a href="${ctx}/question">返回选题</a>
					</div>									
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>