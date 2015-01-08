<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div  id="header">
	  <div id="header_naw">
	    <div id="headerinside_login"> <a href="${ctx}/" class="logo"><img src="${ctx}/resource/photo/logo2.png" width="265" height="70" /></a>
			<c:if test="${sessionScope.loginUser != null}">
			<div class="sh_user_info">
				<ul>
					<li><a href="${ctx}/study/score" class="user_info_stud">我是学生</a></li>
					<li><a href="${ctx}/manage/live" class="user_info_th">我是老师</a></li>
				</ul>
			</div>
			</c:if>
		</div>
	</div>
</div>
