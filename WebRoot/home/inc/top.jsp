<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
		<div class="header">
			<div class="site_mast">
				<img class="logo" src="${ctx}/resource/photo/logo.png" />
				<h2>三间在线补习班</h2>
			</div>
			
			<div class="site_component">
				<span class="city_info">${sessionScope.area.cityName}</span>
				
				<c:if test="${sessionScope.loginUser != null}">
					<p>welcome(${sessionScope.loginUser.showName})---
						<a href="javascript:void(0);" class="logout" ><fmt:message key="index_user_userlogout"/></a>
					</p>
				</c:if>
				<c:if test="${sessionScope.loginUser == null}">
					<p><a href="${ctx }/login.action" ><fmt:message key="index_user_userlogin"/></a></p>
				</c:if>
				
				<p><a href="${ctx }/study/course" >管理中心</a></p>
				
				<div class="component_search">
					<input type="text" name="keywords" />
					<input type="submit" />
				</div>
			
			</div>
		</div>
		
		<ul class="navigator ul_flat">
			<li><a href='${ctx}/'><fmt:message key="menu_index" /></a></li>
			<li><a href='${ctx}/live'><fmt:message key="menu_live" /></a></li>
			<li><a href='${ctx}/school'><fmt:message key="menu_school" /></a></li>
			<li><a href='${ctx}/teacher'><fmt:message key="menu_teacher" /></a></li>
		</ul>