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
	            	
	            	<c:if test="${subjectList != null}">
	                <ul>
	                	<li>科目：</li>
	                	<li class="${class : staticEq('all', subject)}"><a href="${ctx}/course/${grade}${link : full(param)}">全部</a></li>
						<c:forEach items="${subjectList}" var="subjectItem">
						<li class="${class : staticEq(subjectItem.subjectEnName, subject)}"><a href="${ctx}/course/${grade}/${subjectItem.subjectEnName}${link : full(param)}">${subjectItem.subjectName}</a></li>
						</c:forEach>
	                </ul>
	                </c:if>
	                
	                <c:if test="${gradeList != null}">
	                <ul>
	                	<li>类别：</li>
	                	<li class="${class : staticEq('all', grade)}"><a href="${ctx}/course/all/${subject}${link : full(param)}">全部</a></li>
						<c:forEach items="${gradeList}" var="gradeItem">
						<li class="${class : staticEq(gradeItem.gradeEnName, grade)}"><a href="${ctx}/course/${gradeItem.gradeEnName}/${subject}${link : full(param)}">${gradeItem.gradeName}</a></li>
						</c:forEach>
	                </ul>
	                </c:if>
	                
	                <c:if test="${levelList != null}">
	                <ul>
	                	<li>年级：</li>
	                	<li class="${class : startEq(param, 'level', null)}"><a href="${ctx}/course/${grade}/${subject}${link : startRemove(param, 'level')}">全部</a></li>
						<c:forEach items="${levelList}" var="levelItem">
						<li class="${class : startEq(param, 'level', levelItem.levelEnName)}"><a href="${ctx}/course/${grade}/${subject}${link : startReplace(param, levelItem.levelEnName)}">${levelItem.levelName}</a></li>
						</c:forEach>
	                </ul>
	                </c:if>
	                
	            </div>
	        </div>
	        <div class="live_resul clearfix">
	            <div class="result_sorting">
				<a class="${class : sort(param, 'price')}" href="${ctx}/course/${grade}/${subject}${link : sort(param, 'price')}">价格排序</a>
	            <a class="${class : sort(param, 'sale')}" href="${ctx}/course/${grade}/${subject}${link : sort(param, 'sale')}">销量排序</a>
	            <a class="${class : select(param, 'live')}" href="${ctx}/course/${grade}/${subject}${link : containSwitch(param, 'live')}">直播课</a>
	            </div>
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
	                            <h3 class="yuan">${course.price}<span>元</span></h3>
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