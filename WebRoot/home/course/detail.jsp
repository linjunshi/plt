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
					<img src="${ctx}${course.thumbnail}" width="290" height="200" />
					<a href="javascript:void(0)" id="coll_course" class="detai_coll">收藏课程</a>
					<!-- <a href="javascript:void(0);" class="detai_share">分享给朋友</a> -->
					<input type="hidden" name="courseId" value="${course.id}"/>
				</div>
				<div class="priInfo">
		            <h2 class="mb15">${course.courseName}</h2>
		            <div class="pri_m ">
		                <p >价格<b>${course.price}</b>元</p>
		                <p><span>总课时：<em>${course.chapterCount}课时</em></span><span> 结束时间：<em class="data_time"><fmt:formatDate value="${course.endTime}" type="date" dateStyle="default"/></em></span><span> 担保期：<em>15天</em> </span></p>
		            </div>
		            <div class="detai_num clearfix">
		                <p>已经购买人数<br/>
		                    <b> ${course.saleCount}</b><br/>
		                    限购100000人</p>
		                <p>学生满意度<br/>
		                    <b> 0%</b></p>
		                <p>课程评价数<br/>
		                    <b> 0</b><br/>
		                    评价内容</p>
		            </div>
		            <p class="detai_numb">该课程累积购买人数为49人</p>
		            <!-- <div class="detai_but">购买类型： <a href="#">购买整课</a><a href="#">购买部分章节</a> </div> -->
		            <div class="detai_buy"><a href="javascript:void(0);" class="bottom_a buy">立刻购买</a><!-- <a href="#" class="bottom_b">加入购物车</a> --></div>
		        </div>
		        <div class="priSchool">
		            <div>
		                <h2><a href="${ctx}/teacher/${teacher.id}.html" target="_blank">课程所有者</a></h2>
		            </div>
		            <div>
		                <ul class="schoolInfo">
		                    <li>姓名：${teacher.username}</li>
		                    <li>性别：${teacher.gender}</li>
		                    <li>职称：${teacher.positional}</li>
		                    <li>毕业院校：${teacher.graduateSchool}</li>
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
		                <li>课程详情</li>
		                <li>课程目录</li>
		                <li>课程评价</li>
		            </ul>
		        </div>
		        <div class="sec_con" id="course_summary">
		            <h2>2014零基础课程——有机化学</h2>
		            <p>反比例函数是初中学习函数的又一跨越，中考主要对其考察反比例函数的图象和性质，能根据已知条件确定反比例函数的解析式；能用反比例函数的知识解决有关实际问题。<br/>
		                ▪ 本课程把考试说明中的所有要求及知识点体现的淋漓尽致，不但教你怎样数形结合来解题，而且数学中解决实际应用问题的建模思想也能让你全权掌握。<br/>
		                ▪ 反比例函数有特点，双曲线相背离得远；<br/>
		                k为正，图在一、三（象）限；<br/>
		                k为负，图在二、四（象）限；<br/>
		                图在一、三函数减，两个分支分别减；<br/>
		                图在二、四正相反，两个分支分别添；<br/>
		                线越长越近轴，永远与轴不沾边。${course.remark} </p>
		        </div>
		    </div>
		    <div class="th_catalog clr" id="course_struct">
		        <h2>课程目录</h2>     
		        <dl class="th_car_list">
		        	<c:forEach items="${course.chapterDetailList}" var="chapter" varStatus="st">
		            <dt>第${st.index+1}课:${chapter.remark}</dt>
		            	<c:forEach items="${chapter.resourceList}" var="resource">
		            	<dd>
				           	<div class="course_list_des">
					        	<span>${resource.title}---type:${resource.typeCnString}</span>
					       		<a href="${ctx}/${resource.typeEnString}?resId=${resource.id}" target="_blank" >进入</a>
				            </div>
		            	</dd>
		            	</c:forEach>
		        	</c:forEach>
		        </dl>
		    </div>
		    <div class="th_evaluation" id="course_comment">
		        <h2>课程评价</h2>
		        <div class="comm_pjxq clr">
		            <div class="fl th_mr"><span class="pt4">学生满意度</span> <span class="pt5">0%</span> <span class="pt6">已有0人参与评价</span> </div>
		            <div class="th_pt">
		                <label class="fl " for="ricx1">
		                    <input id="ricx1" type="radio" checked="checked" value="0" name="ricx">
		                    全部 </label>
		                <label class="fl " for="ricx2">
		                    <input id="ricx2" type="radio" value="1" name="ricx">
		                    好评 </label>
		                <label class="fl " for="ricx3">
		                    <input id="ricx3" type="radio" value="2" name="ricx">
		                    中评 </label>
		                <label class="fl " for="ricx4">
		                    <input id="ricx4" type="radio" value="3" name="ricx">
		                    差评 </label>
		                <label for="ricx5">
		                    <input id="ricx5" type="checkbox" value="1">
		                    只看有内容的评论 </label>
		            </div>
		            
		        </div>
	
				<div class="th_lea clr">
					<ul>
						<c:forEach items="${course.commentList}" var="comment"
							varStatus="st">
							<li>
								<div class="th_img_user">
									<img src="${ctx}/resource/images/003.jpg" width="80" height="80">
								</div>
								<div class="th_mr2">
									<p>
										<b>${comment.showName}</b>
										<span class="th_post_time">&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${comment.cts}" type="both"/></span>
									</p>
								</div>
								<div class="th_post_content">
									<p>${comment.remark}</p>
								</div>
								<div class="th_post_footer">
									<a href="javascript:void(0);" class="th_btn_upvote">(<em>2120</em>)</a>
									<a href="javascript:void(0);" class="th_btn_reply">回复(<em>98</em>)</a>
								</div>
								<div class="th_text_i"	>
									<label><textarea name="th" cols="2" rows="3" class="th_te"></textarea></label>
									<a href="javascript:void(0);" class="th_text_but">提交</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
	
				<div class="th_con">
					<form action="${ctx}/course/comment" method="post" id="comment_form">
						<label><textarea name="remark" cols="2" rows="3" class="th_textarea"></textarea></label> 
						<input type="hidden" name="courseId" value="${course.id}"/>
						<input class="th_pt_but" type="submit"  value="确定"/>
					</form>
				</div>
			</div>
		    
		</div>		
	</div>
	
	<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>