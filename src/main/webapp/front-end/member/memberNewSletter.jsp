<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.member.model.*"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<% MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>TICK IT 員工後台</title>

    <!-- Favicon -->
    <link rel="icon" href="images/logo.ico"  />
    
<%--     <link rel="stylesheet" type="text/css" href="${context}/main_frame/css/bootstrap.css" /> --%>
    <!-- Add custom CSS here -->
<!--     <link href="css/sb-admin.css" rel="stylesheet" /> -->
<!--     <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" /> -->
    <!-- Page Specific CSS -->
<!--     <link rel="stylesheet" href="css/morris-0.4.3.min.css" /> -->
    <jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>
        <!-- Bootstrap core CSS -->
    <link href="css/memberNewSletter.css" rel="stylesheet" />
    

  </head>
  <body>
    <!-- /#wrapper -->
    <div class="container">
	    <br>
	    <h3 style="font-weight: 600;">發送電子報</h3>
	    <br>
	    <form action="MemberServlet" method="POST">
	    <div  class="subjectText">
	    	<input type="text" name="subject" placeholder="請輸入欲發送的電子報主旨" minlength="1"maxlength="20" >
	    </div>
	    <div  class="newSletterText">
	    	<textarea type="text"name="newSletter" placeholder="請輸入欲發送的電子報內容" rows="10" cols="20"></textarea>
	    </div>
	    <input type="submit" value="發送" /><br /><br />
		</form>
	</div>
    <!-- JavaScript -->
<!--     <script src="js/jquery-1.10.2.js"></script> -->
<!--     <script src="js/bootstrap.js"></script> -->

    <!-- Page Specific Plugins -->
    <script src="js/raphael-min.js"></script>
    <script src="js/tablesorter/jquery.tablesorter.js"></script>
    <script src="js/tablesorter/tables.js"></script>
<!--     <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script> -->
    <script src="js/morris-0.4.3.min.js"></script>
    <script src="js/morris/chart-data-morris.js"></script>
  </body>
</html>
