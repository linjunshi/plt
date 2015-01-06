<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div  id="header">
	    <div id="headerinside"> <a href="${ctx}/" class="logo"><img src="${ctx}/resource/photo/logo.png" width="265" height="70" /></a>
		</div>
	    <div id="wrapper_box">
	        <div id="wrapper">
	            <div>
	                <ul>
						<li><a href='${ctx}/'>首页</a></li>
						<li><a href='${ctx}/video'>微课</a></li>
						<li><a href='${ctx}/course?filter=live'>同步课堂</a></li>
						<li><a href='${ctx}/question'>题库</a></li>
	                </ul>
	            </div>
	        </div>
	    </div>
	</div>
