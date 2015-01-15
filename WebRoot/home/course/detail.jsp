<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<c:set var="title" value="三简在线教育平台" ></c:set>
<c:set var="keywords" value="456" ></c:set>
<c:set var="description" value="789" ></c:set>
<%@ include file="../inc/header.jsp"%>
<script type="text/javascript">
var Globals = {};
Globals.ctx = "${ctx}";
Globals.lang = "${lang}";
Globals.page = "Index_courseDetail";
</script>
</head>
<body>
	<%@ include file="../inc/top.jsp"%>
	<div id="container_box">
		<div id="container_content">
		    <div class="detailPrimary clr">
				<div class="detai_img">
					<div>
						<img src="${ctx}${course.thumbnail}" width="290" height="230" />
					</div>
					<div><a href="javascript:void(0)" id="coll_course" class="detai_coll"><i class="coll_icon"></i>收藏课程&nbsp;(${course.collectCount}人气)</a></div>
					<!-- <a href="javascript:void(0);" class="detai_share">分享给朋友</a> -->
					<div class="bdsharebuttonbox detai_share">
						<a href="#" class="bds_more" data-cmd="more"></a>
						<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
						<a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
						<a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
						<a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
						<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
					</div>
					<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
					<input type="hidden" name="courseId" value="${course.id}"/>
				</div>
				<div class="priInfo">
		            <h2 class="mb15">${course.courseName}</h2>
		            <div class="pri_m ">
		                <p>课程价格：<b>${course.price}</b>元</p>
		                <p><span>总课时：<em>${course.chapterCount} 课时</em></span><span> 课程结束时间：<em class="data_time"><fmt:formatDate value="${course.endTime}" type="date" dateStyle="default"/></em></span></p>
		            </div>
		            <div class="detai_num clearfix">
		                <p>已经购买人数<br/><b> ${course.saleCount}</b><br/> 限购&nbsp;${course.limitCount}&nbsp;人</p>
		                <!-- <p>学生满意度<br/><b> 0%</b></p> -->
		                <p>课程评价数<br/><b> ${course.commentCount}</b><br/> 评价内容</p>
		            </div>
		            <p class="detai_numb">该课程累积收藏人数为&nbsp;${course.collectCount}&nbsp;人</p>
		            <div class="detai_buy">
		            	<c:if test="${hasBuy}">
		            		已经购买，可直接学习
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
								<a href="javascript:void(0);" class="bottom_a buy">立刻购买</a>
								<!-- <input type="image" src="http://merchant3.chinabank.com.cn/images/button_2.gif" > -->
							</form>		            
				            <!-- <a href="javascript:void(0);" class="bottom_a buy">立刻购买</a> -->
				            <!-- <a href="#" class="bottom_b">加入购物车</a> -->
			            </c:if>
		            </div>
		        </div>
		        <div class="priSchool">
		            <div>
		                <h2><a href="${ctx}/teacher/${teacher.id}.html" target="_blank">课程所有者</a></h2>
		            </div>
		            <div>
		                <ul class="schoolInfo">
		                    <li>姓名：${teacher.showName}</li>
		                    <li>性别：<c:if test="${teacher.gender == 1}">男</c:if><c:if test="${teacher.gender == 2}">女</c:if></li>
		                    <li>职称：<c:if test="${teacher.positional == 0}">暂无</c:if>
		                    		<c:if test="${teacher.positional == 1}">助教</c:if>
		                    		<c:if test="${teacher.positional == 2}">讲师</c:if>
		                    		<c:if test="${teacher.positional == 3}">副教授</c:if>
		                    		<c:if test="${teacher.positional == 4}">教授</c:if></li>
		                    <li>毕业院校：<c:if test="${teacher.graduateSchool == null}">暂无</c:if>${teacher.graduateSchool}</li>
		                    <li>所属学校：${teacher.schoolName}</li>
		                    <li>教学科目：${teacher.subjectName}</li>
		                    <li>开设课程数：${teacher.courseCount}</li>
		                    <li>注册时间：<fmt:formatDate value="${teacher.registTime}" type="date" dateStyle="default"/></li>
		                </ul>
		            </div>
		            <div class="pri_bottom"><!-- <a href="javascript:void(0)" class="bottom_c">收藏学校</a> --><a href="${ctx}/school/${teacher.schoolId}.html" target="_blank" class="bottom_d">进入学校</a></div>
		        </div>
		    </div>
		    <div class="sectionMain clr">
		        <div class="sec_main clr">
		            <ul id="content_nav">
		                <li class="current">课程详情</li>
		                <li>课程目录</li>
		                <li>课程评价</li>
		            </ul>
		        </div>
		    </div>
	       <div class="th_introduce clr" id="course_summary">
	        	<h2>课程详情</h2>
	          	<p>${course.remark}<c:if test="${empty course.remark}"><span class="nothing">暂无内容！</span></c:if></p>
	        </div>
		    <div class="th_catalog clr" id="course_struct">
		        <h2>课程目录</h2>     
		        <dl class="th_car_list">
		        	<c:if test="${empty course.chapterDetailList}"><span class="nothing">暂无内容！</span></c:if>
		        	<c:forEach items="${course.chapterDetailList}" var="chapter" varStatus="st">
		            <dt>第 ${st.index+1} 课：${chapter.remark}</dt>
		            	<c:forEach items="${chapter.resourceList}" var="resource">
		            	<dd>
				           	<div class="course_list_des">
				        		<c:if test="${resource.typeEnString == 'file'}">
				        			<img src="${ctx}/resource/images/res_file.png" title="${resource.typeCnString}">
				        			<span>${resource.title}</span>
				        			<div class="course_list_oper">
						        		<a href="${ctx}/${resource.typeEnString}?resId=${resource.id}" target="_blank" title="点击进入">我要观看</a>
						        	</div>
				        		</c:if>
				        		<c:if test="${resource.typeEnString == 'live'}">
				        			<img src="${ctx}/resource/images/res_live.png" title="${resource.typeCnString}">
				        			<span>${resource.title}</span>
				        			<div class="course_list_oper">
					        			<c:if test="${resource.status == -1}"><span class="live_without_start">未开始</span></c:if>
						        		<c:if test="${resource.status == 0}"><a class="liveJump" href="javascript:void(0);" live_id="${resource.id}" title="点击进入">正在直播中...</a></c:if>
						        		<c:if test="${resource.status == 1}"><span class="live_done">已结束</span></c:if>
						        	</div>
				        		</c:if>
				        		<c:if test="${resource.typeEnString == 'doc'}">
				        			<img src="${ctx}/resource/images/res_doc.png" title="${resource.typeCnString}">
				        		</c:if>
				        		<c:if test="${resource.typeEnString == 'train'}">
				        			<img src="${ctx}/resource/images/res_train.png" title="${resource.typeCnString}">
				        			<span>${resource.title}</span>
				        			<div class="course_list_oper">
						        		<a href="${ctx}/${resource.typeEnString}?resId=${resource.id}" target="_blank" title="点击进入">我要做题</a>
						        	</div>
				        		</c:if>
				            </div>
		            	</dd>
		            	</c:forEach>
		        	</c:forEach>
		        </dl>
		    </div>
		    <div class="th_evaluation" id="course_comment">
		        <h2>课程评价</h2>
		        <div class="comm_pjxq clr">
		        	<div class="th_say"><strong>我来说两句</strong></div>
		            <div class="fl th_mr"><span class="pt3">请文明上网，理性发言！</span> 总共有&nbsp;&nbsp;<span class="pt5">${course.commentCount}</span>条课程评论</div>
		        </div>
				<div class="th_con">
					<form action="${ctx}/course/comment" method="post" id="comment_form" class="common_form">
						<div>
							<textarea id="xheditor_remark" name="remark" class="xheditor th_textarea" cols="2" rows="3"></textarea>
                            <script type="text/javascript">
							 	$('#xheditor_remark').xheditor({upImgUrl:'${ctx}/image/upload2', html5Upload:false, upMultiple:1, upImgExt:'jpg,jpeg,gif,png,bmp'});
							</script>
						</div>
						<input type="hidden" name="courseId" value="${course.id}"/>
						<input class="th_pt_but" type="submit"  value="确定"/>
					</form>
				</div>
				<div class="th_lea clr">
					<ul>
						<c:forEach items="${course.commentList}" var="comment" varStatus="st">
							<li>
								<div class="th_img_user"><img src="${ctx}${comment.headPhoto}" width="80" height="80"></div>
								<div class="th_mr2">
									<p>
										<b>${comment.showName}</b>
										<span class="th_post_time">&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${comment.cts}" type="both"/></span>
									</p>
								</div>
								<div class="th_post_content">
									<p>${comment.remark}</p>
								</div>
							<!--<div class="th_post_footer">
									<a href="javascript:void(0);" class="th_btn_upvote">(<em>2120</em>)</a>
									<a href="javascript:void(0);" class="th_btn_reply">回复(<em>98</em>)</a>
								</div>
								<div class="th_text_i"	>
									<label><textarea name="th" cols="2" rows="3" class="th_te"></textarea></label>
									<a href="javascript:void(0);" class="th_text_but">提交</a>
								</div> -->
							</li>
						</c:forEach>
						</ul>
						<c:if test="${empty course.commentList}"><p class="nothing">还没有评论，沙发等你来抢！</p></c:if>
					
				</div>
				
			</div>
		</div>		
	</div>
	
	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>
