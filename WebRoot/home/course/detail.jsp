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
Globals.page = "Index_course";
</script>
</head>
<body>
	<div class="wrap">
		
		<%@ include file="../inc/top.jsp"%>
		
		<div class="main">
			<div class="course_view_info">
				<img src="${ctx}/resource/photo/df_course.jpg" />
			</div>
			
			<div class="course_text_info">
				<p>title:${course.courseName}</p>
				<p>teacher:${course.teacherName}</p>
				<p>price:${course.price}</p>
				<p><input type="button" value="buy" /></p>
			</div>
			
			<div class="course_owner_info">
				<img src="${ctx}/resource/photo/T136.jpg" />
				<p>${teacher.showName}</p>
				<p>xxx</p>
			</div>
			
			<div class="course_detail_info">
				<ul class="course_intro_menu ul_flat">
					<li>课程介绍</li>
					<li>课程大纲</li>
					<li>评价</li>
				</ul>
				<div class="course_summary course_intro_detail">
					<div style="height:400px">111</div>
				</div>
				<div class="course_outline course_intro_detail hide">
					<div style="height:400px">222</div>
				</div>
				<div class="course_comment course_intro_detail hide">
					<div style="height:400px">333</div>
				</div>
			</div>
			
			<div class="course_similar_other">
				<h3>相似的课程</h3>
				<p>.....</p>
			</div>
			
			<div class="course_owner_other">
				<h3>该老师其他课程</h3>
				<p>.....</p>
			</div>
		</div>
		
		<%@ include file="../inc/friendlylink.jsp"%>
		<%@ include file="../inc/copyright.jsp"%>
	</div>
</body>
</html>