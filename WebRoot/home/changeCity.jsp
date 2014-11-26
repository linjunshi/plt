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
		
		    <div class="sectionMain clr">
		        <div class="city_switch">
		            <h3>常用城市:</h3>
		            <p>
						<a href="${ctx}/changecity/beijing">北京</a>
						<a href="${ctx}/changecity/shanghai">上海</a>
						<a href="${ctx}/changecity/guangzhou">广州</a>
			            <a href="${ctx}/changecity/shenzhen">深圳</a>
		            </p>
		        </div>
		        <div class="city_switch">
		            <h3>当前城市:</h3>
		            <p><a>${sessionScope.area.cityName}</a></p>
		        </div>
		        <div class="city_in">
		            <h3 class="city_sec"> 按拼音首字母选择 <span class="arrow"></span> </h3>
		            <ol>
			            <c:forEach items="${cityMap}" var="item">
			                <li>
			                    <h3 class="city_alp">${item.key}</h3>
			                    <p class="city_con">
						    	<c:forEach items="${item.value}" var="city">
						    		<a href="${ctx}/changecity/${city.areaEName}">${city.areaName}</a>
						    	</c:forEach>			                    
			                    </p>
			                </li>
		                </c:forEach>
		            </ol>
		        </div>
		    </div>
		</div>
	</div>
	<%@ include file="inc/friendlylink.jsp"%>	
</body>
</html>