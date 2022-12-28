<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.organizer.model.*"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<% OrganizerVO organizerVO = (OrganizerVO) request.getAttribute("organizerVO");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT 廠商後台</title>

<link href="css/organizer_sen.css" rel="stylesheet" />

</head>
<body>
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

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
        <div class="set">
          <h3 style="font-weight: 600;">銀行帳戶設定</h3>
          <br><br><br><br>
          <div class="Data-Content">
            <div class="Data-Title">
              <div class="AlignRight">
      
        <label for="txt">戶名</label><br />
        <label for="txt">銀行帳號</label><br />
        <label for="txt">銀行代碼</label><br />
        <label for="txt">銀行名稱</label><br />
      </div>
    </div>
  </div>
  <form action="" method="POST">
  <div class="Data-Items">   
      <input type="text" name="accountName" /><br />
      <input type="text" name="accountNumber" /><br />
      <input type="text" name="bankCode" /><br />
      <input type="text" name="bankName" /><br />
    </div>
    <div class="submit">
      <br /><br /><input type="submit" value="儲存個人資料" /><br /><br />
    </div>
      </form>
        </div>
</body>
</html>