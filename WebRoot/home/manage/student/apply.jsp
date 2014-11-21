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
Globals.page = "Manage_apply";
</script>
</head>
<body>
		<%@ include file="../../inc/top2.jsp"%>
		<div id="container_box">
			<div id="container_content">
			    <div>请填下以下信息完成老师身份申请</div>
			<form method="post" action="${ctx}/apply/teacher" class="form_info">
				<input type="hidden" name="id" value="${sessionScope.loginUser.id}" />
				<div class="form_item">
					<label for="pwd">所在学校：</label>
					<div class="form_field">
						<input name="schoolId" class="form_text" type="text">
					</div>
				</div>
				<div class="form_item">
					<label for="pwd">教学科目：</label>
					<div class="form_field">
						<input name="subjectId" class="form_text" type="text">
					</div>
				</div>
				<div class="form_item">
					<label for="pwd">个人简介：</label>
					<div class="form_field">
						<textarea name="remark"></textarea>
					</div>
				</div>
				<div class="form_action">
					<input type="submit" value="提交"/>
				</div>
			</form>
		</div>
		</div>
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>