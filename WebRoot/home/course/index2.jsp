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
	<%@ include file="../inc/top.jsp"%>
	<div id="container_box">
		<div class="container_content">
		    <div class="list_content">
		        <div class="list_category">
		            <div class="cate_list">
		            	<h3>科目：</h3>
	                	<ul>
			            	<c:if test="${subjectList != null}">
			                	<li class="${class : staticEq('all', subject)}"><a href="${ctx}/course/${grade}${link : full()}">全部</a></li>
								<c:forEach items="${subjectList}" var="subjectItem">
								<li class="${class : staticEq(subjectItem.subjectEnName, subject)}"><a href="${ctx}/course/${grade}/${subjectItem.subjectEnName}${link : full()}">${subjectItem.subjectName}</a></li>
								</c:forEach>
			                </c:if>
		                </ul>
		             </div>
		             <div class="cate_list">   
	                	<h3>学段：</h3>
		                <ul>
			                <c:if test="${gradeList != null}">
			                	<li class="${class : staticEq('all', grade)}"><a href="${ctx}/course/all/${subject}${link : full()}">全部</a></li>
								<c:forEach items="${gradeList}" var="gradeItem">
								<li class="${class : staticEq(gradeItem.gradeEnName, grade)}"><a href="${ctx}/course/${gradeItem.gradeEnName}/${subject}${link : full()}">${gradeItem.gradeName}</a></li>
								</c:forEach>
			                </c:if>
		                </ul>
		             </div>    
	                 <c:if test="${levelList != null}">
		                 <div class="cate_list borNone"> 
		                	<h3>年级：</h3>
			                <ul>
			                	<li class="${class : startEq('level', null)}"><a href="${ctx}/course/${grade}/${subject}${link : startRemove('level')}">全部</a></li>
								<c:forEach items="${levelList}" var="levelItem">
								<li class="${class : startEq('level', levelItem.levelEnName)}"><a href="${ctx}/course/${grade}/${subject}${link : startReplace('level', levelItem.levelEnName)}">${levelItem.levelName}</a></li>
								</c:forEach>
			                </ul>
			              </div>
	                  </c:if>
		                
		        </div>
		        <div class="live_resul clr">
		            <div class="result_sorting">
		            <a class="sort ${class : sortWithout()}" href="${ctx}/course/${grade}/${subject}${link : sortWithout()}"><span class="ico"></span><span>默认排序</span></a>
					<a class="sort ${class : sort('price')}" href="${ctx}/course/${grade}/${subject}${link : sort('price')}"><span class="ico"></span><span>价格排序</span></a>
		            <a class="sort ${class : sort('saleCount')}" href="${ctx}/course/${grade}/${subject}${link : sort('saleCount')}"><span class="ico"></span><span>销量排序</span></a>
		            <a class="select ${class : select('live')}" href="${ctx}/course/${grade}/${subject}${link : containSwitch('live')}"><span class="ico"></span><span>直播课</span></a>
		            </div>
		            <div class="result_list">
		                <ul class="clr">
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
		            
		            <c:set var="basicUrl" value="${ctx}/course/${grade}/${subject}${link : full()}" />
	            	<%@ include file="../inc/pagination.jsp"%>
	            	
		        </div>
		    </div>
		    <div class="r" style="width:210px;">
			    <div class="list_hot">
			        <div class="list_hot_hd">
			        	<h2 class="list_hot_title">附近学校</h2>
			        	<a href="${ctx}/school" class="list_box_more" target="_blank">更多</a>
			        </div>
			        <c:forEach items="${schoolList}" var="school">
			        <div class="list_hd_img">
			            <p><a href="${ctx}/school/${school.id}.html" target="_blank">${school.schoolName}</a></p>
			        </div>
			        </c:forEach>
			    </div>
			    
			    <div class="list_hot">
			        <div class="list_hot_hd">
			        	<h2  class="list_hot_title">推荐老师</h2>
			        	<a href="${ctx}/teacher" class="list_box_more" target="_blank">更多</a>
			        </div>
			        <c:forEach items="${teacherList}" var="teacher">
			        <div class="list_hd_img">
			        	<a href="${ctx}/teacher/${teacher.id}.html" target="_blank"><img src="${ctx}/${teacher.headPhoto}" width="190" height="180" border="0"></a>
			            <p>${teacher.showName}</p>
			        </div>
			        </c:forEach>
			    </div>
		    </div>
		</div>
	</div>
	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>