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
		        <div class="schoool_box_list clearfix">
		            <ul>
		            	<c:forEach items="${schoolList}" var="school">
		                <li><a href="${ctx}/school/${school.id}.html"><img src="${ctx}/resource/photo/02.jpg" width="220" height="140"></a>
		                    <h2>${school.schoolName}</h2>
		                    <p><span>2136456</span>关注</p>
		                </li>
		            	</c:forEach>
		            </ul>
		        </div>
		    </div>
		</div>
	</div>
	
	<div class="pagination">
		<c:if test="${query.pageNum > 1}">
		<a href="${ctx}/school/${grade}?page=${query.pageNum - 1}" title="上一页">上一页</a>
		</c:if>
		<c:forEach items="${query.pageSequence}" var="p">
		<a href="${ctx}/school/${grade}?page=${p}" title="第${p}页">${p}</a>
		</c:forEach>
		<c:if test="${query.pageNum < query.pageCount}">
		<a href="${ctx}/school/${grade}?page=${query.pageNum + 1}" title="下一页">下一页</a>
		</c:if>
	</div>
	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>
