<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_question";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../inc/top_new.jsp"%>
	<div class="content clearfix">
		<div class="questions_list">
			<div id="container_box">
				<div class="container_content">
				    <div class="tesch_box">
				        <div class="teach_sea">
				        	<h3>学段：</h3>
				        	<ul>
				        		<li>
						        	<a class="${class : staticEq('all', grade)}" href="${ctx}/school">全部</a>
							    	<c:forEach items="${applicationScope.gradeList}" var="item">
							    	<a class="${class : staticEq(item.gradeEnName, grade)}" href="${ctx}/school/${item.gradeEnName}">${item.gradeName}</a>
						    	</c:forEach>
				        		</li>
				        	</ul>
				        </div>
				        <div class="schoool_box_list">
				            <ul class="question_list">
				            	<c:forEach items="${questionList}" var="question" varStatus="st">
				                <li class="<c:if test="${(st.index+1)%5==0}">margin_right_clear</c:if>">
				                	<c:if test="${question.assemble}">
				                		<a href="javascript:void(0);" class="select" qid="${question.id}">取消选择</a>
				                	</c:if>
				                	<c:if test="${!question.assemble}">
				                		<a href="javascript:void(0);" class="select" qid="${question.id}">选择</a>
				                	</c:if>
				                    <h2>${question.topic}</h2>
				                </li>
				            	</c:forEach>
				            </ul>
				        </div>
				    </div>
				    <div class="select_question_box" style="width:200px; height:200px; border:solid 1px #ccc;">
				    <div>
				    </div>
				    <a href="${ctx}/question/begin">开始做题</a>
				    </div>
				</div>
			</div>
		    <c:set var="basicUrl" value="${ctx}/question" />
		   	<%@ include file="../inc/pagination.jsp"%>
		</div>
	</div>
</div>
</body>
</html>
