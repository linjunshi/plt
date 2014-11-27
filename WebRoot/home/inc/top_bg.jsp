<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div  id="header">
	    <div id="headerinside"> <a href="${ctx}/" class="logo"><img src="${ctx}/resource/photo/logo.png" width="265" height="70" /></a>
	        <div class="city_info">
	            <h2><a class="city_info_name" href="${ctx}/">${sessionScope.area.cityName}</a></h2>
			            <a class="city_info_toggle" href="${ctx}/changecity">切换城市</a>
			</div>
			<form class="search_form" action="${ctx}/course" method="get">
				<div class="search_navigate">
					<div class="search">
						<ul class="search_category">
							<li id="q_course">课程</li>
							<li id="q_school" class="hide">学校</li>
							<li id="q_teacher" class="hide">老师</li>
						</ul>
						<div class="select_down"></div>
						<c:set var="q" value=""/>
						<c:if test="${!empty query && !empty query.keywords}">
							<c:set var="q" value="${query.keywords}"/>	
						</c:if>
						<input class="search_text" type="text" placeholder="请输入你感兴趣的内容" name="q" value="${q}" />
					</div>
					<input class="search_submit" type="submit" value="搜索" />
				</div>
				<div class="fast_navigate"></div>
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
						<li><a href='${ctx}/study/course'>我是学生</a></li>
						<li><a href='${ctx}/manage/live'>我是老师</a></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>