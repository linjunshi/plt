String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * jquery扩展
	$.fn在jquery对象上扩展
	$在$上扩展
 */
jQuery(function($){
	
	// 重写ajax方法，先判断登录在执行success函数 
    var _ajax=$.ajax;
    $.ajax=function(opt){
    	var _success = opt && opt.success || function(a, b){};
        var _opt = $.extend(opt, {
        	cache : false,// 顺便关闭所有缓存
        	success:function(data, textStatus){
        		// 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要找到data是登录页的证据(标记)
        		if(data && data.indexOf && data.indexOf('loginPage') != -1) {
        			window.location.href= Globals.ctx + "/account/login";
        			return;
        		}
        		_success(data, textStatus);
            }  
        });
        _ajax(_opt);
    };
    
	// 数据校验
	$.fn.validate = function() {
		$(this).find(".text_warn").removeClass("text_warn");
        var isPass = true;
        
        // 必填检测
        $(this).find("[required]").each(function(){
        	if($(this).val().trim() == "") {
        		$(this).addClass("text_warn");
                isPass = false;
        	}
        });
        
        // 相同检测
        $(this).find("[equalTo]").each(function(){
        	var equalTo = $(this).attr("equalTo");
        	if($(this).val().trim() != $("input[name=" + equalTo + "]").val().trim()) {
        		$(this).addClass("text_warn");
                isPass = false;
        	}
        });
        
        // 必填IP
        // 正则表达式别用g属性，否则第二次检测的时候会混合第一次结果导致错误
        var re_ip = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/i
        $(this).find("[required_Ip]").each(function(){
        	var val = $(this).val().trim();
        	if(val == "" || !re_ip.test(val) || RegExp.$1 > 256 || RegExp.$2 > 256 || RegExp.$3 > 256 || RegExp.$4 > 256) {
        		$(this).addClass("text_warn");
                isPass = false;
        	}
        });	
        
        // 必填0或正整数
        var re_number = /^[0-9]+$/i
        $(this).find("[required_Number]").each(function(){
        	var val = $(this).val().trim();
        	if(val == "" || !re_number.test(val)) {
        		$(this).addClass("text_warn");
                isPass = false;
        	}
        });	
        
        // 日期类型检测
        var re_Date = /^[2]\d{3}\-[01]{0,1}\d\-[0123]{0,1}\d$/i;
        $(this).find("[required_Date]").each(function(){
        	var val = $(this).val().trim();
        	if(val == "" || !re_Date.test(val)) {
        		$(this).addClass("text_warn");
                isPass = false;
        	}
        });     
        
        // 时间类型检测
        var re_Time = /[012]{0,1}\d:[0123456]{0,1}\d:[0123456]{0,1}\d$/i;
        $(this).find("[required_Time]").each(function(){
        	var val = $(this).val().trim();
        	if(val == "" || !re_Time.test(val)) {
        		$(this).addClass("text_warn");
                isPass = false;
        	}
        });         
        
        return isPass;
	}
	
	// form提交校验数据
	$(".common_form").submit(function() {
		return $(this).validate();
	});
	
	// 同步显示和隐藏
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
	
//	if( !('placeholder' in document.createElement('input')) ){   
//		  
//	    $('input[placeholder],textarea[placeholder]').each(function(){    
//	      var that = $(this),    
//	      text= that.attr('placeholder');    
//	      if(that.val()===""){    
//	        that.val(text).addClass('placeholder');    
//	      }    
//	      that.focus(function(){    
//	        if(that.val()===text){    
//	          that.val("").removeClass('placeholder');    
//	        }    
//	      })    
//	      .blur(function(){    
//	        if(that.val()===""){    
//	          that.val(text).addClass('placeholder');    
//	        }    
//	      })    
//	      .closest('form').submit(function(){    
//	        if(that.val() === text){    
//	          that.val('');    
//	        }    
//	      });    
//	    });    
//	  }	
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