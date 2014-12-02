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
				<div><span style="margin: 0 0 0 30px;height: 30px;line-height: 50px;font-size: 16px;"><b>请填下以下信息完成老师身份申请</b></span></div>
				<form method="post" action="${ctx}/apply/teacher" class="form_info common_form">
					<input type="hidden" name="userId" value="${sessionScope.loginUser.id}" />
					<div class="form_item">
						<label for="pwd">所在学校：</label>
						<div class="form_field">
							<input name="schoolName" type="text" readonly="readonly"
								class="form_text" value="${form.schoolName}" required /> <input
								name="schoolId" type="hidden" value="${form.schoolId}" />
						</div>
					</div>
					<div class="form_item">
						<label for="pwd">教学科目：</label>
						<div class="form_field">
							<select name="subjectId">
								<c:forEach items="${subjectList}" var="subject">
								<option value="${subject.id}"<c:if test="${form.subjectId==subject.id}"> selected="selected"</c:if>>${subject.subjectName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form_item">
						<label for="pwd">身份证号码：</label>
						<div class="form_field">
							<input name="idChard" class="form_text" type="text"
								value="${form.idChard}" required_Idcard />
						</div>
					</div>				
					<div class="form_item">
						<label for="pwd">手机号码：</label>
						<div class="form_field">
							<input name="phone" class="form_text" type="text"
								value="${form.phone}" required_Phone />
						</div>
					</div>
					<div class="form_item">
						<label for="pwd">个人简介：</label>
						<div class="form_field">
							<textarea name="remark" required >${form.remark}</textarea>
						</div>
					</div>
					<div class="form_action">
						<input type="submit" value="提交" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>