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
Globals.page = "Index_schoolDetail";
</script>
</head>
<body>
		<%@ include file="../inc/top.jsp"%>
		<div id="container_box">
			<div id="container_content">
			    <div class="sectionMain clr">
			        <div>
			            <div class="sh_info_le">
			                <div class="sh_info_img"><img src="${ctx}/resource/images/003.jpg" width="160" height="100"></div>
			                <div style="text-indent:20px; font-size:18px; line-height:36px; margin-bottom:20px;">${school.schoolName}</div>
		                    <div class="sh_list_switch">
								<dl class="<c:if test="${query.gradeEnName == null || query.gradeEnName == ''}">current</c:if>">
									<dt><a href="${ctx}/school/${school.id}.html">全部课程</a></dt>
								</dl>
								<c:forEach items="${gradeList}" var="grade">
								<c:forEach items="${grade.gradeLevelList}" var="level">
								<dl class="<c:if test="${grade.gradeEnName == query.gradeEnName && level.levelEnName == query.levelEnName}">current</c:if>">
									<dt><a href="${ctx}/school/${id}.html?grade=${grade.gradeEnName}&level=${level.levelEnName}">${grade.gradeName}${level.levelName}</a></dt>
								</dl>
								</c:forEach>
								</c:forEach>
		                    </div>
			            </div>
			            <div class="sh_info_r">
			                <div class="sh_all ">
			                    <h2>老师阵容</h2>
			                    <a href="#" class="catalog_box_more">换一换</a>
			                    <ul class="sh_all_tea ">
			                    
			                        <c:forEach items="${teacherList}"  var="teacher" varStatus="st">
			                        	<li class="<c:if test="${st.count%4==0}">margin_right_clear</c:if>">
				                        	<a href="${ctx}/teacher/${teacher.id}.html" target="_blank"><img src="${ctx}/resource/images/003.jpg " width="120" height="80"></a>
				                            <p>${teacher.username}</p>
				                        </li>
			                        </c:forEach>
			                        
			                    </ul>
			                </div>
			                <div class="sh_ke">
			                    <h2>全部课程</h2>
			                    <ul class="sh_sch_ke clr">
			                        
			                        <c:forEach items="${courseList}"  var="course" varStatus="ct">
				                        <li class="<c:if test="${ct.count%4==0}">margin_right_clear</c:if>">
				                        	<a href="${ctx}/course/${course.id}.html" target="_blank"><img src="${ctx}${course.thumbnail}" width="220" height="140"></a>
				                            <h2>${course.courseName}</h2>
				                            <p><b>${course.price}</b>元</p>
				                            <p><span class="sch_unm">${course.commentCount}</span>评论</p>
				                            <p><span class="sch_unm">${course.saleCount}</span>购买</p>
				                        </li>
			                        </c:forEach>
			                        
			                    </ul>
								<c:if test="${grade != null && grade != '' }">
									<c:set var="basicUrl" value="${ctx}/school/${school.id}.html?grade=${grade}&level=${level}" />
								</c:if>
								<c:if test="${grade == null || grade == '' }">
					            <c:set var="basicUrl" value="${ctx}/school/${school.id}.html" />
					            </c:if>
				            	<%@ include file="../inc/pagination.jsp"%>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
		<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>