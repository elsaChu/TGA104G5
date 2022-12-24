<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.member.model.*"%>
<% MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>TICK IT</title>

    <!-- Favicon -->
    <link rel="icon" href="images/logo.ico" />

    <!-- Style CSS -->
    <link rel="stylesheet" href="css/memberCentre.css" />

  </head>

  <body>
  
  <jsp:include page="/main_frame/index_header.jsp"></jsp:include>
  
      <!-- 空行 -->
      <div class="row"><div class="col-12">　</div></div>


    <span id="tab-1">帳號設定</span>
    <span id="tab-2">修改密碼</span>
    <span id="tab-3">票券訂單</span>
    <span id="tab-4">商品訂單</span>
    <!-- 頁籤按鈕 -->
    <div id="tab">
      <ul>
        <li><a href="${context}/front-end/member/memberCentre.jsp">帳號設定</a></li>
        <li><a class="active" href="${context}/front-end/member/memberCentreUpdatePwd.jsp">修改密碼</a></li>
        <li><a href="#tab-3">票券訂單</a></li>
        <li><a href="#tab-4">商品訂單</a></li>
      </ul>
	
      <!-- 頁籤的內容區塊 -->

      <div class="tab-content-2">
        <br />
        <h4>修改密碼</h4>
        <br />
        <%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
        <div class="Data-Content">
          <div class="Data-Title">
            <div class="AlignRight">
              <label for="txt">請輸入舊密碼</label><br />
              <label for="txt">請輸入新密碼</label><br />
              <label for="txt">請再次輸入新密碼</label><br />
            </div>
          </div>
        <form action="MemberServlet" method="POST" >
        <div class="Data-Items">
          <input type="password" name="oldPassword"/><br />
          <span class="error" style="color: red">${errors.oldPassword}</span>
          <input type="password" name="newPassword"/><br />
          <input type="password" name="newPassword2"/><br /><br /><br />
        </div>
        <div class="submit">
           <input type="hidden" name="action" value="updatePassword">
          <input type="submit" value="確定修改密碼" /><br /><br />
        </div> 
          </form>
       </div>
	 </div>
	</div>



    <!-- ##### Footer 開始 ##### -->
<jsp:include page="/main_frame/index_footer.jsp"></jsp:include>
	<!-- ##### Footer 結束 ##### -->
  </body>
</html>
