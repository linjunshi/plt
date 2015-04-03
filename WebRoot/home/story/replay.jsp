<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.santrong.plt.system.Global"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="杜巴克教育" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
<c:set var="description" value="杜巴克教育" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript">
	function InitAct(servaddr,port,roleid) {   
		vdActive.Init(servaddr,port,roleid);
		return ;   
	}  

	function StartCollect() {
		vdActive.StartCollect();
		return ;   
	}  

	function StopCollect() {   
		vdActive.StopCollect();
		return ;   
	}  

	function StartPlay() {   
		vdActive.StartPlay();
		return ;   
	}  

	function StopPlay() {   
		vdActive.StopPlay();
		return ;
	}  
	
	function UpdateIndex(index) {
		$(".con_yi li").removeClass("current").eq(index).addClass("current");
		if(index > 4) {
			Globals.pageData.moveNum = 0;
			scorlWord(index - 4);
		}
	}
	
	// 台词滚动
	function scorlWord(offset) {
		Globals.pageData.moveNum += offset;
		if(Globals.pageData.moveNum > ($(".con_yi li").size()-6)) {
			Globals.pageData.moveNum = ($(".con_yi li").size()-6);
		}else if(Globals.pageData.moveNum < 0) {
			Globals.pageData.moveNum = 0;
		}
		
		/*var marginTop = 0;
		for(var i=0;i<Globals.pageData.moveNum;i++) {
			marginTop -= ($(".con_yi li").eq(i).height()+11);//上下padding10和下boder1
		}
		$(".con_yi ul").css({"margin-top" : marginTop+"px"});*/
		
		$(".con_yi li").show();
		for(var i=0;i<Globals.pageData.moveNum;i++) {
			$(".con_yi li").eq(i).hide();
		}
	}
	
</script>
<c:if test="${!isMobile}">
<script type="text/javascript" src="${ctx}/resource/player/swfobject.js"></script>
<script type="text/javascript">
// For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. 
var swfVersionStr = "11.1.0";
// To use express install, set to playerProductInstall.swf, otherwise the empty string. 
var xiSwfUrlStr = "${ctx}/resource/player/playerProductInstall.swf";
var flashvars = {
	//ServAddr: "192.168.10.163",
	ServAddr: "<%=Global.PltDomain%>",
	ServPort: "23456",
	UserID: "${sessionScope.loginUser.id}",
	DramaID: "${story.id}",
	DramaName: "${story.storyEname}",
	Demo: "${demo}"
};

var params = {};
params.quality = "high";
params.bgcolor = "#000000";
params.allowscriptaccess = "sameDomain";
params.allowfullscreen = "true";
var attributes = {};
attributes.id = "VirtualDrama";
attributes.name = "VirtualDrama";
attributes.align = "middle";
window.onload = function() {
	swfobject.embedSWF("${ctx}/resource/player/VirtualDrama.swf", "flashContent", "100%", "100%", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
	swfobject.createCSS("#flashContent", "display:block;text-align:left;background:#000;");
};
</script>
</c:if>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Story_replay";

Globals.pageData = {};
Globals.pageData.moveNum = 0;
</script>
</head>
<body>
<div class="header_p_r">
  <div class="menu_ping">
    	<div class="logo_p">
			<img src="${ctx}/resource/images/logo.png" height="70">
		</div>
		<c:if test="${sessionScope.loginUser != null}">
			<div class="hea_new"><span>${sessionScope.loginUser.showName}</span>|<a href="${ctx}/">首页</a>|<a href="${ctx }/account/logout">注销</a></div>
		</c:if>
		<c:if test="${sessionScope.loginUser == null}">
			<div class="hea_new"><a href="${ctx}/">首页</a>|<a href="javascript:void(0);" id="loginWicket">登录</a>|<a href="${ctx}/account/regist">注册</a>|<a href="${ctx}/account/forgotPwd">忘记密码？</a></div>
		</c:if>
    <div class="con_ping_r">
		<c:if test="${isMobile}">
			<video autoplay="autoplay" controls="controls" style="width:100%; height:100%; background:black;">
				<source src="${ctx}">
			</video>
		</c:if>
		<c:if test="${!isMobile}">
	        <object CLASSID="CLSID:40465476-E37B-4BB8-816E-3F7E525BEDAB" name="vdActive" CODEBASE="${ctx}/resource/player/VDAct.CAB#version=1,0,1,3" style="display:none;"></object>
	        <div id="flashContent">
	           <!--  <p>
	                To view this page ensure that Adobe Flash Player version 
	                11.1.0 or greater is installed. 
	            </p> -->
	            <!-- <p class="">请稍候，剧本正在加载中...</p> -->
	            <p class=""></p>
	           <!--  <script type="text/javascript"> 
	                var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://"); 
	                document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
	                                + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" ); 
	            </script>  -->
	        </div>
		</c:if>
	</div>
    <div class="con_yi">
		<ul>
			<c:forEach var="word" items="${wordList}" varStatus="st">
			<li startTime="${word.startTime}">${st.index+1}.${word.word}</li>
			</c:forEach>
		</ul>    
    </div>
    <div>
	    <a href="javascript:void(0);" class="ping_but_a"></a>
	    <a href="javascript:void(0);"  class="ping_but_b"></a>
   </div>
  </div>
  <div class="footer_p">
  	<p>版权所有：深圳市课云网络有限公司  粤ICP备15022223号</p>
  </div>
</div>
</body>
</html>