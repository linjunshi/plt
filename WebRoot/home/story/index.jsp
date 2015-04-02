<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="杜巴克教育" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
<c:set var="description" value="杜巴克教育" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Story_index";
</script>
</head>
<body>
<div class="header_zhe">
	<div class="menu_zhe">
		<div class="logo_p">
			<img src="${ctx}/resource/images/logo.png" height="70">
		</div>
		<c:if test="${sessionScope.loginUser != null}">
			<div class="hea_new"><span>${sessionScope.loginUser.showName}</span>|<a href="${ctx}/">首页</a>|<a href="${ctx }/account/logout">注销</a></div>
		</c:if>
		<c:if test="${sessionScope.loginUser == null}">
			<div class="hea_new"><a href="${ctx}/">首页</a>|<a href="javascript:void(0);" id="loginWicket">登录</a>|<a href="${ctx}/account/regist">注册</a>|<a href="${ctx}/account/forgotPwd">忘记密码？</a></div>
		</c:if>
		<div class="zhe_home">
			<ul>
				<c:forEach items="${storyList}" var="story">
				<li>
					<p class="zhe_photo">
						<img src="${ctx}/resource/images/zhezhao.png" width="327">
					</p>
					<p class="zhe_im">
						<img src="${ctx}/resource/images/${story.storyEname}_Cover.png" width="260" height="175" alt="${story.storyName}" title="${story.storyName}">
					</p>
					<p>
						<a href="javascript:void(0);" url_attr="${ctx}/story/game/${story.storyEname}?demo=0" class="zhe_but_c isLogin" title="${story.storyName}">&nbsp;</a>
						<a href="javascript:void(0);" url_attr="${ctx}/story/game/${story.storyEname}?demo=1" class="zhe_but_b isLogin" title="${story.storyName}">&nbsp;</a>
					</p>
				</li>
				</c:forEach>
<%-- 				<li>
					<p class="zhe_photo">
						<img src="${ctx}/resource/images/zhezhao.png" width="327">
					</p>
					<p class="zhe_im">
						<img src="${ctx}/resource/images/ShiPinJiaoCheng_Cover.png" width="260" height="175">
					</p>
					<p>
						<a href="javascript:void(0);" url_attr="${ctx}/story/direction" class="zhe_but_a isLogin">&nbsp;</a>
					</p>
				</li> --%>
			</ul>
		</div>
		<div style="width:712px;  display:inline-block; position:absolute; top:810px; left:480px;">
	        <c:set var="basicUrl" value="${ctx}/story" />
	       	<%@ include file="../inc/pagination_new.jsp"%>
		</div>
	</div>
	<div class="footer_p">
		 <p>版权所有：深圳市课云网络有限公司  粤ICP备15022223号</p>
	</div>
</div>
</body>
</html>
