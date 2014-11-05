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
                    <dd><a href="#">我的关注</a></dd>
                    <dd><a href="${ctx}/collection">我的收藏</a></dd>
                    <dd><a href="#">个人信息</a></dd>
                    <dd><a href="#">修改密码</a></dd>
                    <dd><a href="#">账户余额</a></dd>
                    <dd><a href="#">交易记录</a></dd>
                </dl>
                <dl>
                    <dt><a href="#">我是老师</a></dt>
                    <dd><a href="#">一体课课程</a></dd>
                    <dd><a href="#" onclick="alert('developing')">我的直播</a></dd>
                    <dd><a href="${ctx}/study/course">我的课程</a></dd>
                    <dd><a href="${ctx}/manage/video">视频管理</a></dd>
					<dd><a href="${ctx}/manage/doc">学习材料</a></dd>
                    <dd><a href="#" onclick="alert('developing')">试题管理</a></dd>
                    <dd><a href="#" onclick="alert('developing')">测验管理</a></dd>
                    <dd><a href="#">我的关注</a></dd>
                    <dd><a href="#" onclick="alert('developing')">个人信息</a></dd>
                    <dd><a href="#" >修改密码</a></dd>
                    <dd><a href="#">账户余额</a></dd>
                    <dd><a href="#" onclick="alert('developing')">交易记录</a></dd>
                </dl>
            </div>
        </div>
        <script type="text/javascript">
				$(function(){ 
					$('.sh_list_switch .show dt').addClass('icon');
					$('.sh_list_switch .show dd').show('slow');
					$('.sh_list_switch dt').click(function(){
						$(this).toggleClass('icon');
						$(this).nextAll().toggle();
					});					
				});				
		</script>