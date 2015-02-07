<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_historyList";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../../../inc/top_new.jsp"%>
		<div class="content clearfix">
			<%@ include file="../../../inc/leftmenu.jsp"%>
			<div class="qu_right">
				<div class="qu_right_it">
					<h2>${sessionScope.loginUser.showName}</h2>
					<p>你是我的小苹果</p>
					<p>
						财富：012<a href="#">充值</a><span>积分：2365</span>
					</p>
					<p>
						<a href="#">好友<b>12</b></a>｜<a href="#">关注<b>12</b></a>|<a
							href="#">粉丝<b>12</b></a>
					</p>
					<p class="qu_but">
						<a href="#">按钮</a>
					</p>
				</div>
				<div class="qu_ri_school">
			        <div class="sh_ke">
			          <h2>我的好友</h2>
			          <!-- <a href="#" class="catalog_box_more">增加</a> -->
			          <ul class="sh_sch_ky">
			          	<c:forEach items="${userList}" var="user" varStatus="st">
							<li <c:if test="${st.index %4 == 0}">class="maigin_right"</c:if> >
								<a href="javascript:void(0)" target="_blank"><img src="${ctx}${user.headPhoto}" title="${user.showName}" width="160" height="110"></a>
								<h2>${user.showName}</h2>
								<p><a href="${ctx}/personal/cancelFriend?userId=${user.id}">取消好友</a></p>
							</li>
						</c:forEach>
			          </ul>
					  <c:set var="basicUrl" value="${ctx}/personal/friend" />
		       		  <%@ include file="../../../inc/pagination_new.jsp"%>
			        </div>
			      </div>
			</div>
		</div>
	</div>
<div class="footer"></div>
</body>
</html>
