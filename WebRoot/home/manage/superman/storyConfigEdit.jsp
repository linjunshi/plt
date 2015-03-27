<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="杜巴克" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 剧本 故事" ></c:set>
<%@ include file="../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_storyConfigEdit";
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
					<h2>剧本故事配置</h2>
				</div>
				<div class="form_questions">
					<div id="demo_zone">

						<c:if test="${tipError != null && fn:length(tipError)  > 0}">
							<div class="system_tip">
								<c:forEach items="${tipError}" var="tip">
									<p>${tip.msg}</p>
								</c:forEach>
							</div>
						</c:if>
						<form method="post" action="${ctx}/super/story/storyConfigEdit" class="form_info common_form" id="question_form">
							<input id="id" name="id" type="hidden" value="${storyItem.id}"/>
							<input id="page" name="page" type="hidden" value="${pageNum}"/>
							<div class="form_item">
								<label>剧本名称：</label>
								<div class="form_field">
									<input class="form_text" id="storyName" name="storyName" type="text" value="${storyItem.storyName}" required>
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label>剧本英文名称：</label>
								<div class="form_field">
									<input class="form_text" id="storyEname" name="storyEname" type="text" value="${storyItem.storyEname}" required>
									<span class="not-empty" title='此项为必填项'>*</span>
								</div>
							</div>
							<div class="form_item">
								<label>简介<span class="not-empty" title='此项为必填项'>*</span>：</label>
								<div class="form_field">
									<textarea id="xheditor_topic" name="remark" rows="60" cols="100" style="width:85%;height: 230px;" >${storyItem.remark}</textarea>
									<script type="text/javascript">
									 	$('#xheditor_topic').xheditor({upImgUrl:'${ctx}/image/upload2', html5Upload:false, upMultiple:1, upImgExt:'jpg,jpeg,gif,png,bmp', tools:'mfull',skin:'o2007silver'});
									</script>
								</div>
							</div>
							<div class="form_action">
								<input class="btn_approve" type="submit" value="提交" />
								<a class="btn_question" href="${ctx}/super/story/config?page=${pageNum}">取消</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../../inc/footer.jsp"%>
</body>
</html>
