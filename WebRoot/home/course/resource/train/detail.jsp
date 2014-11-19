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
Globals.page = "Index_index";
</script>
</head>
<body>
	<%@ include file="../../../inc/top.jsp"%>
	<div id="container_box">
    	<div id="container_content">
			<div class="sectionMain clr">
		        <!-- <%@ include file="../../../inc/leftmenu.jsp"%> -->
		        <div class="sh_info_r">
		            <div class="sh_title">
		                <h2>${train.title}，来自${chapter.remark}</h2>
		            </div>
		            <div class="form_questions">
		            <c:if test="${total > 0}">
		            <p>当前第${index}题，一共${total}题</p>
		                <div id="demo_zone">
		                    <form method="post" action="${ctx}/train/question/commit" class="form_info" id="register_form">
		                    	<input type="hidden" name="resourceId" value="${resId}"/>
		                    	<input type="hidden" name="questionId" value="${question.id}"/>
		                    	<input type="hidden" name="trainId" value="${train.id}"/>
		                    	<input type="hidden" name="chapterId" value="${chapter.id}"/>
		                    	<input type="hidden" name="total" value="${total}"/>
		                    	<input type="hidden" name="index" value="${index}"/>
		                        <div class="form_item">
		                            <label>题型：</label>
		                            <div class="form_field">
		                                ${question.typeString}
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <label>标题：</label>
		                            <div class="form_field">
		                                ${question.topic}
		                            </div>
		                        </div>
		                        <div class="form_item">
		                            <div class="form_field">
		                                <p>
			                                <input value="1" class="form_radio" name="answer" type="checkbox">
			                                <span class="form_ra_text">Ａ</span><span>${question.opt1}</span>
		                                </p>
		                                <p>
			                                <input value="2" class="form_radio" name="answer" type="checkbox">
			                                <span class="form_ra_text">Ｂ</span><span>${question.opt2}</span>
		                                </p>
		                                <p>
			                                <input value="4" class="form_radio" name="answer" type="checkbox">
			                                <span class="form_ra_text">Ｃ</span><span>${question.opt3}</span>
		                                </p>
		                                <p>
			                                <input value="8" class="form_radio" name="answer" type="checkbox">
			                                <span class="form_ra_text">Ｄ</span><span>${question.opt4}</span>
		                                </p>
		                            </div>
		                        </div>
		                        <div class="form_action">
		                        	<input type="submit" value="确定" />
		                        	<c:if test="${index > 1}">
		                        	<a class="btn_question" href="${ctx}/train?resId=${resId}&index=${index-1}">上一题</a>
		                        	</c:if>
		                        	<c:if test="${index < total}">
			                        <a class="btn_question" href="${ctx}/train?resId=${resId}&index=${index+1}">下一题</a>
			                        </c:if> 
		                        </div>
		                    </form>
		                </div>
		                </c:if>
		                <c:if test="${total <= 0}">
		                该测验没有试题
		                </c:if>
		            </div>
		        </div>
	        </div>
	    </div>
	</div>
	<%@ include file="../../../inc/friendlylink.jsp"%>
</body>
</html>