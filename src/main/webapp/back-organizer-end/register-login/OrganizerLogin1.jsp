<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,tw.com.tibame.organizer.model.OrganizerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <title>Login</title>
  <!-- Custom Theme files -->
  <link href="<%=request.getContextPath()%>/back-organizer-end/register-login/css/style_manufacturer.css" rel="stylesheet" type="text/css" media="all" />
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
    <h2>廠商登入</h2>
    
    <div class="login-top">
      <h1>登入</h1>
      <form  action="<%=request.getContextPath()%>/OrganizerLogin" name="organizerRegisterForm" method="POST">
<!--       <form  action="/JHWorkspace/src/main/java/com/organizer/controller/OrganizerRegisterTwo.java" name="organizerRegisterForm" method="POST"> -->
        <div class="input-icons">
	          <span class="noBlank">Please Enter Info</span>
	          <input type="text" placeholder="帳號" name="OAccount" required maxlength="20"/>
	          <span class="noBlank">Please Enter Info</span>
	          <input type="password" placeholder="請輸入密碼" name="OPassword" required maxlength="20"/>
	          <span class="noBlank">Please Enter Info</span>
	   	</div>
        <div class="forgot">
          <input type="submit" value="登入" id="submitButton" />
        </div>
      </form>
    </div>
    <div class="login-bottom">
      <a class="img" href="index.html">
      <img src="<%=request.getContextPath()%>/back-organizer-end/images/logo80.png" /></a>
      <h3>還沒有帳號? &nbsp;<a href="<%=request.getContextPath()%>/back-organizer-end/register-login/OrganizerRegister.jsp">註冊</a></h3>
    </div>
   </div>
    <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
</body>
<script>
  $(".noBlank").hide();
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