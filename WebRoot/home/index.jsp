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
		    <div id="main">
		        <div class="column_left_b">
			        <div id="MainPromotionBanner">
		                <div id="SlidePlayer">
		                    <ul class="Slides">
		                        <li><a target="_blank" href="${ctx}/"><img src="${ctx}/resource/images/01.jpg" width="728" height="300"></a></li>
		                        <li><a target="_blank" href="${ctx}/"><img src="${ctx}/resource/images/02.jpg" width="728" height="300"></a></li>
		                        <li><a target="_blank" href="${ctx}/"><img src="${ctx}/resource/images/03.jpg" width="728" height="300"></a></li>
		                        <li><a target="_blank" href="${ctx}/"><img src="${ctx}/resource/images/04.jpg" width="728" height="300"></a></li>
		                        <li><a target="_blank" href="${ctx}/"><img src="${ctx}/resource/images/05.jpg" width="728" height="300"></a></li>
		                    </ul>
		                    <!-- <ul class="SlideTriggers"><li class="Current">1</li><li>2</li><li>3</li><li>4</li><li>5</li></ul> -->
		                </div>
		                <script type="text/javascript">
							TB.widget.SimpleSlide.decoration('SlidePlayer', {eventType:'mouse', effect:'scroll'});
						</script> 
	            	</div>
		        </div>
		        <div class="column_right_school">
		        	<c:if test="${sessionScope.loginUser == null}">
						<form class="formlogin common_form" action="${ctx}/account/login" method="post">
							<div class="loginuser">
								<label class="login"><fmt:message key="index_user_username"/>：</label>
								<input name="username" type="text" size="20" class="text2" value="${username}" required>
							</div>
							<div class="loginuser">
								<label class="login"><fmt:message key="index_user_password"/>：</label>
								<input name="password" type="password" size="20" class="text2" required>
								<a href="${ctx}/account/forgotPwd" target="_blank" class="login_forgot">忘记密码?</a>
							</div>
							<div class="loginbut">
								<input type="submit" value="登录" class="btn-login" name="commit" />
							</div>
						</form>
					</c:if>
					<c:if test="${sessionScope.loginUser != null}">
						<div>欢迎你，${sessionScope.loginUser.showName}</div>
						<img src="${ctx}${sessionScope.loginUser.headPhoto}" width="30" width="30" />
						<p><a href="${ctx }/account/logout">注销</a></p>
						<p><a href="${ctx }/study/score">管理中心</a></p>						
					</c:if>
		        </div>
		    </div>
		    
		    <div class="wrap_catalog_box">
		        <div class="catalog_box_header">
					<h2 class="catalog_box_title">小学</h2>
		            <ul class="catalog_box_nav">
	            	    <c:forEach items="${xiaoxue_subjectList}" var="subject">
	                	<li><a href="${ctx}/course/xiaoxue/${subject.subjectEnName}">${subject.subjectName}</a></li>
	                	</c:forEach>
		            </ul>
		            <a href="${ctx}/course/xiaoxue" class="catalog_box_more">更多</a>
				</div>
		        <div class="img_item">
		            <ul>
						<c:forEach items="${xiaoxue_courseList}" var="item" varStatus="st">
		                <li class="img_list<c:if test="${(st.index+1)%5 == 0}"> margin_right_clear</c:if>">
		                    <div class="pic"><a href="${ctx}/course/${item.id}.html" target="_blank"><img src="${ctx}${item.thumbnail}" border="0" /></a></div>
		                    <div class="img_user">
		                        <h2>${item.courseName}</h2>
		                    </div>
		                    <div class="img_counts"><span class="price"><b>${item.price}</b>元</span><span class="sales"><strong>${item.saleCount}</strong>人购买</span></div>
		                </li>						
						</c:forEach>
		            </ul>
		        </div>
		    </div>
		</div>
	</div>
	<%@ include file="inc/friendlylink.jsp"%>	
</body>
</html>