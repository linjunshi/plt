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
.system_tip { width:380px; line-height:26px; margin:10px auto; margin-left:100px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
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
	            	<a href="${ctx}/account/personalInfo">基本信息</a>
	            	<a href="${ctx}/account/personalInfoEdu">教育信息</a>
	            	<a href="${ctx}/account/personalInfoExtend" class="sh_title_hover">其他扩展信息</a>
	            </nav>
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
									<input placeholder="格式：2014-01-01 ,双击选择" class="form_text" name="birthday" id="birthday" type="text" value="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${userExtends.birthday}" />" required_Date>
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
