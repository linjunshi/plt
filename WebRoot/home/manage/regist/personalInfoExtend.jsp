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
Globals.page = "Manage_personalInfoExtend";
</script>
<style type="text/css">
.system_tip { width:350px; line-height:26px; margin:10px auto; margin-left:100px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
</style>
</head>
<body>
	<%@ include file="../../inc/top_bg.jsp"%>
	<div id="container_box">
		<div id="container_content">
			<div class="sectionMain clr">
				<%@ include file="../../inc/leftmenu_regist.jsp"%>
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
		                    <form method="post" action="${ctx}/account/personalInfoExtend" class="form_info common_form" id="personalInfo_form">
		                        <div class="form_item">
		                            <label for="nativePlace">籍贯：</label>
		                            <div class="form_field">
		                                <input placeholder="6-32个字符" class="form_text" id="nativePlace" name="nativePlace" type="text" value="${userExtends.nativePlace}" required/>
		                            </div>
		                        </div>
		                        <div class="form_item">
									<label for="birthday">出生日期：</label>
									<div class="form_field">
										<input placeholder="格式：2014/01/01" class="form_text" name="birthday" id="birthday" type="text" value="<fmt:formatDate type="date" pattern="yyyy/MM/dd" value="${userExtends.birthday}" />" required_Date>
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

	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>