<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_weikeList";
</script>

</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
	<div class="content clearfix">
		<%@ include file="../../inc/leftmenu.jsp"%>
		<div class="qu_right">
			<%@ include file="../../inc/person_header.jsp"%>
			<div class="sh_info_r">
	            <div class="sh_title">
	                <h2>微课列表</h2>			                
	            </div>
	            <div class="sh_collection">
	                <table border="1" class="sh_coll_tab">
	                    <colgroup>
	                    <col width="50">
	                    <col width="310">
	                    <col width="90">
	                    <col width="110">
	                    <col width="80">
	                    <col width="120">
	                    </colgroup>
	                    <thead>
	                        <tr>
	                            <th>序号</th>
	                            <th>课程名称</th>
	                            <th>价格</th>
	                            <th>发布时间</th>
	                            <th>状态</th>
	                            <th>操作</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    	
	                    	<c:forEach items="${courseList}" var="course" varStatus="ct">
		                        <tr>
		                            <td>${ct.index + 1 + (query.pageNum-1)*query.pageSize}</td>
		                            <td class="sh_h_im">
		                            	<a href="${ctx}/course/${course.id}.html" target="_blank"><img src="${ctx}${course.thumbnail}" width="90" height="90"></a>
		                            	<a href="${ctx}/course/${course.id}.html" target="_blank">${course.courseName}</a>
		                            	<p>共${course.chapterCount}课时</p>
		                            </td>
		                            <td>${course.price}</td>
		                            <td>
		                            	<p><fmt:formatDate value="${course.cts}" type="date" /></p>
		                            	<p><fmt:formatDate value="${course.cts}" type="time"/></p>
		                            </td>
		                            <td class="sh_release">
		                            	<c:if test="${course.status == 0}">
											<a href="javascript:void(0);" class="publish" id="i_${course.id}">发布</a>				                            	
		                            	</c:if>
		                            	<c:if test="${course.status == 1}">
		                            		<span>已发布</span>
		                            	</c:if>
			                        </td>
		                            <td class="btn_question_operation">
			                            <div class="btn_question_edit">
											<form action="${ctx}/manage/weike/modify" method="get">
					                            <%-- <a href="${ctx}/manage/weike/modify?courseId=${course.id}&page=${query.pageNum}">修改</a> --%>
												<input type="hidden" value="${query.pageNum}" name="page" />
												<input type="hidden" value="${course.id}" name="courseId" />
												<input type="submit" value="修改" />
											</form>
										</div>
										<div class="btn_question_delete">
											<form action="${ctx}/manage/weike/delete" method="post">
												<input type="hidden" value="${query.pageNum}" name="page" />
												<input type="hidden" value="${course.id}" name="courseId" />
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
			                            <c:set var="basicUrl" value="${ctx}/manage/weike" />
			            				<%@ include file="../../inc/pagination_new.jsp"%>
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
<%@ include file="../../inc/footer.jsp"%>
</body>
</html>
