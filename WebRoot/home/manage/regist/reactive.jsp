<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="杜巴克" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
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
				<div class="sh_title">
					<h2>帐号激活</h2>
				</div>
				<c:if test="${rs == 1}">
				<form action="${ctx}/account/reactive" method="post">
					<div class="sh_act_request">
						<input type="submit" value="重新发送激活邮件"/>
					</div>
				</form>
				</c:if>
				
				<c:if test="${rs == 0}">
				<div class="sh_act_request">
		        	<p>激活邮件发送成功，请登录注册邮箱激活账号，<b>1</b>分钟以后可以重新发送。</p>
		        </div>
				</c:if>
				
				<c:if test="${rs == 10}">
				<div class="sh_act_request">
		        	<p>帐号已经激活!</p>
		        </div>
				</c:if>	
			</div>
		</div>
	</div>
</div>
<%@ include file="../../inc/footer.jsp"%>
</body>
</html>
