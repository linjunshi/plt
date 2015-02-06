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
Globals.page = "Manage_weikeAdd";
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
						<a href="${ctx}/manage/weike/add">基本信息</a>
						</c:if>
						<c:if test="${fn == 'modify'}">
						<a href="${ctx}/manage/weike/modify?courseId=${course.id}">基本信息</a>
						</c:if>
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
							<c:if test="${fn == 'add'}">action="${ctx}/manage/weike/add"</c:if> 
							<c:if test="${fn == 'modify'}">action="${ctx}/manage/weike/modify"</c:if> 
							class="form_info common_form" id="course_form">
								<input type="hidden" name="id" value="${course.id}"/>
								<input id="page" name="page" type="hidden" value="${pageNum}"/>
								<input id="levelId" name="levelId" type="hidden" value="${course.gradeId}">
								<input id="oldSubjectId" name="oldSubjectId" type="hidden" value="${course.subjectId}">
								<input id="oldUnitId" name="oldUnitId" type="hidden" value="${course.unitId}">
								<input id="gradeIdSelected" name="gradeIdSelected" type="hidden" value="">
									<div class="form_item">
										<label for="gradeId">课程分类：</label>
										<div class="form_field">
											<select id="gradeSelect" style="display:none;">
												<c:forEach items="${applicationScope.gradeList}" var="grade" varStatus="st">
												<option value="${grade.gradeEnName}">${grade.gradeName}</option>
												</c:forEach>
											</select>
											<select name="gradeId" id="levelSelect"></select>
											<select name="subjectId" id="subjectSelect"></select>
											<select name="unitId" id="unitSelect"></select>
											<span class="not-empty" title='此项为必填项'>*</span>
										 </div>
									</div>
									<div class="form_item">
										<label>所属知识点<span class="not-empty" title='此项为必填项'>*</span>：</label>
										<div class="form_field">
											<input id="knowledgeIds" name="knowledgeIds" type="hidden" value="${knowledgeIds}"/>
											<input id="knowledgeNames" name="knowledgeNames" placeholder="请点击选择试题所属的知识点" type="button" value="请点击这里选择绑定知识点"/>
										</div>
									</div>
									<div class="form_item">
										<label for="courseName">课程名称：</label>
										<div class="form_field">
											<input class="form_text" id="courseName" name="courseName" type="text" value="${course.courseName}" required>
											<span class="not-empty" title='此项为必填项'>*</span>
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
											<input placeholder="如：1000" class="form_text" name="price" id="price" type="text" value="${course.price}" required required_Number> 元
											<span class="not-empty" title='此项为必填项'>*</span>
										</div>
									</div>
									<div class="form_item">
										<label>限购数量：</label>
										<div class="form_field">
											<input placeholder="如：1000" class="form_text" name="limitCount" id="limitCount" type="text" value="${course.limitCount}" required required_Number>
											<span class="not-empty" title='此项为必填项'>*</span>
										</div>
									</div>
								<%-- 	<div class="form_item">
										<label for="chapterCount">课时数量：</label>
										<div class="form_field">
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
											<span class="not-empty" title='此项为必填项'>*</span>
										</div>
									</div> --%>
									<div class="form_item">
										<label for="endTime">结束时间：</label>
										<div class="form_field">
											<input placeholder="格式：2014-01-01" class="form_text" name="endTime" id="endTime" type="text" value="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${course.endTime}" />" required_Date>
										</div>
									</div>
									<input type="hidden" name="live" value="0" />
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
											<textarea id="xheditor_remark" name="remark" rows="60" cols="100" style="width:85%;height: 230px;" >${course.remark}</textarea>
			                                <script type="text/javascript">
											 	$('#xheditor_remark').xheditor({upImgUrl:'${ctx}/image/upload2', html5Upload:false, upMultiple:1, upImgExt:'jpg,jpeg,gif,png,bmp', tools:'mfull',skin:'o2007silver'});
											</script>
										</div>
									</div>
									<div class="form_action">
										<input class="btn_question" type="submit" value="保存" /> 
										<a class="btn_question" href="${ctx}/manage/weike?page=${pageNum}">取消</a>
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
