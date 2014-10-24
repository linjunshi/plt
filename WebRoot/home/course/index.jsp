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
	    <div class="list_content">
	        <div class="list_category">
	            <div class="live_title"><a href="#">首页></a> <span></span><span>所有分类</span> <span>共有10236个</span></div>
	            <div class="cate_list">
	            
	                <ul>
	                	<li>科目：</li>
	                	<li<c:if test="${subject == 'all'}"> class="current"</c:if>><a href="${ctx}/course/${grade}/all">全部</a></li>
						<c:forEach items="${subjectList}" var="subjectItem">
						<li<c:if test="${subjectItem.subjectEnName == subject}"> class="current"</c:if>><a href="${ctx}/course/${grade}/${subjectItem.subjectEnName}">${subjectItem.subjectName}</a></li>
						</c:forEach>
	                </ul>
	                          
	                <ul>
	                	<li>类别：</li>
	                	<li<c:if test="${grade == 'all'}"> class="current"</c:if>><a href="${ctx}/course/all/${subject}">全部</a></li>
						<c:forEach items="${gradeList}" var="gradeItem">
						<li<c:if test="${gradeItem.gradeEnName == grade}"> class="current"</c:if>><a href="${ctx}/course/${gradeItem.gradeEnName}/${subject}">${gradeItem.gradeName}</a></li>
						</c:forEach>
	                </ul>
	                
	                <c:if test="${levelList != null}">
	                <ul>
	                	<li>年级：</li>
	                	<li<c:if test="${level == null}"> class="current"</c:if>><a href="${ctx}/course/all/${subject}">全部</a></li>
						<c:forEach items="${levelList}" var="levelItem">
						<li<c:if test="${levelItem.levelEnName == level}"> class="current"</c:if>><a href="${ctx}/course/${grade}/${subject}">${levelItem.levelName}</a></li>
						</c:forEach>
	                </ul>
	                </c:if>
	                
	            </div>
	        </div>
	        <div class="live_resul clearfix">
	            <div class="result_sorting"><a href="#">默认排序</a><a href="#">默认排序</a><a href="#">默认排序</a><a href="#">直播课</a></div>
	            <div class="result_list">
	                <ul>
						<c:forEach items="${courseList}" var="course">
						<li class="clearfix"><a href="${ctx}/course/${course.id}.html" target="_blank" class="result_img"><img src="${ctx}/resource/images/005.jpg" width="130" height="110"></a>
	                        <div class="result_con">
	                            <h3 ><a href="${ctx}/course/${course.id}.html" target="_blank" class="result_con_ti">${course.courseName}</a></h3>
	                            <p class="pt1">${course.price}</p>
	                            <p class="pt2">${course.price}</p>
	                            <p class="pt3">${course.price}</p>
	                        </div>
	                        <div class="result_price">
	                            <h3 class="yuan">2365<span>元</span></h3>
	                            <p class="pt2">${course.price}</p>
	                            <p class="pt3">${course.price}</p>
	                        </div>
	                        <div class="result_price">
	                            <p class="pt2">${course.price}</p>
	                            <p class="pt2">${course.price}</p>
	                            <p class="pt3">${course.price}</p>
	                        </div>
	                        <div class="result_price maigin_right">
	                            <p class="pt3">${course.price}</p>
	                        </div>
	                    </li>
						</c:forEach>
	                </ul>
	            </div>
	            <div class="paging"><a href="#">第一页</a><a href="#">1</a><a href="#">2</a> <a href="#">3</a><a href="#">4</a><a href="#">1</a><a href="#">4</a> <a href="#">确定</a> </div>
	        </div>
	        <div class="clr"></div>
	    </div>
	    <div class="list_hot">
	        <div class="list_hot_hd">推荐老师</div>
	        <c:forEach items="${teacherList}" var="teacher">
	        <div class="list_hd_img"><a href="#"><img src="${ctx}/resource/images/93.jpg" width="190" height="180" border="0"></a>
	            <p><a href="#">${teacher.showName}</a></p>
	            <p><span>xxx</span></p>
	        </div>
	        </c:forEach>
	    </div>
	    
	    <div class="list_hot">
	        <div class="list_hot_hd">附近的学校</div>
	        <c:forEach items="${schoolList}" var="school">
	        <div class="list_hd_img"><a href="#"><img src="${ctx}/resource/images/93.jpg" width="190" height="180" border="0"></a>
	            <p><a href="#">${school.schoolName}</a></p>
	            <p><span>xxx</span></p>
	        </div>
	        </c:forEach>
	    </div>
	    <div class="clr"></div>
	</div>
	
	<%@ include file="../inc/friendlylink.jsp"%>

</body>
</html>