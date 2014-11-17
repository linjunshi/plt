String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * jquery扩展
	$.fn在jquery对象上扩展
	$在$上扩展
 */
jQuery(function($){
	
	/**
	 * 同步显示和隐藏
	 */
	$.fn.toggleShow = function(el, opt) {
		opt = $.extend({
			event : "mouseover",
			hide : true
		}, opt || {});
		
		var watcher = $(this);
		watcher.bind(opt.event, function() {
			watcher.removeClass("current");
			$(this).addClass("current");
			var index = watcher.index($(this));
			$(el).hide();
			$(el).eq(index).show();
		});
		
		if(opt.hide) {
			watcher.bind("mouseout", function() {
				$(el).hide();
			});
		}
	};
	
	// 绑定关闭弹框
	$.fn.bindFormClose = function(callback) {
	    $(this).unbind("click").click(function(e){
	    	Boxy.get(this).hideAndUnload();
	    	if(callback)callback();
	    });
	};
});

// 所有页面公用的js
function init() {
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
}

// 入口函数
$(function() {
	init();
	if(Globals && Globals.page) {
		var args = Globals.page.split('_');
		var url = Globals.ctx + "/resource/js/" + args[0] + ".js";
		$.getScript(url, function(rep, st){
			try{
			    var command  = 'var obj = new ' + args[0] + 'Class(); ';
			    command += 'obj.' + args[1] + '()';
			    eval(command);
			}catch(e) {
				console.info("module(" + args[0]  + ") or function(" + args[1]  + ") is not found")
			}
		});
	}
});