<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*, com.organizer.model.OrganizerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <title>TICK IT</title>
  <!-- Custom Theme files -->
  <link href="css/style_manufacturer.css" rel="stylesheet" type="text/css" media="all" />
  <!-- Custom Theme files -->
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="keywords"
    content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!--Google Fonts-->

  <link rel="icon" href="../../main_frame/images/a0svr-jih0d-001.ico" />
</head>

<body>
  <div class="login">
    <h2>廠商註冊</h2>
    
    <div class="login-top">
      <h1>註冊</h1>
      <form  action="<%=request.getContextPath()%>/OrganizerRegisterTwo" name="organizerRegisterForm" method="POST">
        <div class="input-icons">
          <div class="tooltip">
            <iconify-icon class="icon" icon="heroicons:exclamation-circle" width="20" height="20"></iconify-icon>
            <span class="tooltiptext">此名稱為前台顯示的主辦方名稱</span>
          </div>
          <input type="text" placeholder="廠商名稱" name="organizerName" />
        </div>
        <input type="text" placeholder="帳號" name="OAccount"/>
        <input type="password" placeholder="請輸入密碼" name="OPassword"/>
        <input type="password" placeholder="請確認密碼" />
        <input type="text" placeholder="Email" name="windowEmail" />
        <input type="text" placeholder="聯絡人姓名" name="windowName"/>
        <input type="text" placeholder="聯絡人電話" name="windowPhone"/>
        <div class="forgot">
          <input type="submit" value="註冊帳號" />
        </div>
      </form>
    </div>
    <div class="login-bottom">
      <a class="img" href="index.html">
        <img src="../../main_frame/images/logo80.png" /> </a>
      <h3>我已經有帳號了&nbsp;<a href="login_manufacturer.html">登入</h3></a>
    </div>
   </div>
   
   
  
    <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>

</body>

</html>