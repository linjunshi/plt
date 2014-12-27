<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			
		<div class="sh_info_le">
            <div class="sh_info_img"><img src="${ctx}${sessionScope.loginUser.headPhoto}">
				<p class="sh_info_us">${sessionScope.loginUser.showName}</p>
		        <p class="p3"><a href="${ctx}/study/course" title="我是学生">我是学生</a></p>
		        <p class="p3"><a href="${ctx}/manage/live" title="我是老师">我是老师</a></p>
		        <p class="p3"><a href="${ctx }/account/logout" title="退出系统">注销</a></p>
	        </div>
	        <div class="sh_hone"><h2><a href='${ctx}/' title="返回首页"><i></i><em>返回<fmt:message key="menu_index" /></em></a></h2></div>
            <div class="sh_list_switch">
                <dl class="show">
                    <dd><a href="${ctx}/account/personalInfo">个人信息</a></dd>
                    <dd><a href="${ctx}/account/changePwd">修改密码</a></dd>
                    <dd><a href="${ctx}/account/reactive">帐号激活</a></dd>
                </dl>
            </div>
        </div>
