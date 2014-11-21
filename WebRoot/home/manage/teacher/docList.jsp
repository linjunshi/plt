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
	<div class="wrap">
		
		<%@ include file="../../inc/top2.jsp"%>
		
		<div class="main">
			
			<%@ include file="../../inc/leftmenu2.jsp"%>
			
			<div class="maanage_right_major">
				<table>
					<thead>
						<tr><th>序号</th><th>材料名</th><th>所属组</th><th>上传时间</th><th>描述</th><th>操作</th></tr>
					</thead>
					<tbody>
						<c:forEach items="${docList}" var="doc" varStatus="st">
							<tr>
								<td>${st.index + 1}</td>
								<td>${doc.docName}</td>
								<td>${doc.groupId}</td>
								<td>${doc.cts}</td>
								<td>${doc.remark}</td>
								<td><a href="#">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<%@ include file="../../inc/friendlylink.jsp"%>
		<%@ include file="../../inc/copyright.jsp"%>
	</div>
</body>
</html>