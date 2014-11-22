<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../../inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_index";
</script>
</head>
<body>
		<%@ include file="../../inc/top_bg.jsp"%>
		<div id="container_box">
			<div id="container_content">
			    <div class="sectionMain clr">
					<%@ include file="../../inc/leftmenu_student.jsp"%>
					<div class="sh_info_r">
			            
			            <ul>
				            <c:forEach items="${trainList}" var="train">
				            <li>${train.title}----<a href="${ctx}/train?resId=${train.id}" target="_blank">做测验</a></li>
				            </c:forEach>
		                </ul>
		                
			            <c:set var="basicUrl" value="${ctx}/study/train" />
		            	<%@ include file="../../inc/pagination.jsp"%>		                
		                
			        </div>
				</div>
			</div>
		</div>
		
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>