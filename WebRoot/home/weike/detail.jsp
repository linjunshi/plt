<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<c:if test="${!isMobile}">
<script type="text/javascript" src="${ctx}/resource/player/swfobject.js"></script>
<script type="text/javascript">
// For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. 
var swfVersionStr = "11.1.0";
// To use express install, set to playerProductInstall.swf, otherwise the empty string. 
var xiSwfUrlStr = "${ctx}/resource/player/playerProductInstall.swf";
var flashvars = {
	Source: "${ctx}${weike.fileUrl}",
};
var params = {};
params.quality = "high";
params.bgcolor = "#ffffff";
params.allowscriptaccess = "sameDomain";
params.allowfullscreen = "true";
var attributes = {};
attributes.id = "MicroCoursePlayer";
attributes.name = "MicroCoursePlayer";
attributes.align = "middle";
swfobject.embedSWF("${ctx}/resource/player/MicroCoursePlayer.swf", "flashContent", "100%", "100%", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
// JavaScript enabled so display the flashContent div in case it is not replaced with a swf object.
swfobject.createCSS("#flashContent", "display:block;text-align:left;background:#000;");
</script>
</c:if>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_weikeDetail";
</script>
</head>
<body>
	<div class="header">
		<%@ include file="../inc/top_new.jsp"%>
		 <div class="questions_list ">	
		    <div class="pinglun clearfix">
		    <div class="location">
		    <c:if test="${!empty luEntry}">
			    <span>${luEntry.levelName} > </span><span>${luEntry.subjectName} > </span><span>${luEntry.termCnName}  > </span><span>${luEntry.unitName}</span>
		    </c:if>
		    <c:if test="${empty luEntry}"><span>&nbsp;</span></c:if>
			</div>
		      <div class="ping_title">${weike.courseName}</div>
		      <div class="ping_left">
		      	<div class="ping_item"><a href="#" class="school_a">学前预习</a><a href="#" class="vod_a">课程播放</a><a href="#" class="ke_a mar">课后习题</a></div>
		        <div class="ping_li">
		        	<c:if test="${isMobile}">
							<video autoplay="autoplay" controls="controls" style="width:100%; height:100%; background:black;">
								<source src="${ctx}${weike.fileUrl}">
							</video>
						</c:if>
						<c:if test="${!isMobile}">
					        <div id="flashContent">
					            <p>
					                To view this page ensure that Adobe Flash Player version 
					                11.1.0 or greater is installed. 
					            </p>
					            <script type="text/javascript"> 
					                var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://"); 
					                document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
					                                + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" ); 
					            </script> 
					        </div>
						</c:if>
		        </div>
		      </div>
		      <div class="ping_right">
		        <div class="right_sid">
		          <h2>其他相关微课</h2>
		          <c:forEach items="${weikeList}" var="otherWeike" varStatus="wt">
						<p><a href="${ctx}/weike/${otherWeike.id}.html" target="_blank">${otherWeike.courseName}</a></p>
				  </c:forEach>
				  <c:if test="${empty weikeList}"><p class="nothing"><a href="javascript:void(0);">暂无相关的微课！</a></p></c:if>
		        </div>
		        <div class="right_but"><a href="${ctx}/war/exams?type=${type}&weikeId=${weike.id}" target="_blank">课后练习</a></div>
		      </div>
		    </div>
		    <div class="clr"></div>
		    <div class="ping_footer clearfix">
		      <div class="ping_foot_left">
		        <div class="ping_fo_ti">评&nbsp;论</div>
		        <ul class="ping_foo_con">
		           <c:forEach items="${weike.commentList}" var="comment" varStatus="ct">
		           <li class="ping_fo_c clearfix">
						<img src="${ctx}${comment.headPhoto}" width="80" height="80">
						<p>${comment.showName}</p>
						<p>${comment.remark}</p>
						<span class="pi_time"><fmt:formatDate value="${comment.cts}" type="both"/></span>
		           </li>
		           </c:forEach>
		           <c:if test="${empty weike.commentList}"><li class="ping_fo_c clearfix"><p class="nothing">还没有评论，沙发等你来抢！</p></li></c:if>
		        </ul>
		        <div class="ping_comment_title">
					<div class="ping_say"><strong>我来说两句</strong></div>
		            <div class="ping_mr"><span class="pt3">请文明上网，理性发言！</span> 已经有&nbsp;&nbsp;<span class="pt5">${weike.commentCount}</span>人评论了</div>
				</div>
		        <div id="comment" class="ping_form">
		          <form action="${ctx}/weike/comment" method="post" id="comment_form" class="common_form">
						<textarea name="remark" cols="" rows="" class="ping_tarea"></textarea>
						<!-- <div>
							<textarea id="xheditor_remark" name="remark" class="xheditor th_textarea" cols="2" rows="3"></textarea>
                            <script type="text/javascript">
							 	$('#xheditor_remark').xheditor({upImgUrl:'${ctx}/image/upload2', html5Upload:false, upMultiple:1, upImgExt:'jpg,jpeg,gif,png,bmp'});
							</script>
						</div> -->
						<input type="hidden" name="weikeId" value="${weike.id}"/>
						<input class="ping_submit" type="submit"  value="提交"/>
					</form>
		        </div>
		      </div>
		      <div class="ping_foot_right">
		      	<div class="ping_f_bot">
			      	<c:if test="${hasBuy}">
	            		<a href="javascript:void(0);" class="hasBuy" title="亲，您已经购买，可直接观看">已经购买</a>
	            		<a href="javascript:void(0);" class="coll_course" title="点击此按钮，收藏改课程。">收藏</a>
	            	</c:if>
	            	<c:if test="${!hasBuy}">
			            <form action="https://pay3.chinabank.com.cn/PayGate?encoding=UTF-8" method="post" target="_blank" name="E_FORM" class="buy_form">
				            <input type="hidden" name="v_mid" value="" />
				            <input type="hidden" name="v_oid" value="" />
				            <input type="hidden" name="v_amount" value="" />
				            <input type="hidden" name="v_moneytype" value="" />
							<input type="hidden" name="v_url" value="" />
							<input type="hidden" name="v_md5info" value="" />
							<input type="hidden" name="v_rcvname" value="" />
							<input type="hidden" name="remark1" value="" />
							<a href="javascript:void(0);" class="buy" title="点击此按钮，到下一步网银支付后，就可以开始学习了。">立刻购买</a>
							<a href="javascript:void(0);" class="coll_course" title="点击此按钮，收藏改课程。">收藏</a>
						</form>		            
		            </c:if>
		      	</div>
		        <c:forEach items="${weikeUnitList}" var="unitWeike" varStatus="wt">
					<a href="${ctx}/weike/${unitWeike.id}.html" target="_blank"><img src="${ctx}${unitWeike.thumbnail}" width="230" title="${unitWeike.courseName}"></a>
			    </c:forEach>
		      </div>
		    </div>
		  </div>
	</div>
</body>
</html>
