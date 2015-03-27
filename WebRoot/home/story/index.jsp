<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="杜巴克" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
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
		<div class="questions_list_game1">
			<div class="game_list clearfix">
		        <ul>
		            <li>
		                <p><img src="${ctx}/resource/images/img_2.png" width="220" ></p>
		                <p>
		                	<a href="${ctx}/story/game/ProudCock?demo=0" target="_blank" class="game_a"></a>
		                	<a href="${ctx}/story/game/ProudCock?demo=1" target="_blank" class="game_b"></a>
		                </p>
		            </li>
		             <li>
		                <p><img src="${ctx}/resource/images/img_2.png" width="220"></p>
		                <p><a href="${ctx}/story/direction" target="_blank" class="game_c"></a></p>
		            </li>
		        </ul>
		    </div>
		</div>
	</div>
</div>
<%@ include file="../inc/footer.jsp"%>
</body>
</html>
