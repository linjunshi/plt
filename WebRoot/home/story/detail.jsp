<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript" src="${ctx}/resource/player/swfobject.js"></script>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Story_detail";

// For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. 
var swfVersionStr = "11.1.0";
// To use express install, set to playerProductInstall.swf, otherwise the empty string. 
var xiSwfUrlStr = "${ctx}/resource/player/playerProductInstall.swf";
var flashvars = {
	ServAddr: "192.168.10.163",
	ServPort: "23456",
	UserID: "${sessionScope.loginUser.id}",
	DramaName: "ProudCock",
	Demo: "0"
};
var params = {};
params.quality = "high";
params.bgcolor = "#ffffff";
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
</head>
<body>
	<div class="header">
		<%@ include file="../inc/top_new.jsp"%>
		 <div class="questions_list ">		 	
		    <div class="pinglun clearfix">
		      <div class="ping_title">${story.storyName}</div>
		      <div class="ping_left" style="width:800px; height:600px;"><!-- style="width:800px; height:600px;" -->
		        <div class="ping_li" style="width:800px; height:600px;">
		        	<object CLASSID="CLSID:40465476-E37B-4BB8-816E-3F7E525BEDAB" name="vdActive" CODEBASE="${ctx}/resource/player/VDAct.CAB#version=1,0,0,1" style="display:none;"></object>
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
		        </div>
		      </div>
		    </div>
		    <div class="clr"></div>		    
		  </div>
	</div>
	
	<%@ include file="../inc/footer.jsp"%>
</body>
</html>
