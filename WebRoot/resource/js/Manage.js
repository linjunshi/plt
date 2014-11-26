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
				Boxy.load(Globals.ctx + "/manage/course/changeCover", {
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
		},
		
		// TODO 章节维护页面
		chapterList : function(){
			
			// 封面选择
			$("#changeCover").live("click", function() {
				Boxy.load(Globals.ctx + "/manage/course/changeCover", {
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
			
			// TODO 删除一大章节
			$(".removeMaxChapter").live("click", function() {
				var _this = $(this);
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				
				// 判断章节内是否有内容，有内容不给删除
				var dl_dd = _this.parents("dl").find("dd");
				if (dl_dd.size() > 0) {
					if (dl_dd.hasClass("pt10")) {
						alert("该大章节中有小章节，不能删除!");
					}else{
						
						// 通过POST方式，后台处理与数据库的交互逻辑
						$.post(Globals.ctx + "/manage/course/removeChapter", {chapterId : chapterId}, function(result){
							if (result == "success") {
//								_this.parents("dl").remove();
								location.reload();
							}else {
								alert(result);
							}
						});
					}
				}
			});
			
			/********************修改大章节的标题 START********************************************************/				
			// TODO 点击章节 修改
			$(".editMaxChapter").live("click", function() {
				$(this).parents(".sh_add_opera").children(".show_remark").hide();
				$(this).parents(".sh_add_opera").children(".hide_remark").show();
				$(this).parents(".sh_add_opera").children(".sh_operation").hide();
			});
			
			// TODO 点击章节 取消
			$(".chapter_cancel").live("click", function(){
				var chapterId = $(this).parents(".sh_add_chapter").children("input[name=chapterId]").val();
				// chapterId 为空，执行新增操作；否则执行修改操作
				if (chapterId == "") {
					$(".sh_addop_a").show();
					$(this).parents("dl").remove();
				} else {
					$(this).parents(".sh_add_opera").children(".show_remark").show();
					$(this).parents(".sh_add_opera").children(".hide_remark").hide();
					$(this).parents(".sh_add_opera").children(".sh_operation").show();
				}
			});
			
			// TODO 点击章节 保存
			$(".chapter_submit").live("click", function(data){
				var _submit = $(this);
				var chapterId = _submit.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				var remark = _submit.parent().children("input[name=remark]").val();
				if (remark == "") {
					alert("章节标题不能为空！");
				} else {
					
					// chapterId 为空，执行新增操作；否则执行修改操作
					if (chapterId == "") {
						var courseId = $("input[name=courseId]").val();
						if (courseId != "") {
							var priority = $("input[name=priority]").val();
							if (priority == "" || priority == 0) {
								priority = _submit.parents(".sh_collection").find("dl.sh_add_chapter").length;
							}
							// 通过POST方式，递交给后台处理与数据库交互的逻辑
							$.post(Globals.ctx + "/manage/course/addChapter", {courseId : courseId, remark : remark, priority : priority}, function(result){
								if (result == "fail") {
									alert("添加章节失败，请刷新页面后重新操作！");
								} else {
									$("input[name=priority]").val(parseInt(priority) + 1);
									var json = $.parseJSON(result);
									_submit.parents(".sh_add_opera").children(".show_remark").html(json.remark);
									_submit.parents(".sh_add_chapter").children("input[name=chapterId]").val(json.id);
									_submit.parents(".sh_add_opera").children(".show_remark").show();
									_submit.parents(".sh_add_opera").children(".hide_remark").hide();
									_submit.parents(".sh_add_opera").children(".sh_operation").show();
									$(".sh_addop_a").show();
									_submit.parents(".sh_collection").find("dl.sh_add_chapter").children("dd.pt11").show();
								}
							});
						} else {
							alert("添加章节失败，请刷新页面后重新操作！");
						}
						
					} else {
						// 通过POST方式，递交给后台处理与数据库交互的逻辑
						$.post(Globals.ctx + "/manage/course/modifyChapter", {id : chapterId, remark : remark}, function(result){
							if (result == "success") {
								_submit.parents(".sh_add_opera").children(".show_remark").html(remark);
								_submit.parents(".sh_add_opera").children(".show_remark").show();
								_submit.parents(".sh_add_opera").children(".hide_remark").hide();
								_submit.parents(".sh_add_opera").children(".sh_operation").show();
							} else {
								alert(result);
							}
						});
					}
				}
			});
			
			//TODO 回车提交事件
			$(".chapter_remark").live("keydown", function() {
				if (event.keyCode == "13") {//keyCode=13是回车键
					var _submit = $(this);
					var chapterId = _submit.parents(".sh_add_chapter").children("input[name=chapterId]").val();
					var remark = $(this).val();
					if (remark == "") {
						alert("章节标题不能为空！");
					} else {
						
						// chapterId 为空，执行新增操作；否则执行修改操作
						if (chapterId == "") {
							var courseId = $("input[name=courseId]").val();
							if (courseId != "") {
								var priority = $("input[name=priority]").val();
								if (priority == "" || priority == 0) {
									priority = _submit.parents(".sh_collection").find("dl.sh_add_chapter").length;
								}
								// 通过POST方式，递交给后台处理与数据库交互的逻辑
								$.post(Globals.ctx + "/manage/course/addChapter", {courseId : courseId, remark : remark, priority : priority}, function(result){
									if (result == "fail") {
										alert("添加章节失败，请刷新页面后重新操作！");
									} else {
										$("input[name=priority]").val(parseInt(priority) + 1);
										var json = $.parseJSON(result);
										_submit.parents(".sh_add_opera").children(".show_remark").html(json.remark);
										_submit.parents(".sh_add_chapter").children("input[name=chapterId]").val(json.id);
										_submit.parents(".sh_add_opera").children(".show_remark").show();
										_submit.parents(".sh_add_opera").children(".hide_remark").hide();
										_submit.parents(".sh_add_opera").children(".sh_operation").show();
										$(".sh_addop_a").show();
										_submit.parents(".sh_collection").find("dl.sh_add_chapter").children("dd.pt11").show();
									}
								});
							} else {
								alert("添加章节失败，请刷新页面后重新操作！");
							}
							
						} else {
							// 通过POST方式，递交给后台处理与数据库交互的逻辑
							$.post(Globals.ctx + "/manage/course/modifyChapter", {id : chapterId, remark : remark}, function(result){
								if (result == "success") {
									_submit.parents(".sh_add_opera").children(".show_remark").html(remark);
									_submit.parents(".sh_add_opera").children(".show_remark").show();
									_submit.parents(".sh_add_opera").children(".hide_remark").hide();
									_submit.parents(".sh_add_opera").children(".sh_operation").show();
								} else {
									alert(result);
								}
							});
						}
					}
				}
			});
			
			// TODO 添加一个大章节输入框
			$(".sh_addop_a").live("click", function() {
				var dl_length = $(this).parent(".sh_collection").find("dl.sh_add_chapter").length;
				
				var dl_html = "<dl class='sh_add_chapter'>"
					+ "<input type='hidden' id='chapterId' name='chapterId' value=''/>"
					+ "<dt>"
					+ "<div class='sh_add_opera'>"
					+ "<h2>第"+ (dl_length + 1) +"课:</h2>"
					+ "<p class='show_remark'>章节名称</p>"
					+ "<p class='hide_remark'>"
					+ "<input type='text' value='' name='remark' id='remark' class='chapter_remark'> "
					+ "<a href='javascript:void(0);' class='chapter_submit'>保存</a>"
					+ "<a href='javascript:void(0);' class='chapter_cancel'>取消</a>"
					+ "<span>5-30个字符支持汉字、数字、“_”</span>"
					+ "</p>"
					+ "<span class='sh_operation'>"
					+ "<a href='javascript:void(0);' class='removeMaxChapter'>删除</a>"
					+ "<a href='javascript:void(0);' class='editMaxChapter'>修改</a>"
					+ "</span>"
					+ "</div>"
					+ "</dt>"
					+ "<dd class='pt11'>"
					+ "<a href='javascript:void(0);' class='add_resource_file' ><b>+</b>课件</a>"
					+ "<a href='javascript:void(0);' class='add_resource_live' ><b>+</b>直播</a>"
					+ "<a href='javascript:void(0);' class='add_resource_train' ><b>+</b>试题</a>"
					+ "</dd>"
					+ "</dl>";

				//该课程还没有绑定章节的时候，插入一个初始的样式
				var flag = 0;
				if (dl_length == 0) {
					$(dl_html).insertBefore(this);
					flag = 1;
				}
				$(this).parent(".sh_collection").find("dl.sh_add_chapter").each(function(index, domEle){
					var $dl_new = $(domEle);
					if (index + 1 == dl_length) {
						$dl_new = $(dl_html).insertAfter(domEle);
					}
					if (index + 1 == dl_length || flag == 1) {
						$dl_new.children("dt").children(".sh_add_opera").children(".show_remark").hide();
						$dl_new.children("dt").children(".sh_add_opera").children(".hide_remark").show();
						$dl_new.children("dt").children(".sh_add_opera").children(".sh_operation").hide();
						$(".sh_addop_a").hide();
						$dl_new.children("dd.pt11").hide();
					}
				});
			});
			/********************修改大章节的标题 END********************************************************/	
			
			/************************添加课程章节关联资源 START******************************************/
			// TODO 添加一个课件（资源）
			$(".add_resource_file").live("click", function() {
				var _this = $(this);
				var courseId = $("#courseId").val();
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				
				if (courseId != "" && chapterId != "" && courseId != null && chapterId != null) {
					$.get(Globals.ctx + "/manage/course/addResourceFile?courseId="+ courseId +"&chapterId=" + chapterId, function(result){
						$(".sh_info_r").html(result);
					});
				} else {
					alert("添加课件失败，请刷新页面后重新操作！");
				}
			});
			
			// TODO addResourceFile.jsp 点击“选择”按钮的时候触发的事件
			$(".selectResourceFile").live("click", function() {
				var _this = $(this);
				var courseId = $("#courseId").val();
				var chapterId = $("#chapterId").val();
				var oldResourceId = $("#oldResourceId").val();
				var resourceId = _this.parent().children("input[name=resourceId]").val();
				if (courseId != "" && chapterId != "" && resourceId != "" && courseId != null && chapterId != null && resourceId != null) {
					// 异步请求后台服务
					$.get(Globals.ctx + "/manage/course/selectFile?courseId=" + courseId +"&chapterId=" + chapterId + "&resourceId=" + resourceId 
							+ "&oldResourceId=" + oldResourceId, function(result){
						if (result == "error_param") {
							alert("你已经把该课件添加到当前章节了！");
						} else if (result == "fail") {
							alert("课件选择失败，刷新页面后，请重新操作！");
							location.reload();
						} else {
							location.href = Globals.ctx + result;
						}
					});
				} else {
					alert("添加课件失败，请刷新页面后重新操作！");
				}
			});
			
			
			// TODO 添加一个直播（资源）
			$(".add_resource_live").live("click", function() {
				var _this = $(this);
				var courseId = $("#courseId").val();
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				
				if (courseId != "" && chapterId != "" && courseId != null && chapterId != null) {
					$.get(Globals.ctx + "/manage/course/addResourceLive?courseId="+ courseId +"&chapterId=" + chapterId, function(result){
						$(".sh_info_r").html(result);
					});
				} else {
					alert("添加直播失败，请刷新页面后重新操作！");
				}
			});
			
			// TODO 添加一个试题（资源）
			$(".add_resource_train").live("click", function() {
				var _this = $(this);
				var courseId = $("#courseId").val();
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				
				if (courseId != "" && chapterId != "" && courseId != null && chapterId != null) {
					$.get(Globals.ctx + "/manage/course/addResourceTrain?courseId="+ courseId +"&chapterId=" + chapterId, function(result){
						$(".sh_info_r").html(result);
					});
				} else {
					alert("添加试题失败，请刷新页面后重新操作！");
				}
			});
			
			// TODO 点击确认选择，添加试题到题库里去（addResourceTrain.jsp） 
			/************************添加课程章节关联资源 END******************************************/
			$("#selectTrainQuestion").live("click",function(){
				debugger;
				var ids = "";
				var title = $("#title").val();
				var courseId = $("#courseId").val();
				var chapterId = $("#chapterId").val();
				var resourceId = $("#resourceId").val();
				
				if (title == "") {
					alert("测验题目不可以为空，请您务必填写！");
					$("#title").focus();
				}
				
				$("input[name='question_checkbox']").each(function(i) {
				        if ($(this).attr("checked") == "checked") {
				                ids = ids + "," + $(this).val();
				        }
				});
				if (ids.length > 0) {
					ids = ids.replace(',','');
				}
				
				if (courseId != "" && chapterId != "" && courseId != null && chapterId != null) {
					// 通过post方式，后台处理与数据库的交互逻辑
					$.post(Globals.ctx + "/manage/course/selectTrainList",{ids:ids, title:title,courseId:courseId,chapterId:chapterId,resourceId:resourceId},function(result){
						if (result == "success") {
							alert("成功添加试题！");
							location.reload();
						} else {
							alert("添加试题失败！");
						}
					});
				} else {
					alert("添加试题失败，请刷新页面后重新操作！");
				}
				
			});
			
			
			/************************删除一资源 START******************************************/
			// TODO 删除一资源
			$(".removeResource").live("click", function() {
				var _this = $(this);
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				var resourceId = _this.parent().children("input[name=resourceId]").val();
				var resourceType = _this.parent().children("input[name=resourceType]").val();
				if (chapterId != "" && resourceId != "" && resourceType != "" && chapterId != null && resourceId != null && resourceType != null) {
					// 通过get方式，后台处理与数据库的交互逻辑
					$.get(Globals.ctx + "/manage/course/removeResource?chapterId=" + chapterId + "&resourceId=" + resourceId + "&resourceType=" + resourceType, function(result){
						if (result == "success") {
							location.reload();
							$("html,body").animate({scrollTop:$("#" + chapterId).offset().top},1000);
						}else {
							alert(result);
						}
					});
				} else {
					alert("删除失败！");
				}
			});
			/************************删除一资源 END******************************************/
			/************************修改一资源 START******************************************/
			// TODO 修改一资源
			$(".editResource").live("click", function() {
				var _this = $(this);
				var courseId = $("#courseId").val();
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				var resourceId = _this.parent().children("input[name=resourceId]").val();
				var resourceType = _this.parent().children("input[name=resourceType]").val();
				var url = "/manage/course";
				if (courseId != "" && chapterId != "" && resourceId != "" && resourceType != "" && 
					courseId != null && chapterId != null && resourceId != null && resourceType != null) {
					
					if (resourceType == 1) {
						url = "/manage/course/addResourceFile";
					} else if (resourceType == 2) {
						url = "/manage/course/addResourceLive";
					} else if (resourceType == 4) {
						url = "/manage/course/addResourceTrain";
					}
					// 通过get方式，后台处理与数据库的交互逻辑
					$.get(Globals.ctx + url +"?courseId="+ courseId +"&chapterId=" + chapterId + "&resourceId=" + resourceId 
							+ "&resourceType=" + resourceType + "&oldResourceId=" + resourceId , function(result){
						$(".sh_info_r").html(result);
					});
					
				} else {
					alert("修改失败！");
				}
			});
			/************************修改一资源 END******************************************/
			
		}
		
}