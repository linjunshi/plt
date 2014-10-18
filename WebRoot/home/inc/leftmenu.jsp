<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			<div class="manage_left_side">
				<div class="user_tip">
					<img src="${ctx}/resource/photo/T136.jpg" />
				</div>
				<dl class="manage_menu">
					<dt>我是学生</dt>
					<dd><a href="${ctx}/study/course">我的课程</a></dd>
					<dd><a href="${ctx}/study/train">我的测验</a></dd>
					<dd><a href="${ctx}/collection">我的收藏</a></dd>
					<dd><a href="${ctx}/comment">我的评论</a></dd>
					<dt>我是老师</dt>
					<dd><a href="#" onclick="alert('developing')">直播管理</a></dd>
					<dd><a href="${ctx}/manage/video">视频管理</a></dd>
					<dd><a href="${ctx}/manage/doc">学习材料</a></dd>
					<dd><a href="#" onclick="alert('developing')">试题管理</a></dd>
					<dd><a href="#" onclick="alert('developing')">测验管理</a></dd>
					<dd><a href="#" onclick="alert('developing')">一体机管理</a></dd>
					<dt>用户中心</dt>
					<dd><a href="#" onclick="alert('developing')">个人信息</a></dd>
					<dd><a href="#" onclick="alert('developing')">交易记录</a></dd>
					<dd><a href="#" onclick="alert('developing')">......</a></dd>
				</dl>
			</div>