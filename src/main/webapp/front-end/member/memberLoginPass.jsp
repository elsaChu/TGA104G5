<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.member.model.*"%>
<% MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>TICK IT</title>
    <!-- Custom Theme files -->
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
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
      <h2>登入成功 !</h2>
      <a style=" color:  #415A77; text-align: center;  font-weight: 700;  margin: 0px 0px -500px 0px;" href="memberCentre.jsp">會員中心</a>
    </div>
  </body>
</html>