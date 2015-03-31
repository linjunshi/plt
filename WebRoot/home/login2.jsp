<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="杜巴克在线口语平台-用户登录" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_login";
</script>
</head>
<style type="text/css">
.system_tip {position: relative;float: left; width:380px; line-height:26px;min-height:26px; margin:0px 250px 10px 200px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
</style>
<body>

<div class="header">
	<%@ include file="inc/top_new.jsp"%>
	<div class="content clearfix">
			<div class="sh_info_r">
				<div class="st_titile_r sh_title">
					<h2>用户登录</h2>
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
						
						<form action="${ctx}/account/login" method="post" class="form_vertical common_form">
							<div class="form_item">
								<label for="username">用&nbsp; 户  名：</label>
								<div class="form_field">
									<input placeholder="5-32个字符" class="form_text" name="username" type="text" required />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label for="pwd">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
								<div class="form_field">
									<input name="password" id="pwd" class="form_text" type="password" required />
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_action">
								<input class="btn_regist" type="submit" value="登录" name="commit" />
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
