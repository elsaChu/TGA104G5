<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*,tw.com.tibame.organizer.model.OrganizerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <title>Update Insert</title>
  <!-- Custom Theme files -->
  <link href="<%=request.getContextPath()%>/back-organizer-end/css/style_manufacturer.css" rel="stylesheet" type="text/css" media="all" />
  <!-- Custom Theme files -->
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="keywords"
    content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="icon" href="../../main_frame/images/a0svr-jih0d-001.ico" />
</head>

<body>
	<%OrganizerVO selected = (OrganizerVO) request.getAttribute("selected");%>
	<%  System.out.println("UPdateInsert: " + request.getAttribute("selected"));%>
  <div class="login">
    <h2>廠商註冊</h2>
    <div class="login-top">
      <h1>註冊</h1>
      <form  action="<%=request.getContextPath()%>/UpdateInsert" name="organizerRegisterForm" method="POST">
        <div class="input-icons">
          <div class="tooltip">
            <iconify-icon class="icon" icon="heroicons:exclamation-circle" width="20" height="20"></iconify-icon>
            <span class="tooltiptext">此名稱為前台顯示的主辦方名稱</span>
         	 </div>
          		<input type="hidden" placeholder="" name="organizerNumber" value="<%=selected.getOrganizerNumber()%>" />
	        	<label>廠商公司名稱</label>
          		<input type="text" placeholder="廠商名稱" name="organizerName" value="<%=selected.getOrganizerName()%>" />
        	</div>
        	<label>帳號名稱</label>
        <input type="text" placeholder="" name="OAccount" value="<%=selected.getOAccount()%>"/>
        	<label>密碼</label>
        <input type="text" placeholder="請輸入密碼" name="OPassword" value="<%=selected.getOpassword()%>"/>
        	<label>聯絡人姓名</label>
        <input type="text" placeholder="聯絡人姓名" name="windowName" value="<%=selected.getWindowName()%>"/>
        	<label>聯絡人手機</label>
        <input type="text" placeholder="聯絡人電話" name="windowPhone" value="<%=selected.getWindowPhone()%>"/>
        	<label>聯絡信箱</label>      
        <input type="text" placeholder="Email" name="windowEmail" value="<%=selected.getWindowEmail()%>"/>
        
        <div class="forgot">
          <input type="submit" value="確認更新" />
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