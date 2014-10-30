// Index模块初始化
function IndexClass() {
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
	course:function() {
		$(".course_intro_menu li").toggleShow(".course_intro_detail", {event : "click", hide : false});
	},
	
	// 学校列表页
	school:function() {

	}
};