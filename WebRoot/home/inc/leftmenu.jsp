<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			
	   <div class="qu_left">
			<div class="qu_user_pho">
				<img src="${ctx}${sessionScope.loginUser.headPhoto}" width="140" height="140">
			</div>
			<div class="qu_item">
				<ul>
					<c:if test="${sessionScope.loginUser.student}">
					<li><a href="${ctx}/personal/center" class='pt4 <c:if test="${flag == 'center'}">pt4_hover</c:if>' >最新动态</a></li>
					<li><a href="${ctx}/history/list" class='pt4 <c:if test="${flag == 'history'}">pt4_hover</c:if>' >我看过的</a></li>
					<%-- <li><a href="${ctx}/competition/list" class='pt5 <c:if test="${flag == 'competition'}">pt5_hover</c:if>' >我的练习</a></li> --%>
					<li><a href="${ctx}/collection" class='pt6 <c:if test="${flag == 'collection'}">pt6_hover</c:if>' >我的收藏</a></li>
					<li><a href="${ctx}/comment" class='pt7 <c:if test="${flag == 'comment'}">pt7_hover</c:if>' >我的评论</a></li>
					<li><a href="${ctx}/story/list" class='pt8 <c:if test="${flag == 'story'}">pt8_hover</c:if>' >剧本故事</a></li>
					<li><a href="${ctx}/personal/friend" class='pt9 <c:if test="${flag == 'friend'}">pt9_hover</c:if>' >朋友圈</a></li>
					<li>---------------------------------</li>
					<li><a href="#" class='pt10 <c:if test="${flag == 'stat'}">pt10_hover</c:if>' >学习统计</a></li>
					<li>---------------------------------</li>
					</c:if>
					<li><a href="${ctx}/account/personalInfo" class='pt11 <c:if test="${flag == 'personalInfo'}">pt11_hover</c:if>' >个人信息</a></li>
					<li><a href="${ctx}/account/changePwd" class='pt11 <c:if test="${flag == 'changePwd'}">pt11_hover</c:if>' >修改密码</a></li>
					<c:if test="${!sessionScope.loginUser.student}">
					<li><a href="${ctx}/account/reactive" class='pt11 <c:if test="${flag == 'reactive'}">pt11_hover</c:if>' >帐号激活</a></li>
					</c:if>
					<c:if test="${sessionScope.loginUser.teacher}">
					<li>---------------------------------</li>
					<%-- <li><a href="${ctx}/manage/live" class='pt11 <c:if test="${flag == 'live'}">pt11_hover</c:if>' >待上课程</a></li> --%>
                    <%-- <li><a href="${ctx}/manage/course" class='pt11 <c:if test="${flag == 'course'}">pt11_hover</c:if>' >课程管理</a></li> --%>
                    <li><a href="${ctx}/manage/weike" class='pt11 <c:if test="${flag == 'weike'}">pt11_hover</c:if>' >微课管理</a></li>
                    <%-- <li><a href="${ctx}/manage/file" class='pt11 <c:if test="${flag == 'file'}">pt11_hover</c:if>' >课件管理</a></li> --%>
                    <li><a href="${ctx}/manage/question/list" class='pt11 <c:if test="${flag == 'question_list'}">pt11_hover</c:if>' >我的题库</a></li>
					</c:if>
					<c:if test="${sessionScope.loginUser.superMan}">
					<li>---------------------------------</li>
					<li><a href="${ctx}/super/question/approve" class='pt11 <c:if test="${flag == 'question_approve'}">pt11_hover</c:if>' >试题审核</a></li>
					<li><a href="${ctx}/super/knowledge/tree" class='pt11 <c:if test="${flag == 'knowledge_tree'}">pt11_hover</c:if>' >知识点树</a></li>
					</c:if>					
				</ul>
			</div>
		</div>
