<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div  id="header">
	    <div id="headerinside"> <a href="#" class="logo"><img src="${ctx}/resource/images/logo.png" width="148" height="70" /></a>
	        <div class="city_info">
	            <h2><a class="city_info_name" href="#">${sessionScope.area.cityName}</a></h2>
			            <a class="city_info_toggle" href="#">切换城市</a>
			</div>
			<form class="search_form" method="get">
				<div class="search_navigat">
					<div class="search">
						<input name="search" type="text" class="search_text" value="站内搜索" />
					    <input type=hidden name=ie value=GB2312> 
					    <input type=hidden name=oe value=GB2312> 
					    <input type=hidden name=hl value=zh-CN>  
						<a href="javascript:void(0);" class="search_txt">站内搜索</a>
					</div>
					<div class="fast_navigat"></div>
				</div>
			</form>
			<c:if test="${sessionScope.loginUser == null}">
				<a href="${ctx }/account/login" class="user_info_login">登录</a>
				<a href="${ctx }/account/regist" class="user_info_signup">注册</a>
			</c:if>
			<c:if test="${sessionScope.loginUser != null}">
				<span>${sessionScope.loginUser.showName}</span>
				<a href="${ctx }/account/logout">注销</a>
				<a href="${ctx }/study/course">管理中心</a>
			</c:if>
		</div>
	    <div id="wrapper_box">
	        <div id="wrapper">
	            <div>
	                <ul>
						<li><a href='${ctx}/'><fmt:message key="menu_index" /></a></li>
						<li><a href='${ctx}/course?filter=live'><fmt:message key="menu_live" /></a></li>
						<li><a href='${ctx}/school'><fmt:message key="menu_school" /></a></li>
						<li><a href='${ctx}/teacher'><fmt:message key="menu_teacher" /></a></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>