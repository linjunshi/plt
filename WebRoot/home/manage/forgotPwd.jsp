<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_index";
</script>
</head>
<body>
	<%@ include file="../inc/top_bg.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<%@ include file="../inc/leftmenu_student.jsp"%>
				<div class="sh_info_r">
					<div class="sh_title">
						<h2>忘记密码</h2>
					</div>
					<div class="sh_form_con">
						<div id="demo_zone">
							<form method="post" action="#" class="form_info"
								id="register_form">
								<div class="form_item">
									<label for="pwd">验证方式：</label>
									<div class="form_field">
										<input name="pwd" id="pwd" class="form_text" type="password">
	
									</div>
								</div>
								<div class="form_item">
									<label for="pwd">新密码：</label>
									<div class="form_field">
										<input name="pwd" id="pwd" class="form_text" type="password">
									</div>
								</div>
								<div class="form_item">
									<label for="pwd">验证方式：</label>
									<div class="form_field">
										<input name="pwd" id="pwd" class="form_text" type="password">
										<!-- <span class="form_error">输入的密码不正确，请重新输入</span> -->
									</div>
								</div>
								<div class="form_action">
									<a href="#">保存</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>