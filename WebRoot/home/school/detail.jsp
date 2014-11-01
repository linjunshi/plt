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
Globals.page = "Index_schoolDetail";
</script>
</head>
<body>
		<%@ include file="../inc/top.jsp"%>
		<div id="container_box">
			<div id="container_content">
			    <div class="th_propaganda"><img src="${ctx}/resource/images/a0eb.jpg" width="1200" height="108"></div>
			    <div class="sectionMain clr">
			        <div class="sec_main clr">
			            <ul>
			                <li><a href="#">学校首页</a></li>
			                <li><a href="#">学校简介</a></li>
			            </ul>
			        </div>
			        <div>
			            <div class="sh_info_le">
			                <div class="sh_info_img"><img src="${ctx}/resource/images/003.jpg" width="160" height="100"></div>
			              
			                    <div class="sh_list_switch">
			                        <dl class="show">
			                            <dt>高a</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>高b</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>高C</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>中3</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>中2</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>中1</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>小五</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>小四</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>小三</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>小二</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>
			                        <dl>
			                            <dt>小一</dt>
			                            <dd><a href="#">思想</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">传统网</a></dd>
			                            <dd><a href="#">标准的好处</a></dd>
			                            <dd><a href="#">推荐优秀书籍</a></dd>
			                        </dl>                        
			                    </div>
			                
			            </div>
			            <script type="text/javascript">
							$(function(){ 
								$('.sh_list_switch .show dt').addClass('icon');
								$('.sh_list_switch .show dd').show('slow');
								$('.sh_list_switch dt').click(function(){
									$(this).toggleClass('icon');
									$(this).nextAll().slideToggle();
								});
								
							});				
						</script>
			            <div class="sh_info_r">
			                <div class="sh_all ">
			                    <h2>老师阵容</h2>
			                    <a href="#" class="catalog_box_more">换一换</a>
			                    <ul class="sh_all_tea ">
			                    
			                        <c:forEach items="${teacher}"  var="teacher" varStatus="st">
			                        	<li <c:if test="${st.count%6==0}">class="maigin_right"</c:if> >
				                        	<a href="${ctx}/teacher/${teacher.id}.html"><img src="${ctx}/resource/images/003.jpg " width="120" height="80"></a>
				                            <p>${teacher.username}</p>
				                        </li>
			                        </c:forEach>
			                        
			                    </ul>
			                </div>
			                <div class="sh_ke">
			                    <h2>全部课程</h2>
			                    <a href="#" class="catalog_box_more">换一换</a>
			                    <ul class="sh_sch_ke ">
			                        
			                        <c:forEach items="${course}"  var="course" varStatus="ct">
				                        <li <c:if test="${ct.count%4==0}">class="maigin_right"</c:if> >
				                        	<a href="${ctx}/course/${course.id}.html"><img src="${ctx}/resource/photo/03.jpg" width="220" height="140"></a>
				                            <h2>${course.courseName}</h2>
				                            <p>价格：<b>${course.price}</b></p>
				                            <p class="sch_star">评价:<span><img src="${ctx}/resource/images/s21.gif"><img src="${ctx}/resource/images/s21.gif"><img src="${ctx}/resource/images/s21.gif"><img src="${ctx}/resource/images/s21.gif"><img src="${ctx}/resource/images/s21.gif"></span></p>
				                            <p><span class="sch_unm">${course.saleCount}</span>购买</p>
				                        </li>
			                        </c:forEach>
			                        
			                    </ul>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
		<%@ include file="../inc/friendlylink.jsp"%>
</body>
</html>