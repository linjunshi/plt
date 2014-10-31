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
Globals.page = "Index_teacher";
</script>
</head>
<body>
	<%@ include file="../inc/top.jsp"%>
	<div id="container_box">
		<div class="container_content">
			<div class="list_category">
	            <div class="cate_list">
	            	
	            	<c:if test="${subjectList != null}">
	                <ul>
	                	<li>科目：</li>
	                	<li class="${class : staticEq('all', subject)}"><a href="${ctx}/teacher/${grade}${link : full()}">全部</a></li>
						<c:forEach items="${subjectList}" var="subjectItem">
						<li class="${class : staticEq(subjectItem.subjectEnName, subject)}"><a href="${ctx}/teacher/${grade}/${subjectItem.subjectEnName}${link : full()}">${subjectItem.subjectName}</a></li>
						</c:forEach>
	                </ul>
	                </c:if>
	                
	                <c:if test="${gradeList != null}">
	                <ul>
	                	<li>类别：</li>
	                	<li class="${class : staticEq('all', grade)}"><a href="${ctx}/teacher/all/${subject}${link : full()}">全部</a></li>
						<c:forEach items="${gradeList}" var="gradeItem">
						<li class="${class : staticEq(gradeItem.gradeEnName, grade)}"><a href="${ctx}/teacher/${gradeItem.gradeEnName}/${subject}${link : full()}">${gradeItem.gradeName}</a></li>
						</c:forEach>
	                </ul>
	                </c:if>
	                
	            </div>
	        </div>		
		    <div class="tesch_box">
		        <div class="schoool_box_list clearfix">
		            <ul>
		            	<c:forEach items="${teacherList}" var="teacher" varStatus="st">
		                <li class="<c:if test="${(st.index+1)%5==0}">margin_right_clear</c:if>"><a href="${ctx}/teacher/${teacher.id}.html"><img src="${ctx}/resource/images/93.jpg" width="220" height="140"></a>
		                    <h2>${teacher.showName}</h2>
		                    <p>${teacher.schoolName}</p>
		                </li>
		            	</c:forEach>
		            </ul>
		        </div>
		    </div>
		</div>
	</div>
    <c:set var="basicUrl" value="${ctx}/teacher/${grade}/${subject}" />
   	<%@ include file="../inc/pagination.jsp"%>
	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>