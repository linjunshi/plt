<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_index";
</script>
</head>
<body>
	<%@ include file="inc/top_bg.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<c:if test="${tipError != null && fn:length(tipError)  > 0}">
					<div class="system_tip">
						<c:forEach items="${tipError}" var="tip">
						<p>${tip.msg}</p>
						</c:forEach>
					</div>
				</c:if>	
				
				<div class="form_page">
					<div id="demo_zone">
						<form method="post" action="${ctx}/account/mailPwd" class="form_info common_form"
							id="register_form">
							<div class="form_item">
								<label for="pwd">注册邮箱：</label>
								<div class="form_field">
									<input name="email" class="form_text" required required_Mail/>
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_action">
								<input type="submit" value="下一步" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="inc/friendlylink.jsp"%>
</body>
</html>