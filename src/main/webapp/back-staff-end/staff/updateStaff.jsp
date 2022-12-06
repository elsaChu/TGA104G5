<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@
page import="com.staff.model.*"
%>
<%
staffVO staffVO = (staffVO) request.getAttribute("staffVO");
%>

<%
    StaffService staffSvc = new StaffService();
    List<staffVO> list = staffSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta charset="UTF-8">
<title>Update2 title here</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>



</head>

<body bgcolor='white'>

<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>
<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - updateStaff2.jsp</h3>
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="===========" name="form1">
<table>
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><%=staffVO.getStaffNumber()%></td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="staffName" size="45" value="<%=staffVO.getStaffName()%>" /></td>
	</tr>
	<tr>
		<td>員工帳號:</td>
		<td><input type="TEXT" name="staffAccount" size="45"	value="<%=staffVO.getStaffAccount()%>" /></td>
	</tr>
	<tr>
		<td>員工密碼:</td>
		<td><input name="staffPassword" size="45"	value="<%=staffVO.getStaffPassword()%>" /></td>
	</tr>
	

	<jsp:useBean id="deptSvc" scope="page" class="com.staff.model.StaffService" />
	<tr>
		
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empno" value="<%=staffVO.getStaffNumber()%>">
<input type="submit" value="送出修改"></FORM>

	
</body>
</html>