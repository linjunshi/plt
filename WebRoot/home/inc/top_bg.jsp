<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div  id="header">
	  <div id="header_naw">
	    <div id="headerinside_login"> <a href="${ctx}/" class="logo"><img src="${ctx}/resource/photo/logo.png" width="265" height="70" /></a>
	        <div class="city_info">
	            <h2><a class="city_info_name_bg" href="${ctx}/">${sessionScope.area.cityName}</a></h2>
			            <a class="city_info_toggle_bg" href="${ctx}/changecity">切换城市</a>
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
            			<a href="javascript:void(0);" class="search_sid" title="点击搜索"><img src="${ctx}/resource/images/magnifier_24.png"></a>
					</div>
				</div>
				<div class="fast_navigate"></div>
			</form>
			<c:if test="${sessionScope.loginUser == null}">
				<a href="${ctx}/account/login" class="user_info_login">登录</a>
				<a href="${ctx}/account/regist" class="user_info_signup">注册</a>
			</c:if>
			<c:if test="${sessionScope.loginUser != null}">
			<div> 
				<a href="${ctx}/study/course" class="user_info_th">我是老师</a> 
				<a href="${ctx}/manage/live" class="user_info_stud">我是学生</a>
				<a href="${ctx}/account/personalInfo" class="user_info_img" title="帐号设置"></a> 
				<a href="javascript:void(0);" class="user_info_user" title="${sessionScope.loginUser.showName}"><i></i><img src="${ctx}${sessionScope.loginUser.headPhoto}" width="40" width="40"></a> 
			</div>
			</c:if>
		</div>
	</div>
</div>
