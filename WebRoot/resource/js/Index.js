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
	},
	
	// 登录页
	login:function() {
		$("input[type=submit]").click(function(){
			
			$("form").ajaxSubmit({
				success : function(result) {
					if(result == "success") {
						window.location.href = Globals.ctx + "/";
					}else{
						alert(result);
					}
				}
			});
			
			return false;
		});
	},
	
	// 课程详细页
	courseDetail:function() {
		$("#coll_course").click(function(){
			var courseId = $(".detai_img input[name=courseId]").val();
			var url = Globals.ctx + "/course/coll_course"
			$.post(url, {courseId : courseId}, function(result) {
				if(result == "success") {
					alert("收藏成功");
				}else {
					alert("收藏失败");
				}
			})
		});
		$("#content_nav li").click(function(){
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
				if(type=='radio') {//单选
					val = $(this).val()
				}else {// 多选
					$(".sh_work_form input[type=checkbox]:checked").each(function() {
						val = val + $(this).val()/1;
					});
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
					$(".preOne, .nextOne").removeClass("sh_form_grey");
					if(index <= 1) {
						$(".preOne").addClass("sh_form_grey");
					}
					if(index >= total) {
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
							alert(result);
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
						alert(result);
					}
				}
			});
		});
	}
};