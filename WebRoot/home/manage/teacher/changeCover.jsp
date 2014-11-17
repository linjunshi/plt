<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="coverUpload" enctype="multipart/form-data" action="${ctx}/manage/course/coverUpload" method="post">
	<img src="" id="preview" style="width:300px; height:200px;" />
	<input type="file" id="selectCover" />
	<p>
	<a href="javascript:void(0);" class="sure">确定</a>
	<a href="javascript:void(0);" class="close">取消</a>
	</p>
</form>