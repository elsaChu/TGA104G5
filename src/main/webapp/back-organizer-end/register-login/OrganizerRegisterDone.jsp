<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,tw.com.tibame.organizer.model.OrganizerVO"%>
    
<!DOCTYPE html>
<html>

<head>
  <title>Register State</title>
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
    <h2>廠商註冊</h2>
    <div class="login-top">
      <h1><%=  request.getAttribute("isNameTaken") %></h1>
        <div class="input-icons">
          <div class="tooltip">
         	</div>
        </div>
    </div>
    <div class="login-bottom">
      <form action="<%=request.getContextPath()%>/back-organizer-end/register-login/OrganizerRegister.jsp">
        <a class="img" href="index.html"> <img src="<%=request.getContextPath()%>/back-organizer-end/register-login/images/logo80.png" /> </a>
        <h3> &nbsp; <a href="#" id="backRegister"> 返回註冊 </a> </h3>
      </form>
    </div>
  </div>
  <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>

</body>

<script>
  $("#backRegister").click(function(e){
    e.preventDefault();
    $("form").submit();
  });
</script>


</html>