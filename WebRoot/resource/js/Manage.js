// Manage模块初始化
function ManageClass() {
	
};

//Manage模块的具体页面初始化
ManageClass.prototype = {
		
		//新增课程页面
		courseAdd : function(){
			$("#changeCover").click(function() {
				Boxy.load(Globals.ctx + "/manage/course/changeCover", {
					afterShow : function(){
						$("#coverUpload").change(function() {alert($(this).val());
							var extName = /\.[^\.]+$/.exec($(this).val());
							if(/jpg|gif|png/i.test(extName)) {
								$.ajaxFileUpload({
									url : Globals.ctx + '/manage/course/coverUpload', 
									secureuri : false,
									fileElementId : 'coverUpload',
									dataType : 'json',
									success: function (data){
										if(data.result == '1') {
											$(".preview").attr("src", data.url);
										}else {
											alert(data.error);
										}
									}
								});
							}else if(extName != null) {
								alert("不支持该文件类型");
							}
						});
//						$("sure").
						$("close").bindFormClose();
					}
				})
			});
		}
}