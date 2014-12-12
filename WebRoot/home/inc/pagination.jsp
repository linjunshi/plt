<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

				<c:if test="${fn:length(query.pageSequence) > 1}">
					<c:choose>
					    <c:when test="${fn:contains(basicUrl, '?')}">
					    <c:set var="basicUrl" value="${basicUrl}&" />
					    </c:when>
					    <c:otherwise>
					    <c:set var="basicUrl" value="${basicUrl}?" />
					    </c:otherwise>   
					</c:choose>
					<div class="pagination">
						<form action="${basicUrl}" method="get" class="common_form">
							<c:if test="${query.pageNum > 1}">
							<a href="${basicUrl}page=1" title="首页">首页</a>
							<a href="${basicUrl}page=${query.pageNum - 1}" title="上一页">上一页</a>
							</c:if>
							<c:forEach items="${query.pageSequence}" var="p">
							<c:if test="${p != query.pageNum}">
							<a href="${basicUrl}page=${p}" title="第${p}页">${p}</a>
							</c:if>
							<c:if test="${p == query.pageNum}">
							<a href="javascript:void(0);" class="page_current" title="当前页"><b>${p}</b></a>
							</c:if>
							</c:forEach>
							<c:if test="${query.pageNum < query.pageCount}">
							<a href="${basicUrl}page=${query.pageNum + 1}" title="下一页">下一页</a>
							<a href="${basicUrl}page=${query.pageCount}" title="尾页">尾页</a>
							</c:if>
							页码：<b><span class="page_height_light">${query.pageNum}</span>&nbsp;/&nbsp;${query.pageCount}</b>&nbsp;页，跳到第
							<input type="text" name="page" value="" class="page_go_input" required required_Number>&nbsp;页&nbsp;
							<input type="submit" value="GO" class="page_submit_input" title="点击" >，共&nbsp;<b class="page_height_light">${query.count}</b>&nbsp;条记录
						</form>
					</div>
				</c:if>