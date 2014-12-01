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
Globals.page = "Manage_personalInfo";
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
		            <div class="st_titile_r sh_title">
		            	<a href="${ctx}/account/personalInfo">基本信息</a>
		            	<a href="${ctx}/account/personalInfoEdu">教育信息</a>
		            	<a href="${ctx}/account/personalInfoExtend">其他扩展信息</a>
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
		                    <form method="post" action="${ctx}/account/personalInfo" class="form_info" id="personalInfo_form">
		                        <div class="form_item">
		                            <label for="showName">昵称：</label>
		                            <div class="form_field">
		                                <input placeholder="6-32个字符" class="form_text" id="showName" name="showName" type="text" value="${user.showName}" />
		                                <!-- <span class="form_success">昵称可用</span> -->
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label>性别：</label>
		                            <div class="form_field">
	                                    <input <c:if test="${user.gender == 1}"> checked="checked" </c:if> value="1" class="form_radio" name="gender" type="radio">
	                                    <span>男</span>
	                                    <input <c:if test="${user.gender == 2}"> checked="checked" </c:if> value="2" class="form_radio" name="gender" type="radio">
	                                    <span>女</span> 
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label for="realname">姓名：</label>
		                            <div class="form_field">
		                                <input placeholder="请填入您的真实姓名" class="form_text" id="username" name="username" type="text" value="${user.username}">
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label>身份证号码：</label>
		                            <div class="form_field ">
		                               <input placeholder="请填入您的真实身份证号码" class="form_text" id="idCard" name="idCard" type="text" value="${user.idCard}">
		                            </div>
		                        </div>  
		                        <div class="form_item">
		                            <label for="realname">手机号：</label>
		                            <div class="form_field">
		                                <input placeholder="请填入您的手机号码" class="form_text" id="phone" name="phone" type="text" value="${user.phone}">
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label for="name">邮箱：</label>
		                            <div class="form_field">
		                                <input placeholder="请填入您的邮箱" class="form_text" id="email" name="email" type="email" value="${user.email}">
		                                <!-- <span class="form_error">请输入正确的邮箱格式</span></div> -->
		                        	</div>
		                        </div>
								<div class="form_item">
										<label for="realname">上传头像：</label>
										<div class="form_field">
											<img src="${user.headPhoto}" style="width:80px; height:60px;" class="small_preview" />
											<a href="javascript:void(0);" id="changeCover">更改头像</a>
											<input type="hidden" id="url" name="url" value="${user.url}" />
										</div>
									</div>                     
		                        <div class="form_item">
		                            <label>个人简介：</label>
		                            <div class="form_field">
		                                <textarea id="remark" name="remark" class="xheditor-mfull" rows="60" cols="100" style="width:85%;height: 230px;" >${user.remark}</textarea>
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