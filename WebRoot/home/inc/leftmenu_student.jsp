<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			
		<div class="sh_info_le">
            <div class="sh_info_img"><img src="${ctx}/resource/images/003.jpg" width="160" height="100"></div>
            <div><a href="${ctx}/account/personalInfo">帐号设置</a></div>
            <div class="sh_list_switch">
                <dl class="show">
                    <dd><a href="${ctx}/study/course">我的课程</a></dd>
                    <dd><a href="${ctx}/study/train">我的测验</a></dd>
                    <dd><a href="${ctx}/comment">我的评论</a></dd>
                    <dd><a href="${ctx}/collection">我的收藏</a></dd>
                    <dd><a href="#" onclick="alert('开发中')">交易记录</a></dd>
                </dl>
            </div>
        </div>