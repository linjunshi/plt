<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="coverUpload" enctype="multipart/form-data" action="${ctx}/image/upload" method="post">
	<img src="" class="preview" style="width:300px; height:200px;" />
	<input type="file" class="selectCover" name="cover"/>
	<p>
	<a href="javascript:void(0);" class="sure">确定</a>
	<a href="javascript:void(0);" class="close">取消</a>
	</p>
</form>