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
				      <div class="sh_title">
				        <h2>我的测验</h2></div>
		                <c:forEach items="${trainList}" var="train">
						     <div class="sh_work">
						        <div class="sh_work_img"><img src="${ctx}/resource/images/003.jpg" width="40" height="40" /></div>
						        <div class="sh_work_con">
						          <h2>${train.title}<span>来自：${train.courseName}---${train.remark}</span></h2>
						          <p><fmt:formatDate value="${train.cts}" type="both" /></p>
						          <p>良好</p>
						          <p><a href="${ctx}/train?resId=${train.id}" target="_blank">查看作业</a></p>
						        </div>
						      </div>
				     	 </c:forEach>
			            <c:set var="basicUrl" value="${ctx}/study/train" />
		            	<%@ include file="../../inc/pagination.jsp"%>		                
			        </div>
				</div>
			</div>
		</div>
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>