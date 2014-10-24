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

	<%@ include file="inc/top.jsp"%>
	
	<div id="container_box">
	    <div id="main">
	        <div class="column_left_a">
	            <div id="hoverpage">
	                <ul id="outer">
	                	<c:forEach items="${applicationScope.gradeList}" var="grade" varStatus="st">
	                    <li class="page p${st.index+1}">
	                    	<a href="${ctx}/course/${grade.gradeEnName}" class="<c:if test="${st.index != 0}">menuitem</c:if>"><i class="<c:if test="${st.index == 0}">label_bg</c:if>">${grade.gradeName}</i></a>
	                        <div> <em></em>
	                            <ul>
	                            	<c:forEach items="${grade.gradeSubjectList}" var="subject">
	                            	<li><a href="${ctx}/course/${grade.gradeEnName}/${subject.subjectEnName}">${subject.subjectName}</a></li>
	                            	</c:forEach>
	                            </ul>
	                        </div>
	                    </li>
	                    </c:forEach>
	                </ul>
	            </div>
	        </div>
	        <div class="column_left_b">
	            <div id="MainPromotionBanner">
	                <div id="SlidePlayer">
	                    <ul class="Slides">
	                        <li><a target="_blank" href="#"><img src="${ctx}/resource/images/01.jpg" width="728" height="300"></a></li>
	                        <li><a target="_blank" href="#"><img src="${ctx}/resource/images/02.jpg" width="728" height="300"></a></li>
	                        <li><a target="_blank" href="#"><img src="${ctx}/resource/images/03.jpg" width="728" height="300"></a></li>
	                        <li><a target="_blank" href="#"><img src="${ctx}/resource/images/04.jpg" width="728" height="300"></a></li>
	                        <li><a target="_blank" href="#"><img src="${ctx}/resource/images/05.jpg" width="728" height="300"></a></li>
	                    </ul>
	                </div>
	                <script type="text/javascript">
				TB.widget.SimpleSlide.decoration('SlidePlayer', {eventType:'mouse', effect:'scroll'});
	</script> 
	            </div>
	        </div>
	        <div class="column_left_bottom">
	            <div class="column_left_header clearfix"> <a href="#" class="column_left_title">推荐老师</a>
	                <ul class="column_left_nav">
	                	<c:forEach items="${gaozhong_subjectList}" var="subject">
	                	<li><a href="${ctx}/teacher/all/${subject.subjectEnName}">${subject.subjectName}</a></li>
	                	</c:forEach>
	                </ul>
	                <a href="${ctx}/teacher" class="column_left_more">更多</a> </div>
	            <div class="column_img_item">
	                <ul>
						<c:forEach items="${teacherList}" var="item" varStatus="st">
						<li class="<c:if test="${(st.index + 1) % 8 == 0}">img_list_margin</c:if>"><a href="${ctx}/teacher/${item.id}.html" target="_blank"><img src="${ctx}/resource/images/005.jpg" /></a><span>${item.showName}</span><span>XXX</span></li>
						</c:forEach>
	                </ul>
	            </div>
	        </div>
	        <div class="column_right_school">
	            <div class="column_school">
	                <div class="column_school_header">
	                    <h2 class="column_school_title">附近学校</h2>
	                    <a href="${ctx}/school" class="school_box_more" target="_blank">更多</a> </div>
		                <ul class="school_nav">
		                	<c:forEach items="${schoolView}" var="grade">
		                	<li><a href="${ctx}/school/${grade.gradeEnName}" target="_blank">${grade.gradeName}</a></li>
		                	</c:forEach>
		                </ul>
	            </div>
	            <div class="school_img_item">
	            	<c:forEach items="${schoolView}" var="grade" varStatus="st">
	                <ul<c:if test="${st.index > 0}"> style="display:none;"</c:if>>
						<c:forEach items="${grade.schoolList}" var="school">
	                    <li class="school_list">
	                        <div class="school_pic"><a href="${ctx}/school/${school.id}.html" target="_blank"><img src="${ctx}/resource/images/003.jpg" border="0" /></a></div>
	                        <div class="school_user">
	                            <h2>${school.schoolName}</h2>
	                        </div>
	                        <div class="school_con">
	                            <p>1000</p>
	                        </div>
	                    </li>								
						</c:forEach>
	                </ul>
	                </c:forEach>
	            </div>
	        </div>
	    </div>
	    
	    <div class="wrap_catalog_box">
	        <div class="catalog_box_header"> <a href="#" class="catalog_box_title">高中</a>
	            <ul class="catalog_box_nav">
            	    <c:forEach items="${gaozhong_subjectList}" var="subject">
                	<li><a href="${ctx}/course/gaozhong/${subject.subjectEnName}">${subject.subjectName}</a></li>
                	</c:forEach>
	            </ul>
	            <a href="${ctx}/course/gaozhong" class="catalog_box_more">更多</a>
			</div>
	        <div class="img_item">
	            <ul>
					<c:forEach items="${gaozhong_courseList}" var="item" varStatus="st">
	                <li class="img_list<c:if test="${(st.index+1)%5 == 0}"> img_list_margin</c:if>">
	                    <div class="pic"><a href="${ctx}/course/${item.id}.html" target="_blank"><img src="${ctx}/resource/photo/01.jpg" border="0" /></a></div>
	                    <div class="img_user">
	                        <h2>${item.courseName}</h2>
	                    </div>
	                    <div class="img_counts"><span>价格:<b>${item.price}</b></span><span>xxx<strong>(123)</strong></span></div>
	                </li>						
					</c:forEach>
	            </ul>
	        </div>
	    </div>
	    
	    <div class="clr"></div>
	    <div class="wrap_catalog_box">
	        <div class="catalog_box_header"> <a href="#" class="catalog_box_title">初中</a>
	            <ul class="catalog_box_nav">
            	    <c:forEach items="${chuzhong_subjectList}" var="subject">
                	<li><a href="${ctx}/course/xiaoxue/${subject.subjectEnName}">${subject.subjectName}</a></li>
                	</c:forEach>
	            </ul>
	            <a href="${ctx}/course/chuzhong" class="catalog_box_more">更多</a>
			</div>
	        <div class="img_item">
	            <ul>
					<c:forEach items="${chuzhong_courseList}" var="item" varStatus="st">
	                <li class="img_list<c:if test="${(st.index+1)%5 == 0}"> img_list_margin</c:if>">
	                    <div class="pic"><a href="${ctx}/course/${item.id}.html" target="_blank"><img src="${ctx}/resource/photo/01.jpg" border="0" /></a></div>
	                    <div class="img_user">
	                        <h2>${item.courseName}</h2>
	                    </div>
	                    <div class="img_counts"><span>价格:<b>${item.price}</b></span><span>xxx<strong>(123)</strong></span></div>
	                </li>						
					</c:forEach>
	            </ul>
	        </div>
	    </div>
	    
	    <div class="clr"></div>
	    <div class="wrap_catalog_box">
	        <div class="catalog_box_header"> <a href="#" class="catalog_box_title">小学</a>
	            <ul class="catalog_box_nav">
            	    <c:forEach items="${xiaoxue_subjectList}" var="subject">
                	<li><a href="${ctx}/course/xiaoxue/${subject.subjectEnName}">${subject.subjectName}</a></li>
                	</c:forEach>
	            </ul>
	            <a href="${ctx}/course/xiaoxue/${subject.subjectEnName}" class="catalog_box_more">更多</a>
			</div>
	        <div class="img_item">
	            <ul>
					<c:forEach items="${xiaoxue_courseList}" var="item" varStatus="st">
	                <li class="img_list<c:if test="${(st.index+1)%5 == 0}"> img_list_margin</c:if>">
	                    <div class="pic"><a href="${ctx}/course/${item.id}.html" target="_blank"><img src="${ctx}/resource/photo/01.jpg" border="0" /></a></div>
	                    <div class="img_user">
	                        <h2>${item.courseName}</h2>
	                    </div>
	                    <div class="img_counts"><span>价格:<b>${item.price}</b></span><span>xxx<strong>(123)</strong></span></div>
	                </li>						
					</c:forEach>
	            </ul>
	        </div>
	    </div>	    
	    
	</div>
	
	<div class="clr"></div>
	
	<%@ include file="inc/friendlylink.jsp"%>
	
</body>
</html>