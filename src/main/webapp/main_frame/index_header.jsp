<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page import="tw.com.tibame.member.model.*"%>
<% MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TICK IT</title>
    <link   rel="stylesheet" type="text/css" href="${context}/main_frame/css/bootstrap.min.css" />
    <link   rel="stylesheet" type="text/css" href="${context}/main_frame/css/index_header.css" />
    <link rel="icon" href="${context}/main_frame/images/logo.ico" />
</head>
<body>
  <div class="container header_height">
    <nav class="navbar navbar-expand-lg navbar-light bg-while">
      <a class="navbar-brand" href="#">
        <img src="${context}/main_frame/images/logo80.png" />
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
<!--           <li class="nav-item"> -->
<!--             <a class="nav-link" href="#">我的票劵 <span class="sr-only">(current)</span></a> -->
<!--           </li> -->
          <li class="nav-item">
            <a class="nav-link" href="#">禮品專區</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">探索活動</a>
          </li>
          <form class="form-inline my-2 my-lg-0">
            <div class="input-group">
              <div class="input-group-prepend">
                <iconify-icon class="input-group-text" icon="material-symbols:search-rounded" style="color: #f8f8f8;" width="24" height="24"></iconify-icon>
              </div>
              <input class="form-control mr-sm-2 search_bg" type="search" placeholder="搜索活動" aria-label="Search">
            </div>
            <!-- <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
          </form>
        </ul>
        <!-- unlogin -->
        <div id="login_menuB"  class="hidden_login">
          <ul class="navbar-nav mr-right">
            <li class="nav-item">
              <a class="nav-link" href="${context}/front-end/member/memberRegister.jsp">註冊</a>
            </li>
            <li class="nav-item">
              <a class="nav-link disabled" href="#">|</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${context}/front-end/member/memberLogin.jsp">登入</a>
            </li>
          </ul>
        </div>
        <!-- login -->
        <div id="login_menuA"  class="hidden_login">
          <ul class="navbar-nav mr-right">
            <li class="nav-item dropdown">
              <a class="nav-link" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <iconify-icon icon="heroicons:user-circle-solid" width="30" height="30"></iconify-icon>
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">
                  <iconify-icon class="sign-out" icon="heroicons:cog-8-tooth" width="20" height="20"></iconify-icon>
                  設定
                </a>
                <!-- <div class="dropdown-divider"></div> -->
                <a class="dropdown-item" href="#">
                  <iconify-icon class="sign-out" icon="heroicons:ticket" width="20" height="20"></iconify-icon>
                  票券訂單
                </a>
                <!-- <div class="dropdown-divider"></div> -->
                <a class="dropdown-item" href="#">
                  <iconify-icon class="sign-out" icon="heroicons:gift" width="20" height="20"></iconify-icon>
                    禮品訂單
                </a>
                <!-- <div class="dropdown-divider"></div> -->
                <a class="dropdown-item" href="#">
                  <form action="${context}/front-end/member/MemberServlet" method="POST" >
                  <iconify-icon class="sign-out" icon="heroicons:arrow-right-on-rectangle-20-solid" width="20" height="20" style="margin: 0px -7px 0px 2px;"></iconify-icon>
                    <input class="signOut_btn" type="submit" value="登出">
                    <input type="hidden" name="action" value="logout">
                  </form>
                </a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">
                <iconify-icon icon="heroicons:bell-20-solid" width="30" height="30" ></iconify-icon>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">
                <iconify-icon icon="heroicons:heart-20-solid" width="30" height="30" ></iconify-icon>
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">
                <iconify-icon icon="heroicons:shopping-cart-20-solid" width="30" height="30"></iconify-icon>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
  <script>
    var context_header = '${context}';
//   	var membervo = ${memberVO};
  </script>
  <script src="${context}/main_frame/js/jquery/jquery-2.2.4.min.js"></script>
  <script src="${context}/main_frame/js/bootstrap.min.js"></script>
  <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
  <script src="${context}/main_frame/js/index_header.js"></script>
</body>
</html>