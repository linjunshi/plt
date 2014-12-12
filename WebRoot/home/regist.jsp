<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台-用户注册" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_regist";
</script>
</head>
<style type="text/css">
.system_tip { width:300px; line-height:26px; margin:10px auto; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px; text-align: center; }
</style>
<body>

	<%@ include file="inc/top.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<div class="form_page">
					<div id="demo_zone">
						<c:if test="${tipError != null && fn:length(tipError)  > 0}">
							<div class="system_tip">
								<c:forEach items="${tipError}" var="tip">
								<p>${tip.msg}</p>
								</c:forEach>
							</div>
						</c:if>
						
						<form action="${ctx}/account/regist" method="post" class="form_vertical common_form">
							<div class="form_item">
								<label for="username">用户名：</label>
								<div class="form_field">
									<input placeholder="6-32个字符" class="form_text" name="username" type="text" required />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label for="pwd">密码：</label>
								<div class="form_field">
									<input name="password" id="pwd" class="form_text" type="password" required />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label for="pwd">确认密码：</label>
								<div class="form_field">
									<input name="pwdagain" id="pwd" class="form_text" type="password" required equalTo="password" />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label for="pwd">邮箱：</label>
								<div class="form_field">
									<input name="email" class="form_text" type="text" required required_Mail />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>				
							<div class="form_action">
								<input class="btn_regist" type="submit" value="提交" name="regist" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="inc/footer.jsp"%>
	
</body>
</html>