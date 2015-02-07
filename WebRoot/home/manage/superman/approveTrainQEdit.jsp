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
Globals.page = "Manage_approveTrainQEdit";
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
						<h2>审核试题</h2>
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
							<form method="post" action="${ctx}/super/question/approveQuestion" class="form_info common_form" id="question_form">
								<input id="id" name="id" type="hidden" value="${tqItem.id}"/>
								<input id="page" name="page" type="hidden" value="${pageNum}"/>
								<input type="hidden" name="operation" id="operation" value="${operation}"/>
								<input id="levelId" name="levelId" type="hidden" value="${tqItem.gradeId}">
								<input id="oldSubjectId" name="oldSubjectId" type="hidden" value="${tqItem.subjectId}">
								<input id="oldUnitId" name="oldUnitId" type="hidden" value="${tqItem.unitId}">
								<input id="gradeIdSelected" name="gradeIdSelected" type="hidden" value="">
								<div class="form_item">
									<label for="gradeId">试题分类<span class="not-empty" title='此项为必填项'>*</span>：</label>
									<div class="form_field">
										<select id="gradeSelect" style="display:none;">
											<c:forEach items="${applicationScope.gradeList}" var="grade" varStatus="st">
											<option value="${grade.gradeEnName}">${grade.gradeName}</option>
											</c:forEach>
										</select>
										<select name="gradeId" id="levelSelect">
										</select>
										<select name="subjectId" id="subjectSelect">
										</select>
										<select name="unitId" id="unitSelect">
										</select>
									 </div>
								</div>
								<div class="form_item">
									<label>所属知识点<span class="not-empty" title='此项为必填项'>*</span>：</label>
									<div class="form_field">
										<input id="knowledgeIds" name="knowledgeIds" type="hidden" value="${knowledgeIds}"/>
										<input id="knowledgeNames" name="knowledgeNames" placeholder="请点击选择试题所属的知识点" type="button" value="请点击这里选择绑定知识点"/>
									</div>
								</div>
								<div class="form_item">
									<label>难易程度<span class="not-empty" title='此项为必填项'>*</span>：</label>
									<div class="form_field">
										<input
											<c:if test="${tqItem.level==0}">checked="checked"</c:if>
											value="0" class="form_radio" name="level" type="radio">
										<span class="form_ra_text">易</span> <input
											<c:if test="${tqItem.level==10}">checked="checked"</c:if>
											value="10" class="form_radio" name="level" type="radio">
										<span class="form_ra_text">中</span> <input
											<c:if test="${tqItem.level==100}">checked="checked"</c:if>
											value="100" class="form_radio" name="level" type="radio">
										<span class="form_ra_text">难</span>
									</div>
								</div>
								<div class="form_item">
									<label>试题类型<span class="not-empty" title='此项为必填项'>*</span>：</label>
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
									</div>
								</div>
								<div class="form_item">
									<label>题目名称<span class="not-empty" title='此项为必填项'>*</span>：</label>
									<div class="form_field">
										<textarea id="xheditor_topic" name="topic" rows="60" cols="100" style="width:85%;height: 230px;" >${tqItem.topic}</textarea>
										<script type="text/javascript">
										 	$('#xheditor_topic').xheditor({upImgUrl:'${ctx}/image/upload2', html5Upload:false, upMultiple:1, upImgExt:'jpg,jpeg,gif,png,bmp', tools:'mfull',skin:'o2007silver'});
										</script>
									</div>
								</div>
								<div class="form_item">
									<label>正确答案<!-- <span class="not-empty" title='此项为必填项'>*</span> -->：</label>
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
									</div>
									<div class="form_field" id="answer2_radio">
										<input value="1" class="form_radio" name="answer2" type="radio"
											<c:if test="${tqItem.containA}">checked="checked"</c:if> />
										<span class="form_ra_text">正确</span> 
										<input value="2" class="form_radio" name="answer2" type="radio"
											<c:if test="${tqItem.containB}">checked="checked"</c:if> />
										<span class="form_ra_text">错误</span> 
									</div>
									<div class="form_field"  id="answer_input">
										<textarea id="answer3" name="answer3">${tqItem.answer}</textarea>
									</div>
								</div>
								<div class="form_item">
									<label>试题详解：</label>
									<div class="form_field">
										<textarea id="remark" name="remark" required_length="256">${tqItem.remark}</textarea>
										<p class="form_des">请匆超过256字符</p>
									</div>
								</div>
								<div class="form_action">
									<input type="hidden" id="status" name="status" value = "${tqItem.status}" title="审核状态"/>
									<input class="btn_approve" type="submit" value="审核通过" />
									<input class="btn_disapprove" type="submit" value="审核不通过" />
									<a class="btn_question" href="${ctx}/super/question/approve?page=${pageNum}">取消</a>
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