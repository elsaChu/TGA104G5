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
					<a
						href="${context}/back-organizer-end/product/selectProduct.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th width="400"><div align="center">商品編號</div></th>
			<th width="400"><div align="center">活動編號</div></th>
			<th width="400"><div align="center">廠商編號</div></th>
			<th width="400"><div align="center">商品名稱</div></th>
			<th width="400"><div align="center">商品規格</div></th>
			<th width="400"><div align="center">商品單價</div></th>
			<th width="400"><div align="center">庫存數量</div></th>
			<th width="400"><div align="center">商品詳情</div></th>
			<th width="400"><div align="center">商品總評價</div></th>
			<th width="400"><div align="center">商品是否上架</div></th>
		</tr>
		<tr>
			<td><%=prodVo.getProdNo()%></td>
			<td><%=prodVo.getEventNumber()%></td>
			<td><%=prodVo.getOrganizerNumber()%></td>
			<td><%=prodVo.getProdName()%></td>
			<td><%=prodVo.getProdSpec()%></td>
			<td><%=prodVo.getUnitPrice()%></td>
			<td><%=prodVo.getProdStock()%></td>
			<td><%=prodVo.getProdDetails()%></td>
			<td><%=prodVo.getProdScore()%></td>
			<td><%=prodVo.getIsPOn()%></td>
		</tr>
	</table>
</body>
</html>