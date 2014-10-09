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
Globals.page = "Index_index";
</script>
</head>
<body>
	<div class="wrap">
		<div class="header">
			<img src="${ctx}/resource/photo/logo.png" />
			
			<c:if test="${user != null}">
				<p>welcome(${user.showName})---
				<a href="javascript:void(0);" class="logout" ><fmt:message key="index_user_userlogout"/></a>
				---you city is ${sessionScope.city}
				</p>
			</c:if>
			<c:if test="${user == null}">
				<p><a href="${ctx }/login.action" ><fmt:message key="index_user_userlogin"/></a></p>---you city is ${sessionScope.city}
			</c:if>
			
			<input type="text" name="keywords" />
			<input type="submit" />
		</div>
		
		<ul class="navigator">
			<li><a href='${ctx}/'><fmt:message key="menu_index" /></a></li>
			<li><a href='${ctx}/live'><fmt:message key="menu_live" /></a></li>
			<li><a href='${ctx}/vod'><fmt:message key="menu_vod" /></a></li>
			<li><a href='${ctx}/school'><fmt:message key="menu_school" /></a></li>
			<li><a href='${ctx}/teacher'><fmt:message key="menu_teacher" /></a></li>
		</ul>
		
		<div class="main">
			<div class="main_top">
			
				<ul class="category_grade">
					<c:forEach items="${subjectView}" var="grade">
						<li>
							<a href="${ctx}/123">${grade.gradeName}</a>
							<div class="category_subject">
							<c:forEach items="${grade.subjectList}" var="subject">
								<a href="${ctx}/123">${subject.subName}</a>
							</c:forEach>
							</div>
						</li>
					</c:forEach>
				</ul>
				
				<div class="subject_flash"></div>
				
				<div class="school_recommend">
					<c:forEach items="${schoolView}" var="grade">
						<div>
							<a href="${ctx}/123">${grade.gradeName}</a>
							<ul>
							<c:forEach items="${grade.schoolList}" var="school">
								<li><a href="${ctx}/123">${school.schoolName}</a></li>
							</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
				
				<div class="teacher_recommend"></div>
				
			</div>
			
			<div class="course_group_block"></div>
			<div class="course_group_block"></div>
			<div class="course_group_block"></div>
			
		</div>
	</div>
</body>
</html>