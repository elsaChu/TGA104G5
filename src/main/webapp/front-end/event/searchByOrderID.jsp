<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.event.model.*"%>

<%
 OrderVO ordervo = (OrderVO) request.getAttribute("orderVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>從訂單編號篩選 - searchByOrderID.jsp</title>
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

	<table id="table-1">
		<tr>
			<td>
				<h3>從訂單編號篩選 - searchByOrderID.jsp</h3>
			</td>
		</tr>
	</table>
<%-- 	<%@ include file="page1.file"%> --%>
	<table>
		<tr>
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>訂單日期</th>
			<th>訂單狀態</th>
			<th>總金額</th>
			<th>總票數</th>
			<th>聯絡人資訊</th>
			<th>取消原因</th>
		</tr>

		<c:forEach var="orderVO" items="${list}">
			<tr>
				<td>${orderVO.orderID}</td>
				<td>${orderVO.number}</td>
				<td>${orderVO.orderDate}</td>
				<td>${orderVO.orderType}</td>
				<td>${orderVO.total}</td>
				<td>${orderVO.totalTicket}</td>
				<td>${orderVO.pData}</td>
				<td>${orderVO.reason}</td>
			</tr>
		</c:forEach>
	</table>



</body>
</html>