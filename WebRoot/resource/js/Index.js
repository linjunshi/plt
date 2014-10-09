// Index模块初始化
function IndexClass() {
	$(".category_grade li").mouseover(function(){
		$(".category_subject").hide();
		$(this).find(".category_subject").show();
	});
	
	$(".category_grade li").mouseout(function(){
		$(".category_subject").hide();
	});
};

//index模块的具体页面初始化
IndexClass.prototype = {
	// 登录页
	login:function() {
	},
	
	// 首页
	index:function() {
		$(".logout").click(function(){
			$.post(Globals.ctx + '/logout.action', function(result) {
				if(result == "success"){
					window.location.href="/index.action";
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
						window.location.href="/index.action";
					}else{
						alert(result);
					}
				}
			});
			
			return false;
		});
	}
};