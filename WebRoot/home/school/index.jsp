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
Globals.page = "Index_school";
</script>
</head>
<body>
	<%@ include file="../inc/top.jsp"%>
	<div id="container_box">
		<div class="container_content">
		    <div class="tesch_box">
		        <div class="teach_sea">
		        	<a class="${class : staticEq('all', grade)}" href="${ctx}/school">全部</a>
			    	<c:forEach items="${applicationScope.gradeList}" var="item">
			    	<a class="${class : staticEq(item.gradeEnName, grade)}" href="${ctx}/school/${item.gradeEnName}">${item.gradeName}</a>
			    	</c:forEach>
		        </div>
		        <div class="schoool_box_list clr">
		            <ul>
		            	<c:forEach items="${schoolList}" var="school" varStatus="st">
		                <li class="<c:if test="${(st.index+1)%5==0}">margin_right_clear</c:if>"><a href="${ctx}/school/${school.id}.html"><img src="${ctx}/resource/photo/default_school.jpg" width="220" height="140"></a>
		                    <h2>${school.schoolName}</h2>
		                </li>
		            	</c:forEach>
		            </ul>
		        </div>
		    </div>
		</div>
	</div>
    <c:set var="basicUrl" value="${ctx}/school/${grade}" />
   	<%@ include file="../inc/pagination.jsp"%>
	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>
