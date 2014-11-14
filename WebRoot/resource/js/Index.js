// Index模块初始化
function IndexClass() {
	
	// 搜索框的搜索类型切换
	var args = Globals.page.split('_');
	if(args.length == 2) {
		var li = $("#q_course");
		if(args[1] == 'school' || args[1] == 'schoolDetail') {
			var li = $("#q_school");
		}
		if(args[1] == 'teacher' || args[1] == 'teacherDetail') {
			var li = $("#q_teacher");
		}
		li.siblings().addClass("hide");
		li.removeClass("hide");		
		$(".search_category").prepend(li);
		$(".search_form").attr("action", Globals.ctx + "/" + li.attr("id").split("_")[1]);
		if($(".search_text").val() != "请输入你感兴趣的内容") {
			$(".search_text").css("color", "black");
		}
	}	
	
	// 切换搜索类型
	$(".search_category").click(function(event) {
		if($(this).children("li").eq(1).hasClass("hide")) {// 显示
			$(this).children("li").removeClass("hide");
			$(this).css("border-color", "#ccc");
		}else {// 隐藏
			var li = $(event.target);
			if(li.is('li')) {
				$(this).prepend(li);
				li.siblings().addClass("hide");
				$(this).css("border-color", "#fff");
				$(".search_form").attr("action", Globals.ctx + "/" + li.attr("id").split("_")[1]);
			}
		}
	}).mouseleave(function() {
		if(!$(this).children("li").eq(1).hasClass("hide")) {
			$(this).children('li').eq(0).click();
		}
	});
	
	// 搜索框获取焦点清除字符
	$(".search_text").focus(function() {
			if($(this).val() == "请输入你感兴趣的内容") {
				$(this).css("color", "black");
				$(this).val("");
			}
		}).blur(function() {
			if($(this).val() == "") {
				$(this).css("color", "#ccc");
				$(this).val("请输入你感兴趣的内容");
			}
	});
	
	// 搜索按钮
	$(".search_submit").click(function() {
		if($(".search_text").val() == "请输入你感兴趣的内容") {
			$(".search_text").val("");
		}
	});
	
};

//index模块的具体页面初始化
IndexClass.prototype = {
	// 登录页
	login:function() {
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
	school:function() {

	}
};