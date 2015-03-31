// Story模块初始化
function StoryClass() {

};

//Story模块的具体页面初始化
StoryClass.prototype = {
		detail : function() {
			// 选项卡切换
			$(".ping_game a").mouseover(function(){
				$(".ping_game a").removeClass("current");
				$(this).toggleClass("current");
				if($(this).index() == 0){
					$(".comment_tab").show();
					$(".remark_tab").hide();
					return;
				}
				if($(this).index() == 1){
					$(".comment_tab").hide();
					$(".remark_tab").show();
					return;
				}
			});
		},
		
		index : function(){
			
			// 判断是否已经登陆
			$(".isLogin").click(function(){
				var _this = $(this);
				var url = _this.attr("url_attr");
				$.ajax({
					url : Globals.ctx + "/account/isLogin", 
//					data : {}, 
					type : "GET",
					success : function(result) {
						if(result == "fail"){
							$.loginWicket(url);
						}else{
							window.location.href = url;
						}
					}
				});
				
				
			});
		}

};
