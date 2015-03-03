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
Globals.page = "Index_war";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../inc/top_new.jsp"%>
	<div class="content clearfix">
		<div class="questions_list">
			<div class="question_bg">
		    	<div class="que_but_list">
					<a href="javascript:void(0)" class="que_but_b egg" rel="person">个人练习</a>
					<a href="javascript:void(0)" class="que_but_d" rel="group">多人竞赛</a>
					<a href="javascript:void(0)" class="que_but_e" rel="game">趣味游戏</a>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../inc/footer.jsp"%>
</body>
</html>
