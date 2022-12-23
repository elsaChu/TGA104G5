<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.event.model.*"%>

<% 
OrderService orderSvc = new OrderService();
// Integer no = Integer.valueOf(request.getAttribute("xxx").toString());
List<OrderVO> list = orderSvc.selectByEventNumber(2);
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT - 活動列表</title>

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
	width: 800px;
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

<script>
	function searchEventType() {
	}
	document.getElementById("search").submit();

	function searchByOrderID() {
		document.getElementById("searchByOrderID").submit();
	}
	
	function selectByNumber(){
		document.getElementById("selectByNumber").submit();
	}
</script>

</head>

<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td>
				<h3>廠商活動訂單列表 - listOneOrganizerEvent.jsp</h3>
			</td>
		</tr>
	</table>
	<h3>廠商活動訂單列表</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" id="search"
		ACTION="<%=request.getContextPath()%>/OrderServlet" name="action">
		<b>訂單編號查詢</b>
		<input type="text" name="searchByOrderID" value=""
			style="" placeholder="訂單編號" >
		<input type="submit" 
			name="action" value="searchByOrderID" onclick="searchByOrderID()">
	</FORM>

	<FORM METHOD="post" id="search"
		ACTION="<%=request.getContextPath()%>/OrderServlet" name="action">
		<b>會員編號查詢</b>
		<input type="text" name="searchByNumber" value=""
			style="" placeholder="會員編號">
		<input type="submit"
			name="action" value="searchByNumber" onclick="selectByNumber()">
	</FORM>

	<FORM METHOD="post" id="search"
		ACTION="<%=request.getContextPath()%>/OrderServlet" name="action">
		<b>訂單狀態查詢</b> <SELECT>
			<option disabled selected>請選擇訂單狀態</option>
			<option value="notyet">未開賣</option>
			<option value="onsale">販售中</option>
			<option value="soldout">已售罄</option>
			<option value="ending">已結束</option>
		</SELECT>
		<!-- 			<input type="text" name="searchEventType" value="" style="" placeholder="訂單狀態"> -->
		<!-- 			<input type="submit" name="action" value="search" onclick="searchEventType()"> -->
	</FORM>

	<FORM METHOD="post" id="search"
		ACTION="<%=request.getContextPath()%>/OrderServlet" name="action">
		<b>訂單日期查詢</b> <input type="datetime-local" name="startTime" value=""
			style="" placeholder="訂單日期">
	</FORM>

	<FORM METHOD="post" ACTION="StaffServlet" name="form1">
		<!-- 		<form method="post" -->
		<%-- 			action="<%=request.getContextPath()%>/InsertStaffServlet" --%>
		<!-- 			name="action" value="update"> -->

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
			<%@ include file="page1.file"%>
			<div style="float: right; margin-right: 23%;"></div>

			<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

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
		<%@ include file="page2.file"%>
	</FORM>


</body>
</html>