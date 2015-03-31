<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/common.jsp"%>
<c:set var="title" value="杜巴克教育" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
<c:set var="description" value="杜巴克教育" ></c:set>
<!-- loginPage -->
<%@ include file="inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_login";
</script>
<style type="text/css">
* {	margin: 0;padding: 0;}
body {color: #666;font: 12px/1.8em Arial, Helvetica, sans-serif;background:#FFF;margin: 0;padding: 0;overflow: inherit;}
.mr h2 {text-align: center;font-size:26px;margin:30px 0 10px 0; padding-top:20px; height:30px; line-height:30px;color: #f90; font-weight:100;}
.border {background: url(${ctx}/resource/images/banner_img_4.jpg) no-repeat center center;height:420px;clear: both;}
.center {width: 1000px;	margin: 0 auto;	height: 420px;position: relative;}
.user {	width: 330px;height: 230px;border:1px #fff solid;background: url(${ctx}/resource/images/007.png);	position: absolute;	top: 100px;	right: 20px;border-radius: 15px;}
.formlogin {height:auto;width:300px;padding-top: 20px;}
.loginuser {width: 330px;display: block;float: left;color: #FFF;margin:15px 0;height:30px;}
.login {display: block;	width: 100px;text-align: right;float: left;font-size: 16px;color: #000;	height:30px;line-height:30px;}
.text2 {display: block;	width: 150px;height:26px;text-align:left;float:left;}

.validation {display: block;width:60px;height:24px; line-height:24px;text-align: left;float: left;margin-right:3px;}
.loginuser a{ line-height:40px;}
.loginbut {	width: 300px;display:block;float:left;color: #FFF;margin:20px 0 0 0px;}
.btn-login {float: left;width:100px;height: 30px;background:#f90;display: block;line-height: 30px;text-align: center;letter-spacing: 4px;color: #fff;text-decoration:none;font-size:18px;margin-left:50px;border:0;}
.login_index {float: left;width:100px;height: 30px;background:#f90;display: block;line-height: 30px;text-align: center;letter-spacing: 4px;color: #fff;text-decoration:none;font-size:18px;margin-left:30px;border:0;}
.login_index:hover{background:#188EEE;cursor: pointer;}
/* .loginbut a {width:100px;height: 30px;background:#f90;display: block;line-height: 30px;text-align: center;letter-spacing: 4px;color: #fff;text-decoration:none;font-size:18px;margin-left:100px;}
.loginbut a:hover{background:#f90;} 
*/
.loginbut .btn:focus,.btn-login:hover{background:#188EEE;cursor: pointer;}
.footer_p { font-size:16px; margin-top:20px; text-align:center;}
.footer_p a { color: black;}
.footer_p a:HOVER { color: black;text-decoration:underline;}
/*------------------以下是鼠标放上去的效果可有可无-------------------------*/
.text2, textarea,.validation{border: 1px solid #CCC;border-radius:3px 3px 3px 3px;padding:4px;}
.text2, textarea,.validation { -webkit-transition: border linear 0.2s, box-shadow linear 0.2s; -moz-transition: border linear 0.2s, box-shadow linear 0.2s; -ms-transition: border linear 0.2s, box-shadow linear 0.2s; -o-transition: border linear 0.2s, box-shadow linear 0.2s; transition: border linear 0.2s, box-shadow linear 0.2s; -webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);  -moz-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);}
.text2:focus, textarea:focus, .validation{outline: 0 none;}
.text2:focus, textarea:focus,.validation:focus { border-color: rgba(82, 168, 236, 0.8);
  -webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1), 0 0 8px rgba(82, 168, 236, 0.6);
  -moz-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1), 0 0 8px rgba(82, 168, 236, 0.6);
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1), 0 0 8px rgba(82, 168, 236, 0.6);}
  .system_tip {width:300px; line-height:26px; margin:10px auto; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;text-align: center;}
</style>
</head>
<!-- <body scroll="no"> -->
<body>
<div class="mr">
  <h2>用户登录</h2>
</div>

<c:if test="${tipError != null && fn:length(tipError)  > 0}">
	<div class="system_tip">
		<c:forEach items="${tipError}" var="tip">
		<p>${tip.msg}</p>
		</c:forEach>
	</div>
</c:if>
<div class="border">
  <div class="center">
  
    <div class="user">
      <form class="formlogin common_form" action="${ctx}/account/login" method="post">
        <div class="loginuser">
          <label class="login">用户名：</label>
          <input name="username" type="text" size="20" class="text2" value="${username}" required>
          <a href="${ctx}/account/regist" target="_blank" class="login_new">注册新用户</a>
        </div>
        <div class="loginuser">
          <label class="login">密码：</label>
          <input name="password" type="password" size="20" class="text2" required>
          <a href="${ctx}/account/forgotPwd" target="_blank" class="login_forgot">忘记密码?</a>
        </div>
        <div class="loginbut">
		  <input type="submit" value="登录" class="btn-login" name="commit" />
		  <input type="button" value="返回首页" class="login_index" name="index" onclick="document.location='${ctx}/'" />
      	</div>
      </form>
      
    </div>
  </div>
</div>
<p class="footer_p">Copyright © 2014-2015, 技术支持：深圳市课云网络科技有限公司, 备案号：粤ICP备15022223号</p>
</body>
</html>

