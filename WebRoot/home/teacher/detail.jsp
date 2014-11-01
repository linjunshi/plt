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
Globals.page = "Index_teacherDetail";
</script>
</head>
<body>
		<%@ include file="../inc/top.jsp"%>
		<div id="container_box">
			<div id="container_content">
				<div class="th_box clr">
					<div class="th_box_left">
						<img src="${ctx}/resource/images/006.png" width="170" height="150">
						<div class="profile_identity">
							<p>${teacher.username}</p>
							<p>所属学校：${teacher.schoolName}</p>
							<p>教学科目：${teacher.subjectName}</p>
						</div>
					</div>
					<div class="th_box_right">
						<div class="th_box_con">
							<h2>简介</h2>
							<div>${teacher.remark}</div>
						</div>
					</div>
				</div>
				<div class="th_box clr">
					<div class="teacher_course">他的课程</div>
					<div class="th_box_left">
						<ul class="th_left_sid">
							<li class="<c:if test="${query.gradeEnName == null || query.gradeEnName == ''}">current</c:if>"><a href="${ctx}/teacher/${teacher.id}.html">全部课程</a></li>
							<c:forEach items="${applicationScope.gradeList}" var="grade">
							<c:forEach items="${grade.gradeLevelList}" var="level">
							<li class="<c:if test="${grade.gradeEnName == query.gradeEnName && level.levelEnName == query.levelEnName}">current</c:if>"><a href="${ctx}/teacher/${id}.html?grade=${grade.gradeEnName}&level=${level.levelEnName}">${grade.gradeName}${level.levelName}</a></li>
							</c:forEach>
							</c:forEach>
						</ul>
					</div>
					<div class="th_box_right">
						<ul class="th_items_as clr">
	            			<c:forEach items="${courseList}" var="course" varStatus="clt">
								<li class="th_items">
									<a href="${ctx}/course/${course.id}.html"><img src="${ctx}/resource/images/356.jpg" width="240" height="160"></a>
									<div class="titi">
										<strong>${course.courseName}</strong>
									</div>
									<div class="infos">
										${course.teacher}
									</div>
									<div class="infos">
										${course.chapterCount}课时
									</div>
									<div class="course_operate">
										<p class="pt6">${course.price}元</p>
										<p class="pt7">
											<span>${course.saleCount}人购买</span><span class="right">${course.collectCount} 条评论</span>
										</p>
										<p class="pt8">
											结束时间：<fmt:formatDate value="${course.endTime}" type="date"/>
										</p>
										<p class="pt9">
											<a href="${ctx}/course/${course.id}.html" target="_blank">[详细]</a>
										</p>
									</div>
								</li>
	            			</c:forEach>
						</ul>
						
						<c:if test="${grade != null && grade != '' }">
							<c:set var="basicUrl" value="${ctx}/teacher/${teacher.id}.html?grade=${grade}&level=${level}" />
						</c:if>
						<c:if test="${grade == null || grade == '' }">
			            <c:set var="basicUrl" value="${ctx}/teacher/${teacher.id}.html" />
			            </c:if>
		            	<%@ include file="../inc/pagination.jsp"%>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>