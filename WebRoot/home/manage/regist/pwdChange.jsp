<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_changPwd";
</script>
<style type="text/css">
.system_tip {position: relative;float: left; width:350px; line-height:26px;min-height:26px; margin:0px 200px 10px 100px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
</style>
</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
	<div class="content clearfix">
		<%@ include file="../../inc/leftmenu.jsp"%>
		<div class="qu_right">
			<%@ include file="../../inc/person_header.jsp"%>
			<div class="sh_info_r">
				<div class="st_titile_r sh_title">
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
						<form method="post" action="${ctx}/account/changePwdPost" class="form_info common_form" id="pwdChange_form">
							<div class="form_item">
								<label for="oldPwd">原始密码：</label>
								<div class="form_field">
									<input placeholder="请输入您的原密码！" name="oldPwd" id="oldPwd" class="form_text" type="password" required/> 
									<span class="not-empty" title='此项为必填项'>*</span>
									<span class="form_error"><a href="${ctx}/account/forgotPwd" target="_blank">忘记密码</a></span>
								</div>
							</div>
							<div class="form_item">
								<label for="newPwd">新密码：</label>
								<div class="form_field">
									<input placeholder="请输入您的新密码！" name="newPwd" id="newPwd" class="form_text" type="password" required/>
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label for="comfirmPwd">确认新密码：</label>
								<div class="form_field">
									<input placeholder="请输入您的确认新密码！" name="comfirmPwd" id="comfirmPwd" class="form_text" type="password" required equalTo="newPwd"/>
									<span class="not-empty" title='此项为必填项'>*</span>
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
<div class="footer"></div>
</body>
</html>
