<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>


<div class="sh_title">
    <h2>新建直播</h2>
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
		<form method="post" action="${ctx}/manage/course/addResourceLive" class="form_info" id="live_form">
			<input type="hidden" id="courseId" name="courseId" value="${courseId}" />
			<input type="hidden" id="chapterId" name="chapterId" value="${chapterId}" />
			<input type="hidden" id="resourceId" name="resourceId" value="${resourceId}" />
		
			<div class="form_item">
				<label for="courseName">直播名称：</label>
				<div class="form_field">
					<input class="form_text" id="title" name="title" type="text" value="${live.title}">
				</div>
			</div>
			<div class="form_item">
				<label for="chapterCount">时长：</label>
				<div class="form_field">
					<input placeholder="如：2" class="form_text" name="duration" id="duration" type="text" value="${live.duration}"> 小时
				</div>
			</div>
			<div class="form_item">
				<label for="beginTime">开始时间：</label>
				<div class="form_field">
					<input placeholder="格式：2014-01-01 59:59:59" class="form_text" name="beginTime" id="beginTime" type="text" value="<fmt:formatDate type="both" value="${live.beginTime}" />">
				</div>
			</div>
			<div class="form_item">
				<label for="endTime">结束时间：</label>
				<div class="form_field">
					<input placeholder="格式：2014-01-01 59:59:59" class="form_text" name="endTime" id="endTime" type="text" value="<fmt:formatDate type="both" value="${live.endTime}" />">
				</div>
			</div>
			<div class="form_item">
				<label for="realname">资源路径：</label>
				<div class="form_field">
					<img src="" style="width:80px; height:60px;" class="small_preview" />
					<a href="javascript:void(0);" id="changeCover">更改路径</a>
					<input type="hidden" name="url" value="" />
				</div>
			</div>
			<div class="form_action">
				<input class="btn_question" type="submit" value="保存" /> 
				<a class="btn_question" href="${ctx}/manage/course/chapterEditor?courseId=${courseId}">取消</a>
			</div>
		</form>
	</div>
</div>
