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
		<div id="container_box">
			<div id="container_content">
			    <div class="sectionMain clr">
					<%@ include file="../../inc/leftmenu.jsp"%>
					<div class="sh_info_r">
			            <div class="sh_title">
			                <h2>课件列表</h2>
			            </div>					
						<table>
							<thead>
								<tr><th>序号</th><th>文件名</th><th>录制时间</th><th>时长</th><th>所属组</th><th>描述</th><th>操作</th></tr>
							</thead>
							<tbody>
								<c:forEach items="${fileList}" var="file" varStatus="st">
									<tr>
										<td>${st.index + 1}</td>
										<td>${file.title}</td>
										<td>${file.cts}</td>
										<td>${file.duration}</td>
										<td>${file.groupId}</td>
										<td>${file.remark}</td>
										<td><a href="#">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
	                    <c:set var="basicUrl" value="${ctx}/manage/file" />
	    				<%@ include file="../../inc/pagination.jsp"%>						
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>