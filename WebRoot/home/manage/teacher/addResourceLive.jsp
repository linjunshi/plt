<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>


<div class="sh_title">
    <h2>
    	<c:if test="${fn=='add'}">新建直播</c:if>
    	<c:if test="${fn=='modify'}">修改直播</c:if>
    </h2>
    <a href="${ctx}/manage/course/chapterEditor?courseId=${courseId}" class="sh_add">&#60;&#60;返回上级</a>
</div>
<div class="sh_form_con">
	<div id="demo_zone">
		<c:if test="${tipError != null && fn:length(tipError)  > 0}">
			<div class="system_tip">
				<c:forEach items="${tipError}" var="tip">
				<p>${tip.msg}</p>
				</c:forEach>
			</div>
		</c:if>
		<form method="post" action="${ctx}/manage/course/addResourceLive" class="form_info common_form" id="live_form">
			<input type="hidden" id="courseId" name="courseId" value="${courseId}" />
			<input type="hidden" id="chapterId" name="chapterId" value="${chapterId}" />
			<input type="hidden" id="resourceId" name="resourceId" value="${resourceId}" />
		
			<div class="form_item">
				<label for="courseName">直播名称：</label>
				<div class="form_field">
					<input class="form_text" id="title" name="title" type="text" value="${live.title}" required>
				</div>
			</div>
			<div class="form_item">
				<label for="beginTime">直播开始时间：</label>
				<div class="form_field">
					<input placeholder="格式：2014-01-01 59:59" class="form_text" name="beginTime" id="beginTime" type="text" value="<fmt:formatDate type="date" pattern="yyyy-MM-dd HH:mm" value="${live.beginTime}" />" required_Date>
				</div>
			</div>
			<div class="form_item">
				<label for="chapterCount">课程时长：</label>
				<div class="form_field">
					<select name="duration" size="1" id="duration" class="inline_ele">
						<option <c:if test="${live.duration==5}">selected="selected"</c:if> value="5">5分钟</option>
						<option <c:if test="${live.duration==10}">selected="selected"</c:if> value="10">10分钟</option>
						<option <c:if test="${live.duration==15}">selected="selected"</c:if> value="15">15分钟</option>
						<option <c:if test="${live.duration==20}">selected="selected"</c:if> value="20">20分钟</option>
						<option <c:if test="${live.duration==30}">selected="selected"</c:if> value="30">30分钟</option>
						<option <c:if test="${live.duration==45}">selected="selected"</c:if> value="45">45分钟</option>
						<option <c:if test="${live.duration==60}">selected="selected"</c:if> value="60">60分钟</option>
						<option <c:if test="${live.duration==90}">selected="selected"</c:if> value="90">90分钟</option>
					</select>
				</div>
			</div>
			<div class="form_action">
				<input class="btn_question" type="submit" id="liveSubmit" name="liveSubmit" value="保存" /> 
				<a class="btn_question" href="${ctx}/manage/course/chapterEditor?courseId=${courseId}">取消</a>
			</div>
		</form>
	</div>
</div>
