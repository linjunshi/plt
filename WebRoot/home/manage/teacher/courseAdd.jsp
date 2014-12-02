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
Globals.page = "Manage_courseAdd";
</script>
</head>
<body>
	<%@ include file="../../inc/top_bg.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<%@ include file="../../inc/leftmenu_teacher.jsp"%>
				<div class="sh_info_r">
					<div class="st_titile_r sh_title">
						<c:if test="${fn == 'add'}"> 
						<a href="${ctx}/manage/course/add">基本信息</a>
						</c:if>
						<c:if test="${fn == 'modify'}">
						<a href="${ctx}/manage/course/modify?courseId=${course.id}">基本信息</a>
						</c:if>
						<a href="${ctx}/manage/course/chapterEditor?courseId=${course.id}" <c:if test="${fn == 'add'}">style="display: none;"</c:if> >章节维护</a>
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
							<form method="post" 
							<c:if test="${fn == 'add'}">action="${ctx}/manage/course/add"</c:if> 
							<c:if test="${fn == 'modify'}">action="${ctx}/manage/course/modify"</c:if> 
							class="form_info" id="course_form">
								<input type="hidden" name="id" value="${course.id}"/>
								<div class="form_item">
									<label for="gradeId">课程分类：</label>
									<div class="form_field">
										<select id="gradeSelect">
											<c:forEach items="${applicationScope.gradeList}" var="grade" varStatus="st">
											<option value="${grade.gradeEnName}">${grade.gradeName}</option>
											</c:forEach>
										</select>
										<select name="gradeId" id="levelSelect">
										</select>
										<select name="subjectId" id="subjectSelect">
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
											<input placeholder="如：1000" class="form_text" name="price" id="price" type="text" value="${course.price}"> 元
										</div>
									</div>
									<div class="form_item">
										<label for="chapterCount">课时数量：</label>
										<div class="form_field">
											<%-- <input placeholder="如：2" class="form_text" name="chapterCount" id="chapterCount" type="text" value="${course.chapterCount}"> 小时 --%>
											<select name="chapterCount" size="1" id="chapterCount" class="inline_ele">
												<option <c:if test="${course.chapterCount==5}">selected="selected"</c:if> value="5">5分钟</option>
												<option <c:if test="${course.chapterCount==10}">selected="selected"</c:if> value="10">10分钟</option>
												<option <c:if test="${course.chapterCount==15}">selected="selected"</c:if> value="15">15分钟</option>
												<option <c:if test="${course.chapterCount==20}">selected="selected"</c:if> value="20">20分钟</option>
												<option <c:if test="${course.chapterCount==30}">selected="selected"</c:if> value="30">30分钟</option>
												<option <c:if test="${course.chapterCount==45}">selected="selected"</c:if> value="45">45分钟</option>
												<option <c:if test="${course.chapterCount==60}">selected="selected"</c:if> value="60">60分钟</option>
												<option <c:if test="${course.chapterCount==90}">selected="selected"</c:if> value="90">90分钟</option>
											</select>
										</div>
									</div>
									<div class="form_item">
										<label for="endTime">结束时间：</label>
										<div class="form_field">
											<input placeholder="格式：2014-01-01 59:59:59" class="form_text" name="endTime" id="endTime" type="date" value="<fmt:formatDate type="date" pattern="yyyy/MM/dd" value="${course.endTime}" />">
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
										<label for="realname">课程封面：</label>
										<div class="form_field">
											<img src="${course.url}" style="width:80px; height:60px;" class="small_preview" />
											<a href="javascript:void(0);" id="changeCover">更改封面</a>
											<input type="hidden" name="url" value="${course.url}" />
										</div>
									</div>
									<div class="form_item">
										<label>课程描述：</label>
										<div class="form_field">
											<textarea id="remark" name="remark" class="xheditor-mfull" rows="60" cols="100" style="width:85%;height: 230px;" >${course.remark}</textarea>
										</div>
									</div>
									<div class="form_action">
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
