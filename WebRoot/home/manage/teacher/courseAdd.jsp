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
						<h2>新建课程页</h2>
					</div>
					<div class="sh_form_con">
						<div id="demo_zone">
							<c:if test="${tipError != null && fn:length(tipError)  > 0}">
								<div class="system_tip">
									<c:forEach items="${tipError}" var="tip">
									<p>${tip.msg}</p>
									</c:forEach>
								</div>
							</c:if>
							<form method="post" action="${ctx}/manage/course/modifyPost" class="form_info" id="course_form">
								<div class="form_item">
									<label for="gradeId">课程分类：</label>
									<div class="form_field">
										<select name="grade">
											<option selected="selected" value="1">高中</option>
											<option value="2">初中</option>
											<option value="3">小学</option>
										</select> 
										<select name="grade">
											<option selected="selected" value="1">高一</option>
											<option value="2">高二</option>
											<option value="3">高三</option>
										</select> 
										<select name="grade">
											<option selected="selected" value="1">初一</option>
											<option value="2">初二</option>
											<option value="3">初三</option>
										</select> 
										<select name="grade">
											<option selected="selected" value="1">一年级</option>
											<option value="2">二年级</option>
											<option value="3">三年级</option>
											<option value="2">四年级</option>
											<option value="3">五年级</option>
											<option value="2">六年级</option>
										</select>
									</div>
									<div class="form_item">
										<label for="courseName">课程名称：</label>
										<div class="form_field">
											<input class="form_text" id="courseName" name="courseName" type="text" value="${course.courseName}">
										</div>
									</div>
									<div class="form_item">
										<label for="teacher">主讲老师：</label>
										<div class="form_field">
											<input class="form_text" id="teacher" name="teacher" type="text" value="${course.teacher}">
										</div>
									</div>
									<div class="form_item">
										<label for="price">课程价格：</label>
										<div class="form_field">
											<input class="form_text" name="price" id="price" type="text" value="${course.price}"> 元
										</div>
									</div>
									<div class="form_item">
										<label for="chapterCount">课时数量：</label>
										<div class="form_field">
											<input class="form_text" name="chapterCount" id="chapterCount" type="text" value="${course.chapterCount}"> 小时
										</div>
									</div>
									<div class="form_item">
										<label for="endTime">结束时间：</label>
										<div class="form_field">
											<input placeholder="格式：2014-01-01 59:59:59" class="form_text" name="endTimeStr" id="endTimeStr" type="date" value="<fmt:formatDate type="both" value="${course.endTime}" />">
										</div>
										
									</div>
									 <div class="form_item">
		                            	<label>是否直播课：</label>
			                            <div class="form_field">
		                                    <input <c:if test="${course.live == 1}"> checked="checked" </c:if> value="1" class="form_radio" name="live" type="radio">
		                                    <span>是</span>
		                                    <input <c:if test="${course.live == 0}"> checked="checked" </c:if> value="0" class="form_radio" name="live" type="radio">
		                                    <span>否</span> 
			                            </div>
			                        </div>
									<div class="form_item">
										<label>课程描述：</label>
										<div class="form_field">
											<textarea id="remark" name="remark">${course.remark}</textarea>
											<p class="form_des">说明注释文字可以放在这里啊</p>
										</div>
									</div>
									<div class="form_item">
										<label for="realname">课程封面：</label>
										<div class="form_field">
											<input name="file" type="file" />
										</div>
									</div>
									<div class="form_action">
										<input type="hidden" value="${course.id}" name="id" /> 
										<input class="btn_question" type="submit" value="保存" /> 
										<a class="btn_question" href="${ctx}/manage/course">取消</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>