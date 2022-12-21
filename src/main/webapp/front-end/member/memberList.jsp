<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.member.model.*"%>
<% MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");%>
<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>TICK IT 員工後台</title>

    <!-- Bootstrap core CSS -->
    <link href="css/staffBootstrap.css" rel="stylesheet" />

    <!-- Favicon -->
    <link rel="icon" href="images/logo.ico"   />
    <!-- Add custom CSS here -->
    <link href="css/sb-admin.css" rel="stylesheet" />
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" />
    <!-- Page Specific CSS -->
    <link rel="stylesheet" href="css/morris-0.4.3.min.css" />

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
          <a class="navbar-brand" href="index.html">員工姓名</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                ><i class="sign-out"></i><iconify-icon class="sign-out" icon="heroicons:users-20-solid" width="20" height="20"></iconify-icon>員工管理
                <b class="caret"></b
              ></a>
              <ul class="dropdown-menu sign-out">
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:user-plus" width="20" height="20"></iconify-icon>員工帳號新增</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:table-cells-solid" width="20" height="20"></iconify-icon>員工列表</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                ><i class="sign-out"></i><iconify-icon class="sign-out" icon="heroicons:identification-20-solid" width="20" height="20"></iconify-icon>會員管理
                <b class="caret"></b
              ></a>
              <ul class="dropdown-menu sign-out">
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:table-cells-solid" width="20" height="20"></iconify-icon>會員列表</a></li>
                <li><a href="#"><iconify-icon class="sign-out"icon="heroicons:envelope" width="20" height="20"></iconify-icon>發送電子報</a></li>
              </ul>
            </li>
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="sign-out"></i> <iconify-icon class="sign-out" icon="heroicons:calendar-solid" width="20" height="20"></iconify-icon>活動管理
            <b class="caret"></b
          ></a>
          <ul class="dropdown-menu sign-out">
            <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:table-cells-solid" width="20" height="20"></iconify-icon>活動列表</a></li>
            <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:circle-stack" width="20" height="20"></iconify-icon>活動分類</a></li>
            <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:building-office-2" width="20" height="20"></iconify-icon>活動場地</a></li>
            <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:clipboard-document-list" width="20" height="20"></iconify-icon>活動訂單</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"
            ><i class="sign-out"></i><iconify-icon class="sign-out" icon="heroicons:gift-20-solid" width="20" height="20"></iconify-icon>商品管理
            <b class="caret"></b
          ></a>
          <ul class="dropdown-menu sign-out">
            <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:table-cells-solid" width="20" height="20"></iconify-icon>商品列表</a></li>
            <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:clipboard-document-list" width="20" height="20"></iconify-icon>商品訂單</a></li>
          </ul>
        </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                ><i class="sign-out"></i><iconify-icon class="sign-out"icon="heroicons:window-solid" width="20" height="20"></iconify-icon>網站管理
                <b class="caret"></b
              ></a>
              <ul class="dropdown-menu sign-out">
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:radio" width="20" height="20"></iconify-icon>系統公告</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:light-bulb" width="20" height="20"></iconify-icon>常見問題</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:information-circle" width="20" height="20"></iconify-icon>聯絡資訊</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:film" width="20" height="20"></iconify-icon>首頁輪播</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:document-chart-bar" width="20" height="20"></iconify-icon>隱私權政策</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:document-chart-bar" width="20" height="20"></iconify-icon>服務條款</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                ><i class="sign-out"></i><iconify-icon class="sign-out" icon="heroicons:cog-8-tooth-solid" width="20" height="20"></iconify-icon>廠商管理
                <b class="caret"></b
              ></a>
              <ul class="dropdown-menu sign-out">
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:check-circle" width="20" height="20"></iconify-icon>廠商審核</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:check-circle" width="20" height="20"></iconify-icon>註銷申請</a></li>
                <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:identification-20-solid" width="20" height="20"></iconify-icon>廠商資訊</a></li>
              </ul>
            </li>
          </ul>

          <ul class="nav navbar-nav navbar-right navbar-user">
            <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:arrow-right-on-rectangle-20-solid" width="20" height="20"></iconify-icon>登出</a></li>
       
          </div>
        <!-- /.navbar-collapse -->
      </nav>
  
    <!-- /#wrapper -->
    <br/> 
    <h3 style="font-weight: 600;">會員列表</h3>
    <br/> 
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
    <div >
	<form action="MemberServlet" method="POST" >
    <input  
    style="font-size: 14px;width: 10%;  
    border-radius: 6px;margin: 50px 0px -100px 500px;" 
    placeholder="搜尋會員編號" name="number"/>
    <input type="hidden" name="action" value="search">
    <input style="font-size: 14px;width: 10%;  background: #000000;padding: 5px 5px;  border-radius: 6px; margin: 50px 0px -100px 0px;" outline: none;
  border: none; type="submit" value="搜尋" />
     </form>
    </div>
    <div>
    
      <table class="box">
        <tr>
          <td>會員編號</td>
          <td>帳號</td>
          <td>姓名</td>
          <td>生日</td>
          <td>EMAIL</td>
          <td>手機號碼</td>
          <td>是否訂閱TICK</td>
          <td>查詢訂單</td>
        </tr>


      
	<%@ include file="page1.file" %>
       <c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
        <tr>
          <td>${memberVO.number}</td>
          <td>${memberVO.account}</td>
          <td>${memberVO.name}</td>
          <td>${memberVO.birthday}</td>
          <td>${memberVO.email}</td>
          <td>${memberVO.phoneNumber}</td>
          <td class="myTd">${memberVO.subscription}</td>
          <td>查詢</td>
		</tr>
       </c:forEach>		

      </table>      
      <%@ include file="page2.file" %>

  </div>

 

	<script>
// 	  var value = document.getElementById("myTd").innerHTML;
// 	  console.log(value);
// 	  if (value == false) {
// 	    document.getElementById("myTd").innerHTML = '<i class="fa fa-times"></i>';
// 	  } else {
// 	    document.getElementById("myTd").innerHTML = '<i class="fa fa-check"></i>';
// 	  }

  var tds = document.getElementsByClassName("myTd");
  console.log(tds);
  for (var i = 0; i < tds.length; i++) {
    var value = tds[i].innerHTML;
    console.log(value);
    if(value == "true"){
    	tds[i].innerHTML = '<i class="fa fa-check"></i>';
    } else {
    	tds[i].innerHTML = '<i class="fa fa-times"></i>';
    }
   }
	</script>
  
  	
    <!-- JavaScript -->
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>

    <!-- Page Specific Plugins -->
    <script src="js/raphael-min.js"></script>
    <script src="js/morris/morris-0.4.3.min.js"></script>
    <script src="js/morris/chart-data-morris.js"></script>
    <script src="js/tablesorter/jquery.tablesorter.js"></script>
    <script src="js/tablesorter/tables.js"></script>
    <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
  </body>
</html>
