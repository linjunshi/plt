String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * jquery扩展 $.fn在jquery对象上扩展 $在$上扩展
 */
jQuery(function($) {

	// 重写ajax方法，先判断登录在执行success函数
	var _ajax = $.ajax;
	$.ajax = function(opt) {
		var _success = opt && opt.success || function(a, b) {
		};
		var _opt = $.extend(opt, {
			cache : false,// 顺便关闭所有缓存
			success : function(data, textStatus) {
				// 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要找到data是登录页的证据(标记)
				if (data && data.indexOf && data.indexOf('loginPage') != -1) {
					window.location.href = Globals.ctx + "/account/login";
					return;
				}
				_success(data, textStatus);
			}
		});
		_ajax(_opt);
	};
	
    // 弹出进行中
    $.showFloatExcuting = function() {
		var w_width = $(window).width();
		var w_height = $(window).height();
		var html = '<img id="floatExcuting" src="/resource/photo/excuting.gif" class="boxy-content" style="position:fixed; left:' + w_width/2 + 'px; top:' + w_height/2 + 'px;">';
		html += '<div id="floatExcuting_layer" class="boxy-modal-blackout" style="z-index: 1996; opacity: 0.2; width: ' + w_width + 'px; height: ' + w_height + 'px;"></div>';
		if($("#floatExcuting").size() < 1) {
			$('body').append(html);
		}
    };
	
	// 取消进行中
	$.hideFloatExcuting = function() {
		$("#floatExcuting").remove();
		$("#floatExcuting_layer").remove();
	};

	// 数据校验
	$.fn.validate = function() {
		$(this).find(".text_warn").removeClass("text_warn");
		var isPass = true;

		// 必填检测
		$(this).find("[required]").each(function() {
			if ($(this).val().trim() == "") {
				$(this).addClass("text_warn");
				isPass = false;
			}
		});

		// 相同检测
		$(this).find("[equalTo]").each(function() {
			var equalTo = $(this).attr("equalTo");
			if ($(this).val().trim() != $(
					"input[name=" + equalTo + "]").val().trim()) {
				$(this).addClass("text_warn");
				isPass = false;
			}
		});
		
		// 必填0或正整数
		var re_number = /^[0-9]+$/i
		$(this).find("[required_Number]").each(function() {
			var val = $(this).val().trim();
			if (val == "" || !re_number.test(val)) {
				$(this).addClass("text_warn");
				$(this).val("");
				isPass = false;
			}
		});
		
		// 必填正整数Positive Integer：1到＋∞
		var re_number = /^[1-9]+$/i
		$(this).find("[required_PositiveInteger]").each(function() {
			var val = $(this).val().trim();
			if (val == "" || !re_number.test(val)) {
				$(this).addClass("text_warn");
				$(this).val("");
				isPass = false;
			}
		});
		
		// 最小长度检测
		$(this).find("[required_MinLength]").each(function() {
			var val = $(this).val().trim();
			var minLength = eval($(this).attr("required_MinLength"));
			if (val == "" || val.length < minLength) {
				$(this).addClass("text_warn");
				alert("您输入的长度不能小于" + minLength);
				isPass = false;
			}
		});
		
		// 校验输入数字的范围： 小于等于required_MaxRange.val()
		$(this).find("[required_MaxRange]").each(function() {
			var val = eval($(this).val().trim());
			var maxRange = eval($(this).attr("required_MaxRange"));
			if (val != "" && val > maxRange) {
				$(this).addClass("text_warn");
				alert("您输入的最大数值不能大于：" + maxRange);
				$(this).val("");
				isPass = false;
			}
		});
		
		// 校验输入字符的最大长度
		$(this).find("[required_length]").each(function() {
			var val = eval($(this).val().trim().length);
			var length = eval($(this).attr("required_length"));
			if (val != "" && val > length) {
				$(this).addClass("text_warn");
				alert("您输入的字符不能超过" + length + "个");
				$(this).focus();
				isPass = false;
			}
		});
		// 必填IP
		// 正则表达式别用g属性，否则第二次检测的时候会混合第一次结果导致错误
		var re_ip = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/i
		$(this).find("[required_Ip]").each(function() {
			var val = $(this).val().trim();
			if (val == "" || !re_ip.test(val) || RegExp.$1 > 256
					|| RegExp.$2 > 256 || RegExp.$3 > 256
					|| RegExp.$4 > 256) {
				$(this).addClass("text_warn");
				isPass = false;
			}
		});

		// 日期类型检测 :yyyy-MM-dd
		var re_Date = /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$/i;
		$(this).find("[required_Date]").each(function() {
			var val = $(this).val().trim();
			if (val != "" && !re_Date.test(val)) {
				$(this).addClass("text_warn");
				alert("日期类型必须为:yyyy-MM-dd");
				isPass = false;
			}
		});

		// 时间类型检测 Hi:mm[:ss]
		var re_Time = /(0\d|1\d|2[0-3]):[0-5]\d(:([0-5]\d))?$/i;
		$(this).find("[required_Time]").each(function() {
			var val = $(this).val().trim();
			if (val != "" && !re_Time.test(val)) {
				$(this).addClass("text_warn");
				alert("日期类型必须为:Hi:mm[:ss]，秒是可选填的");
				isPass = false;
			}
		});
		
		// 日期时间类型检测:yyyy-MM-dd Hi:mm:ss
		var re_Time = /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))(\s(0\d|1\d|2[0-3]):[0-5]\d(:([0-5]\d)))?$/i;
		$(this).find("[required_DateTime]").each(function() {
			var val = $(this).val().trim();
			if (val != "" && !re_Time.test(val)) {
				$(this).addClass("text_warn");
				isPass = false;
			}
		});
		
		// 邮箱类型检测
		var re_Mail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i;
		$(this).find("[required_Mail]").each(function() {
			var val = $(this).val().trim();
			if (val == "" || !re_Mail.test(val)) {
//				$(this).focus();
				$(this).addClass("text_warn");
				isPass = false;
			}
		});

		// 手机类型检测
		var re_Phone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/i;
		$(this).find("[required_Phone]").each(function() {
			var val = $(this).val().trim();
			if (val != "" && !re_Phone.test(val)) {
				$(this).addClass("text_warn");
				isPass = false;
			}
		});

		// 身份证类型检测
		var re_Idcard = /^([0-9]{17}[0-9X]{1})$|^([0-9]{15})$/;
		$(this).find("[required_Idcard]").each(function() {
			var val = $(this).val().trim();
			if (val != "" ) {
				if (!re_Idcard.test(val)) {
					$(this).addClass("text_warn");
					isPass = false;
				}
			}
		});

		return isPass;
	}

	// 判断是否是IE，包括11
	$.isIE = function isIE() {
	    if (!!window.ActiveXObject || "ActiveXObject" in window)
	        return true;
	    else
	        return false;
	};	
	
	// 让form使用ajax提交
	$.fn.bindFormClick = function(options) {
		options = $.extend({
//			url : null,//不能设置默认的URL，否则jquery使用$.extend合并参数的时候会发现options.url存在，则最终结果会是null
			tip : true,
			isGoodCall : true,//是否只有返回success才回调afterSubmit
			beforeSubmit : function(){},
			afterSubmit : function(){}
		}, options||{});
		
		$(this).unbind("click").click(function(){
	    	var form = $(this).closest("form");
	    	if(form.length > 0){
	    		
    			var rs = options.beforeSubmit(form, options);
    			if(rs == false) {
    				return;
    			}
	    		
    			var isComplete = false;
	    		form.ajaxSubmit({
	    			url : options.url,//如果url参数为空，jquery form会调用form的action地址作为url
		    		beforeSubmit : function(){
		    			var rt = form.validate();
		    			if(rt != false) {
			        		setTimeout(function() {
			        			if(!isComplete) {
			        				$.showFloatExcuting();
			        			}
			        		}, 360);//360ms没有完成请求则显示loading
		    			}
		    			return rt;
		    		},
		    		success : function(result) {
		    			isComplete = true;
		    			$.hideFloatExcuting();
		    			if(options.tip) {
		    				Boxy.alert(Message.dynamic(result));
		    			}
		    			if(result == "success") {
		    				$(".close").click();
		    			}
		    			if((options.isGoodCall && result == "success") || !options.isGoodCall){
		    				options.afterSubmit(form, result);
		    			}
		    		}
		    	});
	    	}
	    	return false;
		});
	};	
	
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

		if (opt.hide) {
			watcher.bind("mouseout", function() {
				$(el).hide();
			});
		}
	};

	// 绑定关闭弹框
	$.fn.bindFormClose = function(callback) {
		$(this).unbind("click").click(function(e) {
			Boxy.get(this).hideAndUnload();
			if (callback)
				callback();
		});
	};
});

// 所有页面公用的js
function init() {

	// 回车和esc
	$(document).unbind("keydown").keydown(function(e){
		if(e.keyCode==13){// 回车
			var el;			
			// 浮动对话框
			el = $(".answers .boxy-btn1");
			if(el.size() > 0) {
				el.click();
				return;
			}
			
			// 登录
			el = $(".login_submit");
			if(el.size() > 0) {
				el.click();
				return;
			}
			
		}else if(e.keyCode==27) {// ESC
			// 清除所有浮动层---一次性移除，不一层层移除了，麻烦
			$(".boxy-wrapper").remove();
			$(".boxy-modal-blackout").remove();
			
		}
	});	
	
	// form提交校验数据
	$(".common_form").submit(function() {
		return $(this).validate();
	});

	// 让form_submit类具备提交能力
	$(".form_submit").click(function() {
		$(this).closest("form").submit();
	});
	
	// IE8支持HTML5的placeholder属性
	if (!('placeholder' in document.createElement('input'))) {
		$('input[placeholder],textarea[placeholder]').each(function() {
			var that = $(this), text = that.attr('placeholder');
			if (that.val() === "") {
				that.val(text).addClass('placeholder');
			}
			that.focus(function() {
				if (that.val() === text) {
					that.val("").removeClass('placeholder');
				}
			}).blur(function() {
				if (that.val() === "") {
					that.val(text).addClass('placeholder');
				}
			}).closest('form').submit(function() {
				if (that.val() === text) {
					that.val('');
				}
			});
		});
	}
}

// 入口函数
$(function() {
	init();
	if (Globals && Globals.page) {
		var args = Globals.page.split('_');
		var url = Globals.ctx + "/resource/js/" + args[0] + ".js";
		$.getScript(url, function(rep, st) {
			try {
				var command = 'var obj = new ' + args[0] + 'Class(); ';
				command += 'obj.' + args[1] + '()';
				eval(command);
			} catch (e) {
				console.info("module(" + args[0] + ") or function(" + args[1]
						+ ") is not found")
			}
		});
	}
});