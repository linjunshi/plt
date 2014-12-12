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
Globals.page = "Manage_myTrainMAdd";
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
						<c:if test="${addOrModify == 'add'}"><h2>添加试题</h2></c:if>
						<c:if test="${addOrModify == 'modify'}"><h2>修改试题</h2></c:if>
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
							<form method="post" action="${ctx}/manage/question/addOrModifyQuestion" class="form_info common_form" id="question_form">
								<input type="hidden" value="${tqItem.questionType}" />
								<div class="form_item">
									<label>题型：</label>
									<div class="form_field">
										<input
											<c:if test="${tqItem.questionType==1}">checked="checked"</c:if>
											value="1" class="form_radio" name="questionType" type="radio">
										<span class="form_ra_text">单选题</span> <input
											<c:if test="${tqItem.questionType==2}">checked="checked"</c:if>
											value="2" class="form_radio" name="questionType" type="radio">
										<span class="form_ra_text">多选题</span> <input
											<c:if test="${tqItem.questionType==3}">checked="checked"</c:if>
											value="3" class="form_radio" name="questionType" type="radio">
										<span class="form_ra_text">判断题</span> <input
											<c:if test="${tqItem.questionType==4}">checked="checked"</c:if>
											value="4" class="form_radio" name="questionType" type="radio">
										<span class="form_ra_text">填空题</span>
										<span class="not-empty" title='此项为必填项'>*</span>
									</div>
								</div>
								<div class="form_item">
									<label>标题：</label>
									<div class="form_field">
										<textarea id="topic" name="topic" required>${tqItem.topic}</textarea>
										<p class="form_des">请匆超过100字符&nbsp;<span class="not-empty" title='此项为必填项'>*</span></p>
									</div>
								</div>
								<div class="form_item">
									<label>选项：</label>
									<div class="form_field">
										<label class="field_te">A: </label> 
										<input placeholder="请填入试题" class="form_tarea form_text" id="opt1" name="opt1" type="text" value="${tqItem.opt1}" required><span class="not-empty" title='此项为必填项'>*</span><br /> 
										<label class="field_te ma">B: </label> 
										<input placeholder="请填入试题" class="form_tarea form_text" id="opt2" name="opt2" type="text" value="${tqItem.opt2}"><br /> 
										<label class="field_te ma">C: </label>
										<input placeholder="请填入试题" class="form_tarea form_text" id="opt3" name="opt3" type="text" value="${tqItem.opt3}"><br /> 
										<label class="field_te ma">D: </label> 
										<input placeholder="请填入试题" class="form_tarea form_text" id="opt4" name="opt4" type="text" value="${tqItem.opt4}"><br />
									</div>
								</div>
								<div class="form_item">
									<label>正确答案：</label>
									<div class="form_field" id="answer_radio">
										<input value="1" class="form_radio" name="answer" type="radio"
											<c:if test="${tqItem.containA}">checked="checked"</c:if> />
										<span class="form_ra_text">Ａ</span> 
										<input value="2" class="form_radio" name="answer" type="radio"
											<c:if test="${tqItem.containB}">checked="checked"</c:if> />
										<span class="form_ra_text">Ｂ</span> 
										<input value="4" class="form_radio" name="answer" type="radio"
											<c:if test="${tqItem.containC}">checked="checked"</c:if> />
										<span class="form_ra_text">Ｃ</span> 
										<input value="8" class="form_radio" name="answer" type="radio"
											<c:if test="${tqItem.containD}">checked="checked"</c:if> />
										<span class="form_ra_text">Ｄ</span>
										<span class="not-empty" title='此项为必填项'>*</span>
									</div>
									<div class="form_field" id="answer_checkbox">
										<input value="1" class="form_radio" name="pageAnswer" type="checkbox"
											<c:if test="${tqItem.containA}">checked="checked"</c:if> />
										<span class="form_ra_text">Ａ</span> 
										<input value="2" class="form_radio" name="pageAnswer" type="checkbox"
											<c:if test="${tqItem.containB}">checked="checked"</c:if> />
										<span class="form_ra_text">Ｂ</span> 
										<input value="4" class="form_radio" name="pageAnswer" type="checkbox"
											<c:if test="${tqItem.containC}">checked="checked"</c:if> />
										<span class="form_ra_text">Ｃ</span> 
										<input value="8" class="form_radio" name="pageAnswer" type="checkbox"
											<c:if test="${tqItem.containD}">checked="checked"</c:if> />
										<span class="form_ra_text">Ｄ</span>
										<span class="not-empty" title='此项为必填项'>*</span>
									</div>
								</div>
								<div class="form_item">
									<label>试题详解：</label>
									<div class="form_field">
										<textarea id="remark" name="remark">${tqItem.remark}</textarea>
										<p class="form_des">请匆超过200字符</p>
									</div>
								</div>
								<div class="form_action">
									<input type="hidden" value="${tqItem.id}" name="id" id="id"/>
									<input class="btn_question" type="submit" value="提交" /> 
									<a class="btn_question" href="${ctx}/manage/question/list">取消</a>
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