// Manage模块初始化
function ManageClass() {
	
	var levelId = $("#levelId").val();//年级Id
	var oldSubjectId = $("#oldSubjectId").val();//科目Id
	var oldUnitId = $("#oldUnitId").val();//单元Id
	var changeNum = 0;//用来监控年级联动控件是否人为触发
	
	var gradeSelectFn = function(levelId) {
		$.get(Globals.ctx + "/data/gradeByLevelId?levelId=" + levelId, function(data) {
			if(data != "error") {
				var json = eval('(' + data + ')');
				$("#gradeSelect").val(json.gradeEnName);
				//首次触发年级联动
				$("#gradeSelect").change();
			}
		})
	}
	
	// 年级选择
	$("#gradeSelect").change(function() {
		$.get(Globals.ctx + "/data/levelByGrade?gradeEnName=" + $(this).val(), function(data) {
			if(data != "error") {
				var json = eval('(' + data + ')');
				var html = '';
				for(var i = 0; i < json.length; i++) {
					html += '<option value="' + json[i].levelId + '">' + json[i].levelName + '</option>';
				}
				$("#levelSelect").html(html);
				if ( levelId != "") {//修改页面初始化默认选择年级
					$("#levelSelect").val(levelId);
				}
				$("#levelSelect").change();
			}
		})
	});

	// 学科选择
	$("#levelSelect").change(function() {
		$("#gradeIdSelected").val($(this).val());//保存当前选择的年级id
		$.get(Globals.ctx + "/data/subjectByLevel?gradeId=" + $(this).val(), function(data) {
			if(data != "error") {
				var json = eval('(' + data + ')');
				var html = '';
				for(var i = 0; i < json.length; i++) {
					html += '<option value="' + json[i].id + '">' + json[i].subjectName + '</option>';
				}
				$("#subjectSelect").html(html);
				if ( oldSubjectId != "") {//修改页面初始化默认选择学科
					$("#subjectSelect").val(oldSubjectId);
				}
				$("#subjectSelect").change();
			}
		})
	});
	
	// 学期单元选择控件
	$("#subjectSelect").change(function(){
		var gradeIdSelected = $("#gradeIdSelected").val();//获取当前选择的年级id
		$.get(Globals.ctx + "/data/unitByGradeAndSubject?gradeId=" + gradeIdSelected + "&subjectId=" + $(this).val(), function(data) {
			if(data != "error") {
				var json = eval('(' + data + ')');
				var html = '';
				for(var i = 0; i < json.length; i++) {
					html += '<option value="' + json[i].id + '">' + json[i].termUnitCnName + '</option>';
				}
				$("#unitSelect").html(html);
				if ( oldUnitId != "") {//修改页面初始化默认选择学科
					$("#unitSelect").val(oldUnitId);
				}
				$("#unitSelect").change();
			}
		})
	});
	
	// 学科的change()事件用来清空之前绑定的知识点，让用户重新绑定知识点(myTrainMAdd.jsp)
	$("#unitSelect").change(function(){
		if (changeNum > 0 && $("#knowledgeIds").length > 0) {
			$("input[name=knowledgeIds]").val("");//客户端界面上清空绑定的所有知识点
			$("input[name=knowledgeNames]").val("请点击选择试题所属的知识点");
		}
		++changeNum;//用来监控年级联动控件是否人为触发
	});
	
	if ( levelId != "") {
		gradeSelectFn(levelId);
	} else {
		//新增页面，自动首次触发年级联动控件，默认选择
		$("#gradeSelect").change();
	}
};

//Manage模块的具体页面初始化
ManageClass.prototype = {
		
		//新增课程页面
		courseAdd : function(){
			
			// 封面选择
			$("#changeCover").click(function() {
				Boxy.load(Globals.ctx + "/component/change/cover", {title : '封面选择',
					afterShow : function(){
						$(".selectCover").change(function() {
							var extName = /\.[^\.]+$/.exec($(this).val());
							if(/\.jpg$|\.gif$|\.png$|\.bmp$|\.jpeg$/i.test(extName)) {
								 // 返回 KB，保留小数点后两位
							    var fileSize = (this.files[0].size / 1024).toFixed(2);
								if(fileSize > 0){
									$("#fileSize").html(fileSize + "KB");
									if(fileSize > 300){      
										$(".upload_icon .wrong").html("图片大小不允许超过300KB");
										$(".upload_icon .wrong").show();
										$(".upload_icon .right").hide();
										return false;
									} else {
										$(".upload_icon .right").html("图片可以上传");
										$(".upload_icon .right").show();
										$(".upload_icon .wrong").hide();
									}
								}
								$(".coverUpload").ajaxSubmit({
									success: function (data){
										var json = eval('(' + data + ')');
										if(json.result == '1') {
											$(".preview").attr("src", json.url);
										}else {
											Boxy.alert("<i class='error'></i><span>"+ json.error +"</span>");
										}
									}
								});
							}else if(extName != null) {
								$(".upload_icon .wrong").html("不支持该文件类型，只能上传图片，限于.bmp、.png、.gif、.jpeg、.jpg格式");
								$(".upload_icon .wrong").show();
								$(".upload_icon .right").hide();
							}
						});
						
						$(".sure").click(function() {
							var preview = $(".preview").attr("src");
							if(preview != null && preview != '' && preview.indexOf("img_display.jpg") == -1) {
								$(".small_preview").attr("src", preview)
								$("input[name=url]").val(preview);
								$(".close").click();
							}
						});
						
						$(".close").bindFormClose();
					}
				})
			});
			
			// 日期选择控件
			var DatePickerFn = function(){

				var endTime = $("#endTime").val();
			    
				$('#endTime').datetimepicker({
					dayOfWeekStart : 1,//一周从星期几开始：1为星期一，2为星期二...7为星期日
					lang : 'zh',//语言为简体中文
					timepicker : false,//timepicker 时间控件隐藏
					format : 'Y-m-d',//默认格式显示，format:	'Y/m/d H:i',
//					disabledDates:[year + '/' + month +  '/' + '27'],//不显示指定的日期，不能触发点击事件
//					startDate :	input_value,//从那一天开始，默认选择日期
					value : endTime, //文本框内默认显示的日期
//					step : 30,//timepicker 设置时间的间隔，以分钟为单位
				});
			}
			DatePickerFn();
			
		},
		
		courseList : function() {
			$(".publish").click(function(){
				var courseId = $(this).attr("id").split("_")[1];
				var url = Globals.ctx + "/manage/course/publish"
				$.post(url, {courseId : courseId}, function(result) {
					if(result == "success") {
						Boxy.alert("<i class='right'></i><span>发布成功 !</span>");
						location.reload();
					}else {
						Boxy.alert("<i class='error'></i><span>发布失败 !</span>");
					}
				})
			});
		},
		
		// 申请老师
		applyTeacher : function() {
			//选学校
			$("input[name=schoolName]").click(function() {
				Boxy.load(Globals.ctx + "/component/choice/school", {title : '选择学校',
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
											html += '<a href="javascript:void(0);" id="a_'+ json[i].id + '" title="' + json[i].schoolName + '">' + json[i].schoolName + '</a>';
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
		
		// 老师待上课程
		liveList : function() {
			$(".open_tool").click(function(){
//				if(!/MSIE/.test(navigator.userAgent)) {// IE11不兼容
				if(!$.isIE()) {
					Boxy.alert("<i class='warn'></i><span>仅支持IE !</span>");
					return;
				}
				
				var liveId = $(this).attr("id").split("_")[1];
				if(liveId) {
					$.ajax({url : Globals.ctx + "/manage/live/openTool", data : {liveId : liveId}, success : function(result) {
						if(result.indexOf('{') != -1) {
							var json = eval('(' + result + ')');
//							if(SantrongPlayer && SantrongPlayer.StartPlayEX) {
							if(SantrongPlayer) {
//								SantrongPlayer.StartLiveManager(json.rtmpUrl, json.webUrl, json.pltHost, json.teacherId, json.teacherName, json.sourceId, json.sourceTitle, json.beginTimeString, json.endTimeString);
								SantrongPlayer.StartLiveManager("192.168.10.163", json.webUrl, "192.168.10.163", json.teacherId, json.teacherName, json.sourceId, json.sourceTitle, json.beginTimeString, json.endTimeString);
							}else{
								Boxy.alert("<i class='info'></i><span>请下载老师客户端 !</span>");
							}
						}else {
							Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
						}
					}});
				}
				
			});
		},
		
		// TODO 章节维护页面
		chapterList : function(){

			//时间控件
			var DateTimePickerFn = function(){
				var now   = new Date();
				var year  = now.getFullYear();
				var month = now.getMonth() + 1 < 10 ? ('0' + (now.getMonth() + 1)) : now.getMonth() + 1;
				var date  = now.getDate() < 10 ? ('0' + now.getDate()) : now.getDate();
				var hours = now.getHours() < 10 ? ('0' + now.getHours()) : now.getHours();
				var minutes = now.getMinutes() < 10 ? ('0' + now.getMinutes()) : now.getMinutes();
				
				var input_value = year + '-' + month + '-' + date + ' ' + hours + ':' + minutes;
				
				var resourceId = $("#resourceId").val();
				if (resourceId != null && resourceId != "") {
					input_value = $("#beginTime").val();
				}
			    
				$('#beginTime').datetimepicker({
					dayOfWeekStart : 1,//一周从星期几开始：1为星期一，2为星期二...7为星期日
					lang : 'zh',//语言为简体中文
					format:	'Y-m-d H:i',//默认格式显示，format:	'Y/m/d H:i',
//					disabledDates:[year + '/' + month +  '/' + '27'],//不显示指定的日期，不能触发点击事件
//					startDate :	input_value,//从那一天开始，默认选择日期
					onGenerate : function( ct ){
						$(this).find('.xdsoft_date').toggleClass('xdsoft_disabled');
						//解决当前日期能选择的功能
						var data_year = $(this).find('td.xdsoft_current').attr("data-year");
						var data_month = $(this).find('td.xdsoft_current').attr("data-month");
						var data_date = $(this).find('td.xdsoft_current').attr("data-date");
						if (data_year == now.getFullYear() && data_month == now.getMonth() && data_date == now.getDate()) {
							$(this).find('td.xdsoft_current').toggleClass('xdsoft_disabled');
						}
					},//小于当前日期的都不能触发点击事件，默认为灰色
					minDate:'-' + year + '/02/2',//yesterday is minimum date(for today use 0 or -1970/01/01)
					maxDate:'+1970/01/1',//tommorow is maximum date calendar
					value : input_value, //文本框内默认显示的日期
					step : 30,//timepicker 设置时间的间隔，以分钟为单位
				});
			}
			
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
											Boxy.alert("<i class='error'></i><span>"+ json.error +"</span>");
										}
									}
								});
							}else if(extName != null) {
								Boxy.alert("<i class='warn'></i><span>不支持该文件类型 !</span>");
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
				var courseId = $("#courseId").val();
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				
				// 判断章节内是否有内容，有内容不给删除
				var dl_dd = _this.parents("dl").find("dd");
				if (dl_dd.size() > 0) {
					if (dl_dd.hasClass("pt10")) {
						Boxy.alert("<i class='warn'></i><span>该大章节中有小章节，不能删除 !</span>");
					}else{
						if (courseId != "" && courseId != null && chapterId != "" && chapterId != null) {
							// 通过POST方式，提交数据到后台处理业务逻辑
							$.post(Globals.ctx + "/manage/course/removeChapter", {courseId : courseId, chapterId : chapterId}, function(result){
								if (result == "success") {
									location.reload();
								}else {
									Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
								}
							});
						} else {
							Boxy.alert("<i class='error'></i><span>删除失败，请刷新页面后重新操作 !</span>");
						}
					}
				}
			});
			
			/********************修改大章节的标题 START********************************************************/				
			// TODO 点击章节 修改
			$(".editMaxChapter").live("click", function() {
				var parent_obj = $(this).parents(".sh_add_opera");
				$(parent_obj).children(".show_remark").hide();
				$(parent_obj).children(".hide_remark").show();
				$(parent_obj).children(".sh_operation").hide();
			});
			
			// TODO 点击章节 取消
			$(".chapter_cancel").live("click", function(){
				var chapterId = $(this).parents(".sh_add_chapter").children("input[name=chapterId]").val();
				var parent_obj = $(this).parents(".sh_add_opera");
				// chapterId 为空，执行新增操作；否则执行修改操作
				if (chapterId == "") {
					$(".sh_addop_a").show();
					$(this).parents("dl").remove();
				} else {
					$(parent_obj).children(".show_remark").show();
					$(parent_obj).children(".hide_remark").hide();
					$(parent_obj).children(".sh_operation").removeAttr("style");
				}
			});
			
			// TODO 点击章节 保存
			$(".chapter_submit").live("click", function(data){
				var opera_obj = $(this).parents(".sh_add_opera");
				var dls_obj = $(this).parents(".sh_collection").find("dl.sh_add_chapter");
				var chapter_obj = $(this).parents(".sh_add_chapter").children("input[name=chapterId]");
				var chapterId = $(chapter_obj).val();
				var courseId = $("#courseId").val().trim();
				var remark = $(this).parent().children("input[name=remark]").val().trim();
				if (remark == "") {
					Boxy.alert("<i class='warn'></i><span>章节标题不能为空 !</span>");
				} else {
					// chapterId 为空，执行新增操作；否则执行修改操作
					if (chapterId == "") {
						//新增操作
						if (courseId != "") {
							var priority = $("input[name=priority]").val();
							if (priority == "" || priority == 0) {
								priority = $(dls).length;
							}
							// 通过POST方式，递交给后台处理与数据库交互的逻辑
							$.post(Globals.ctx + "/manage/course/addChapter", {courseId : courseId, remark : remark, priority : priority}, function(result){
								if (result == "fail") {
									Boxy.alert("<i class='error'></i><span>添加章节失败，请刷新页面后重新操作 !</span>");
								} else {
									$("input[name=priority]").val(parseInt(priority) + 1);
									var json = $.parseJSON(result);
									$(opera_obj).children(".show_remark").html(json.remark);
									$(chapter_obj).val(json.id);
									$(opera_obj).children(".show_remark").show();
									$(opera_obj).children(".hide_remark").hide();
									$(opera_obj).children(".sh_operation").removeAttr("style");
									$(".sh_addop_a").show();
									$(dls_obj).children("dd.pt11").show();
								}
							});
						} else {
							Boxy.alert("<i class='error'></i><span>添加章节失败，请刷新页面后重新操作 !</span>");
						}
						
					} else {
						//修改操作
						// 通过POST方式，递交给后台处理与数据库交互的逻辑
						$.post(Globals.ctx + "/manage/course/modifyChapter", {courseId : courseId, id : chapterId, remark : remark}, function(result){
							if (result == "success") {
								$(opera_obj).children(".show_remark").html(remark);
								$(opera_obj).children(".show_remark").show();
								$(opera_obj).children(".hide_remark").hide();
								$(opera_obj).children(".sh_operation").removeAttr("style");
							} else {
								Boxy.alert("<i class='warn'></i><span>"+ result +"</span>");
							}
						});
					}
				}
			});
			
			//TODO 回车提交事件
			$(".chapter_remark").live("keydown", function() {
				if (window.event.keyCode == "13") {//keyCode=13是回车键
					var opera_obj = $(this).parents(".sh_add_opera");
					var dls_obj = $(this).parents(".sh_collection").find("dl.sh_add_chapter");
					var chapter_obj = $(this).parents(".sh_add_chapter").children("input[name=chapterId]");
					var chapterId = $(chapter_obj).val();
					var courseId = $("#courseId").val().trim();
					var remark = $(this).val().trim();
					if (remark == "") {
						Boxy.alert("<i class='error'></i><span>章节标题不能为空 !</span>");
					} else {
						
						// chapterId 为空，执行新增操作；否则执行修改操作
						if (chapterId == "") {
							//新增操作
							if (courseId != "") {
								var priority = $("input[name=priority]").val();
								if (priority == "" || priority == 0) {
									priority = $(dls_obj).length;
								}
								// 通过POST方式，递交给后台处理与数据库交互的逻辑
								$.post(Globals.ctx + "/manage/course/addChapter", {courseId : courseId, remark : remark, priority : priority}, function(result){
									if (result == "fail") {
										Boxy.alert("<i class='error'></i><span>添加章节失败，请刷新页面后重新操作 !</span>");
									} else {
										$("input[name=priority]").val(parseInt(priority) + 1);
										var json = $.parseJSON(result);
										$(opera_obj).children(".show_remark").html(json.remark);
										$(chapter_obj).val(json.id);
										$(opera_obj).children(".show_remark").show();
										$(opera_obj).children(".hide_remark").hide();
										$(opera_obj).children(".sh_operation").removeAttr("style");
										$(".sh_addop_a").show();
										$(dls_obj).children("dd.pt11").show();
									}
								});
							} else {
								Boxy.alert("<i class='error'></i><span>添加章节失败，请刷新页面后重新操作 !</span>");
							}
							
						} else {
							// 通过POST方式，递交给后台处理与数据库交互的逻辑
							$.post(Globals.ctx + "/manage/course/modifyChapter", {courseId : courseId, id : chapterId, remark : remark}, function(result){
								if (result == "success") {
									$(opera_obj).children(".show_remark").html(remark);
									$(opera_obj).children(".show_remark").show();
									$(opera_obj).children(".hide_remark").hide();
									$(opera_obj).children(".sh_operation").removeAttr("style");
								} else {
									Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
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
						var opera_obj = $dl_new.children("dt").children(".sh_add_opera");
						$(opera_obj).children(".show_remark").hide();
						$(opera_obj).children(".hide_remark").show();
						$(opera_obj).children(".hide_remark").children("input[name='remark']").focus();
						$(opera_obj).children(".sh_operation").hide();
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
					Boxy.alert("<i class='error'></i><span>添加课件失败，请刷新页面后重新操作 !</span>");
				}
			});
			
			// 翻页、跳转到指定页面
			var PageTurningFn = function(page){
				var courseId = $("#courseId").val();
				var chapterId = $("#chapterId").val();
				var resourceType = $("#resourceType").val();
				if (resourceType == 1) {
					var oldResourceId = $("#oldResourceId").val();
					$.ajax({
						url: Globals.ctx + "/manage/course/addResourceFile",
	                    data: { courseId:courseId, chapterId:chapterId, oldResourceId:oldResourceId, page:page},
	                    type: "GET",
	                    success: function (data) {
	                    	$(".sh_info_r").html(data);
	                    }
					});//ajax
				} else if (resourceType == 4) {
					var title = $("#title").val().trim();
					var resourceId = $("#resourceId").val();
					$.ajax({
	                    url: Globals.ctx + "/manage/course/addResourceTrain",
	                    data: { courseId:courseId, chapterId:chapterId, resourceId:resourceId, page:page},
	                    type: "GET",
//	                    dataType : "html",//返回纯文本 HTML 信息；包含的 script 标签会在插入dom时执行。
	                    success: function (data) {
	                    	//var json = eval("(" + data + ")");
	    					$(".sh_info_r").html(data);
	    					CheckBox_Checked();//翻页是默认勾选
	    					$("#title").val(title);
	                    }
					});//ajax
				}
			}
			
			// TODO 点击分页按钮时
			$(".pagination a").live("click", function() {
				//动态获取点击元素<a>上的属性href的跳转地址值
				var url = $(this).attr("page_url");
				if (url == undefined) { return false; }//点击当前页的时候do nothing
				var page = url.split("page")[1].split("=")[1];
				PageTurningFn(page);
			});
			
			// TODO 填写页码，点击“GO”按钮时，跳转到指定页面(注意：章节维护页面中嵌进去的页面addResourceFile.jsp、addResourceLive.jsp)
			var GoToPageFn = function(){
				var page = eval($("#page").val().trim());
				var pageCount = eval($("#pageCount").val().trim());
				var re_number = /^[0-9]{1,}$/i;
				if (page == "" || page == null || !re_number.test(page) || pageCount < page || page == 0) {
					Boxy.alert("<i class='warn'></i><span>请填写的页码数值范围为：1 - " + pageCount +" !</span>");
					$("#page").val("");
					$("#page").addClass("text_warn");
					return false;
				} else{
					PageTurningFn(page);
				}
			}
			$(".page_submit_input2").live("click", function() {
				GoToPageFn();
			});
			$(".page_go_input2").live("keydown", function() {
				if (window.event.keyCode == "13") {//keyCode=13是回车键
					GoToPageFn();
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
							Boxy.alert("<i class='warn'></i><span>你已经把该课件添加到当前章节了 !</span>");
						} else if (result == "fail") {
							Boxy.alert("<i class='error'></i><span>课件选择失败，刷新页面后，请重新操作 !</span>");
							location.reload();
						} else {
							location.href = Globals.ctx + result;
						}
					});
				} else {
					Boxy.alert("<i class='error'></i><span>添加课件失败，请刷新页面后重新操作 !</span>");
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
						DateTimePickerFn();
					});
				} else {
					Boxy.alert("<i class='error'></i><span>添加直播失败，请刷新页面后重新操作 !</span>");
				}
			});
			
			//addResourceLive.jsp 提交直播表单校验
			$("#liveSubmit").live("click", function (){
				var title = $("#title").val().trim();
				if (title == "" || title == null) {
					Boxy.alert("<i class='warn'></i><span>直播名称不可以为空，请您务必填写 !</span>");
					$("#title").focus();
					return false;
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
					Boxy.alert("<i class='error'></i><span>添加试题失败，请刷新页面后重新操作 !</span>");
				}
			});
			
			// TODO 点击 试题列表的复选框时，保存勾选的id值或移除取消选的值
			var checkArr = new Array();//创建一个数组对象，代码移到addResourceTrain.jsp最底部 
			$("input[name='question_checkbox']").live("change",function(){
				var currentVal = $(this).val();
				if ($(this).attr("checked") != undefined) {
					if ($(this).attr("checked") == "checked") {
						if (checkArr.length == 0) {
							checkArr.push(currentVal);
						} else {
							// 不管数组checkArr里有没有currentVal，先过滤掉currentVal，然后再加currentVal，避免重复值
							checkArr = $.grep(checkArr,function(val,key){
								//过滤函数有两个参数,第一个为当前元素,第二个为元素索引
								return val != currentVal;
							});
							checkArr.push(currentVal);
						}
					} 
				} else {
					// 从数组checkArr中，移除之前勾选过的值
					if (checkArr.length > 0) {
						checkArr = $.map( checkArr, function(n){
							  return n != currentVal ? n : null;
							});
					}
				}
			});
			
			// 翻页之后，遍历数组checkArr，有的就默认勾选
			var CheckBox_Checked = function() {
				$("input[name='question_checkbox']").each(function(i) {
					var _this = $(this);
					$.each( checkArr , function(key, val){
						  if (val == _this.val()) {
							  _this.attr('checked','checked')
						  }
					});
				});
			}
			
			// TODO 点击确认选择，添加试题到题库里去（addResourceTrain.jsp） 
			/************************添加课程章节关联资源 END******************************************/
			$("#selectTrainQuestion").live("click",function(){
				var ids = "";
				var title = $("#title").val().trim();
				var courseId = $("#courseId").val();
				var chapterId = $("#chapterId").val();
				var resourceId = $("#resourceId").val();
				
				if (title == "" || title == null) {
					Boxy.alert("<i class='warn'></i><span>测验题目不可以为空，请您务必填写 !</span>");
					$("#title").focus();
					return false;
				}
				
				$.each( checkArr , function(key, val){
					ids = ids + "," + val;
				});

				if (ids.length > 0) {
					ids = ids.replace(',','');
				} else {
					Boxy.alert("<i class='warn'></i><span>请您勾选试题 !</span>");
					return false;
				}
				
				if (courseId != "" && chapterId != "" && courseId != null && chapterId != null) {
					// 通过post方式，提交数据到后台处理业务逻辑
					$.post(Globals.ctx + "/manage/course/selectTrainList",{ids:ids, title:title,courseId:courseId,chapterId:chapterId,resourceId:resourceId},function(result){
						if (result == "success") {
							Boxy.alert("<i class='right'></i><span>成功添加试题 !</span>");
							location.reload();
						} else {
							Boxy.alert("<i class='error'></i><span>添加试题失败 !</span>");
						}
					});
				} else {
					Boxy.alert("<i class='error'></i><span>添加试题失败，请刷新页面后重新操作 !</span>");
				}
				
			});
			
			
			/************************删除一资源 START******************************************/
			// TODO 删除一资源
			$(".removeResource").live("click", function() {
				var _this = $(this);
				var courseId = $("#courseId").val();
				var chapterId = _this.parents(".sh_add_chapter").children("input[name=chapterId]").val();
				var resourceId = _this.parent().children("input[name=resourceId]").val();
				var resourceType = _this.parent().children("input[name=resourceType]").val();
				if (courseId != "" && chapterId != "" && resourceId != "" && resourceType != "" && 
					courseId != null && chapterId != null && resourceId != null && resourceType != null) {
					// 通过get方式，提交数据到后台处理业务逻辑
					$.get(Globals.ctx + "/manage/course/removeResource?courseId=" + courseId + "&chapterId=" + chapterId 
							+ "&resourceId=" + resourceId + "&resourceType=" + resourceType, function(result){
						if (result == "success") {
							location.reload();
							$("html,body").animate({scrollTop:$("#" + chapterId).offset().top},1000);
						}else {
							Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
						}
					});
				} else {
					Boxy.alert("<i class='error'></i><span>删除失败 !</span>");
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
					// 通过get方式，提交数据到后台处理业务逻辑
					$.get(Globals.ctx + url +"?courseId="+ courseId +"&chapterId=" + chapterId + "&resourceId=" + resourceId 
							+ "&resourceType=" + resourceType + "&oldResourceId=" + resourceId , function(result){
						$(".sh_info_r").html(result);
						if (resourceType == 2) {
							DateTimePickerFn();
						} else if (resourceType == 4) {
							checkArr.length = 0;  // 输出 []，空数组，即被清空了
//							console.log(checkArr); // 输出 []，空数组，即被清空了 
							var questionIds = $("#questionIds").val();
							if (questionIds != "" && questionIds != null) {
								checkArr = questionIds.split(",");
								CheckBox_Checked();//设置默认勾选
							}
						}
					});
					
				} else {
					Boxy.alert("<i class='error'></i><span>修改失败 !</span>");
				}
			});
			/************************修改一资源 END******************************************/
			
		},
		
		//试题新增 修改页面
		myTrainMAdd : function() {
			
			var answer_radio_show = function(){//只显示单选题
				$("#answer_radio").show();
				$("#answer_checkbox").hide();
				$("#answer2_radio").hide();
				$("#answer_input").hide();
				$("#answer3").removeAttr("required");//移除填空题答案输入框的必填属性
			}
			var answer_checkbox_show = function(){//只显示多选题
				$("#answer_radio").hide();
				$("#answer_checkbox").show();
				$("#answer2_radio").hide();
				$("#answer_input").hide();
				$("#answer3").removeAttr("required");//移除填空题答案输入框的必填属性
			}
			var answer2_radio_show = function(){//只显示判断题
				$("#answer_radio").hide();
				$("#answer_checkbox").hide();
				$("#answer2_radio").show();
				$("#answer_input").hide();
				$("#answer3").removeAttr("required");//移除填空题答案输入框的必填属性
			}
			var answer_input_show = function(){//只显示填空题
				$("#answer_radio").hide();
				$("#answer_checkbox").hide();
				$("#answer2_radio").hide();
				$("#answer_input").show();
				$("#answer3").attr("required","required");//添加填空题答案输入框的必填属性
			}
			
			var id = $("#id").val();
			if (id == "") {
				//新增页面默认选项
				$("input[name='questionType']").eq(0).attr("checked","checked");
				$("input[name='level']").eq(0).attr("checked","checked");
				answer_radio_show();
			} else {
				//修改页面默认选项
				var questionType = "1";
				$("input[name='questionType']").each(function(i,data){
					questionType = $(this).val();
					if (questionType == "1" && $(this).attr("checked") == "checked") {
						answer_radio_show();
					} else if (questionType == "2" && $(this).attr("checked") == "checked") {
						answer_checkbox_show();
					} else if (questionType == "3" && $(this).attr("checked") == "checked") {
						answer2_radio_show();
					} else if (questionType == "4" && $(this).attr("checked") == "checked") {
						answer_input_show();
					}
				});
			}
			
			//改变选择类型，显示相应类型的控件
			$("input[name='questionType']").change(function(){
				if (eval($(this).val()) == 1) {
					answer_radio_show();
				} else if (eval($(this).val()) == 2) {
					answer_checkbox_show();
				} else if (eval($(this).val()) == 3) {
					answer2_radio_show();
				} else if (eval($(this).val()) == 4){
					answer_input_show();
				}
			});
			
			var kIds = $("input[name=knowledgeIds]").val();
			if (kIds != "" && kIds != null) {
				$("input[name=knowledgeNames]").val("已绑定知识点");
			}
			// TODO 打开知识点绑定列表弹窗
			$("input[name=knowledgeNames]").click(function(){
				var questionId = $("input[name=id]").val();
				var knowledgeIds = $("input[name=knowledgeIds]").val();
				var gradeId = $("#levelSelect").val();
				var subjectId = $("#subjectSelect").val();
				var unitId = $("#unitSelect").val();
				if (gradeId != "" && gradeId != null && subjectId != "" && subjectId != null && unitId != "" && unitId != null) {
					Boxy.load(Globals.ctx + "/component/bind/bingKnowledge?questionId=" + questionId + "&knowledgeIds=" + knowledgeIds 
							+ "&gradeId=" + gradeId + "&subjectId=" + subjectId + "&unitId=" + unitId, {title : '知识点绑定列表',
						afterShow : function(){
							if(oListboxTo.length == 0 && knowledgeIds != "" && knowledgeIds != null){
								var str = knowledgeIds.split(",");
								for(var i = 0; i < str.length; i++){
									oListboxTo.appendChild(str[i]); //
									//i -= 1;  //每删除一个选项后，每个选项的index会被置
								}
							}
							// 全部向右移动
							$(".join_right_all").click(function(){
								var options = oListboxFrom.options;
								for(var i = 0; i < options.length; i++){
									oListboxTo.appendChild(options[i]); //
									i -= 1;  //每删除一个选项后，每个选项的index会被置
								}
							});
							// 部分向右移动
							$(".join_right_but").click(function(){
								var options = oListboxFrom.options;
								for(var i = 0; i < options.length; i++){
									if(options[i].selected){
										oListboxTo.appendChild(options[i]);
										i -= 1;
									}
								}
							});
							// 部分向左移动
							$(".join_left_but").click(function(){
								var options = oListboxTo.options;
								for(var i = 0; i < options.length; i++){
									if(options[i].selected){
										oListboxFrom.appendChild(options[i]);
										i -= 1;
									}
								}
							});
							// 全部向左移动
							$(".join_left_all").click(function(){
								var options = oListboxTo.options;
								for(var i = 0; i < options.length; i++){
									oListboxForm.appendChild(options[i]); //
									i -= 1;  //每删除一个选项后，每个选项的index会被置
								}
							});
							// 向上移动
							$(".join_up_but").click(function(){
								if(oListbox.selectedIndex > 0){
									var oOption = oListbox.options[oListbox.selectedIndex];
									var oPrevOption = oListbox.options[oListbox.selectedIndex-1];
									oListbox.insertBefore(oOption,oPrevOption);
								}
							});
							// 向下移动
							$(".join_down_but").click(function(){
								if(oListbox.selectedIndex < oListbox.options.length-1){
									var oOption = oListbox.options[oListbox.selectedIndex];
									var oNextOption = oListbox.options[oListbox.selectedIndex+1];
									oListbox.insertBefore(oNextOption,oOption);
								}
							});
							$(".sure").click(function(){
								var options = oListboxTo.options;
								var ids = "";
								for(var i = 0; i < options.length; i++){
									ids += "," + oListboxTo.options[i].value;
								}
								if (ids != "" && ids.length > 0) {
									$("input[name=knowledgeIds]").val(ids.substr(1));
									$("input[name=knowledgeNames]").val("已绑定知识点");
									$(".close").click();
								}
							});
							$(".close").bindFormClose();
						}
					});
					
				} else {
					Boxy.alert("<i class='error'></i><span>亲，你还有选择知识点分类（所属的年级和学科）哦 !</span>");
				}
			});
			
			// 审核通过
			$(".btn_approve").click(function(){
				$("#status").val(1);
			});
			// 审核不通过
			$(".btn_disapprove").click(function(){
				$("#status").val(2);
			});
		},
		
		//TODO 个人信息-基本页面
		personalInfo : function() {
			
			// 上传头像控件
			$("#changeCover").click(function() {
				Boxy.load(Globals.ctx + "/component/change/cover", {title : '上传头像',
					afterShow : function(){
						$(".selectCover").change(function() {
							var extName = /\.[^\.]+$/.exec($(this).val());
							if(/\.jpg$|\.gif$|\.png$|\.bmp$|\.jpeg$/i.test(extName)) {
								 // 返回 KB，保留小数点后两位
							    var fileSize = (this.files[0].size / 1024).toFixed(2);
								if(fileSize > 0){
									$("#fileSize").html(fileSize + "KB");
									if(fileSize > 300){      
										$(".upload_icon .wrong").html("图片大小不允许超过300KB");
										$(".upload_icon .wrong").show();
										$(".upload_icon .right").hide();
										return false;
									} else {
										$(".upload_icon .right").html("图片可以上传");
										$(".upload_icon .right").show();
										$(".upload_icon .wrong").hide();
									}
								}
								$(".coverUpload").ajaxSubmit({
									success: function (data){
										var json = eval('(' + data + ')');
										if(json.result == '1') {
											$(".preview").attr("src", json.url);
										}else {
											Boxy.alert("<i class='error'></i><span>"+ json.error +"</span>");
										}
									}
								});
							}else if(extName != null) {
								$(".upload_icon .wrong").html("不支持该文件类型，只能上传图片，限于.bmp、.png、.gif、.jpeg、.jpg格式");
								$(".upload_icon .wrong").show();
								$(".upload_icon .right").hide();
							}
						});
						
						$(".sure").click(function() {
							var preview = $(".preview").attr("src");
							if(preview != null && preview != '' && preview.indexOf("img_display.jpg") == -1 ) {
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
		
		//TODO 个人信息-扩展信息页面
		personalInfoExtend : function() {
			
			// 生日时间选择控件
			var DatePickerFn = function(){

				var birthday = $("#birthday").val();
			    
				$('#birthday').datetimepicker({
					dayOfWeekStart : 1,//一周从星期几开始：1为星期一，2为星期二...7为星期日
					lang : 'zh',//语言为简体中文
					timepicker : false,//timepicker 时间控件隐藏
					format : 'Y-m-d',//默认格式显示，format:	'Y/m/d H:i',
//					disabledDates:[year + '/' + month +  '/' + '27'],//不显示指定的日期，不能触发点击事件
//					startDate :	input_value,//从那一天开始，默认选择日期
					value : birthday, //文本框内默认显示的日期
//					step : 30,//timepicker 设置时间的间隔，以分钟为单位
				});
			}
			
			// 当DOM载入完成时绑定一个要执行的DatePickerFn()函数。
			$(this).ready(function(){
				DatePickerFn();
			});
			
			//点击生日文本框时，触发点击事件
			$("#birthday").click(function(){
				DatePickerFn();
			});
		},
		
		// TODO 知识点结构树
		knowledgeMTree : function() {
		
			var setting = {
					view: {
						addHoverDom: addHoverDom,
						removeHoverDom: removeHoverDom,
						selectedMulti: false
					},
					edit: {
						enable: true,
						editNameSelectAll: true,//打开编辑的时候，选中被编辑文本
						showRemoveBtn: showRemoveBtn,
						showRenameBtn: showRenameBtn,
						removeTitle: "删除",
						renameTitle: "编辑",
					},
					data: {
						simpleData: {
							enable: true
						}//设置使用标准还是简单的JSON格式数据结构，这里使用数组类型的简单JSON格式数据结构
					},
					callback: {
						beforeDrag: beforeDrag,
						beforeEditName: beforeEditName,
						beforeRemove: beforeRemove,
						beforeRename: beforeRename,
						onRemove: onRemove,
						onRename: onRename
					}
				};
			
				var zNodes ;
				function ajaxSyncGetNodes() {
					$.ajax({
						url : Globals.ctx + "/manage/knowledge/getTreeNodes", 
//						data : {liveId : liveId}, 
						type: "GET",
//						dataType: 'text', 
						async : false,//设置为同步操作就可以给全局变量赋值成功 
						success : function(result) {
						if(result.indexOf('{') != -1) {
							var json = eval('(' + result + ')');
							zNodes = json;
						} else {
							Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
						}
					}});
				}
				
				// TODO 打开新增修改知识点归类
				var boxySubmit = function(addOrEdit, treeNode) {
					var gradeId = treeNode.gradeId;//年级
					var subjectId = treeNode.subjectId;//科目
					var code = treeNode.code;//学期单元的编码
					var unitId = treeNode.unitId;//单元id
					var type = treeNode.type;//标识树每个层级的类型
					var level = treeNode.level;//从0 开始，zTree控件自带的
					var dataId = treeNode.dataId;
					var parentId = treeNode.dataId;//新增的时候默认当前节点为新增子节点的父节点
					
					if (addOrEdit == "edit") {//修改的时候获取父节点的JSON对象
						var zTree = $.fn.zTree.getZTreeObj("zTree");
						var sNodes = zTree.getSelectedNodes();
						if (sNodes.length > 0) {
							var node = sNodes[0].getParentNode();
							parentId = node.dataId;
						}
					}
					// 弹出知识点归类的页面
					Boxy.load(Globals.ctx + "/manage/knowledge/addKnowledgeTree?gradeId="+ gradeId + "&subjectId=" 
							+ subjectId + "&type="+ type + "&unitId="+ unitId + "&code=" + code + "&level=" + level + "&dataId=" + dataId
							+ "&addOrEdit=" + addOrEdit ,{title : '知识点归类',
						afterShow : function(){

							$(".sure").click(function() {
								var dataId = $("#dataId").val().trim();
								var unitId = $("#unitId").val();
								var type = $("#type").val();
								var code = $("#code").val();
								var level = $("#level").val();
								var gradeId = $("#gradeId").val();
								var subjectId = $("#subjectId").val();
								var knowledgeName = $("#knowledgeName").val().trim();
								var addOrEdit = $("#addOrEdit").val().trim();
								if (knowledgeName == null || knowledgeName == "") {
									Boxy.alert("<i class='warn'></i><span>亲，请填写知识点名称 !</span>");
								}
								if (gradeId != null && gradeId != "" && subjectId != null && subjectId != "" ) {
									$.ajax({
										url: Globals.ctx + "/manage/knowledge/submitKnowledgeBySync",
										data: { dataId:dataId, unitId:unitId, type:type, code:code, level:level, 
											gradeId:gradeId, subjectId:subjectId, knowledgeName:knowledgeName, addOrEdit:addOrEdit},
										type: "post",
//				                    async : false,//设置为同步操作就可以给全局变量赋值成功 
										success: function (result) {
											if (addOrEdit == "add") {
												if (result.indexOf('{') != -1) {
													var json = eval('(' + result + ')');
													var zTree = $.fn.zTree.getZTreeObj("zTree");
													zTree.addNodes(treeNode, json);// 新增子节点
													$(".close").click();
												} else if(result == "fail") {
													Boxy.alert("<i class='error'></i><span>亲，对不起，操作失败了，请您刷新页面后重新操作 !</span>");
												} else {
													Boxy.alert("<i class='warn'></i><span>"+ result +"</span>");
												}
											} else {
												if (result.indexOf('{') != -1) {
													var json = eval('(' + result + ')');
													$("#" + treeNode.tId + "_span").html(json.name);// 如：<span id='zTree_21_span'>json.name</span>
													$("#" + treeNode.tId + "_a").attr("title",json.name);// 如：<a id='zTree_21_a' title="json.name"></a>
													$(".close").click();
												} else if(result == "fail") {
													Boxy.alert("<i class='error'></i><span>亲，对不起，操作失败了，请您刷新页面后重新操作 !</span>");
												} else {
													Boxy.alert("<i class='warn'></i><span>"+ result +"</span>");
												}
											}
										}
									});//ajax
								} else {
									Boxy.alert("<i class='warn'></i><span>亲，请您选择年级和科目 !</span>");
								}
							});
							
							$(".close").bindFormClose();
						}
					});
				}
				
				var log, className = "dark";
				function beforeDrag (treeId, treeNodes) {
					return false;
				}
				function beforeEditName (treeId, treeNode) {
					if (treeNode.type == 6) {//只有知识点的类型才提供修改的操作
						className = (className === "dark" ? "":"dark");
						var zTree = $.fn.zTree.getZTreeObj("zTree");
						zTree.selectNode(treeNode);
						boxySubmit("edit", treeNode);//弹窗修改当前节点
						return true;
					}
				}
				
				//用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作,默认值：null
				function beforeRemove (treeId, treeNode) {
					var isRemove = false;
					className = (className === "dark" ? "":"dark");
					$.fn.zTree.getZTreeObj("zTree").selectNode(treeNode);
					if(confirm("确认要删除节点【" + treeNode.name + "】吗？")){
						$.ajax({
							url : Globals.ctx + "/manage/knowledge/remove", 
							data : {knowledgeId : treeNode.dataId}, 
							type: "post",
							dataType: 'text', 
							async : false,//设置为同步操作就可以给全局变量赋值成功 
							success : function(result) {
								if (result == "success") {
									isRemove = true;
								}
							}});
					}
					return isRemove;
				}
				function onRemove (e, treeId, treeNode) {
					//用于捕获删除节点之后的事件回调函数 null
				}
				function beforeRename (treeId, treeNode, newName, isCancel) {
					className = (className === "dark" ? "":"dark");
					if (newName.length == 0) {
						Boxy.alert("<i class='warn'></i><span>节点名称不能为空 !</span>");
						var zTree = $.fn.zTree.getZTreeObj("zTree");
						setTimeout(function(){zTree.editName(treeNode)}, 10);
						return false;
					}
					return true;
				}
				function onRename (e, treeId, treeNode, isCancel) {
				}
				function showRemoveBtn (treeId, treeNode) {
					/* return !treeNode.isFirstNode; */
					return !treeNode.isParent;//如果设置了isParent=true,那么都不显示删除按钮
				}
				function showRenameBtn(treeId, treeNode) {
//					return !treeNode.isLastNode;//最后节点不能显示修改按钮
					//只有单元和知识点的类型才提供新增的操作
					return (eval(treeNode.type) == 6);
				}

				function addHoverDom(treeId, treeNode) {
					if (treeNode.type == 5 || treeNode.type == 6) {//只有单元和知识点的类型才提供新增的操作
						var sObj = $("#" + treeNode.tId + "_span");
						if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
						var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='新增' onfocus='this.blur();'></span>";
						sObj.after(addStr);
						var btn = $("#addBtn_"+treeNode.tId);
						if (btn) btn.bind("click", function(){
							if (eval(treeNode.level + 1) < 8) {//知识点限制只能新增三级。  注意：treeNode.level 从0开始的
								boxySubmit("add", treeNode);//弹窗新增节点
							} else {
								Boxy.alert("<i class='warn'></i><span>亲，很抱歉，知识点级别最多为三级，只能允许添加到这个级别了!</span>");
							}
							return false;
						});
					}
				};
				function removeHoverDom(treeId, treeNode) {
					$("#addBtn_"+treeNode.tId).unbind().remove();
				};
				$(document).ready(function(){
					ajaxSyncGetNodes();
					$.fn.zTree.init($("#zTree"), setting, zNodes);
				});
				
		}
}
