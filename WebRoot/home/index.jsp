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

		<%@ include file="inc/top.jsp"%>
		
		<div class="main">
			<div class="main_top">
			
				<div class="category_block">
					<ul class="category_grade">
						<c:forEach items="${applicationScope.gradeList}" var="grade">
							<li>
								<a href="${ctx}/course/${grade.gradeEnName}">${grade.gradeName}</a>
								<div class="category_subject hide">
								<c:forEach items="${grade.gradeSubjectList}" var="subject">
									<a href="${ctx}/course/${grade.gradeEnName}/${subject.subjectEnName}">${subject.subjectName}</a>
								</c:forEach>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				
				<div class="subject_flash"><img src="${ctx}/resource/photo/flash1.jpg" /></div>
				
				<div class="school_recommend">
					<c:forEach items="${schoolView}" var="grade">
						<div>
							<div class="school_grade">
								<a href="${ctx}/school/${grade.gradeEnName}" target="_blank">${grade.gradeName}</a>
							</div>
							<ul>
							<c:forEach items="${grade.schoolList}" var="school">
								<li><a href="${ctx}/school/${school.id}.html" target="_blank">${school.schoolName}</a></li>
							</c:forEach>
							</ul>
						</div>
					</c:forEach>
				</div>
				
				<div class="teacher_recommend">
					<c:forEach items="${teacherList}" var="item">
						<div class="teacher_index_wrap">
							<a href="${ctx}/teacher/${item.id}.html" target="_blank"><img src="${ctx}/resource/photo/T136.jpg" width="150" height="130" /></a>
							<p>title:${item.showName}</p>
							<p>xxx</p>
						</div>
					</c:forEach>
				</div>
				
			</div>
			
			<div class="course_group_block"></div>
			<div class="course_group_block">
				<c:forEach items="${gaozhong_vodList}" var="item">
					<div class="course_index_wrap">
						<a href="${ctx}/course/${item.id}.html" target="_blank"><img src="${ctx}/resource/photo/df_course.jpg" width="220" height="185" /></a>
						<p>title:${item.courseName}</p>
						<p>price:${item.price}</p>
					</div>
				</c:forEach>
			</div>

			<div class="course_group_block">
				<c:forEach items="${chuzhong_vodList}" var="item">
					<div class="course_index_wrap">
						<a href="${ctx}/course/${item.id}.html" target="_blank"><img src="${ctx}/resource/photo/df_course.jpg" width="220" height="185" /></a>
						<p>title:${item.courseName}</p>
						<p>price:${item.price}</p>
					</div>
				</c:forEach>
			</div>
			
			<div class="course_group_block">
				<c:forEach items="${xiaoxue_vodList}" var="item">
					<div class="course_index_wrap">
						<a href="${ctx}/course/${item.id}.html" target="_blank"><img src="${ctx}/resource/photo/df_course.jpg" width="220" height="185" /></a>
						<p>title:${item.courseName}</p>
						<p>price:${item.price}</p>
					</div>
				</c:forEach>
			</div>
			
		</div>
		
		<%@ include file="inc/friendlylink.jsp"%>
		<%@ include file="inc/copyright.jsp"%>
	</div>
</body>
</html>