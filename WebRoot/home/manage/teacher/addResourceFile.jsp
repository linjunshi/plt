<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../inc/common.jsp"%>

<input type="hidden" id="courseId" name="courseId" value="${courseId}" />
<input type="hidden" id="chapterId" name="chapterId" value="${chapterId}" />
<input type="hidden" id="oldResourceId" name="oldResourceId" value="${oldResourceId}" />
<div class="sh_title">
    <h2>课件列表</h2>
    <a href="${ctx}/manage/course/chapterEditor?courseId=${courseId}" class="sh_add">&#60;&#60;返回上级</a>
</div>
<div class="sh_collection">
       <table border="1" class="sh_coll_tab">
           <colgroup>
           <col width="80">
           <col width="330">
           <col width="110">
           <col width="150">
           <col width="150">
           <col width="150">
           </colgroup>
           <thead>
               <tr>
                   <th>序号</th>
                   <th>课件名</th>
                   <th>录制时间</th>
                   <th>录制时长</th>
                   <th>描述</th>
                   <th>操作</th>
               </tr>
           </thead>
           <tbody>
           	<c:forEach items="${fileList}" var="file" varStatus="st">
                 <tr>
                     <td>${st.index + 1}</td>
                     <td class="sh_h_im">
                     	<a href="${ctx}/course/${course.id}.html" target="_blank"><img src="${ctx}/resource/images/003.jpg"></a>
                     	<p>${file.title}</p>
                     </td>
                     <td><fmt:formatDate value="${file.cts}" type="both" /></td>
                     <td>${file.duration}</td>
                     <td>${file.remark}</td>
                     <td class="btn_question_operation">
                      	<div class="btn_input">
                      		<input type="hidden" id="resourceId" name="resourceId" value="${file.id}" />
							<a class="selectResourceFile" href='javascript:void(0);'>选择</a>
						</div>
                     </td>
                 </tr>
           	</c:forEach>
               
              </tbody>
           <tfoot>
               <tr>
                   <td colspan="6">
                    <c:set var="basicUrl" value="${ctx}/manage/course/addResourceFile?courseId=${courseId}&chapterId=${chapterId}&oldResourceId=${oldResourceId}" />
    				<%@ include file="../../inc/pagination.jsp"%>
    			</td>
               </tr>
           </tfoot>
       </table>
</div>			            