<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台-用户注册" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_regist";
</script>
</head>
<body>

	<%@ include file="inc/top.jsp"%>

	<div class="form_page">
		<div id="demo_zone">
			<form action="${ctx}/regist" method="post" class="form_vertical" id="register_form">
				<div class="form_item">
					<label for="username">昵称：</label>
					<div class="form_field">
						<input placeholder="6-32个字符" class="form_text" id="username" name="username" type="text"> 
						<span class="form_success">昵称可用</span>
					</div>
				</div>
				<div class="form_item">
					<label for="name">邮箱：</label>
					<div class="form_field">
						<input class="form_text" id="email" name="email" type="email">
						<span class="form_error">请输入正确的邮箱格式</span>
					</div>
				</div>
				<div class="form_item">
					<label for="pwd">密码：</label>
					<div class="form_field">
						<input name="password" id="pwd" class="form_text" type="password">
					</div>
				</div>
				<div class="form_item">
					<label for="pwd">确认密码：</label>
					<div class="form_field">
						<input name="pwdagain" id="pwd" class="form_text" type="password">
						<span class="form_error">输入的密码不正确，请重新输入</span>
					</div>
				</div>
				<div class="form_item">
					<label>性别：</label>
					<div class="form_field">
						<label> <input checked="checked" value="man"
							class="form_radio" name="sex" type="radio"> <span>男</span>
							<input value="woman" class="form_radio" name="sex" type="radio">
							<span>女</span>
						</label>
					</div>
				</div>
				<div class="form_item">
					<label for="realname">姓名：</label>
					<div class="form_field">
						<input placeholder="请填入真实姓名" class="form_text" id="realname"
							name="realname" type="text">
					</div>
				</div>
				<div class="form_item">
					<label>证件号码：</label>
					<div class="form_field ">
						<select name="id_type" size="1" id="id_type" class="inline_ele">
							<option selected="selected" value="1">身份证</option>
							<option value="2">学生证</option>
							<option value="3">军官证</option>
						</select> <input class="form_text" id="id_text" name="id_text" type="text">
					</div>
				</div>
				<div class="form_item">
					<label>个人简介：</label>
					<div class="form_field">
						<textarea id="intro" name="intro"></textarea>
						<p class="form_des">说明注释文字可以放在这里啊</p>
					</div>
				</div>
				<div class="form_item">
					<label for="codetext">验证码：</label>
					<div class="form_field">
						<input class="form_text" name="codetext" id="codetext" type="text">
						<div class="code_pic">
							<img
								src="form%E8%A1%A8%E5%8D%95_%E5%A4%A9%E6%B6%AF%E5%89%8D%E7%AB%AF_w3cplus%20css3%E6%95%99%E7%A8%8B-css3%E5%AE%9E%E4%BE%8B-css3%E5%8A%A8%E7%94%BB_files/code.jpg"
								alt="" id="codePic"><a href="#" id="change_code">换一个</a>
						</div>
					</div>
				</div>
				<div class="form_item item_no_label">
					<div class="form_field">
						<label> <input checked="checked" value="1"
							class="form_checkbox" name="" type="checkbox"> <span>我同意所有条款……</span></label>
					</div>
				</div>
				<div class="form_action">
					<input class="btn_regist" type="submit" value="提交" name="regist" />
				</div>
			</form>
		</div>
	</div>

	<%@ include file="inc/footer.jsp"%>
	
</body>
</html>