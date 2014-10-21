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
Globals.page = "Index_index";
</script>
</head>
<body>
	<div class="wrap">
		
		<%@ include file="../inc/top.jsp"%>
		
		<div class="main">
			<div class="main_left_major">
				<div class="condition">
					<ul class="grade ul_flat">
						<c:forEach items="${applicationScope.gradeList}" var="gradeItem">
							
								<li<c:if test="${gradeItem.gradeEnName == grade}"> class="current"</c:if>>${gradeItem.gradeName}</li>
						</c:forEach>
					</ul>
					<ul class="subject ul_flat">
						<c:set var="gaozhong" value="${applicationScope.gradeList[0]}" />
						<c:forEach items="${gaozhong.gradeSubjectList}" var="subjectItem">
							<li<c:if test="${subjectItem.subjectEnName == subject}"> class="current"</c:if>>${subjectItem.subjectName}</li>
						</c:forEach>
					</ul>
				</div>
				
				<div class="filter">
					<ul class="ul_flat">
						<li>时间↑</li>
						<li>价格↑</li>
					</ul>
				</div>
				
				<div class="course_list">
					<c:forEach items="${courseList}" var="course">
						<div class="course_group_block">
							<a href="${ctx}/course/${course.id}.html" target="_blank"><img src="${ctx}/resource/photo/df_course.jpg"></a>
							<p>${course.courseName}</p>
							<p>${course.price}</p>
						</div>
					</c:forEach>
				</div>
			</div>
			
			<div class="interest_school side_interest">
				<h3>可能感兴趣的学校</h3>
				<p>...</p>
			</div>
			
			<div class="interest_live side_interest">
				<h3>类似的直播课</h3>
				<p>...</p>				
			</div>
		</div>
		
		<%@ include file="../inc/friendlylink.jsp"%>
		<%@ include file="../inc/copyright.jsp"%>
	</div>
</body>
</html>