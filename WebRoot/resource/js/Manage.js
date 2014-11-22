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
				Boxy.load(Globals.ctx + "/component/change/cover", {title : '-',
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
		
		// 申请老师
		applyTeacher : function() {
			//选学校
			$("input[name=schoolName]").click(function() {
				Boxy.load(Globals.ctx + "/component/choice/school", {title : '-',
					afterShow : function(){
						$(".province, .city, .county").click(function(e) {
							var target = $(e.target);
							if(target.is("a")) {
								
								var group = target.parent();
								group.find("a").removeClass("current");
								target.addClass("current");
								
								var areaCode = target.attr("id").split("_")[1];
								$.get(Globals.ctx + "/component/choice/school/data?areaCode=" + areaCode, function(result) {
									var html = '';
									var json = eval('{' + result + '}');
									for(var i=0;i<json.length;i++) {
										if(group.hasClass("county")) {
											html += '<a href="javascript:void(0);" id="a_'+ json[i].id + '">' + json[i].schoolName + '</a>';
										}else{
											html += '<a href="javascript:void(0);" id="a_'+ json[i].areaCode + '">' + json[i].areaName + '</a>';
										}
									}
									group.nextAll().hide();
									group.next().html(html);
									group.next().show();
								});
							}
						});
						
						$(".school").click(function(e) {
							var target = $(e.target);
							if(target.is("a")) {
								var schoolId = target.attr("id").split("_")[1];
								$("input[name=schoolName]").val(target.text());
								$("input[name=schoolId]").val(schoolId);
								Boxy.get(this).hideAndUnload();
							}
						});
					}
				});
			});
		},
		
		//章节维护页面
		chapterList : function(){
			
			// 删除一大章节
			$(".removeMaxChapter").live("click", function() {
				var _this = $(this);
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				
				// TODO 判断章节内是否有内容，有内容不给删除
				var dl_dd = _this.parents("dl").find("dd");
				if (dl_dd.size() > 0) {
					if (dl_dd.hasClass("pt10")) {
						alert("该大章节中有小章节，不能删除!");
					}else{
						
						// TODO 通过POST方式，后台处理与数据库的交互逻辑
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
			// 点击修改
			$(".editMaxChapter").live("click", function() {
				$(this).parents(".sh_add_opera").children(".show_remark").hide();
				$(this).parents(".sh_add_opera").children(".hide_remark").show();
				$(this).parents(".sh_add_opera").children(".sh_operation").hide();
			});
			
			// 点击取消
			$(".chapter_cancel").live("click", function(){
				var chapterId = $(this).parents(".sh_add_chapter").children("input[name=chapterId]").val();
				// TODO chapterId 为空，执行新增操作；否则执行修改操作
				if (chapterId == "") {
					$(".sh_addop_a").show();
					$(this).parents("dl").remove();
				} else {
					$(this).parents(".sh_add_opera").children(".show_remark").show();
					$(this).parents(".sh_add_opera").children(".hide_remark").hide();
					$(this).parents(".sh_add_opera").children(".sh_operation").show();
				}
			});
			
			// 点击保存
			$(".chapter_submit").live("click", function(data){
				var _submit = $(this);
				var chapterId = _submit.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				var remark = _submit.parent().children("input[name=remark]").val();
				if (remark == "") {
					alert("章节标题不能为空！");
				} else {
					
					// TODO chapterId 为空，执行新增操作；否则执行修改操作
					if (chapterId == "") {
						var courseId = $("input[name=courseId]").val();
						if (courseId != "") {
							var priority = $("input[name=priority]").val();
							if (priority == "" || priority == 0) {
								priority = _submit.parents(".sh_collection").find("dl.sh_add_chapter").length;
							}
							// TODO 新增操作：通过POST方式，后台处理与数据库的交互逻辑
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
						// TODO 修改操作：通过POST方式，后台处理与数据库的交互逻辑
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
					var remark_this = $(this);
					var chapterId = remark_this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
					var remark = $(this).val();
					if (remark == "") {
						alert("章节标题不能为空！");
					} else {

						// TODO 通过POST方式，后台处理与数据库的交互逻辑
						$.post(Globals.ctx + "/manage/course/modifyChapter", {id : chapterId, remark : remark}, function(result){
							if (result == "success") {
								remark_this.parents(".sh_add_opera").children(".show_remark").show();
								remark_this.parents(".sh_add_opera").children(".hide_remark").hide();
								remark_this.parents(".sh_add_opera").children(".sh_operation").show();
							} else {
								alert(result);
							}
						});
					}
				}
			});
			
			/********************修改大章节的标题 END********************************************************/			
			
			// 添加一个大章节输入框
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
					+ "<a href='javascript:void(0);' class='addMinChapter' >添加一小节</a>"
					+ "</dd>"
					+ "</dl>";
				
				$(this).parent(".sh_collection").find("dl.sh_add_chapter").each(function(index, domEle){
					if (index + 1 == dl_length) {
						var $dl_new = $(dl_html).insertAfter(domEle);
						$dl_new.children("dt").children(".sh_add_opera").children(".show_remark").hide();
						$dl_new.children("dt").children(".sh_add_opera").children(".hide_remark").show();
						$dl_new.children("dt").children(".sh_add_opera").children(".sh_operation").hide();
						$(".sh_addop_a").hide();
						$dl_new.children("dd.pt11").hide();
					}
				});
				
			});
			
			// 添加一个小章节（资源）
			$(".addMinChapter").live("click", function() {
				alert("添加一个小章节");
			});
		}
		
}
