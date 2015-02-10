<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="coverUpload" enctype="multipart/form-data" action="${ctx}/image/upload" method="post">
	<div class="portrait_con">
		<div class="show_img">
			<p><img src="${ctx}/resource/images/img_display.jpg" class="preview"/></p>
		</div>
		<div class="file_info">
			<div class="file_box"> 
				<input type='text' name='textfield' id='textfield' class='file_txt'/> 
				<input type='button' class='file_btn' value='浏览...'/> 
				<input type="file" name="cover" class="file_file selectCover" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" /> 
			</div>
			<p class="fileSize">文件大小为：<strong id="fileSize">？</strong></p>
			<p class="file_tips">文件尺寸为：如180 x 180，280 x 280， 文件大小不允许超过300KB</p>
			<p class="upload_icon">
				<span class="right"></span>
				<span class="wrong"></span>
			</p>
		</div>
		<div class="soll_footer">
			<a href="javascript:void(0);" class="close">取消</a>
			<a href="javascript:void(0);" class="sure">确定</a>
		</div>
	</div> 
</form>