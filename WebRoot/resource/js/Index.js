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
	
	// 题库首页
	question : function() {
		
		// 选题和取消选题
		$(".question_list .select").click(function() {
			var _this = $(this);
			var qid = $(this).attr("qid");
			$.post(Globals.ctx + "/question/assembleQuestion", {qid : qid}, function(result) {
				if(result == 'add') {
					_this.text('取消选择');
				}else if(result == 'remove'){
					_this.text('选择');
				}else {
					Boxy.alert("<i class='error'></i><span>"+ result +"</span>");
				}
			});
		});
	},
	
	// 做题页面
	questionBegin : function() {
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
				var val = $(".question_index").eq(index-1).find("input[name=answer]").val()/1;
				console.info("--"+val);
				var bit = 2;
				for(var i=0;i<4;i++) {// ABCD
					var key = Math.pow(bit, i);
					if((val&key)==key) {
						$(".sh_work_form input[value="+key+"]").attr("checked", "checked");
					}
				}
			}
			
			// 结果模式，禁用控件
			if(module == 'result') {
				$(".sh_work_form input").attr("disabled",true);
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
//						val = val + $(this).val()/1;
						val = val + "," + $(this).val();
					});
					if (val != "") {
						
						alert(val);
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
