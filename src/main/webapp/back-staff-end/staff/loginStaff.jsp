<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@
page import="tw.com.tibame.staff.model.*"
%>
<%
StaffVO staffVO = (StaffVO) request.getAttribute("staffVO");
%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <title>TICK IT</title>
    <!-- Custom Theme files -->
    <link
      href="${context}/back-staff-end/staff/css/style_staff.css"
      rel="stylesheet"
      type="text/css"
      media="all"
    />
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1"
    />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta
      name="keywords"
      content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"
    />
    <!--Google Fonts-->
    <link
      href="http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic"
      rel="stylesheet"
      type="text/css"
    />
    <!--Google Fonts-->

    <link rel="icon" href="images/logo.ico" />
  </head>
  <body>
    <div class="login">
      <h2>員工登入</h2>
      <div class="login-top">
        <h1>LOGIN</h1>
        <%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
        <form  method="post" ACTION="${context}/StaffServlet">
          <input type="text" name="staffAccount" placeholder="Staff Id" autocomplete="off"/>
          <input type="password" name="staffPassword" placeholder="Staff Password" autocomplete="off"/>
        
        <div class="forgot">
        <input type="hidden" name="action" value="loginForStaff">
          <input type="submit" value="Login" />
          </form>
        </div>
      </div>
      <div class="login-bottom">
        <!-- <a class="img" href="index.html"> 
          <img src="images\logo80.png" /> </a> -->
      </div>
    </div>
  </body>
</html>
