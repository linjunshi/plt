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
		
		$(".logout").click(function(){
			$.post(Globals.ctx + '/logout.action', function(result) {
				if(result == "success"){
					window.location.href = Globals.ctx + "/";
				}else{
					alert(result);
				}
			});
		});
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
	}
};