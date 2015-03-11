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
					<li><a href="${ctx}/personal/center" class='center <c:if test="${flag == 'center'}">center_hover</c:if>' >最新动态</a></li>
					<li><a href="${ctx}/history/list" class='history <c:if test="${flag == 'history'}">history_hover</c:if>' >我看过的</a></li>
					<%-- <li><a href="${ctx}/competition/list" class='competition <c:if test="${flag == 'competition'}">competition_hover</c:if>' >我的练习</a></li> --%>
					<li><a href="${ctx}/collection" class='collection <c:if test="${flag == 'collection'}">collection_hover</c:if>' >我的收藏</a></li>
					<li><a href="${ctx}/comment" class='comment <c:if test="${flag == 'comment'}">comment_hover</c:if>' >我的评论</a></li>
					<li><a href="${ctx}/story/list" class='story <c:if test="${flag == 'story'}">story_hover</c:if>' >剧本故事</a></li>
					<li><a href="${ctx}/personal/friend" class='friend <c:if test="${flag == 'friend'}">friend_hover</c:if>' >朋友圈</a></li>
<%-- 					<li>---------------------------------</li>
					<li><a href="#" class='stat <c:if test="${flag == 'stat'}">stat_hover</c:if>' >学习统计</a></li> --%>
					<li>---------------------------------</li>
					</c:if>
					<li><a href="${ctx}/account/personalInfo" class='personalInfo <c:if test="${flag == 'personalInfo'}">personalInfo_hover</c:if>' >个人信息</a></li>
					<li><a href="${ctx}/account/changePwd" class='changePwd <c:if test="${flag == 'changePwd'}">changePwd_hover</c:if>' >修改密码</a></li>
					<c:if test="${!sessionScope.loginUser.student}">
					<li><a href="${ctx}/account/reactive" class='reactive <c:if test="${flag == 'reactive'}">reactive_hover</c:if>' >帐号激活</a></li>
					</c:if>
					<c:if test="${sessionScope.loginUser.teacher}">
					<li>---------------------------------</li>
					<%-- <li><a href="${ctx}/manage/live" class='live <c:if test="${flag == 'live'}">live_hover</c:if>' >待上课程</a></li> --%>
                    <%-- <li><a href="${ctx}/manage/course" class='course <c:if test="${flag == 'course'}">course_hover</c:if>' >课程管理</a></li> --%>
                    <li><a href="${ctx}/manage/weike" class='weike <c:if test="${flag == 'weike'}">weike_hover</c:if>' >微课管理</a></li>
                    <%-- <li><a href="${ctx}/manage/file" class='file <c:if test="${flag == 'file'}">file_hover</c:if>' >课件管理</a></li> --%>
                    <li><a href="${ctx}/manage/question/list" class='question_list <c:if test="${flag == 'question_list'}">question_list_hover</c:if>' >我的题库</a></li>
					</c:if>
					<c:if test="${sessionScope.loginUser.superMan}">
					<li>---------------------------------</li>
					<li><a href="${ctx}/super/question/approve" class='question_approve <c:if test="${flag == 'question_approve'}">question_approve_hover</c:if>' >试题审核</a></li>
					<li><a href="${ctx}/super/knowledge/tree" class='knowledge_tree <c:if test="${flag == 'knowledge_tree'}">knowledge_tree_hover</c:if>' >知识点树</a></li>
					<li><a href="${ctx}/super/story/config" class='story_config <c:if test="${flag == 'story_config'}">story_config_hover</c:if>' >剧本配置</a></li>
					</c:if>					
				</ul>
			</div>
		</div>
