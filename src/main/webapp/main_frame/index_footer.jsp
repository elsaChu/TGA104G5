<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--     <link   rel="stylesheet" type="text/css" href="${context}/main_frame/css/bootstrap.min.css" /> --%>
    <link   rel="stylesheet" type="text/css" href="${context}/main_frame/css/index_footer.css" />
     <link   rel="stylesheet" type="text/css" href="${context}/main_frame/css/bootstrap.min.css" />
</head>
<body>
    <nav class="navbar relative-bottom navbar-expand-lg navbar-light bg-while">
        <div class="container">
                <button class="navbar-toggler mx-auto" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                  <ul class="navbar-nav mx-auto">
                    <li class="nav-item">
                      <a class="nav-link" href="#">常見問題 <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">聯絡我們</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">系統公告</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">網站地圖</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">隱私權政策</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">服務條款</a>
                    </li>
                  </ul>
                </div>
        </div>
    </nav>
<%--   <script src="${context}/main_frame/js/jquery/jquery-2.2.4.min.js"></script> --%>
<%--   <script src="${context}/main_frame/js/bootstrap.min.js"></script> --%>
</body>
</html>