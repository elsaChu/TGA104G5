<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
 <%@page import="tw.com.tibame.staff.model.*"%>
<%@ page import="tw.com.tibame.member.model.*"%>

<% StaffVO staffVO = (StaffVO) request.getAttribute("staffVO");%>
<% MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");%>
<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
	<title>TICK IT 員工後台</title>

 <link href="${context}/front-end/member/css/staffBootstrap.css" rel="stylesheet" />
    
  </head>
 
  <body>
     <jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>
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
 </body>
</html>
