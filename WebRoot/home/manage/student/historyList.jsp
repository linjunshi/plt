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
Globals.page = "Manage_historyList";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
		<div class="content clearfix">
			<%@ include file="../../inc/leftmenu_student2.jsp"%>
			<div class="qu_right">
				<div class="qu_right_it">
					<h2>王小</h2>
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
				<div class="qu_ri_school">
					<div class="sh_ke clearfix">
						<h2>最近浏览过的</h2>
						<!-- <a href="#" class="catalog_box_more">换一换</a> -->
						<ul class="sh_sch_ke ">
						
							<c:forEach items="${weikeList}" var="weike" varStatus="st">
								<li <c:if test="${st.index %4 == 0}">class="maigin_right"</c:if> >
									<a href="${ctx}/weike/${weike.id}.html" target="_blank"><img src="${ctx}${weike.thumbnail}" title="${weike.courseName}" width="220" height="140"></a>
									<h2>${weike.courseName}</h2>
									<p>
										<input type="hidden" name="weikeId" value="${weike.id}"/>
										<input type="hidden" name="attendId" value="${weike.attendId}"/>
										<span class="sch_unm"><a href="javascript:void(0);" class="coll_course">加入收藏</a></span>
										<span class="sch_unm"><a href="javascript:void(0);" class="remove_history">删除记录</a></span>
									</p>
									<c:if test="${weike.orderStatus == 1}"><p class="sh_sch_but"><a href="javascript:void(0);" class="col_st">已购买</a></p></c:if>
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
											<p class="sh_sch_but">
												<input type="hidden" name="weikeId" value="${weike.id}"/>
												<a href="javascript:void(0);" target="_blank" class="col_sh buy">购买</a>
											</p>
										</form>	
									</c:if>
								</li>
							</c:forEach>

						</ul>
						<c:set var="basicUrl" value="${ctx}/history/list" />
		       			<%@ include file="../../inc/pagination_new.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
<div class="footer"></div>
</body>
</html>
