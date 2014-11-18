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
							<form method="post" action="#" class="form_info" id="addcourse_form">
								<div class="form_item">
									<label for="username">课程分类：</label>
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
										<label for="name">课程名称：</label>
										<div class="form_field">
											<input class="form_text" id="email" name="email" type="email">
										</div>
									</div>
									<div class="form_item">
										<label for="pwd">课程价格：</label>
										<div class="form_field">
											<input name="text" id="pwd" class="form_text" type="password">元
										</div>
									</div>
									<div class="form_item">
										<label for="pwd">结束时间：</label>
										<div class="form_field">
											<input name="text" id="pwd" class="form_text" type="password">
										</div>
									</div>
									<div class="form_item">
										<label for="realname">购买人数限制：</label>
										<div class="form_field">
											<input placeholder="请填入数量" class="form_text" id="realname" name="realname" type="text"> 人
										</div>
									</div>
									<div class="form_item">
										<label>课程描述：</label>
										<div class="form_field">
											<textarea id="intro" name="intro"></textarea>
											<p class="form_des">说明注释文字可以放在这里啊</p>
										</div>
									</div>
									<div class="form_item">
										<label for="realname">课程封面：</label>
										<div class="form_field">
											<img src="" style="width:80px; height:60px;" class="small_preview" />
											<a href="javascript:void(0);" id="changeCover">更改封面</a>
											<input type="hidden" name="url" value="" />
										</div>
									</div>
									<div class="form_action">
										<a href="#">保存</a>
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