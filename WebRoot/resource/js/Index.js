// Index模块初始化
function IndexClass() {

};

//index模块的具体页面初始化
IndexClass.prototype = {
	// 登录页
	login : function() {
	},
	
	// 注册页
	regist : function() {
		var levelId = $("#levelId").val();//年级Id
		
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
				}
			})
		});

		
		if ( levelId != "") {
			gradeSelectFn(levelId);
		} else {
			//新增页面，自动首次触发年级联动控件，默认选择
			$("#gradeSelect").change();
		}
	},
	
	// 首页
	index:function() {
		$(".category_grade li").toggleShow(".category_subject");

		$(".school_nav li").toggleShow(".school_img_item ul", {hide : false});
		
		// 首页课程标签导航栏切换功能
		$("#outer").children("li").each(function(){
			if($(this).attr("class") == "outer_li") {
				var firstClassName = $(this).children("a").attr("class");
				$(this).children("a").attr("class",firstClassName + "_hover");
			}
			$(this).live("mouseover",function(){
				$("#outer").children("li").each(function(){
					$(this).removeAttr("class");
					if($(this).attr("class") != "outer_li") {
						var newClassName = $(this).children("a").attr("class").replace("_hover","");
						$(this).children("a").attr("class", newClassName);
					}
					$(this).children("ul").removeClass("current_ul");
				});
				$(this).toggleClass("outer_li");
				if($(this).attr("class") == "outer_li") {
					var className = $(this).children("a").attr("class");
					$(this).children("a").attr("class" , className + "_hover");
				}
				$(this).children("ul").addClass("current_ul");
				
			});
		});
		
	},
	
	// 登录页
	login:function() {
	},
	
	// 课程详细页
	courseDetail:function() {
		
		//收藏
		$("#coll_course").click(function(){
			var courseId = $(".detai_img input[name=courseId]").val();
			var url = Globals.ctx + "/course/coll_course"
			$.post(url, {courseId : courseId}, function(result) {
				if(result == "success") {
					Boxy.alert("<i class='right'></i><span>收藏成功 !</span>");
				}else {
					Boxy.alert("<i class='error'></i><span>收藏失败 !</span>");
				}
			})
		});
		
		// 购买
		$(".buy").click(function(){
			var courseId = $(".detai_img input[name=courseId]").val();
			var url = Globals.ctx + "/course/buy"
			$.post(url, {courseId : courseId}, function(result) {
				if(result.indexOf('{') != -1) {
					// 新页面打开网银支付
					var json = eval('(' + result + ')');
					$(".buy_form input[name=v_mid]").val(json.v_mid);
					$(".buy_form input[name=v_oid]").val(json.v_oid);
					$(".buy_form input[name=v_amount]").val(json.v_amount);
					$(".buy_form input[name=v_moneytype]").val(json.v_moneytype);
					$(".buy_form input[name=v_url]").val(json.v_url);
					$(".buy_form input[name=v_md5info]").val(json.v_md5info);
					$(".buy_form input[name=v_rcvname]").val(json.v_rcvname);
					$(".buy_form input[name=remark1]").val(json.remark1);
					$(".buy_form").submit();
					
					// 本页面弹框问是否给钱了
	    			Boxy.ask("<i class='ask'></i><span>亲，您已经付款成功了么?</span>", ["是的", "还没呢"], function(response) {
	    	            if (response == "是的") {
	    	            	location.reload();
	    	            }else{
	    	            	Boxy.alert("<i class='info'></i><span>我要重新再买一次 !!!</span>");
	    	            }
	    			});

				}else {
					Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
				}
			});
		});
		
		// 课程描述选项卡切换
		$("#content_nav li").mouseover(function(){
			$(this).parent().children("li").removeClass("current");
			$(this).toggleClass("current");
			if($(this).index() == 0){
				$("#course_summary").show();
				$("#course_struct").show();
				$("#course_comment").show();
				return;
			}
			if($(this).index() == 1){
				$("#course_summary").hide();
				$("#course_struct").show();
				$("#course_comment").hide();
				return;
			}
			if($(this).index() == 2){
				$("#course_summary").hide();
				$("#course_struct").hide();
				$("#course_comment").show();
				return;
			}
		});
		
		// 点击观看直播，判断是否已经购买课程
		$(".liveJump").click(function(){
			var courseId = $("input[name=courseId]").val();
			var liveID = $(this).attr("live_id");
			if (liveID != "" && liveID != null &&  courseId != "" && courseId != null ) {
				$.post(Globals.ctx + "/live/isPay?courseId=" + courseId + "&resId=" + liveID, function(result) {
					if (result == "success") {
							window.open(Globals.ctx + "/live?courseId=" + courseId + "&resId=" + liveID, '_blank');
//							window.open(Globals.ctx + "/live?courseId=" + courseId + "&resId=" + liveID, '_newtab');// Chrome
						} else {
							Boxy.alert("<i class='warn'></i><span>请您先购买该课程 !</span>");
						}
					});
			} else {
				Boxy.alert("<i class='warn'></i><span>请刷新页面后，重新操作 !</span>");
			}
		});
	},
	
	// 挑战页面
	war : function() {
		
		// 选题和取消选题
		$(".egg").click(function() {
			var channel = $(this).attr("rel");
			var title = $(this).html();
			Boxy.load(Globals.ctx + "/war/" + channel, {title : title,
				afterShow : function(){
					
				}
			});
		});
	},
	
	// 做题页面
	questionBegin : function() {alert("做题页面")
		// 做题
		$(".sh_work_form input").change(function() {
			var type = $(this).attr("type");
			
			var val = 0;
			if(type=='radio') {//单选
				val = $(this).val()
			}else {// 多选
				$(".sh_work_form input[type=checkbox]:checked").each(function() {
					val = val + $(this).val()/1;
				});
			}
			
			var index = $(this).parents(".sh_work_rad").find(".current_index").text();
			$(".question_index").eq(index-1).find("input[name=answer]").val(val);
			$(".question_index").eq(index-1).addClass("done");// 标识已做题
		});
		
		// 提交结果
		$(".ajax_submit").click(function() {
	    	var form = $(this).closest("form");
	    	if(form.length > 0){
	    		form.submit();
	    	}
		});		
		
	},
	
	// 个人挑战的测试页面
	personExams : function() {
		
		// 通过改变图片的样式，标识当前已经选择的选项
		$(".selectControl a").click(function(){
			var option = $(this).attr("option");
			var questionType = $("#questionType").val();
			if (questionType/1 != 2) {//不是多选题，就不用清除所有的
				$(".selectControl a.selected").each(function(){
					var _option = $(this).attr("option");
					$(this).children("img").attr("src",Globals.ctx + "/resource/images/" + _option + ".png");
					$(this).css("cursor","normal");
					$(this).removeClass("selected");
				});
			} else {//多选题
				if ($(this).hasClass("selected")) {//如果有selected样式就不切换图片的名称
					$(this).children("img").attr("src",Globals.ctx + "/resource/images/" + option + ".png");
					$(this).css("cursor","normal");
					$(this).removeClass("selected");//移除已经选择的样式标识
					return false;
				}
			}
			$(this).children("img").attr("src",Globals.ctx + "/resource/images/" + option + "_hover_1.png");
			$(this).css("cursor","hand");
			$(this).addClass("selected");
			$("#answer").val($(this).attr("answer").trim());
		});
		
		// 鼠标移过字母时，切换图片的样式
		$(".selectControl a").hover( 
			function () {
				var option = $(this).attr("option");
				$(this).children("img").attr("src",Globals.ctx + "/resource/images/" + option + "_hover_1.png");
				$(this).css("cursor","hand");
			},
			function () {
				if (!$(this).hasClass("selected")) {//如果有selected样式就不切换图片的名称
					var option = $(this).attr("option");
					$(this).children("img").attr("src",Globals.ctx + "/resource/images/" + option + ".png");
					$(this).css("cursor","normal");
				}
			}
		);
		
		// 保存选择题、判断题到页面的隐藏字段里去
		var setAnswersFn = function(questionType) {
			var answers = "";
			$(".selectControl a.selected").each(function(){
				answers += "," + $(this).attr("answer");
			});
			if (answers != "" && answers.length > 0) {
				$("input[name=answer]").val(answers.substr(1));
			}
		} 
		
		// 提交测试题的数据
		$(".submit").click(function(){
			var questionType = $("#questionType").val();
			if (questionType/1 != 4) {//如果不为填空题的话
				setAnswersFn(questionType);
			} else {
				$("#answer").val($("#answer1").val().trim());//填空题
			}
			var answer = $("#answer").val().trim();
			var questionId = $("#questionId").val();
			if (answer != null && answer != "") {
				$.ajax({
					type : 'GET',
					url : Globals.ctx + '/war/addExamToHistory',
					data : {answer : answer , questionId : questionId, questionType : questionType},
					success : function(result){
						if (result == "success") {
							$(".answer").show();
							$(".button_submit").hide();
							$(".button_next").show();
						} else {
							Boxy.alert("<i class='warn'></i><span>"+result+"</span>");
						}
					}
				});
			} else {
				Boxy.alert("<i class='warn'></i><span>嘿嘿，您还没有作答呢 ！</span>");
			}
		});
	},
	
	// 微课详细页面
	weikeDetail : function() {
		
	},
	
	// 学校列表页
	school : function() {

	},
	
	// 做作业页面
	doneTrain : function() {
		
		// 做题事件
		var bindAnswer = function() {
			var index = $("input[name=index]").val();
			var module = $("input[name=module]").val();
			
			// 答题模式，还原选择
			if(module == 'answer') {
				var questionType = $("input[name=questionType]").val()/1;
				var val = $(".question_index").eq(index-1).find("input[name=answer]").val();
				if (questionType == 4) {// 填空题
					$(".sh_work_form textarea[name=answer]").text(val);
				} else {// 单选题、判断题、多选题
					console.info("--" + val);
					var bit = 2;
					var optionCount = 4; //选项的个数ABCD和对错
					if (questionType == 3) {
						var optionCount = 2;//选项只有 对、错
					} else if (questionType == 2){//多选题 原来格式是1,2,4,8的字符串格式，这里要把他取出来再相加求和
						if (val != "" && val.length > 0) {
							var arr = val.split(",");
							val = 0;
							for (var int = 0; int < arr.length; int++) {
								val += eval(arr[int]);
							}
						} else {
							val = 0;
						}
					}
					val = val/1;
					for(var i = 0; i < optionCount; i++) {// ABCD
						var key = Math.pow(bit, i);
						if((val&key) == key) {//通过位运算，默认设置勾选状态
							$(".sh_work_form input[value="+key+"]").attr("checked", "checked");
						}
					}
				}
			}
			
			// 结果模式，禁用控件
			if(module == 'result') {
				$(".sh_work_form input").attr("disabled",true);
				$(".sh_work_form textarea").attr("disabled",true);
			}
			
			// 事件
			$(".sh_work_form input").change(function() {
				var type = $(this).attr("type");
				var val = 0;
				if(type == 'radio') {//单选题
					val = $(this).val();
				} else if(type == 'checkbox'){// 多选题
					val = "";
					$(".sh_work_form input[type=checkbox]:checked").each(function() {
						val = val + "," + $(this).val();
					});
					if (val != "" && val.length > 0) {
						val = val.substr(1);
					}
				} else{// 填空题
					val = $(this).val().trim();
				}
				
				$(".question_index").eq(index-1).find("input[name=answer]").val(val);
				if(module == 'answer') {
					$(".question_index").eq(index-1).addClass("done");// 标识已做题
				}
			});
			
			// 事件 // 填空题
			$(".sh_work_form textarea").change(function() {
				var type = $(this).attr("type");
				var val = 0;
				if(type == 'radio') {//单选题
					val = $(this).val();
				} else if(type == 'checkbox'){// 多选题
					val = "";
					$(".sh_work_form input[type=checkbox]:checked").each(function() {
						val = val + "," + $(this).val();
					});
					if (val != "" && val.length > 0) {
						val = val.substr(1);
					}
				} else{// 填空题
					val = $(this).val().trim();
				}
				
				$(".question_index").eq(index-1).find("input[name=answer]").val(val);
				if(module == 'answer') {
					$(".question_index").eq(index-1).addClass("done");// 标识已做题
				}
			});
		}
		
		// 作业序号事件
		$(".question_index").click(function() {
			var module = $("input[name=module]").val();
			if(module == 'answer') {
				$(".sh_work_right ul li.current").each(function(){
					$(this).toggleClass("current");// 移除之前点击过的序号 样式current
				});
				$(this).addClass("current");// 当前点击的序号，添加样式current
			}
			var total = $("input[name=total]").val();
			var index = $(this).find("span").text();
			$("input[name=index]").val(index);
			var trainId = $("input[name=trainId]").val();
			var chapterId = $("input[name=chapterId]").val();
			$.ajax({
				type : 'GET',
				url : Globals.ctx + '/train/question',
				data : {trainId : trainId , chapterId : chapterId, index : index},
				success : function(result){
					$(".sh_work_form").html(result);
					$(".current_index").html(index);
					$(".preOne, .nextOne").removeClass("sh_form_grey");
					if(eval(index) <= 1) {
						$(".preOne").addClass("sh_form_grey");
					}
					if(eval(index) >= total) {
						$(".nextOne").addClass("sh_form_grey");
					}					
					bindAnswer();
				}
			});
		});
		
		// 上一题
		$(".preOne").click(function() {
			if(!$(this).hasClass("sh_form_grey")) {
				$(".question_index").eq($("input[name=index]").val()-2).click();
			}
		});
		
		// 下一题
		$(".nextOne").click(function() {
			if(!$(this).hasClass("sh_form_grey")) {
				$(".question_index").eq($("input[name=index]").val()).click();
			}
		});
		
		// 默认显示第一题
		if($(".question_index").size()>0) {
			$(".question_index").eq(0).click();
		}

		// 提交结果
		$(".ajax_submit").click(function() {
	    	var form = $(this).closest("form");
	    	if(form.length > 0){
	    		form.ajaxSubmit({
	    			success : function(result) {
						if(result == 'success') {
							location.reload();
						}else {
							Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
						}
	    			}
	    		});
	    	}
		});
		
		// 重新做题
		$(".ajax_reset").click(function() {
			var chapterId = $("input[name=chapterId]").val();
			var trainId = $("input[name=trainId]").val();
			$.ajax({
				type : 'POST',
				url : Globals.ctx + '/train/reset',
				data : {chapterId : chapterId, trainId : trainId},
				success : function(result) {
					if(result == 'success') {
						location.reload();
					}else {
						Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
					}
				}
			});
		});
	}
};
