<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../../../inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_doneTrain";
</script>
</head>
<body>
	<%@ include file="../../../inc/top.jsp"%>
	<div id="container_box">
    	<div id="container_content">
			<div class="sectionMain clr">
		        <!-- <%@ include file="../../../inc/leftmenu.jsp"%> -->
		        <div class="sh_info_r" id="trainDiv">
		            <div class="sh_title">
		                <h2>${train.title}，来自${chapter.remark}，课程名称${course.courseName}</h2>
		            </div>
		            <div class="form_questions">
		            <p>当前第1题，一共${total}题</p>
		            
		                <div id="questionDiv">
		                </div>
		                
						<div class="form_action">
								<a class="btn_question preOne" style="visibility:hidden" href="javascript:void(0);">上一题</a>
								<a class="btn_question nextOne" <c:if test="${total <= 1}">style="visibility:hidden"</c:if> href="javascript:void(0);">下一题</a>
						</div>
		                
	                    <form action="${ctx}/train/question" method="post">
							<input type="hidden" name="trainId" value="${train.id}" />
							<input type="hidden" name="chapterId" value="${chapter.id}" />
							<input type="hidden" name="module" value="${module}" />
							<input type="hidden" name="total" value="${total}" />
							<input type="hidden" name="index" value="1" />
	                       	<c:if test="${module == 'result'}">
	                       	<span>已答${doneCount}题，错误${wrongCount}题</span>
	                       	</c:if>							
							<c:forEach items="${indexList}" var="qIndex" varStatus="st">
	                               <div class="question_index ${qIndex.classString}">
	                               <span>${st.count}</span>
	                               <input type="hidden" name="answer" value="${qIndex.answer}" />
	                               </div>
	                       	</c:forEach>
	                       	<c:if test="${module == 'answer'}">
	                       	<a href="javascript:void(0);" class="ajax_submit">提交试卷</a>
	                       	</c:if>
	                       	<c:if test="${module == 'result'}">
	                       	<a href="javascript:void(0);" class="ajax_reset">重新做题</a>
	                       	</c:if>                 	
                       	</form>
		            </div>
		        </div>
	        </div>
	    </div>
	</div>
	<%@ include file="../../../inc/friendlylink.jsp"%>
</body>
</html>