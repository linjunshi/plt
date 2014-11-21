<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			
		<div class="sh_info_le">
            <div class="sh_info_img"><img src="${ctx}/resource/images/003.jpg" width="160" height="100"></div>
            <div class="sh_list_switch">         
                <dl class="show">
                    <dd><a href="#" onclick="alert('开发中')">我的直播</a></dd>
                    <dd><a href="${ctx}/manage/course">课程管理</a></dd>
                    <dd><a href="${ctx}/manage/file">课件管理</a></dd>
                    <dd><a href="${ctx}/manage/question/list">试题管理</a></dd>
                </dl>
            </div>
        </div>
