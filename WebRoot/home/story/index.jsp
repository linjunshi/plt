<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Story_index";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../inc/top_new.jsp"%>
	<div class="content clearfix">
		<div class="questions_list">
			<div style="width:100%; height:200px; text-align:center;">
			<a href="${ctx}/story/game/jiaoaodegongji">骄傲的公鸡</a>-----------------<a href="${ctx}/story/direction">使用教程</a>
			</div>
		</div>
	</div>
</div>
<%@ include file="../inc/footer.jsp"%>
</body>
</html>
