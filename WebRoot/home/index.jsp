<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="课云"></c:set>
<c:set var="keywords" value="课云教育"></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱"></c:set>
<%@ include file="inc/header_new.jsp"%>
<script type="text/javascript">
	var Globals = {};
	Globals.ctx = "${ctx}";
	Globals.lang = "${lang}";
	Globals.page = "Index_index";
</script>
</head>
<body>
	<div class="header">
		<div class="main">
			<div class="hea_top">
				<div class="logo">
					<img src="${ctx}/resource/images/logo.png" width="260" height="52">
				</div>
				<c:if test="${sessionScope.loginUser != null}">
					<div class="hea_new"><span>${sessionScope.loginUser.showName}</span>|<a href="${ctx}/study/syllabus">学习中心</a>|<a href="${ctx}/study/center">个人空间</a>|<a href="${ctx }/account/logout">注销</a></div>
				</c:if>				
				<div class="hea_nav">
					<a href="${ctx}/" class="a_a">首页</a>
					<a href="${ctx}/weike" class="a_b">学堂</a>
					<a href="${ctx}/course" class="a_c">直播课</a>
					<a href="${ctx}/war" class="a_d">挑战</a>
				</div>
				<div class="gun_img">
					<img src="${ctx}/resource/images/gun_img.jpg" width="620" height="300">
				</div>
				<div class="con_login">
					<c:if test="${sessionScope.loginUser == null}">
						<form action="${ctx}/account/login" method="post"
							class="common_form">
							<p>
								<input type="text" name="username" placeholder="用户名" class="con_user" required />
							</p>
							<p>
								<input type="password" name="password" placeholder="密码" class="con_user" required />
							</p>
							<p>
								<a href="javascript:void(0);" class="con_user_but form_submit">登录</a>
								<a href="${ctx}/account/regist" class="con_user_but">注册</a>
								<a href="${ctx}/account/forgotPwd" target="_blank" class=" con_not_psd">忘记密码</a>
							</p>
						</form>
					</c:if>
					<c:if test="${sessionScope.loginUser != null}">
						<div class="user_info_hidd">
							<h2 class="user_info_sid"><img src="${ctx}${sessionScope.loginUser.headPhoto}" width="50" height="50"><span>欢迎你，${sessionScope.loginUser.showName}</span></h2>
							<p><a href="${ctx }/friend">找找朋友</a></p>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<div class="content clearfix">
			<div class="con_column">
				<h2>最新视频</h2>
				<ul>
					<c:forEach items="${xiaoxue_subjectList}" var="subject">
						<li><a href="${ctx}/${xiaoxue_courseType}/${subject.subjectEnName}">${subject.subjectName}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="con_col_list">
				<ul>
					<c:forEach items="${xiaoxue_courseList}" var="item" varStatus="st">
						<li class="<c:if test="${(st.index+1)%5 == 0}">mar</c:if>"><a
							href="${ctx}/${xiaoxue_courseType}/${item.id}.html" target="_blank"><img
								src="${ctx}${item.thumbnail}" width="166" height="180"></a>
							<h2>${item.courseName}</h2>
							<p class="con_listen">
								购买：<b>${item.saleCount}</b>
							</p>
							<p class="con_but">
								<a href="${ctx}/${xiaoxue_courseType}/${item.id}.html" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
							</p>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="footer "></div>
</body>
</html>
