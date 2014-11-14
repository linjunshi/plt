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
<style type="text/css">
.system_tip { width:350px; line-height:26px; margin:10px auto; margin-left:100px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
</style>
</head>
<body>
	<%@ include file="../inc/top.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<%@ include file="../inc/leftmenu.jsp"%>
				<div class="sh_info_r">
					<div class="sh_title">
						<h2>修改密码</h2>
					</div>
					<div class="sh_form_con">
						<div id="demo_zone">
							<c:if test="${tipError != null && fn:length(tipError)  > 0}">
								<div class="system_tip">
									<c:forEach items="${tipError}" var="tip">
									<p>${tip.msg}</p>
									</c:forEach>
								</div>
							</c:if>
							<form method="post" action="${ctx}/account/changePwdPost" class="form_info" id="pwdChange_form">
								<div class="form_item">
									<label for="pwd">原始密码：</label>
									<div class="form_field">
										<input placeholder="请输入您的原密码！" name="oldPwd" id="oldPwd" class="form_text" type="password"/> 
										<span class="form_error"><a href="${ctx}/account/forgotPwd">忘记密码</a></span>
									</div>
								</div>
								<div class="form_item">
									<label for="pwd">新密码：</label>
									<div class="form_field">
										<input placeholder="请输入您的新密码！" name="newPwd" id="newPwd" class="form_text" type="password"/>
									</div>
								</div>
								<div class="form_item">
									<label for="pwd">确认新密码：</label>
									<div class="form_field">
										<input placeholder="请输入您的确认新密码！" name="comfirmPwd" id="comfirmPwd" class="form_text" type="password"/>
										<!-- <span class="form_error">输入的密码不正确，请重新输入</span> -->
									</div>
								</div>
								<div class="form_action">
									<input type="submit" value="保存" />
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