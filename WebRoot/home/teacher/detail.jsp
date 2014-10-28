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
		
		<%@ include file="../inc/top.jsp"%>

		<div id="container_box">
			<div class="th_box clearfix">
				<div class="th_box_left">
					<a href="#"><img src="${ctx}/resource/images/006.png" width="170" height="100"></a>
					<ul class="th_left_sid">
						<li><a href="#">TA的动态</a></li>
						<li><a href="#">TA的动态</a></li>
						<li><a href="#">TA的动态</a></li>
						<li><a href="#">TA的动态</a></li>
					</ul>
				</div>
				<div class="th_box_right">
				<div class="th_box_con clearfix">
					<div class="profile_identity">
						<h2>${teacher.username}</h2>
						<p class="te_mi">毕业院校：${teacher.graduateSchool}&nbsp;&nbsp;&nbsp;职称：${teacher.positional}&nbsp;&nbsp;&nbsp;学历：${teacher.education}</p>
						<p>简介：</p>
						<p>${teacher.remark}</p>
					</div>
					<div class="teacher_intro">他的课程</div>
						<ul class="th_items_as">
							
	            			<c:forEach items="${courseList}" var="course" varStatus="clt">
								<li class="th_items"><a href="#"><img
										src="${ctx}/resource/images/356.jpg" width="240" height="160"></a>
									<div class="titi">
										<strong><a href="#">课程名字:</a></strong><a href="#">${course.courseName}</a>
									</div>
									<div class="infos">
										主讲教师：<a href="${ctx}/teacher/"> ${course.teacher}</a> 课程数：171节 <em>（${course.endTime}分钟）</em> 
										内容简介:<a href="${ctx}/course/detail?id=${course.id}"> [详细]</a>
									</div>
									<div class="course_operate">
										<p class="pt6">¥${course.price}</p>
										<p class="pt7">
											<a href="#">打包买更优惠</a>
										</p>
										<p class="pt8">
											2人购买<a href="${ctx}/course/course_comment?cid=${course.id}"> 有 ${course.collectCount} 条评论</a>
										</p>
										<p class="pt9">
											<a href="#">加入购特车</a>
										</p>
									</div>
								</li>
	            			</c:forEach>
							
						</ul>
					</div>
				</div>
			</div>
		</div>

		<%@ include file="../inc/friendlylink.jsp"%>
		<%@ include file="../inc/copyright.jsp"%>
</body>
</html>