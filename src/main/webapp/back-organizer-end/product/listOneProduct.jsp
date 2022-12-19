<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>


<%
ProductVO prodVo = (ProductVO) request.getAttribute("ProductVO");
%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>

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
<body>
	<table id="table-1">
		<tr>
			<td>
				<h2>查詢商品</h2>
				<h4>
					<a href="${context}/back-organizer-end/product/selectProduct.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table id="table-2">
		<tr>
			<th>商品編號</th>
			<th>活動編號</th>
			<th>廠商編號</th>
			<th>商品名稱</th>
			<th>商品規格</th>
			<th>商品單價</th>
			<th>庫存數量</th>
			<th>商品詳情</th>
			<th>商品總評價</th>
			<th>商品是否上架</th>
			<th>修改</th>
		</tr>
		<tr>
			<td class=group1><%=prodVo.getProdNo()%></td>
			<td class=group1><%=prodVo.getEventNumber()%></td>
			<td class=group1><%=prodVo.getOrganizerNumber()%></td>
			<td class=prodName><%=prodVo.getProdName()%></td>
			<td class=group1><%=prodVo.getProdSpec()%></td>
			<td class=group1><%=prodVo.getUnitPrice()%></td>
			<td class=group1><%=prodVo.getProdStock()%></td>
			<td class=prodDetails><%=prodVo.getProdDetails()%></td>
			<td class=group1><%=prodVo.getProdScore()%></td>
			<td class=group1><%=prodVo.getIsPOn()%></td>
			<!-- 			<td> -->
			<%-- 				<form method="post" action="${context}/ProductServlet"> --%>
			<%-- 					<input type="hidden" name="prodNo" value="${productVO.prodNo}"> --%>
			<!-- 					<input type="hidden" name="action" value="getOne_For_Update"> -->
			<!-- 					<input type="submit" value="修改"> -->
			<!-- 				</form> -->
			<!-- 			</td> -->
		</tr>
	</table>
</body>
</html>