<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_personalInfo";
</script>
<style type="text/css">
.system_tip {position: relative;float: left; width:350px; line-height:26px;min-height:26px; margin:0px 200px 10px 100px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
</style>
</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
	<div class="content clearfix">
		<%@ include file="../../inc/leftmenu.jsp"%>
		<div class="qu_right">
			<%@ include file="../../inc/person_header.jsp"%>
			<div class="sh_info_r">
	            <nav class="st_titile_r sh_title">
	            	<a href="${ctx}/account/personalInfo" class="sh_title_hover">基本信息</a>
	            	<a href="${ctx}/account/personalInfoEdu">教育信息</a>
	            	<a href="${ctx}/account/personalInfoExtend">其他扩展信息</a>
	            </nav>
	            <div class="sh_form_con">
	                <div id="demo_zone">
	                    <form method="post" action="${ctx}/account/personalInfo" class="form_info common_form" id="personalInfo_form">
           		        <c:if test="${tipError != null && fn:length(tipError)  > 0}">
							<div class="system_tip">
								<c:forEach items="${tipError}" var="tip">
								<p>${tip.msg}</p>
								</c:forEach>
							</div>
						</c:if>
	                    	<input type="hidden" name="id" value="${user.id}" />
	                    	<input type="hidden" id="personalInfo" name="personalInfo" value="personalInfo" />
	                    	<input id="levelId" name="levelId" type="hidden" value="${user.gradeId}">
	                        <div class="form_item">
	                            <label for="username">昵称：</label>
	                            <div class="form_field">
	                                <input placeholder="6-32个字符" class="form_text" id="username" name="username" type="hidden" value="${user.username}"/>
	                                <strong>${user.username}</strong>
	                               <!--  <span class="not-empty" title='此项为必填项'>*</span> -->
	                                <span class="form_tips">（此项为登录名）</span>
	                            </div>
	                        </div>
	                        <div class="form_item">
	                            <label>性别：</label>
	                            <div class="form_field">
                                    <input <c:if test="${user.gender == 1}"> checked="checked" </c:if> value="1" class="form_radio" name="gender" type="radio">
                                    <span>男</span>
                                    <input <c:if test="${user.gender == 2}"> checked="checked" </c:if> value="2" class="form_radio" name="gender" type="radio">
                                    <span>女</span>
                                    <span class="form_tips" title='此项为必填项'>*</span>
	                            </div>
	                        </div>
	                        <div class="form_item">
								<label for="gradeId">所属年级：</label>
								<div class="form_field">
									<select id="gradeSelect" style="display: none;">
										<c:forEach items="${applicationScope.gradeList}" var="grade" varStatus="st">
										<option value="${grade.gradeEnName}">${grade.gradeName}</option>
										</c:forEach>
									</select>
									<select name="gradeId" id="levelSelect"></select>
									<span class="not-empty" title='此项为必填项'>*</span>
									 <span class="form_tips">（系统自动根据您当前所属年级推荐相关的课程、微课等信息）</span>
								 </div>
							</div>
	                        <div class="form_item">
	                            <label for="showName">真实姓名：</label>
	                            <div class="form_field">
	                                <input placeholder="填写您的真实姓名" class="form_text" id="showName" name="showName" type="text" value="${user.showName}" required/>
	                                <span class="not-empty" title='此项为必填项'>*</span>
	                                <span class="form_tips">（请填写个人的真实姓名）</span>
	                            </div>
	                        </div>
	                        <div class="form_item">
	                            <label>身份证号码：</label>
	                            <div class="form_field ">
	                               <input placeholder="请填入您的真实身份证号码" class="form_text" id="idCard" name="idCard" type="text" value="${user.idCard}" required_Idcard>
	                               <span class="form_tips">（真实身份证号码为15数字或者18位数字或者17位数字+X）</span>
	                            </div>
	                        </div>  
	                        <div class="form_item">
	                            <label for="phone">手机号：</label>
	                            <div class="form_field">
	                                <input placeholder="请填入您的手机号码" class="form_text" id="phone" name="phone" type="text" value="${user.phone}" required_Phone>
	                                <span class="form_tips">（手机号码为11位数字）</span>
	                            </div>
	                        </div>
	                        <div class="form_item">
	                            <label for="email">邮箱：</label>
	                            <div class="form_field">
	                            <c:if test="${user.email==null}">
	                            	<input placeholder="请填入您的邮箱" class="form_text" id="email" name="email" type="email" value="${user.email}" style="">
	                            </c:if>
	                                <input placeholder="请填入您的邮箱" class="form_text" id="email" name="email" type="email" value="${user.email}" required required_Mail>
	                                <span class="not-empty" title='此项为必填项'>*</span>
	                                <span class="form_tips">（邮箱格式，如：xxx@qq.com,xxx@163.com...）</span>
	                            </div>
	                        </div>
							<div class="form_item">
									<label for="url">上传头像：</label>
									<div class="form_field">
										<img src="${user.headPhoto}" style="width:80px; height:60px;border:1px #ccc solid;" class="small_preview" />
										<a href="javascript:void(0);" id="changeCover">更改头像</a>
										<input type="hidden" id="url" name="url" value="${user.url}" />
										<span class="form_tips">（图片大小不允许超过300K）</span>
									</div>
								</div>                     
	                        <div class="form_item">
	                            <label>个人简介：</label>
	                            <div class="form_field">
	                                <textarea id="xheditor_remark" name="remark" style="width:80%;height:230px;">${user.remark}</textarea>
	                                <script type="text/javascript">
									 	$('#xheditor_remark').xheditor({upImgUrl:'${ctx}/image/upload2', html5Upload:false, upMultiple:1, upImgExt:'jpg,jpeg,gif,png,bmp', tools:'mfull',skin:'o2007silver'});
									</script>
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
<div class="footer"></div>
</body>
</html>
