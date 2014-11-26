<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_index";
</script>
<style type="text/css">
.system_tip { width:350px; line-height:26px; margin:10px auto; margin-left:100px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
</style>
</head>
<body>
	<%@ include file="../inc/top_bg.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<%@ include file="../inc/leftmenu_regist.jsp"%>
				<div class="sh_info_r">
		            <div class="st_titile_r sh_title"> <a href="#">基本信息</a><a href="#">教育信息</a><a href="#">其他扩展信息</a></div>
		            <div class="sh_form_con">
		                <div id="demo_zone">
		                	<c:if test="${tipError != null && fn:length(tipError)  > 0}">
								<div class="system_tip">
									<c:forEach items="${tipError}" var="tip">
									<p>${tip.msg}</p>
									</c:forEach>
								</div>
							</c:if>
		                    <form method="post" action="${ctx}/account/changePslInfo" class="form_info" id="personalInfo_form">
		                        <div class="form_item">
		                            <label for="showName">昵称：</label>
		                            <div class="form_field">
		                                <input placeholder="6-32个字符" class="form_text" id="showName" name="showName" type="text" value="${userDetailView.showName}" />
		                                <span class="form_success">昵称可用</span></div>
		                        </div>
		                        <div class="form_item">
		                            <label for="name">邮箱：</label>
		                            <div class="form_field">
		                                <input class="form_text" id="email" name="email" type="email">
		                                <span class="form_error">请输入正确的邮箱格式</span></div>
		                        </div>
		                        <!-- <div class="form_item">
		                            <label for="password">密码：</label>
		                            <div class="form_field">
		                                <input name="password" id="password" class="form_text" type="password"/>
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label for="comfirmPwd">确认密码：</label>
		                            <div class="form_field">
		                                <input name="comfirmPwd" id="comfirmPwd" class="form_text" type="password">
		                                <span class="form_error">输入的密码不正确，请重新输入</span> </div>
		                        </div> -->
		                        <div class="form_item">
		                            <label>性别：</label>
		                            <div class="form_field">
	                                    <input <c:if test="${userDetailView.gender == 1}"> checked="checked" </c:if> value="1" class="form_radio" name="gender" type="radio">
	                                    <span>男</span>
	                                    <input <c:if test="${userDetailView.gender == 2}"> checked="checked" </c:if> value="2" class="form_radio" name="gender" type="radio">
	                                    <span>女</span> 
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label for="realname">姓名：</label>
		                            <div class="form_field">
		                                <input placeholder="请填入真实姓名" class="form_text" id="username" name="username" type="text" value="${userDetailView.username}">
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label for="realname">手机号：</label>
		                            <div class="form_field">
		                                <input placeholder="请填入手机号码" class="form_text" id="phonenum" name="phonenum" type="text" value="">
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label>生日：</label>
		                            <div class="form_field ">
		                                <select name="birthday" size="1" id="birthday" class="inline_ele">
		                                    <option selected="selected" value="1">1982</option>
		                                    <option value="2">1983</option>
		                                    <option value="3">1984</option>
		                                </select>
		                                <select name="birthday" size="1" id="birthday" class="inline_ele">
		                                    <option selected="selected" value="1">01</option>
		                                    <option value="2">02</option>
		                                    <option value="3">03</option>
		                                </select>
		                                <select name="birthday" size="1" id="birthday" class="inline_ele">
		                                    <option selected="selected" value="1">01</option>
		                                    <option value="2">25</option>
		                                    <option value="3">31</option>
		                                </select>                                
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label>证件号码：</label>
		                            <div class="form_field ">
		                                <select name="id_type" size="1" id="id_type" class="inline_ele">
		                                    <option selected="selected" value="1">身份证</option>
		                                    <option value="2">学生证</option>
		                                    <option value="3">军官证</option>
		                                </select>
		                                <input class="form_text" id="id_text" name="id_text" type="text">
		                            </div>
		                        </div>                        
		                        <div class="form_item">
		                            <label>毕业学校：</label>
		                            <div class="form_field ">
		                                <select name="graduateSchool" size="1" id="graduateSchool" class="inline_ele">
		                                    <option selected="selected" value="1">XXXX学校</option>
		                                    <option value="2">广东学校</option>
		                                    <option value="3">广东学校</option>
		                                </select>
		                                
		                            </div>
		                        </div>                        
		                        <div class="form_item">
		                            <label>个人简介：</label>
		                            <div class="form_field">
		                                <textarea id="remark" name="remark">${userDetailView.remark}</textarea>
		                                <p class="form_des">说明注释文字可以放在这里啊</p>
		                            </div>
		                        </div>
		                        <div class="form_action"><input type="submit" value="保存" /></div>
		                    </form>
		                </div>
		            </div>
		        </div>
			</div>
		</div>
	</div>

	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>