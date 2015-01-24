<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_course";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../inc/top_new.jsp"%>
	<div class="content clearfix">
		<div class="questions_list">
			<div class="filter_breadcrumb clearfix">
			    <div class="filter_content">
					<div class="filter_title">科目:</div>
					<ul class="filter_ul">
						<c:if test="${subjectList != null}">
							<li class="${class : staticEq('all', subject)}"><a href="${ctx}/course${link : full()}">全部</a></li>
							<c:forEach items="${subjectList}" var="subjectItem">
							<li class="${class : staticEq(subjectItem.subjectEnName, subject)}"><a href="${ctx}/course/${subjectItem.subjectEnName}${link : full()}">${subjectItem.subjectName}</a></li>
							</c:forEach>
						</c:if>
					</ul>
			    </div>
			    <div class="filter_content">
					<div class="filter_title">年级:</div>
					<ul class="filter_ul">
						<li class="${class : startEq('level', null)}"><a href="${ctx}/course/${subject}${link : startRemove('level')}">全部</a></li>
						<c:forEach items="${levelList}" var="levelItem">
						<li class="${class : startEq('level', levelItem.levelEnName)}"><a href="${ctx}/course/${subject}${link : startReplace('level', levelItem.levelEnName)}">${levelItem.levelName}</a></li>
						</c:forEach>
					</ul>
			    </div>
			</div>
			<div class="live_resul clearfix">
				<div class="result_sorting">
					<a class="sort ${class : sortWithout()}" href="${ctx}/course/${subject}${link : sortWithout()}"><span class="ico"></span><span>默认排序</span></a>
					<a class="sort ${class : sort('price')}" href="${ctx}/course/${subject}${link : sort('price')}"><span class="ico"></span><span>价格排序</span></a>
					<a class="sort ${class : sort('saleCount')}" href="${ctx}/course/${subject}${link : sort('saleCount')}"><span class="ico"></span><span>销量排序</span></a>
				</div>
				<div class="result_list">
					<ul>
						<c:forEach items="${courseList}" var="course">
							<li class="clearfix"><a href="${ctx}/course/${course.id}.html" target="_blank" class="result_img"><img src="${ctx}${course.thumbnail}" width="130" height="110"></a>
								<div class="result_con">
									<h3 ><a href="${ctx}/course/${course.id}.html" target="_blank" class="result_con_ti">${course.courseName}</a></h3>
									<p class="pt1">${course.teacher}</p>
									<p class="pt2">${course.chapterCount}<span>课时</span></p>
								</div>
								<div class="result_price">
									<h3 class="yuan">${course.price}<span>元</span></h3>
									<p class="pt2">${course.saleCount}<span>购买</span></p>
									<p class="pt2">${course.commentCount}<span>评论</span></p>
								</div>
								<div class="result_price margin_right_clear">
									<p class="pt2">结束时间</p>
									<p class="pt3">${course.endTime}</p>
								</div>
							</li>
						</c:forEach>                    
					</ul>
				</div>
		        <c:set var="basicUrl" value="${ctx}/course/${subject}${link : full()}" />
		       	<%@ include file="../inc/pagination_new.jsp"%>				
			</div>
		</div>
	</div>
</div>
</body>
</html>