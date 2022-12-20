<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@
page import="tw.com.tibame.staff.model.*"%>

<%
 StaffVO staffvo = (StaffVO) request.getAttribute("staffVO");
%>

<html>
<head>
<title>員工資料 - listOneStaff.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料 - ListOneStaff.jsp</h3>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
		</tr>
		<tr>
			<td><%=staffvo.getStaffNumber()%></td>
			<td><%=staffvo.getStaffName()%></td>
			<td><%=staffvo.getStaffAccount()%></td>
			<td><%=staffvo.getStaffPassword()%></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/StaffServlet"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="staffNumber" value="${staffVO.staffNumber}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
		</tr>
	</table>

</body>
</html>