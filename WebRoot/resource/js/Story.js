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
		}

};
