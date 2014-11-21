// Manage模块初始化
function ManageClass() {
	
};

//Manage模块的具体页面初始化
ManageClass.prototype = {
		
		//新增课程页面
		courseAdd : function(){
			
			// 年级选择
			$("#gradeSelect").change(function() {
				$.get(Globals.ctx + "/data/levelByGrade?gradeEnName=" + $(this).val(), function(data) {
					if(data != "error") {
						var json = eval('(' + data + ')');
						var html = '';
						for(var i=0;i<json.length;i++) {
							html += '<option value="' + json[i].levelId + '">' + json[i].levelName + '</option>';
						}
						$("#levelSelect").html(html);
						$("#levelSelect").change();
					}
				})
			});
			
			// 学科选择
			$("#levelSelect").change(function() {
				$.get(Globals.ctx + "/data/subjectByLevel?gradeId=" + $(this).val(), function(data) {
					if(data != "error") {
						var json = eval('(' + data + ')');
						var html = '';
						for(var i=0;i<json.length;i++) {
							html += '<option value="' + json[i].id + '">' + json[i].subjectName + '</option>';
						}
						$("#subjectSelect").html(html);
					}
				})
			});
			
			// 封面选择
			$("#changeCover").click(function() {
				Boxy.load(Globals.ctx + "/manage/course/changeCover", {title : '-',
					afterShow : function(){
						
						$(".selectCover").change(function() {
							var extName = /\.[^\.]+$/.exec($(this).val());
							if(/jpg|gif|png/i.test(extName)) {
								$(".coverUpload").ajaxSubmit({
									success: function (data){
										var json = eval('(' + data + ')');
										if(json.result == '1') {
											$(".preview").attr("src", json.url);
										}else {
											alert(json.error);
										}
									}
								});
							}else if(extName != null) {
								alert("不支持该文件类型");
							}
						});
						
						$(".sure").click(function() {
							var preview = $(".preview").attr("src");
							if(preview != null && preview != '') {
								$(".small_preview").attr("src", preview)
								$("input[name=url]").val(preview);
								$(".close").click();
							}
						});
						
						$(".close").bindFormClose();
					}
				})
			});
		}
}