<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="userBean" class="com.santrong.plt.javabean.UserBean" scope="page" />
			<div class="qu_right_it">
				<h2>${sessionScope.loginUser.showName}</h2>
				<p>
					<a href="${ctx}/personal/friend">好友<b><%=userBean.getFriendCount() %></b></a>｜<a href="${ctx}/collection">收藏<b><%=userBean.getCollectionWeikeCount() %></b></a>
				</p>
			</div>
