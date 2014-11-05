<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../inc/header2.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_index";
</script>
</head>
<body>
		<%@ include file="../inc/top.jsp"%>
		<div id="container_box">
			<div id="container_content">
			    <div class="sectionMain clr">
					<%@ include file="../inc/leftmenu.jsp"%>
					<div class="sh_info_r">
			            <div class="st_titile_r"> <a href="#">是茜空手</a><a href="#">是茜空手</a></div>
			            <div class="result_list pt10">
			                <ul>
			                    <li class="clearfix"><a href="#" class="result_img"><img src="${ctx}/resource/images/005.jpg" width="130" height="110"></a>
			                        <div class="result_con">
			                            <h3 ><a href="#" class="result_con_ti">直线与圆的位置关系</a></h3>
			                            <p class="pt1">正在直播中...</p>
			                            <p class="pt2">心流学院</p>
			                            <p class="pt3">学校信用：</p>
			                        </div>
			                        <div class="result_price">
			                            <h3 class="yuan">0000<span>元</span></h3>
			                            <p class="pt2">共2课时</p>
			                        </div>
			                        <div class="result_price">
			                            <p class="pt2">150人购买</p>
			                            <p class="pt2">满意度 88.89%</p>
			                            <p class="pt3">9 条评价</p>
			                        </div>
			                        <div class="result_price maigin_right">
			                            <p class="pt3">担保期15天</p>
			                        </div>
			                    </li>
			                </ul>
			            </div>
			            
		                <div class="sh_ke">
		                    <h2>全部课程</h2>
		                    <ul class="sh_sch_ke clr">
		                        
		                        <c:forEach items="${courseList}"  var="course" varStatus="ct">
			                        <li class="<c:if test="${ct.count%4==0}">margin_right_clear</c:if>">
			                        	<a href="${ctx}/course/${course.id}.html" target="_blank"><img src="${ctx}/resource/photo/03.jpg" width="220" height="140"></a>
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
		
		<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>