<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			
		<div class="sh_info_le">
            <div class="sh_info_img"><img src="${ctx}${sessionScope.loginUser.headPhoto}" width="160" height="100"></div>
            <div class="sh_list_switch">
                <dl class="show">
                    <dd><a href="${ctx}/account/personalInfo">个人信息</a></dd>
                    <dd><a href="${ctx}/account/changePwd">修改密码</a></dd>
                    <dd><a href="${ctx}/account/reactive">帐号激活</a></dd>
                </dl>
            </div>
        </div>
