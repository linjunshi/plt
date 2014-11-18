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
Globals.page = "Manage_index";
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
		                <h2>章节维护</h2>
		            </div>
		            
		            <div class="sh_collection">
						<c:forEach items="${chapterList}" var="chapter" varStatus="ct">
							<dl class="sh_add_chapter">
								<dt>
									<div class="sh_add_opera">
										<h2>第一百零三章</h2>
										<p>故事的发展有了进一步的改观</p>
										<span class="sh_operation"><a href="#">删除</a><a
											href="#">修改</a></span>
									</div>
								</dt>
								<dd>第一小节</dd>
								<dd class="pt11">
									<a href="#">添加一小节</a>
								</dd>
							</dl>
						</c:forEach>
						<a href="#" class="sh_addop_a">添加一章</a> 
		             </div>
		        </div>
				
			</div>
		</div>
	</div>

	<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>