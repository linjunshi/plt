<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_index";
</script>
</head>
<body>
	<%@ include file="inc/top.jsp"%>
	<div id="container_box">
		<div class="container_content">
		    
		    <p>当前城市：${sessionScope.area.cityName}</p>
		    
		    <c:forEach items="${cityMap}" var="item">
		    	<div>
		    	<p>${item.key}</p>
		    	<c:forEach items="${item.value}" var="city">
		    		<a style="margin:4px 10px;" href="${ctx}/changecity/${city.areaEName}">${city.areaName}</a>
		    	</c:forEach>
		    	</div>
		    </c:forEach>
		    
		</div>
	</div>
	<%@ include file="inc/friendlylink.jsp"%>	
</body>
</html>