<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%
  staffVO staffVO = (staffVO) request.getAttribute("staffVO");
//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>    
<%@ page import="com.staff.model.*"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工資料修改 - updateStaff.jsp</title>
<title>Insert title here</title>

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

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - updateStaff.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="emp.do" name="form1">
<table>
	<tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td><%=staffVO.getStaffNumber()%></td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="staffname" size="45" value="<%=staffVO.getStaffName()%>" /></td>
	</tr>
	<tr>
		<td>員工帳號:</td>
		<td><input type="TEXT" name="staffaccount" size="45"	value="<%=staffVO.getStaffAccount()%>" /></td>
	</tr>
	<tr>
		<td>員工密碼:</td>
		<td><input type="TEXT" name="staffpassword" size="45"	value="<%=staffVO.getStaffPassword()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="staffnumber" value="<%=staffVO.getStaffNumber()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>