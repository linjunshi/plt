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
.system_tip { width:350px; line-height:26px; margin:10px auto; margin-left:100px; border:solid 1px #F5D8A7; border-radius:2px; background-color:#FFF6DB;padding-left: 10px;  }
</style>
</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
	<div class="content clearfix">
		<%@ include file="../../inc/leftmenu.jsp"%>
		<div class="qu_right">
			<div class="qu_right_it">
				<h2>${sessionScope.loginUser.showName}</h2>
				<p>你是我的小苹果</p>
				<p>
					财富：012<a href="#">充值</a><span>积分：2365</span>
				</p>
				<p>
					<a href="#">好友<b>12</b></a>｜<a href="#">关注<b>12</b></a>|<a
						href="#">粉丝<b>12</b></a>
				</p>
				<p class="qu_but">
					<a href="#">按钮</a>
				</p>
			</div>
			<div class="sh_info_r">
	          	<nav class="st_titile_r sh_title">
	            	<a href="${ctx}/account/personalInfo">基本信息</a>
	            	<a href="${ctx}/account/personalInfoEdu" class="sh_title_hover">教育信息</a>
	            	<a href="${ctx}/account/personalInfoExtend">其他扩展信息</a>
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
	                    <form method="post" action="${ctx}/account/personalInfoEdu" class="form_info common_form" id="personalInfo_form">
                    	    <div class="form_item">
	                            <label>学历：</label>
	                            <div class="form_field ">
	                                <select name="education" size="1" id="education" class="inline_ele">
	                                    <option value="1">小学</option>
	                                    <option value="2">初中</option>
	                                    <option value="3">高中</option>
	                                    <option value="4">大专</option>
	                                    <option selected="selected" value="5">本科</option>
	                                    <option value="6">硕士</option>
	                                    <option value="7">博士</option>
	                                </select>
	                                <span class="not-empty" title='此项为必填项'>*</span>
	                            </div>
	                        </div>
	                          <div class="form_item">
	                            <label>职称：</label>
	                            <div class="form_field ">
	                                <select name="positional" size="1" id="positional" class="inline_ele">
	                                    <option value="1">助教</option>
	                                    <option selected="selected" value="2">讲师</option>
	                                    <option value="3">副教授</option>
	                                    <option value="4">教授</option>
	                                </select>
	                                <span class="not-empty" title='此项为必填项'>*</span>
	                            </div>
	                        </div>
	                        <div class="form_item">
	                            <label for="graduateSchool">毕业院校：</label>
	                            <div class="form_field">
	                                <input placeholder="请输入你的毕业院校" class="form_text" id="graduateSchool" name="graduateSchool" type="text" value="${userEducation.graduateSchool}" required/>
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
