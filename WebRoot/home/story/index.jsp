<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="杜巴克" ></c:set>
<c:set var="keywords" value="杜巴克教育" ></c:set>
<c:set var="description" value="在线教育 私人定制 知识图谱" ></c:set>
<%@ include file="../inc/header_new.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Story_index";
</script>
</head>
<body>
<div class="header_zhe">
	<div class="menu_zhe">
		<div class="logo_p">
			<img src="${ctx}/resource/images/logo.png" height="70">
		</div>
		<c:if test="${sessionScope.loginUser != null}">
			<div class="hea_new"><span>${sessionScope.loginUser.showName}</span>|<a href="${ctx}/">首页</a>|<a href="${ctx}/personal/center">个人空间</a>|<a href="${ctx }/account/logout">注销</a></div>
		</c:if>
		<c:if test="${sessionScope.loginUser == null}">
			<div class="hea_new"><a href="${ctx}/">首页</a>|<a href="javascript:void(0);" id="loginWicket">登录</a>|<a href="${ctx}/account/regist">注册</a>|<a href="${ctx}/account/forgotPwd">忘记密码？</a></div>
		</c:if>
		<div class="zhe_home">
			<ul>
				<li>
					<p class="zhe_photo">
						<img src="${ctx}/resource/images/zhezhao.png" width="327">
					</p>
					<p class="zhe_im">
						<img src="${ctx}/resource/images/h_si.jpg" width="260" height="175" alt="美丽的公鸡" title="美丽的公鸡">
					</p>
					<p>
						<a href="${ctx}/story/game/ProudCock?demo=0" target="_blank" class="zhe_but_c" title="美丽的公鸡">&nbsp;</a>
						<a href="${ctx}/story/game/ProudCock?demo=1" target="_blank" class="zhe_but_b" title="美丽的公鸡">&nbsp;</a>
					</p>
				</li>
				<li>
					<p class="zhe_photo">
						<img src="${ctx}/resource/images/zhezhao.png" width="327">
					</p>
					<p class="zhe_im">
						<img src="${ctx}/resource/images/h_si.jpg" width="260" height="175" alt="功夫片段A" title="功夫片段A">
					</p>
					<p>
						<a href="${ctx}/story/game/KongFuA?demo=0" target="_blank" class="zhe_but_c" title="功夫片段A">&nbsp;</a>
						<a href="${ctx}/story/game/KongFuA?demo=1" target="_blank" class="zhe_but_b" title="功夫片段A">&nbsp;</a>
					</p>
				</li>
				<li>
					<p class="zhe_photo">
						<img src="${ctx}/resource/images/zhezhao.png" width="327">
					</p>
					<p class="zhe_im">
						<img src="${ctx}/resource/images/h_si.jpg" width="260" height="175" alt="功夫片段B" title="功夫片段B">
					</p>
					<p>
						<a href="${ctx}/story/game/KongFuB?demo=0" target="_blank" class="zhe_but_c" title="功夫片段B">&nbsp;</a>
						<a href="${ctx}/story/game/KongFuB?demo=1" target="_blank" class="zhe_but_b" title="功夫片段B">&nbsp;</a>
					</p>
				</li>
				<li>
					<p class="zhe_photo">
						<img src="${ctx}/resource/images/zhezhao.png" width="327">
					</p>
					<p class="zhe_im">
						<img src="${ctx}/resource/images/h_si.jpg" width="260" height="175" alt="让子弹飞片段A" title="让子弹飞片段A">
					</p>
					<p>
						<a href="${ctx}/story/game/RangZiDanFeiA?demo=0" target="_blank" class="zhe_but_c" title="让子弹飞片段A">&nbsp;</a>
						<a href="${ctx}/story/game/RangZiDanFeiA?demo=1" target="_blank" class="zhe_but_b" title="让子弹飞片段A">&nbsp;</a>
					</p>
				</li>
				<li>
					<p class="zhe_photo">
						<img src="${ctx}/resource/images/zhezhao.png" width="327">
					</p>
					<p class="zhe_im">
						<img src="${ctx}/resource/images/h_si.jpg" width="260" height="175" alt="让子弹飞片段B" title="让子弹飞片段B">
					</p>
					<p>
						<a href="${ctx}/story/game/RangZiDanFeiA?demo=0" target="_blank" class="zhe_but_c" title="让子弹飞片段B">&nbsp;</a>
						<a href="${ctx}/story/game/RangZiDanFeiA?demo=1" target="_blank" class="zhe_but_b" title="让子弹飞片段B">&nbsp;</a>
					</p>
				</li>
				<li>
					<p class="zhe_photo">
						<img src="${ctx}/resource/images/zhezhao.png" width="327">
					</p>
					<p class="zhe_im">
						<img src="${ctx}/resource/images/h_si.jpg" width="260" height="175">
					</p>
					<p>
						<a href="${ctx}/story/direction" target="_blank" class="zhe_but_a">&nbsp;</a>
					</p>
				</li>
			</ul>
		</div>
	</div>
	<div class="footer_p">
		 <p>版权所有：深圳市课云网络有限公司  粤ICP备15022223号</p>
	</div>
</div>
</body>
</html>
