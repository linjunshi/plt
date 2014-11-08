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
		<%@ include file="../../inc/top.jsp"%>
		                        <c:forEach items="${courseList}"  var="course" varStatus="ct">
			                        <li class="<c:if test="${ct.count%4==0}">margin_right_clear</c:if>">
			                        	<a href="${ctx}/course/${course.id}.html" target="_blank"><img src="${ctx}/resource/photo/03.jpg" width="220" height="140"></a>
			                            <h2>${course.courseName}</h2>
			                            <p><b>${course.price}</b>元</p>
			                            <p><span class="sch_unm">${course.commentCount}</span>评论</p>
			                            <p><span class="sch_unm">${course.saleCount}</span>购买</p>
			                        </li>
		                        </c:forEach>
		<div id="container_box">
			<div id="container_content">
			    <div class="sectionMain clr">
					<%@ include file="../../inc/leftmenu.jsp"%>
					<div class="sh_info_r">
            <div class="sh_title">
                <h2>我的收藏</h2>
            </div>
            <div class="sh_collection">
                <ul>
                    <li>
                        <div><a href="#"><img src="images/005.jpg"></a></div>
                        <div class="sh_coll_con">
                            <h2><a href="#">AE基础10分钟玩转影果汁变糖果字幕公开课</a></h2>
                            <p>张老师</p>
                            <p>锐取学院</p>
                            <p>评论人数:200</p>
                        </div>
                        <div class="sh_coll_m"><p>6958.00</p></div>
                        <div class="sh_coll_but"><a href="#">取消收藏</a></div>
                    </li>
                    <li>
                        <div><a href="#"><img src="images/005.jpg"></a></div>
                        <div class="sh_coll_con">
                            <h2><a href="#">AE基础10分钟玩转影果汁变糖果字幕公开课</a></h2>
                            <p>张老师</p>
                            <p>锐取学院</p>
                            <p>评论人数:200</p>
                        </div>
                        <div class="sh_coll_m"><p>6958.00</p></div>
                        <div class="sh_coll_but"><a href="#">取消收藏</a></div>
                    </li>
                    <li>
                        <div><a href="#"><img src="images/005.jpg"></a></div>
                        <div class="sh_coll_con">
                            <h2><a href="#">AE基础10分钟玩转影果汁变糖果字幕公开课</a></h2>
                            <p>张老师</p>
                            <p>锐取学院</p>
                            <p>评论人数:200</p>
                        </div>
                        <div class="sh_coll_m"><p>6958.00</p></div>
                        <div class="sh_coll_but"><a href="#">取消收藏</a></div>
                    </li>
                </ul>
            </div>
        </div>
					<div class="sh_info_r">
		                <div class="sh_ke">
		                    <ul class="sh_sch_ke clr">
		                        
		                        
		                    </ul>

				            <c:set var="basicUrl" value="${ctx}/collection" />
			            	<%@ include file="../../inc/pagination.jsp"%>
		                </div>
			        </div>
				</div>
			</div>
		</div>
		
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>