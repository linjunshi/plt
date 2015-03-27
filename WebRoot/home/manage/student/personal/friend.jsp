<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<c:set var="title" value="杜巴克" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_friend";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../../../inc/top_new.jsp"%>
		<div class="content clearfix">
			<%@ include file="../../../inc/leftmenu.jsp"%>
			<div class="qu_right">
				<%@ include file="../../../inc/person_header.jsp"%>
				<div class="qu_ri_school">
			        <div class="sh_ke">
			          <nav class="st_titile_r">
			          	<a href="${ctx}/personal/friend" class="sh_title_hover">我的朋友</a>
			          	<a href="${ctx}/personal/findFriend">可能认识的人</a>
			          </nav>
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
<%@ include file="../../../inc/footer.jsp"%>
</body>
</html>
