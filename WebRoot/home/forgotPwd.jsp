<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_forgotPwd";
</script>
<style type="text/css">
.system_tip {position: relative;float: left; width:350px; line-height:26px;min-height:26px; margin:0px 200px 10px 100px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
</style>
</head>
<body>
<div class="header">
	<%@ include file="inc/top_new.jsp"%>
	<div class="content clearfix">
			<div class="sh_info_r">
				<div class="st_titile_r sh_title">
					<h2>找回密码</h2>
				</div>
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
								<label for="pwd">通过邮箱找回：</label>
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
<%@ include file="inc/footer.jsp"%>
</body>
</html>
