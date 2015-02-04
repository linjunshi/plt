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
Globals.page = "Manage_syllabus";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
	<div class="school_but">
		<div class="school_sid">
			<a href="${ctx}/study/syllabus?subjectId=10000" class="sid_a<c:if test='${subjectId == 10000}'>_hover</c:if>">&nbsp;</a>
			<a href="${ctx}/study/syllabus?subjectId=10001" class="sid_b<c:if test='${subjectId == 10001}'>_hover</c:if>">&nbsp;</a>
			<a href="${ctx}/study/syllabus?subjectId=10002" class="sid_c<c:if test='${subjectId == 10002}'>_hover</c:if>">&nbsp;</a>
		</div>
	</div>
	<div class="content clearfix">
	
		<c:forEach items="${lessonUnitList}" var="lessonUnit" varStatus="st">
			<div class="school_con<c:if test="${st.first}"> school_con_hover</c:if><c:if test="${st.last}"> school_con_last</c:if> clearfix">
				<h2><c:if test="${lessonUnit.term == 1}">上学期</c:if>
					<c:if test="${lessonUnit.term == 2}">下学期</c:if> -> ${lessonUnit.unitName}</h2>
				<p>&nbsp;</p>
				<ul <c:if test="${st.index == 0}">class='current'</c:if>>
					<c:forEach items="${lessonUnit.weikeList}" var="weike" varStatus="st">
						<li>
							<p><a href="${ctx}/weike/${weike.id}.html"><img src="${ctx}${weike.thumbnail}" width="200"height="100"></a></p>
							<p class="sc_co_title">${weike.courseName}</p>
							<p>时长: ${weike.duration}分钟</p>
							<p>
								<c:if test="${weike.orderStatus == 1}"><a href="javascript:void(0);" class="guomai">已购买</a></c:if>
								<c:if test="${weike.orderStatus != 1}">
									<form action="https://pay3.chinabank.com.cn/PayGate?encoding=UTF-8" method="post" target="_blank" name="E_FORM" class="buy_form">
							            <input type="hidden" name="v_mid" value="" />
							            <input type="hidden" name="v_oid" value="" />
							            <input type="hidden" name="v_amount" value="" />
							            <input type="hidden" name="v_moneytype" value="" />
										<input type="hidden" name="v_url" value="" />
										<input type="hidden" name="v_md5info" value="" />
										<input type="hidden" name="v_rcvname" value="" />
										<input type="hidden" name="remark1" value="" />
										<input type="hidden" name="weikeId" value="${weike.id}"/>
										<a href="javascript:void(0);" target="_blank" class="notguomai buy"  title="点击此按钮，到下一步网银支付后，就可以开始学习了。">购买</a>
									</form>		           
								</c:if>
							</p>
						</li>
					</c:forEach>
					<c:if test="${empty lessonUnit.weikeList}"><p class="nothing">暂无相关的微课！</p></c:if>
				</ul>
			</div>
		</c:forEach>

	</div>
</div>
<div class="footer"></div>
</body>
</html>
