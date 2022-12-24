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
  

    <span id="tab-1">帳號設定</span>
    <span id="tab-2">修改密碼</span>
    <span id="tab-3">票券訂單</span>
    <span id="tab-4">商品訂單</span>
    <!-- 頁籤按鈕 -->
    <div id="tab">
      <ul>
        <li><a class="active" href="${context}/front-end/member/memberCentre.jsp">帳號設定</a></li>
        <li><a href="${context}/front-end/member/memberCentreUpdatePwd.jsp">修改密碼</a></li>
        <li><a href="#tab-3">票券訂單</a></li>
        <li><a href="#tab-4">商品訂單</a></li>
      </ul>
	
      <!-- 頁籤的內容區塊 -->
      
      <div class="tab-content-1">
        <br />
        <h4>修改帳號設定</h4>
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
              <label for="txt">使用者帳號</label><br />
              <label for="txt">姓名</label><br />
              <label for="txt">信箱</label><br />
              <label for="txt">生日</label><br />
              <label for="txt">手機號碼</label><br /><br /><br />
              <hr style="width: 400%" />
              <label for="txt" style="font-weight: 600; font-size: 16px"
                >以下資料為報名表單的預填資料：</label
              ><br />
              <label for="txt">身份證字號</label><br /><br /><br /><br />
            </div>
          </div>
        
        <form action="MemberServlet" method="POST" >
        <div class="Data-Items">
      		<input type="text" name="account" readonly="readonly" value="<%=(memberVO == null) ? "" : memberVO.getAccount()%>"/><br />
          	<input type="text" name="name" value="<%=(memberVO == null) ? "" : memberVO.getName()%>"/><br />
          	<input type="email" name="email" value="<%=(memberVO == null) ? "" : memberVO.getEmail()%>"/><br />
          	<input type="date" name="birthday" value="<%=(memberVO == null) ? "" : memberVO.getBirthday()%>"/>
         	 <span class="error" style="color: red">${errors.birthday}</span><br />
          	<input type="tel" name="phoneNumber" value="<%=(memberVO == null) ? "" : memberVO.getPhoneNumber()%>"/>
          	<span class="error" style="color: red">${errors.phoneNumber}</span><br />
          	<label for="txt"
            >此手機號碼將作為購票緊急聯絡的用途，部分活動可能需要手機認證才能報名購票，請務必輸入正確的手機號碼。</label
          ><br /><br /><br />
          <hr style="width: 0%" />
          	<input type="text" name="IDNumber" value="<%=(memberVO == null) ? "" : memberVO.getIDNumber()%>"/>
          	<span class="error" style="color: red">${errors.IDNumber}</span><br /><br />
        </div>
        <div class="checkbox">
          <input type="checkbox" name="subscription" id="subscription"  value="<%=(memberVO == null) ? "" : memberVO.getSubscription()%>"
          
          />
          是否訂閱TICK IT最新消息<br />
        </div>
        <div class="submit">
        	<input type="hidden" name="action" value="update">
          <input type="submit" id="goo" value="儲存個人資料" /><br /><br />
          </div>
          </form>
          </div>
        </div>
        </div>
        
        
        <script type="text/javascript">
        var subscription = document.getElementById("subscription")
        subscription.addEventListener('change',(e) => {
        	if(e.currentTarget.checked){
        		subscription.value = "true";
        	} else {
        		subscription.value = "false";
        	}
        });

        </script>


    <!-- ##### Footer 開始 ##### -->
<jsp:include page="/main_frame/index_footer.jsp"></jsp:include>
	<!-- ##### Footer 結束 ##### -->
  </body>
    
    
  </body>
</html>
