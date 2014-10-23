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
	
});

$(function() {
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