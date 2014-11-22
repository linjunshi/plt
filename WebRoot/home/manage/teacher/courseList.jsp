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
			                <h2>课程列表</h2>
			                <a href="${ctx}/manage/course/add" class="sh_add">+新增</a>
			            </div>
			            <div class="sh_collection">
			                <table border="1" class="sh_coll_tab">
			                    <colgroup>
			                    <col width="80">
			                    <col width="330">
			                    <col width="110">
			                    <col width="150">
			                    <col width="150">
			                    <col width="150">
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
				                            <td>${ct.index + 1}</td>
				                            <td class="sh_h_im">
				                            	<a href="${ctx}/course/${course.id}.html" target="_blank"><img src="${ctx}/resource/images/003.jpg"></a>
				                            	<a href="${ctx}/course/${course.id}.html" target="_blank">${course.courseName}</a>
				                            	<p>共${course.chapterCount}课时</p>
				                            </td>
				                            <td>${course.price}</td>
				                            <td>
				                            	<p><fmt:formatDate value="${course.cts}" type="date" /></p>
				                            	<p><fmt:formatDate value="${course.cts}" type="time"/></p>
				                            </td>
				                            <td class="sh_release">
					                            <a href="#">未发布</a>
					                            <a href="#">发布</a>
					                        </td>
				                            <td class="btn_question_operation">
					                            <div class="btn_input">
													<form action="${ctx}/manage/course/modify" method="post">
														<input type="hidden" value="${course.id}" name="courseId" />
														<input type="submit" value="修改" />
													</form>
												</div>
												<div>
													<form action="${ctx}/manage/course/delete" method="post">
														<input type="hidden" value="${course.id}" name="courseId" />
														<input type="submit" value="删除" />
													</form>
												</div>
				                            </td>
				                        </tr>
			                    	</c:forEach>
			                        
		                        </tbody>
			                    <tfoot>
			                        <tr>
			                            <td colspan="6">
				                            <c:set var="basicUrl" value="${ctx}/study/course" />
				            				<%@ include file="../../inc/pagination.jsp"%>
				            			</td>
			                        </tr>
			                    </tfoot>
			                </table>
			            </div>
			        </div>
				</div>
			</div>
		</div>
		
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>