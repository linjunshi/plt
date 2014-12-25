<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="coverUpload" enctype="multipart/form-data" action="${ctx}/image/upload" method="post">
	
	<div id="portrait_up">
		<div class="portrait_con">
			<div class="show_img">
				<p><img src="${ctx}/resource/images/img_display.jpg" class="preview"/></p>
			</div>
			<div class="file_info">
				<input type="file" class="selectCover" name="cover" />
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
	</div>
</form>

