<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			
		<div class="sh_info_le">
            <div class="sh_info_img"><img src="${ctx}/resource/images/003.jpg" width="160" height="100"></div>
            <div class="sh_list_switch">
                <dl class="show">
                    <dt><a href="#">我是学生</a></dt>
                    <dd><a href="${ctx}/study/course">我的课程</a></dd>
                    <dd><a href="${ctx}/study/train">我的测验</a></dd>
                    <dd><a href="${ctx}/comment">我的评论</a></dd>
                    <dd><a href="${ctx}/collection">我的收藏</a></dd>
                    <dd><a href="${ctx}/account/personalInfo">个人信息</a></dd>
                    <dd><a href="${ctx}/account/changePwd">修改密码</a></dd>
                    <dd><a href="#" onclick="alert('开发中')">交易记录</a></dd>
                </dl>            
                <dl class="show">
                    <dt><a href="#">我是老师</a></dt>
                    <dd><a href="#" onclick="alert('开发中')">我的直播</a></dd>
                    <dd><a href="${ctx}/manage/course">课程管理</a></dd>
                    <dd><a href="${ctx}/manage/file">课件管理</a></dd>
                    <dd><a href="${ctx}/manage/question/list">试题管理</a></dd>
                </dl>
            </div>
        </div>
