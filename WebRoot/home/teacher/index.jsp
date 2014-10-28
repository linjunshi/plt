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
	    <div class="tesch_box">
	        <div class="teach_sea"><a href="#">全部</a><a href="#">人气排行</a></div>
	        <div class="schoool_box_list clearfix">
	            <ul>
	            	<c:forEach items="${teacherList}" var="teacher">
	                <li><a href="${ctx}/teacher/${teacher.id}.html"><img src="${ctx}/resource/photo/02.jpg" width="220" height="140"></a>
	                    <h2>${teacher.showName}</h2>
	                    <p><span>2136456</span>关注</p>
	                </li>
	            	</c:forEach>
	            </ul>
	        </div>
	    </div>
	</div>
	<div class="clr"></div>
	
	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>