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
		
		// 练习模式
		replay : function() {
			
			// 第一个台词处于播放状态
			UpdateIndex(0);
			
			// 切换播放台词
			$(".con_yi li").click(function(){
				$(".con_yi li").removeClass("current");
				$(this).addClass("current");
				
				var startTime = $(this).attr("startTime");
				DramaSeek(startTime);
			});
			
			// 上下移动
			$(".ping_but_a, .ping_but_b").click(function(){
				if($(this).hasClass("ping_but_b")) {
					Globals.pageData.moveNum += 4;
					if(Globals.pageData.moveNum >= $(".con_yi li").size()) {
						Globals.pageData.moveNum = $(".con_yi li").size();
					}		
				}else{
					Globals.pageData.moveNum -= 4;
					if(Globals.pageData.moveNum < 0) {
						Globals.pageData.moveNum = 0;
					}					
				}
				
				var marginTop = 0;
				for(var i=0;i<Globals.pageData.moveNum;i++) {
					marginTop -= $(".con_yi li").eq(i).height();
				}
				$(".con_yi ul").css({"margin-top" : marginTop+"px"});
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
