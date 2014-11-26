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
			            <div class="st_titile_r"> <a href="${ctx}/study/course">我的课程</a><a href="${ctx}/study/live">我的直播</a></div>
			            <div class="result_list pt10">
			                <ul>
			                    <li class="clr"><a href="#" class="result_img"><img src="${ctx}/resource/images/005.jpg" width="130" height="110"></a>
			                        <div class="result_con">
			                            <h3 ><a href="#" class="result_con_ti">直线与圆的位置关系</a></h3>
			                            <p class="pt1">正在直播中...</p>
			                            <p class="pt2">心流学院</p>
			                            <p class="pt3">学校信用：</p>
			                        </div>
			                        <div class="result_price">
			                            <h3 class="yuan">0000<span>元</span></h3>
			                            <p class="pt2">共2课时</p>
			                        </div>
			                        <div class="result_price">
			                            <p class="pt2">150人购买</p>
			                            <p class="pt2">满意度 88.89%</p>
			                            <p class="pt3">9 条评价</p>
			                        </div>
			                        <div class="result_price maigin_right">
			                            <p class="pt3">担保期15天</p>
			                        </div>
			                    </li>
			                </ul>
			            </div>
			        </div>
				</div>
			</div>
		</div>
		
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>