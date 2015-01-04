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
Globals.page = "Manage_orderList";
</script>
</head>
<body>
		<%@ include file="../../inc/top_bg.jsp"%>
		<div id="container_box">
			<div id="container_content">
			    <div class="sectionMain clr">
					<%@ include file="../../inc/leftmenu_student.jsp"%>
					<div class="sh_info_r">
			            <div class="sh_title">
			                <h2>交易记录</h2>
			            </div>
			            <div class="sh_collection">
			                <table border="1" class="sh_coll_tab">
			                    <colgroup>
			                    <col width="50">
			                    <col width="430">
			                    <col width="110">
			                    <col width="120">
			                    <col width="100">
			                    <col width="150">
			                    </colgroup>
			                    <thead>
			                        <tr>
			                            <th>序号</th>
			                            <th>课程名称</th>
			                            <th>实付款(元)</th>
			                            <th>交易时间</th>
			                            <th>交易状态</th>
			                            <th>交易操作</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	
			                    	<c:forEach items="${orderList}" var="order" varStatus="ct">
				                        <tr>
				                            <td>${ct.index + 1}</td>
				                            <td class="sh_h_im">
				                            	<div>
				                            		<a href="${ctx}/course/${order.courseId}.html" target="_blank"><img src="${ctx}${order.thumbnail}" width="90" height="90"></a>
					                            	<div class="sh_order_title">
					                            		<a href="${ctx}/course/${order.courseId}.html" target="_blank">${order.courseName}</a>
					                            	</div>
					                            	<div class="sh_order_detail">
					                            		授课老师：<a href="${ctx}/teacher/${order.ownerId}.html" target="_blank">${order.teacher}</a>
					                            		&nbsp;&nbsp;共${order.chapterCount}课时
					                            		&nbsp;&nbsp;课程简介： <a href="${ctx}/course/${order.courseId}.html" target="_blank">[详细]</a>
					                            	</div>
					                            </div>
				                            </td>
				                            <td>${order.price}</td>
				                            <td>
				                            	<p><fmt:formatDate value="${order.cts}" type="date" /></p>
				                            	<p><fmt:formatDate value="${order.cts}" type="time"/></p>
				                            </td>
				                            <td class="sh_release">
				                            	<c:if test="${order.status == 0}">
													<span>未付款</span>			                            	
				                            	</c:if>
				                            	<c:if test="${order.status == 1}">
				                            		<span>交易成功</span>
				                            		<!-- <span>已支付</span> -->
				                            	</c:if>
				                            	<c:if test="${order.status == -1}">
				                            		<span>交易已取消</span>
				                            	</c:if>
					                        </td>
				                            <td class="btn_order_operation">
				                            	<c:if test="${order.status == 0}">
													<a href="javascript:void(0);" onclick="alert('开发中')" class="buy">现在付款</a>
													<a href="javascript:void(0);" onclick="alert('开发中')" class="cancelOrder" title="未付款的交易可以取消订单">取消订单</a>
												</c:if>
				                            	<c:if test="${order.status == 1}">
					                            	<a href="${ctx}/course/${order.courseId}.html#remark" target="_blank">追加评论</a>
												</c:if>
												<c:if test="${order.status == -1}">
													<a href="javascript:void(0);" class="redo_buy">重新购买</a>
				                            	</c:if>
				                            </td>
				                        </tr>
			                    	</c:forEach>
			                        
		                        </tbody>
		                        <c:if test="${fn:length(query.pageSequence) > 1}">
				                    <tfoot>
				                        <tr>
				                            <td colspan="6">
					                            <c:set var="basicUrl" value="${ctx}/order/list" />
					            				<%@ include file="../../inc/pagination.jsp"%>
					            			</td>
				                        </tr>
				                    </tfoot>
			                    </c:if>
			                </table>
			            </div>
			        </div>
				</div>
			</div>
		</div>
		
		<%@ include file="../../inc/friendlylink.jsp"%>
</body>
</html>