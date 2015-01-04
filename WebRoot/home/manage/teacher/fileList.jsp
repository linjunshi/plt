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
					<%@ include file="../../inc/leftmenu_teacher.jsp"%>
					<div class="sh_info_r">
			            <div class="sh_title">
			                <h2>课件列表</h2>
			            </div>		
			            
						<div class="sh_collection">
			                <table border="1" class="sh_coll_tab">
			                    <colgroup>
			                    <col width="80">
			                    <col width="330">
			                    <col width="160">
			                    <col width="100">
			                    <col width="150">
			                    <col width="150">
			                    </colgroup>
			                    <thead>
			                        <tr>
			                            <th>序号</th>
			                            <th>课件名</th>
			                            <th>录制时间</th>
			                            <th>录制时长</th>
			                            <th>描述</th>
			                            <th>操作</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach items="${fileList}" var="file" varStatus="st">
				                        <tr>
				                            <td>${st.index + 1}</td>
				                            <td>
				                            	<%-- <a href="${ctx}/course/${file.id}.html" target="_blank"><img src="${ctx}/resource/images/003.jpg" width="40" height="40"></a> --%>
				                            	<p>${file.title}</p>
				                            </td>
				                            <td><fmt:formatDate value="${file.cts}" type="both" /></td>
				                            <td>${file.duration}</td>
				                            <td>${file.remark}</td>
				                            <td class="btn_question_operation">
					                            <div class="btn_input">
													<input type="submit" onclick="alert('开发中')" value="预览" />
												</div>
												<div class="btn_input">
													<form action="${ctx}/manage/file/delete" method="post">
														<input type="hidden" value="${file.id}" name="fileId" />
														<input type="submit" value="删除" />
													</form>
												</div>
				                            </td>
				                        </tr>
			                    	</c:forEach>
			                        
		                        </tbody>
		                        <c:if test="${fn:length(query.pageSequence) > 1}">
				                    <tfoot>
				                        <tr>
				                            <td colspan="6">
					                            <c:set var="basicUrl" value="${ctx}/manage/file" />
					            				<%@ include file="../../inc/pagination.jsp"%>
					            			</td>
				                        </tr>
				                    </tfoot>
			                    </c:if>
			                </table>
			            </div>			            
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>