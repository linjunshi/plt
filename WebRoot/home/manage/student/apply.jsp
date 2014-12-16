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
Globals.page = "Manage_applyTeacher";
</script>
</head>
<body>
	<%@ include file="../../inc/top_bg.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<div class="form_page">
					<div id="demo_zone">
						<div><span style="margin: 0 0 0 30px;height: 30px;line-height: 50px;font-size: 16px;"><b>请填下以下信息完成老师身份申请:</b></span></div>
						<form method="post" action="${ctx}/apply/teacher" class="form_info common_form">
							<input type="hidden" name="userId" value="${sessionScope.loginUser.id}" />
							<div class="form_item">
								<label for="schoolName">所在学校：</label>
								<div class="form_field">
									<input name="schoolName" type="text" readonly="readonly" class="form_text" value="${form.schoolName}" required /> 
									<input name="schoolId" type="hidden" value="${form.schoolId}" />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label for="subjectId">教学科目：</label>
								<div class="form_field">
									<select name="subjectId">
										<c:forEach items="${subjectList}" var="subject">
										<option value="${subject.id}"<c:if test="${form.subjectId==subject.id}"> selected="selected"</c:if>>${subject.subjectName}</option>
										</c:forEach>
									</select>
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label for="idChard">身份证号码：</label>
								<div class="form_field">
									<input name="idChard" class="form_text" type="text" value="${form.idChard}" required required_Idcard />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>				
							<div class="form_item">
								<label for="phone">手机号码：</label>
								<div class="form_field">
									<input name="phone" class="form_text" type="text" value="${form.phone}" required required_Phone />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label for="remark">个人简介：</label>
								<div class="form_field">
									<textarea id="remark" name="remark" class="xheditor-mfull xheditor {skin:'o2007silver'}" rows="60" cols="100" style="width:80%;height: 230px;" >${form.remark}</textarea>
								</div>
							</div>
							<div class="form_action">
								<input type="submit" value="提交" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>