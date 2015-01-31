<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>
<c:set var="title" value="课云" ></c:set>
<c:set var="keywords" value="课云教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Manage_personalCenter";
</script>
</head>
<body>
<div class="header">
	<%@ include file="../../inc/top_new.jsp"%>
	<div class="content clearfix">
		<div class="qu_left">
			<div class="qu_user_pho">
				<img src="${ctx}${sessionScope.loginUser.headPhoto}" width="140" height="140">
			</div>
			<div class="qu_item">
				<ul>
					<li><a href="${ctx}/study/course" class="pt4">我的课程</a></li>
					<li><a href="${ctx}/manage/competition" class="pt5">我的练习</a></li>
					<li><a href="${ctx}/collection" class="pt6">我的收藏</a></li>
					<li><a href="${ctx}/comment" class="pt7">我的评论</a></li>
					<li><a href="#" class="pt8">居本故事</a></li>
					<li><a href="#" class="pt9">朋友圈</a></li>
					<li>---------------------------------</li>
					<li><a href="#" class="pt10">学习统计</a></li>
					<li>---------------------------------</li>
					<li><a href="${ctx}/account/personalInfo" class="pt11">个人设置</a></li>
				</ul>
			</div>
		</div>
		<div class="qu_right">
			<div class="qu_right_it">
				<h2>${sessionScope.loginUser.showName}</h2>
				<p>你是我的小苹果</p>
				<p>
					财富：012<a href="#">充值</a><span>积分：2365</span>
				</p>
				<p>
					<a href="#">好友<b>12</b></a>｜<a href="#">关注<b>12</b></a>|<a
						href="#">粉丝<b>12</b></a>
				</p>
				<p class="qu_but">
					<a href="#">按钮</a>
				</p>
			</div>
			<div class="qu_tarea clearfix">
				<textarea name="" cols="" rows="" class="qu_tar">有什么心得一起来分享！</textarea>
				<p>
					<span>还可以输入 140 字</span><a href="#">发言</a>
				</p>
			</div>
			<div class="qu_info">
				<h2>全部动态</h2>
			</div>
			<div class="qu_no_info">
				<p>
					你还没有任何动态<a href="#">去群里看看</a>
				</p>
			</div>
		</div>
	</div>
</div>
<div class="footer"></div>
</body>
</html>
