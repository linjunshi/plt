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
Globals.page = "Manage_liveList";
</script>
</head>
<body>
		<%@ include file="../../inc/top_bg.jsp"%>
		<div id="container_box">
			<div id="container_content">
			    <div class="sectionMain clr">
					<%@ include file="../../inc/leftmenu_teacher.jsp"%>
					<div class="sh_info_r">
			            <div class="sh_title">
			                <h2>待上课程</h2>
			                <a href="${ctx}/download/LiveManagerSetup.exe" class="sh_add">下载老师客户端工具</a>
			            </div>
			            <object id="SantrongPlayer" classid="clsid:27671653-7A2D-4F23-92CF-76C7984F2CD5" class="hide" VIEWASTEXT></object>
						<div class="sh_myvod_ti"><span>时间</span><span class="sh_ti_sid">直播名称</span><span>所属课程</span><span>操作</span></div>
				            <div class="sh_myvod">
				                <ul>
				                	<c:forEach items="${liveList}" var="live">
				                    <li style="height:54px;">
				                        <div class="sh_myvod_le">
				                            <h3><fmt:formatDate value="${live.beginTime}" type="date"/></h3>
				                            <span><fmt:formatDate value="${live.beginTime}" pattern="HH:mm"/>--<fmt:formatDate value="${live.endTime}" pattern="HH:mm"/></span>
										</div>
				                        <div class="sh_myvod_ri clr"> <span><img src="${ctx}/resource/images/007.jpg"></span>
				                            <h2>${live.title}</h2>
				                            <p>${live.courseName}<br/>${live.remark}</p>
				                            <p><a href="javascript:void(0);" class="open_tool" id="i_${live.id}">进入</a></p>
				                        </div>
				                    </li>
				                    </c:forEach>
				                </ul>
				            </div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>