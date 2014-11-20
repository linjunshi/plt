<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../../../inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_index";
</script>
</head>
<body>
	<%@ include file="../../../inc/top.jsp"%>
	<div id="container_box">
    	<div id="container_content">
			<div class="sectionMain clr">
		        <!-- <%@ include file="../../../inc/leftmenu.jsp"%> -->
		        <div class="sh_info_r">
		            <div class="sh_title">
		                <h2>答题情况</h2>
		            </div>
		            <div class="form_questions">
		            <p>回答${replayCount}题，一共${total}题</p>
					<c:forEach items="${historyList}" var="history">
					<p>${history.topic},A${history.opt1},B${history.opt1},C${history.opt1},D${history.opt1}---选择答案${history.historyAnswer}---正确答案${history.questionAnswer}----回答结果${history.result}</p>
					</c:forEach>
		            </div>
		        </div>
	        </div>
	    </div>
	</div>
	<%@ include file="../../../inc/friendlylink.jsp"%>
</body>
</html>