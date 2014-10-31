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
						<c:if test="${query.pageNum > 1}">
						<a href="${basicUrl}page=${query.pageNum - 1}" title="上一页">上一页</a>
						</c:if>
						<c:forEach items="${query.pageSequence}" var="p">
						<c:if test="${p != query.pageNum}">
						<a href="${basicUrl}page=${p}" title="第${p}页">${p}</a>
						</c:if>
						<c:if test="${p == query.pageNum}">
						${p}
						</c:if>
						</c:forEach>
						<c:if test="${query.pageNum < query.pageCount}">
						<a href="${basicUrl}page=${query.pageNum + 1}" title="下一页">下一页</a>
						</c:if>
					</div>
				</c:if>