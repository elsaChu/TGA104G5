<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,tw.com.tibame.organizer.model.OrganizerVO"%>
    
    <c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>TICK IT 廠商後台</title>

    <!-- Bootstrap core CSS -->
    <link href="${context}/main_frame/css/bootstrap.css" rel="stylesheet" />

    <!-- Favicon -->
    <link rel="icon" href="${context}/main_frame/images/a0svr-jih0d-001.ico" />
    <!-- Add custom CSS here -->
    <link href="${context}/main_frame/css/sb-admin.css" rel="stylesheet" />
    <link rel="stylesheet" href="${context}/main_frame/font-awesome/css/font-awesome.min.css" />
    <!-- Page Specific CSS -->
    <link rel="stylesheet" href="${context}/main_frame/css/morris-0.4.3.min.css" />

  </head>

  <body>
    <div id="wrapper">
      <!-- Sidebar -->
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button
            type="button"
            class="navbar-toggle"
            data-toggle="collapse"
            data-target=".navbar-ex1-collapse"
          >
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
           <%OrganizerVO vo = (OrganizerVO) request.getSession().getAttribute("loginOrganizer");%>
           <%String oName = vo.getOrganizerName();%>
          <a class="navbar-brand" href="index.html"><%=((OrganizerVO) request.getSession().getAttribute("loginOrganizer")).getOAccount()%> 您好</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                ><i class="sign-out"></i> <iconify-icon class="sign-out" icon="heroicons:calendar-solid" width="20" height="20"></iconify-icon>活動管理
                <b class="caret"></b
              ></a>
              <ul class="dropdown-menu sign-out">
                <li><a href="${context}/back-organizer-end/event/addEvent1.jsp"><iconify-icon class="sign-out" icon="heroicons:plus-circle" width="20" height="20"></iconify-icon>活動上架</a></li>
                <li><a href="${context}/front-end/event/ListAllEvent.jsp"><iconify-icon class="sign-out" icon="heroicons:table-cells-solid" width="20" height="20"></iconify-icon>活動列表</a></li>
                <li><a href="${context}/front-end/event/listOneOrganizerEvent.jsp"><iconify-icon class="sign-out" icon="heroicons:clipboard-document-list" width="20" height="20"></iconify-icon>活動訂單</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                ><i class="sign-out"></i><iconify-icon class="sign-out" icon="heroicons:gift-20-solid" width="20" height="20"></iconify-icon>商品管理
                <b class="caret"></b
              ></a>
              <ul class="dropdown-menu sign-out">
                <li><a href="${context}/back-organizer-end/product/addProduct.jsp"><iconify-icon class="sign-out" icon="heroicons:plus-circle" width="20" height="20"></iconify-icon>商品上架</a></li>
                <li><a href="${context}/back-organizer-end/product/selectProduct.jsp"><iconify-icon class="sign-out" icon="heroicons:clipboard-document-list" width="20" height="20"></iconify-icon>商品查詢</a></li>
                <li><a href="${context}/back-organizer-end/product/listAllProduct.jsp"><iconify-icon class="sign-out" icon="heroicons:table-cells-solid" width="20" height="20"></iconify-icon>商品列表</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:clipboard-document-list" width="20" height="20"></iconify-icon>商品訂單</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:exclamation-triangle" width="20" height="20"></iconify-icon>退貨申請</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                ><i class="sign-out"></i><iconify-icon class="sign-out" icon="heroicons:user-circle-solid" width="20" height="20"></iconify-icon>基本資料
                <b class="caret"></b
              ></a>
              <ul class="dropdown-menu sign-out">
                <li><a href="${context}/back-staff-end/organizer/organizerMaterial.jsp"><iconify-icon class="sign-out" icon="heroicons:identification" width="20" height="20"></iconify-icon>基本資料設定</a></li>
                <li><a href="${context}/back-staff-end/organizer/organizerBank.jsp"><iconify-icon class="sign-out" icon="heroicons:currency-dollar" width="20" height="20"></iconify-icon>銀行帳戶設定</a></li>
              </ul>
            </li>
          </ul>

          <ul class="nav navbar-nav navbar-right navbar-user">
            <li><a href="${context}/OrganizerLogOut"><iconify-icon class="sign-out" icon="heroicons:arrow-right-on-rectangle-20-solid" width="20" height="20"></iconify-icon>登出</a></li>
        
          </div>
        <!-- /.navbar-collapse -->
      </nav>
      
  
    <!-- /#wrapper -->
    

    <!-- JavaScript -->
    <script src="${context}/main_frame/js/jquery-1.10.2.js"></script>
    <script src="${context}/main_frame/js/bootstrap.js"></script>

    <!-- Page Specific Plugins -->
    <script src="${context}/main_frame/js/raphael-min.js"></script>
<!--     <script src="js/morris-0.4.3.min.js"></script> -->
<!--     <script src="js/morris/chart-data-morris.js"></script> -->
<%--     <script src="${context}/main_frame/js/tablesorter/jquery.tablesorter.js"></script> --%>
<%--     <script src="${context}/main_frame/js/tablesorter/tables.js"></script> --%>
    <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
  </body>
</html>
