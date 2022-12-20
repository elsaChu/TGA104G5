<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%> 
<%@ page import="java.util.*,tw.com.tibame.organizer.model.OrganizerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <title>Register</title>
  <!-- Custom Theme files -->
  <link href="css/OrganizerRegister.css" rel="stylesheet" type="text/css" media="all" />
  <!-- Custom Theme files -->
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="keywords"
    content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!--Google Fonts-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <link rel="icon" href="../../main_frame/images/a0svr-jih0d-001.ico" />
</head>
 
<body>
  <div class="login">
    <h2>廠商註冊</h2>
    
    <div class="login-top">
      <h1>註冊</h1>
      <form  action="<%=request.getContextPath()%>/OrganizerRegisterTwo" name="organizerRegisterForm" method="POST">
<!--       <form  action="/JHWorkspace/src/main/java/com/organizer/controller/OrganizerRegisterTwo.java" name="organizerRegisterForm" method="POST"> -->
        <div class="input-icons">
          <div class="tooltip">
            <iconify-icon class="icon" icon="heroicons:exclamation-circle" width="20" height="20"></iconify-icon>
            <span class="tooltiptext">此名稱為前台顯示的主辦方名稱</span>
         	 </div>
            <span class="noBlank">Please Enter Info</span>
            <input type="text" placeholder="廠商名稱" name="organizerName" required maxlength="20"/>
        	</div>
          <span class="noBlank">Please Enter Info</span>
          <input type="text" placeholder="帳號" name="OAccount" required maxlength="20"/>
          <span class="noBlank">Please Enter Info</span>
          <input type="password" placeholder="請輸入密碼" name="OPassword" required maxlength="20"/>
          <span class="noBlank">Please Enter Info</span>
        <input type="email" placeholder="Email" name="windowEmail" required />
          <span class="noBlank">Please Enter Info</span>
        <input type="text" placeholder="聯絡人姓名" name="windowName" required maxlength="10"/>
        <span class="noBlank">Please Enter Info</span>
        <input type="tel" placeholder="聯絡人電話" name="windowPhone" required maxlength="10" minlength="8"/>
        <div class="forgot">
          <input type="submit" value="註冊帳號" id="submitButton" />
        </div>
      </form>
    </div>
    <div class="login-bottom">
      <a class="img" href="index.html">
        <img src="<%=request.getContextPath()%>/back-organizer-end/images/logo80.png" /> </a>
      <h3>已經擁有帳號? &nbsp;<a href="<%=request.getContextPath()%>/back-organizer-end/register-login/OrganizerLogin1.jsp">登入</h3></a>
    </div>
   </div>
    <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
    <%System.out.println(request.getParameter("organizerName"));%>
    <%System.out.println(request.getAttribute("MyFilter"));%>
</body>
<script>
  $(".noBlank").hide();
//   console.log();
  // $("#submitButton").on("click", function(){
  //   console.log("submitPressed");
  //   //檢查所有欄位填寫
  //   let isNull = false;
  //   $("input").each(function(){
  //     if($(this).val() === ""){
  //       $(this).prev("span").show();
  //       isNull = true;
  //     }
  //   })
    // if(!isNull){
      // $("form").submit();
    // }
  // })
  
 </script>
 <style> .noBlank {
  color: red;
 }
  </style>
</html>